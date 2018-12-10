package com.ctfs.qloudMarket.market_service.permission_service.endpoint;
import com.qloudfin.qloudbus.annotation.RequestMapping;
import com.qloudfin.qloudbus.annotation.RequestMethod;
import com.qloudfin.qloudbus.annotation.RequestParam;
import com.qloudfin.qloudbus.reactive.Callback;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/11/26
 * Time: 10:27
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@RequestMapping("/")
public class PermissionServiceEndPoint {
    private static Logger logger= LoggerFactory.getLogger(PermissionServiceEndPoint.class);

    @RequestMapping(value = "/permissions", method = RequestMethod.GET)
    public void queryPermission(final Callback<Map<String,Object>> callback, @RequestParam("userid") String userid, @RequestParam("path") String path, @RequestParam("action") String action){
        logger.info("queryPermission:userid:{},path:{},action{}",userid,path,action);
        Map result=new HashMap();
        result.put("status",new Boolean(true));
        callback.accept(result);
    }
}
