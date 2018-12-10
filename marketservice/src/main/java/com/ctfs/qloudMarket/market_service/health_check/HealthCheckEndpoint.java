package com.ctfs.qloudMarket.market_service.health_check;

import com.qloudfin.qloudbus.annotation.RequestMapping;
import com.qloudfin.qloudbus.annotation.RequestMethod;
import com.qloudfin.qloudbus.reactive.Callback;
import io.advantageous.qbit.server.ServiceEndpointServer;
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
    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public void addArtifact(final Callback<Map> callback ) {
      //  ServiceEndpointServer serviceEndpointServer=null;

        Map res=new HashMap();
        res.put("version","1.0");
        res.put("name","market_service");
        callback.accept(res);
    }
}
