package com.csft.qloudmarket.market_agent.pollers.runner;

import com.csft.qloudmarket.market_agent.pollers.queue.DeployQueue;
import com.csft.qloudmarket.market_agent.rancher.service.RancherService;
import com.csft.qloudmarket.market_agent.util.JacksonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.Observable;

import static java.lang.Thread.sleep;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/9/25
 * Time: 18:44
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public class DeployRunner implements IAgentRunner {
    private static Logger logger = LoggerFactory.getLogger(DeployRunner.class);
    private String name="deploy Runner";
    RancherService rancherService=null;
    public static final int RETRYCOUNT=9999;
    private  String  workerStatus="D";
    public  String getWorkerStatus(){
        return workerStatus;
    }

    public  void setWorkerStatus(String status){
        workerStatus=status;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public Thread getThread() {
        return null;
    }

    @Override
    public void setThread(Thread thread) {

    }

    public DeployRunner(){
        rancherService=new RancherService();
    }
    @Override
    public void run() {
        logger.info("{} begin ",name);
        workerStatus="R";
        try {
            doService();
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.info("poller program has exception");
            workerStatus="S";
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("EXCEPTION,{}",e.getMessage());
            workerStatus="S";
        } finally {
            workerStatus="S";
        }
    }

    public void doService() throws Exception {
            logger.info("Ranchar deploy service is running \n");
            logger.info("DeployQueue.deployQueue.size:{}", DeployQueue.deployQueue.size());

            if( DeployQueue.deployQueue.size()>0){
                Map deploydata=  DeployQueue.deployQueue.take();
                logger.info("request Data:{}",deploydata);
                deploydata.put("retry",0);
                //  StringBuffer infobf=new StringBuffer((String)deploydata.get("msg"));
                StringBuffer infobf=new StringBuffer("");

                rancherService.refreshCatalog();//先刷新一下仓库 否则查不到

                Map info=  rancherService.searchApp((String)deploydata.get("catalog"),(String)deploydata.get("appName"),(String)deploydata.get("version"));


                if(info!=null) {
                    Map audeployResult = rancherService.autoDeploy((String) deploydata.get("nameSpace"), (String) deploydata.get("serverName"), (String) deploydata.get("appName"), (String) deploydata.get("projectName"), (String) deploydata.get("version"), (String) deploydata.get("catalog"));  //自动部署

                    infobf.append(JacksonUtils.mapToJson(audeployResult)).append("\n");

                    logger.info("deploy Result:{}", audeployResult);

                }else { //重试3次 如果超过3次直接扔掉

                    int retry= (int) deploydata.get("retry");
                    logger.info("@@@ Could not found! {}",retry);
                    if(retry<RETRYCOUNT){
                        deploydata.put("retry",retry++);
                        DeployQueue.deployQueue.put(deploydata);
                    }else{
                        //..........
                        infobf.append("deployError  3 times retry \n");
                    }

                }
            }else {
                logger.info(" no data in queue");
            }

    }
}
