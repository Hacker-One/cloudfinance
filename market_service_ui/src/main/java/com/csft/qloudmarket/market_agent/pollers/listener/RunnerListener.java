package com.csft.qloudmarket.market_agent.pollers.listener;

import com.csft.qloudmarket.market_agent.pollers.runner.IAgentRunner;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/9/26
 * Time: 10:13
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public class RunnerListener extends Thread {
    private static RunnerListener instance;
    @Setter
    @Getter
    private List<IAgentRunner> runner;

    private RunnerListener(){
        runner=new ArrayList<>();
    }

    public static RunnerListener getInstance(){
        if(instance==null){
            instance=new RunnerListener();
        }
        return instance;
    }
    @Override
    public void run() {
        try {
        while (true) {
            for (IAgentRunner r : runner) {
                synchronized (r) {
                  if(("S".equals(r.getWorkerStatus()))||("D".equals(r.getWorkerStatus()))){
                      (new Thread(r)).start();
                  }
                }
            }
            System.out.println("thread will sleep 5 seconds");
                sleep(5000);

        }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
