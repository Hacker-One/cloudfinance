package com.csft.qloudmarket.market_agent;

import com.csft.qloudmarket.market_agent.pollers.listener.RunnerListener;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;
//import com.csft.qloudmarket.market_agent.pollers.runner.BussiRunner;
//import com.csft.qloudmarket.market_agent.pollers.runner.DeployRunner;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/8/8
 * Time: 15:13
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    public static void main(String [] args){
//        RunnerListener listener=RunnerListener.getInstance();
//        listener.start();

        FormattingTuple ft=  MessageFormatter.arrayFormat("一个测试 哈哈哈哈{}@{}", new String[]{"dsadsa","dsadsa123"});
        System.out.println(ft.getMessage());
    }

}
