package com.csft.qloudmarket.market_agent.pollers.queue;

import org.jgroups.util.ConcurrentLinkedBlockingQueue;

import java.util.Map;
import java.util.concurrent.BlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/9/21
 * Time: 10:37
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public class DeployQueue {
    public static BlockingQueue<Map> deployQueue = new ConcurrentLinkedBlockingQueue(100);
}
