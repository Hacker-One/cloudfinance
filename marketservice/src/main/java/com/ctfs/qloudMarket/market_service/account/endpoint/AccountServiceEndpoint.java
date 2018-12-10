package com.ctfs.qloudMarket.market_service.account.endpoint;

import com.ctfs.qloudMarket.market_service.account.pojo.AccountPojo;
import com.ctfs.qloudMarket.market_service.account.service.AccountService;
import com.ctfs.qloudMarket.market_service.product.pojo.ProductPojo;
import com.ctfs.qloudMarket.market_service.util.BaseEndpoint;
import com.ctfs.qloudMarket.market_service.util.JacksonUtils;
import com.ctfs.qloudMarket.market_service.vendor.endpoint.VendorServiceEndpoint;
import com.qloudfin.qloudbus.annotation.HeaderParam;
import com.qloudfin.qloudbus.annotation.PathVariable;
import com.qloudfin.qloudbus.annotation.RequestMapping;
import com.qloudfin.qloudbus.annotation.RequestMethod;
import com.qloudfin.qloudbus.reactive.Callback;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/10/16
 * Time: 13:57
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@RequestMapping("/")
public class AccountServiceEndpoint  extends BaseEndpoint{
    private static Logger logger= LoggerFactory.getLogger(AccountServiceEndpoint.class);
    private AccountService accountService=new AccountService();
    @RequestMapping(value = "/accounts", method = RequestMethod.POST)
    public void addAccount(final Callback<Map> callback, final Map<String,Object>  requestBody,@HeaderParam("X-Qloud-Token") String token){
        Map result=new HashMap();
        try {
            logger.info("\n\n\ntoken :{}",token);
            result = (Map) this.doService(token,accountService,"addAccount","/accounts",requestBody);

        } catch (Exception e) {
            logger.info("error:{}",e.getStackTrace());
            result.put("code","001");
            result.put("msg","error");
            e.printStackTrace();
        }
        callback.accept(result);
    }

    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.GET)
    public void getAccount(final Callback<Map> callback, @PathVariable("id") String id,@HeaderParam("X-Qloud-Token") String token ){
        Map result=new HashMap();
        try {
            AccountPojo accountPojo=  accountService.getAccount(id);
            if(accountPojo!=null){
                Map data= JacksonUtils.json2map(JacksonUtils.obj2json(accountPojo));
                result.put("code","000");
                result.put("msg","succeed");
                result.put("data",data);
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

    @RequestMapping(value = "/accounts/{id}/deployers",method =RequestMethod.POST )
    public  void addAcountDeployers(final Callback<Map> callback,final Map<String,Object>  requestBody){

    }

    @RequestMapping(value = "/accounts/{id}/products", method = RequestMethod.GET)
    public void getAccountProducts(final Callback<Map> callback, @PathVariable("id") String id,@HeaderParam("X-Qloud-Token") String token ){
        Map result=new HashMap();
        try {
            Map<String,List> data=  accountService.getAccountProducts(id);
                result.put("code","000");
                result.put("msg","succeed");
                result.put("data",data);
            callback.accept(result);
        } catch (Exception e) {
            logger.info("error:{}",e.getStackTrace());
            result.put("code","001");
            result.put("msg","error");
            callback.accept(result);
        }
    }
}
