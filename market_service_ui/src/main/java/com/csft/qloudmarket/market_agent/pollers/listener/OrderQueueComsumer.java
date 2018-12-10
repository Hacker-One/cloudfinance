package com.csft.qloudmarket.market_agent.pollers.listener;

import com.csft.qloudmarket.market_agent.deploy.Deployer;
import com.csft.qloudmarket.market_agent.pollers.service.OrderpollerService;
import com.csft.qloudmarket.market_agent.util.JacksonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/9/19
 * Time: 20:04
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public class OrderQueueComsumer extends Thread {
    // private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    private static Logger logger = LoggerFactory.getLogger(OrderQueueComsumer.class);

    private static final int MAX = 10;
    private int threadCount = 0;

    //   public static void execute() throws InterruptedException {
//       Thread.currentThread().sleep(5*1000);
//       while(true){
//           logger.info("cachedThreadPool@#@!#@!#@!");
//           Map operData=null;
//           synchronized (OrderpollerService.orderQueue){
//               operData=OrderpollerService.orderQueue.take();
//           }
//           //logger.info("cachedThreadPool@#@!#@!#@!");
//           if(operData!=null)
//               cachedThreadPool.execute(new Deployer(operData));
//
//           Thread.currentThread().sleep(5*1000);
//       }
//   }
    public void run() {

      //  ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
//        while (true) {
//            try {
//                //logger.info("000000000000000000000000000000: {}", OrderpollerService.orderQueue.size());
//                if( OrderpollerService.orderQueue.size()>0){
//                    //Map operData = null;
//                    Map order=(Map)((Map)OrderpollerService.orderQueue.take().get("process")).get("order");
//                    logger.info("{}",order);
//                    if(order.get("data")!=null){
//                        (new Deployer(JacksonUtils.json2map((String)order.get("data")))).run();
//                    }
//                }
//                logger.info("sleep 10 seconds:");
//                sleep(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            Map operData = null;
//            //synchronized (OrderpollerService.orderQueue) {
//                try {
//                    if (threadCount < MAX) {
//                        Map order=null;//(Map)((Map)OrderpollerService.orderQueue.take().get("process")).get("order");
//                        if(order.get("data")!=null){
//                            operData=order;
//                        }
//                        if (operData != null) {
//                            threadCount++;
//                            //cachedThreadPool.execute(new Deployer(operData));
//                            (new Deployer(operData)).run();
//                        }
//                        logger.info("sleep 10 seconds:");
//                        sleep(10000);
//                    }
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//         //   }
//
////            try {
////
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }

   //     }
    }
}
