package com.ctfs.qloudMarket.market_service.util;

import com.ctfs.qloudMarket.market_service.account.pojo.UserPojo;
import com.ctfs.qloudMarket.market_service.account.service.UserService;
import com.ctfs.qloudMarket.market_service.auth_service.service.AuthService;
import com.ctfs.qloudMarket.market_service.permission_service.service.PermissionServer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/11/28
 * Time: 16:27
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public  class BaseEndpoint {

    private static Logger logger= LoggerFactory.getLogger(BaseEndpoint.class);
    private AuthService authService=new AuthService();
    private PermissionServer permissionServer=new PermissionServer();
    private UserService userService=new UserService();
   //protected Object doService()
    protected Object doService(String token,Object service,String methodName,String path,Object... parameters) throws Exception {
        Object result=null;
        Class serviceClass=service.getClass();
        String className=serviceClass.getName();
        try {
            logger.info("do service {}.{}({})",className,methodName,parameters);
            Map tokenInfo=  authService.queryToken(token);
            if(tokenInfo==null){
                Map data=new HashMap<>();
                data.put("code","101");
                data.put("msg","failed token error");
                return  data;
            }
            else {
              String username= (String) tokenInfo.get("id");
             UserPojo userInfo=userService.getUser(username);
              if(userInfo==null){ //如果用户不存在 则 告诉前端进行补录
                  Map data=new HashMap<>();
                  data.put("code","401");
                  data.put("msg","user not exist on market");
                  return  data;
              }
             // String    permissionUnfo=permissionServer.checkUserPermission(username,path);
           //  if(StringUtils.isNotEmpty(permissionUnfo)){
                if(permissionServer.checkUserResource(username,path)){
                 Method [] methods =serviceClass.getMethods();
                 for(int i=0;i<methods.length;i++){
                     Method method=methods[i];
                     logger.info("{}:",method.getName());
                     if(method.getName().equals(methodName)){
                         logger.info("INVOKE :{}:",methodName);
                         result=  method.invoke(service,parameters);
                         logger.info("\n\n\n\n\n\n{}",result.getClass().getName());
                     }
                 }
             }else {
                 Map data=new HashMap<>();
                 data.put("code","102");
                 data.put("msg","permission deny");
                 return  data;
             }
            }
            logger.info("do service over ");
        }  catch (IllegalAccessException e) {
            e.printStackTrace();
            throw e;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return  result;
    }


}
