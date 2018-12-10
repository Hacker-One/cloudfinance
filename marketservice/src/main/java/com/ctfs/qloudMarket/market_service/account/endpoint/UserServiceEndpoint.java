package com.ctfs.qloudMarket.market_service.account.endpoint;

import com.ctfs.qloudMarket.market_service.account.pojo.AccountPojo;
import com.ctfs.qloudMarket.market_service.account.service.UserService;
import com.ctfs.qloudMarket.market_service.auth_service.pojo.AuthUser;
import com.ctfs.qloudMarket.market_service.auth_service.service.AuthService;
import com.ctfs.qloudMarket.market_service.util.JacksonUtils;
import com.qloudfin.qloudbus.annotation.PathVariable;
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
 * Date: 2018/10/16
 * Time: 14:07
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@RequestMapping("/")
public class UserServiceEndpoint {
    private static Logger logger= LoggerFactory.getLogger(UserServiceEndpoint.class);
    private UserService userService=new UserService();
//    private AuthService authService=new AuthService();

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public void addUser(final Callback<Map> callback, final Map<String,Object>  requestBody){
        Map result=new HashMap();
        try {
            int r=  userService.addUser(requestBody);
            if(r>0){
                result.put("code","000");
                result.put("msg","succeed");
            }else {
                result.put("code","002");
                result.put("msg","operate Error");
            }
        } catch (Exception e) {
            logger.info("error:{}",e.getStackTrace());
            result.put("code","001");
            result.put("msg","error");
            e.printStackTrace();
        }
        callback.accept(result);
    }

    @RequestMapping(value = "/users/{id}/accounts", method = RequestMethod.GET)
    public void getAccount(final Callback<Map> callback,  @PathVariable("id") String id){
        Map result=new HashMap();
        try {
            AccountPojo accountPojo=  userService.getAccountByUser(id);
            if(accountPojo!=null){
             //   Map data= JacksonUtils.json2map(JacksonUtils.obj2json(accountPojo));
                result.put("code","000");
                result.put("msg","succeed");
                result.put("data",accountPojo);
            }else {
                result.put("code","002");
                result.put("msg","error");
            }
            callback.accept(result);
        } catch (Exception e) {
            logger.info("error:{}",e.getStackTrace());
            result.put("code","001");
            result.put("msg","error");
            callback.accept(result);
        }
    }
}
