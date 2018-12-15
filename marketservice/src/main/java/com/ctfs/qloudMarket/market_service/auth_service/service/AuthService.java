package com.ctfs.qloudMarket.market_service.auth_service.service;

import com.ctfs.qloudMarket.market_service.artifact_service.service.ArtifactService;
import com.ctfs.qloudMarket.market_service.auth_service.pojo.AuthUser;
import com.ctfs.qloudMarket.market_service.util.BaseService;
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
 * Date: 2018/10/31
 * Time: 16:51
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public class AuthService extends BaseService {
    private static Logger logger= LoggerFactory.getLogger(AuthService.class);
    private HTTPUtils httpUtils=new HTTPUtils();
    private String auth_base_url=Common.getPropertiesKey(Common.AUTH_BASE_URL,"");
    private String questTokenUrl= Common.getPropertiesKey(Common.API_GATEWAY_ADD_KEY);
    public static String AUTH_STATUS_ERROR="err";
    public static String AUTH_STATUS_OK="ok";
    /**
     * synchronize a user to auth
     * @param user
     * @return
     * @throws Exception
     */
    public Map createUserOnAuth(AuthUser user) throws Exception {
        logger.info("\n createUserOnAuth :{}",user);
       Map result=null;
        try {
          String requestData=  JacksonUtils.obj2json(user);
          Map<String,String> header= new HashMap<>();
          header.put(HTTPUtils.CONTENT_TYPE,HTTPUtils.JSON_CONTENT_TYPE);
         // header.put(HTTPUtils.API_KEY,Common.getPropertiesKey(Common.AUTH_APIKEY_KEY));
          StringBuffer url=new StringBuffer(auth_base_url).append(Common.AUTH_USER_PATH);
          logger.info("url: {}",url);
          String resultStr =  httpUtils.proxyHttpRequest(url.toString(),httpUtils.METHOD_POST,header,requestData);
          logger.info("\ncreateUserOnAuth result:{}",resultStr);
          result=JacksonUtils.json2map(resultStr);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("createUserOnAuth ERROR: {}",e.getStackTrace());
            throw  e;
        }
        return  result;
    }


    /**
     *
     * @param user
     * @return
     * @throws Exception
     */
    public Map getLoginToken(AuthUser user) throws Exception {
        logger.info("getLoginToken :{}",user);
        Map result=null;
        try {
            Map<String,String> requestMap=new HashMap<>();
            requestMap.put("phone",user.getId());
            requestMap.put("password",user.getPassword());
            String requestData=  JacksonUtils.mapToJson(requestMap);
            Map<String,String> header= new HashMap<>();
            header.put(HTTPUtils.CONTENT_TYPE,HTTPUtils.JSON_CONTENT_TYPE);
           // header.put(HTTPUtils.API_KEY,Common.getPropertiesKey(Common.AUTH_APIKEY_KEY));
            StringBuffer url=new StringBuffer(auth_base_url).append(Common.AUTH_TOKEN_PATH);
            logger.info("\n request url:{}",url);
            String resultStr =  httpUtils.proxyHttpRequest(url.toString(),httpUtils.METHOD_POST,header,requestData);
            logger.info("result:{}",resultStr);
            result=JacksonUtils.json2map(resultStr);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("getLoginToken ERROR: {}",e.getStackTrace());
            throw  e;
        }
        return  result;
    }


    public Map<String,Object> queryToken(String token) throws Exception {
        Map result=null;
        Map head=new HashMap();
        logger.info("\n X-Qloud-Token:{}",token);
        head.put("X-Qloud-Token",token);
       // head.put(HTTPUtils.API_KEY,Common.getPropertiesKey(Common.AUTH_APIKEY_KEY));
        String info= httpUtils.proxyHttpRequest(questTokenUrl , HTTPUtils.METHOD_GET, head,null);
        if(StringUtils.isNotEmpty(info)){
            result= JacksonUtils.json2map(info);
        }
        return  result;
    }
}
