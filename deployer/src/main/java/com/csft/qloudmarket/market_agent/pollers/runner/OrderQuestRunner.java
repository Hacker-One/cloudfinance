package com.csft.qloudmarket.market_agent.pollers.runner;

import com.csft.qloudmarket.market_agent.deploy.Deployer;
import com.csft.qloudmarket.market_agent.pollers.service.OrderpollerService;
import com.csft.qloudmarket.market_agent.util.JacksonUtils;
import io.advantageous.boon.core.Sys;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/9/26
 * Time: 10:22
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public class OrderQuestRunner implements IAgentRunner {
    private static Logger logger = LoggerFactory.getLogger(OrderQuestRunner.class);

    OrderpollerService orderpollerService=null;

     private Thread thread;
    private String workerStatus = "D";
    private String name;

    public OrderQuestRunner(String name) {

            this.name = name;
            orderpollerService = new OrderpollerService();
    }

    @Override
    public void run() {
        try {
            logger.info("{} begin ",name);
            workerStatus="R";
            Map header=orderpollerService.getRequestToken();
            Map r= orderpollerService.purchaseNotify2(name,header);
            orderpollerService.notifyServer(name,header,r);
            // Thread.sleep(30*1000);
            setWorkerStatus("X");
            logger.info("execute OVER {} \n",r);
        } catch (Exception e) {
           logger.info("{} thread exception \n info ",name,e);
            setWorkerStatus("X");
        } finally {
            setWorkerStatus("X");
        }
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
        return name;
    }

    @Override
    public void setName(String name) {
        this.name=name;
    }

    @Override
    public Thread getThread() {
        return this.thread;
    }

    @Override
    public void setThread(Thread thread) {
       this.thread=thread;
    }
}
