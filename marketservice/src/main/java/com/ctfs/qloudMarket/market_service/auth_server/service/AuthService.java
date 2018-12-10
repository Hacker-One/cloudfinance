package com.ctfs.qloudMarket.market_service.auth_server.service;

import com.ctfs.qloudMarket.market_service.util.BaseEndpoint;
import com.ctfs.qloudMarket.market_service.util.Common;
import com.ctfs.qloudMarket.market_service.util.HTTPUtils;
import com.ctfs.qloudMarket.market_service.util.JacksonUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/12/4
 * Time: 9:44
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public class AuthService {
    private static Logger logger= LoggerFactory.getLogger(AuthService.class);
    private String questTokenUrl= Common.getPropertiesKey(Common.API_GATEWAY_ADD_KEY);
    private HTTPUtils httpUtils=new HTTPUtils();

    public Map<String,Object> queryToken(String token) throws Exception {
        Map result=null;
        Map head=new HashMap();
        logger.info("\n X-Qloud-Token:{}",token);
        head.put("X-Qloud-Token",token);
        String info= httpUtils.proxyHttpRequest(questTokenUrl , HTTPUtils.METHOD_GET, head,null);
        if(StringUtils.isNotEmpty(info)){
            result= JacksonUtils.json2map(info);
        }
        return  result;
    }

 //   public


}
