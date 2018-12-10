package com.csft.qloudmarket.market_agent.pollers.runner;

import com.csft.qloudmarket.market_agent.chartmuseum.ChartMuseumService;
import com.csft.qloudmarket.market_agent.deploy.Deployer;
import com.csft.qloudmarket.market_agent.license.server.LicenseService;
import com.csft.qloudmarket.market_agent.pollers.service.OrderpollerService;
import com.csft.qloudmarket.market_agent.pollers.service.SychronizServer;
import com.csft.qloudmarket.market_agent.registry.RegistryAccessService;
import com.csft.qloudmarket.market_agent.util.JacksonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/9/26
 * Time: 11:38
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public class SynchronizeDataRunner implements IAgentRunner {
    private static Logger logger = LoggerFactory.getLogger(SynchronizeDataRunner.class);

    public static String name = "DATA_SYNCHRONIZER";


    private String workerStatus = "D";


    private SychronizServer sychronizServer;
    public SynchronizeDataRunner() {
        sychronizServer=new SychronizServer();
    }


    @Override
    public String getWorkerStatus() {
        return workerStatus;
    }

    @Override
    public void setWorkerStatus(String status) {
        workerStatus = status;
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

    @Override
    public void run() {
//        try {
//            logger.info("{} begin ",name);
//            workerStatus="R";
//            while (true) {
//                if (OrderpollerService.orderQueue.size() > 0) {
//                    //Map operData = null;
//                    Map order = (Map) ((Map) OrderpollerService.orderQueue.take().get("process")).get("order");
//                    logger.info("{}", order);
//
//                    if (order.get("data") != null) {
//                        String orderData= (String) order.get("data");
//                        logger.info("sychronized data {}",orderData);
//                        sychronizServer.purchaseNotify(JacksonUtils.json2map(orderData));
//                    }
//                }
//                logger.info("sleep 10 seconds:");
//                Thread.sleep(10000);
//            }
//        } catch (Exception e) {
//            logger.info("{} thread exception \n info ", name, e);
//            setWorkerStatus("S");
//        } finally {
//            setWorkerStatus("S");
//        }
    }
}
