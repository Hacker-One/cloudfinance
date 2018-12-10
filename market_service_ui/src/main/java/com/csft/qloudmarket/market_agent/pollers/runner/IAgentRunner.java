package com.csft.qloudmarket.market_agent.pollers.runner;

import com.csft.qloudmarket.market_agent.pollers.listener.OrderListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/9/26
 * Time: 10:09
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public interface IAgentRunner extends Runnable {

//    private static Logger logger = LoggerFactory.getLogger(TestRunner.class);
//    public String  workerStatus="D";
    public  String getWorkerStatus();

    public  void setWorkerStatus(String status);



    public  String getName();

    public  void setName(String name);


    public  Thread getThread();

    public  void setThread(Thread thread);
//    @Override
//    public void run() {
//        try {
//            while(true) {
//                logger.info("thread is running");
//                Thread.sleep(1000);
//               throw  new Exception();
//            }
//        }catch (Exception e){
//            logger.info("thread exception");
//            workerStatus="S";
//        }finally {
//            workerStatus="S";
//        }
//    }
}
