package com.csft.qloudmarket.market_agent.pollers.event;

import com.csft.qloudmarket.market_agent.pollers.listener.RunnerListener;
import com.csft.qloudmarket.market_agent.pollers.runner.DeployRunner;
import com.csft.qloudmarket.market_agent.pollers.runner.IAgentRunner;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/10/23
 * Time: 11:53
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public class EventListenerThread  extends Thread {
    private static Logger logger = LoggerFactory.getLogger(EventListenerThread.class);
    private static EventListenerThread instance;
    private static  String STATUS="STOP";
    @Setter
    @Getter
    private List<IAgentRunner> runner;

    private EventListenerThread(){
        runner=new ArrayList<>();
//        if("STOP".equals(STATUS)){
//            logger.info("start!!!!!!!!!!!!!!!!!!!!\n\n\n\n\n");
//            //this.start();
//        }
    }

    public static EventListenerThread getInstance(){
        if(instance==null){
            instance=new EventListenerThread();
        }
        return instance;
    }
    public void startP(){
        if(!this.isAlive()){
            logger.info("restart ....................\n\n\n\n");
            start();
        }
    }
    @Override
    public void run() {
        try {
            synchronized (STATUS) {
                STATUS="RUNNING";
                while (true) {
                    logger.info("size:"+runner.size());
                    for (IAgentRunner r : runner) {
                        synchronized (r) {
                            logger.info("\n\n\n {}",r.getWorkerStatus());
                            if(("D".equals(r.getWorkerStatus()))){
                                r.setThread(new Thread(r));
                                r.getThread().start();
                            }
                        }
                    }
                    logger.info("thread will sleep 5 seconds");
                    sleep(5000);
                }
            }
        } catch (InterruptedException e) {
            logger.info("exception {}",e.getStackTrace());
            e.printStackTrace();
        }
    }

}
