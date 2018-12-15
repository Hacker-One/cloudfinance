package com.ctfs.qloudMarket.market_service.account.endpoint;

import com.ctfs.qloudMarket.market_service.account.pojo.UserPojo;
import com.ctfs.qloudMarket.market_service.account.service.UserService;
import com.ctfs.qloudMarket.market_service.auth_service.pojo.AuthUser;
import com.ctfs.qloudMarket.market_service.auth_service.service.AuthService;
import com.qloudfin.qloudbus.annotation.RequestMapping;
import com.qloudfin.qloudbus.annotation.RequestMethod;
import com.qloudfin.qloudbus.reactive.Callback;
import io.advantageous.boon.core.Str;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/10/18
 * Time: 14:19
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@RequestMapping("/")
public class LoginBaffleEndPoint {
    private static Logger logger = LoggerFactory.getLogger(LoginBaffleEndPoint.class);
    private UserService userService = new UserService();
    private AuthService authService = new AuthService();

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void addAccount(final Callback<Map> callback, final Map<String, Object> requestBody) {
        Map result = new HashMap();
        try {
            String loginNam = (String) requestBody.get("loginName");
            String pwd = (String) requestBody.get("pwd");
            Map data = userService.userLogin(loginNam, pwd);
            if(data!=null){
                result.put("code", "000");
                result.put("msg", "succeed");
                result.put("data", data);
            }else {
                result.put("code", "002");
                result.put("msg", "user not exist!");
            }
        } catch (Exception e) {
            logger.info("error:{}", e.getStackTrace());
            result.put("code", "001");
            result.put("msg", "error");
            e.printStackTrace();
        }
        callback.accept(result);
    }
}
