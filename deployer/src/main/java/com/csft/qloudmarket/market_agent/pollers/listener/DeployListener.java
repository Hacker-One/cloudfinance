package com.csft.qloudmarket.market_agent.pollers.listener;

import com.csft.qloudmarket.market_agent.pollers.queue.DeployQueue;
import com.csft.qloudmarket.market_agent.rancher.service.RancherService;
import com.csft.qloudmarket.market_agent.util.JacksonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/9/21
 * Time: 10:39
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public class DeployListener  extends Thread  {
    private static Logger logger = LoggerFactory.getLogger(OrderListener.class);
    RancherService rancherService=null;
    public static final int RETRYCOUNT=9999;
    private  static    String  workerStatus="D";
    public static String getWorkerStatus(){
        return workerStatus;
    }

    public static void setWorkerStatus(String status){
        workerStatus=status;
    }
    public DeployListener(){
        rancherService=new RancherService();
    }

    public void run(){
        logger.info("Deploy   thread begin ");
        workerStatus="R";
        try {
            while (true){
                logger.info("Ranchar deploy service is running \n");
                logger.info("DeployQueue.deployQueue.size:{}",DeployQueue.deployQueue.size());

               if( DeployQueue.deployQueue.size()>0){
                 Map deploydata=  DeployQueue.deployQueue.take();
                 logger.info("request Data:{}",deploydata);
                 deploydata.put("retry",0);
                 //  StringBuffer infobf=new StringBuffer((String)deploydata.get("msg"));
                 StringBuffer infobf=new StringBuffer("");

                 Map info=null;
                 for(int i=0;i<10&&info==null;i++) {
                     rancherService.refreshCatalog();//先刷新一下仓库 否则查不到
                     info = rancherService.searchApp((String) deploydata.get("catalog"), (String) deploydata.get("appName"), (String) deploydata.get("version"));
                     if(info==null){
                         Thread.sleep(10*1000);
                     }
                 }
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
               }
                sleep(10*1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.info("poller program has exception");
            workerStatus="S";
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("EXCEPTION,{}",e.getMessage());
            this.start();
        } finally {
            workerStatus="S";
        }

    }

//    @Override
//    public void update(Observable o, Object arg) {
//
//    }
}
