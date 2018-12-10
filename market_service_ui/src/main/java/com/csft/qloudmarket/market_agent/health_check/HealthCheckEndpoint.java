package com.csft.qloudmarket.market_agent.health_check;

import com.csft.qloudmarket.market_agent.pollers.listener.OrderQueueComsumer;
import com.csft.qloudmarket.market_agent.pollers.listener.RunnerListener;
import com.csft.qloudmarket.market_agent.pollers.runner.DeployRunner;
import com.csft.qloudmarket.market_agent.pollers.runner.OrderQuestRunner;
import com.csft.qloudmarket.market_agent.pollers.runner.SynchronizeDataRunner;
import com.csft.qloudmarket.market_agent.util.Common;
import com.qloudfin.qloudbus.annotation.RequestMapping;
import com.qloudfin.qloudbus.annotation.RequestMethod;
import com.qloudfin.qloudbus.reactive.Callback;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/9/13
 * Time: 17:10
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@RequestMapping("/")
public class HealthCheckEndpoint {
    private static Logger logger= LoggerFactory.getLogger(HealthCheckEndpoint.class);
//    static {
//        logger.info("start comsumer");
////        OrderQueueComsumer orderQueueComsumer=new OrderQueueComsumer();
////        orderQueueComsumer.start();
//        RunnerListener listener=RunnerListener.getInstance();
//        listener.getRunner().add(new DeployRunner());
//        listener.getRunner().add(new OrderQuestRunner());
//        listener.getRunner().add(new SynchronizeDataRunner());
//        listener.start();
//    }
    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public void addArtifact(final Callback<Map> callback ) {
        Map res=new HashMap();
        res.put("version", Common.getPropertiesKey(Common.MA_DEPLOYER_VERSION));
        res.put("name","market_agent");
        callback.accept(res);
    }
}
