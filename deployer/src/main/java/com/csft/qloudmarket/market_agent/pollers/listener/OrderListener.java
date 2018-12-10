package com.csft.qloudmarket.market_agent.pollers.listener;

import com.csft.qloudmarket.market_agent.pollers.service.OrderpollerService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/9/18
 * Time: 16:16
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class OrderListener extends Thread {
    private static Logger logger = LoggerFactory.getLogger(OrderListener.class);
    private  static    String  workerStatus="D";
    public static String getWorkerStatus(){
        return workerStatus;
    }

    public static void setWorkerStatus(String status){
        workerStatus=status;
    }

    OrderpollerService orderpollerService=null;
    public OrderListener(){
        orderpollerService=new OrderpollerService();
    }


    public void run(){
        logger.info("thread begin ");
        workerStatus="R";
        try {
            while (true){
                 orderpollerService.requestOrder();
                 sleep(30*1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.info("poller program has exception");
            workerStatus="S";
        }finally {
            workerStatus="S";
        }

    }
}
