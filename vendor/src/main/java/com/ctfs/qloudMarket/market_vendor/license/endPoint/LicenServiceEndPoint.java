package com.ctfs.qloudMarket.market_vendor.license.endPoint;

import com.ctfs.qloudMarket.market_vendor.license.util.Common;
import com.ctfs.qloudMarket.market_vendor.license.util.HTTPUtils;
import com.ctfs.qloudMarket.market_vendor.license.util.JacksonUtils;
import com.qloudfin.qloudbus.annotation.RequestMapping;
import com.qloudfin.qloudbus.annotation.RequestMethod;
import com.qloudfin.qloudbus.reactive.Callback;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/10/18
 * Time: 15:15
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@RequestMapping("/")
public class LicenServiceEndPoint {
    private static Logger logger= LoggerFactory.getLogger(LicenServiceEndPoint.class);
     private HTTPUtils httpUtils=new HTTPUtils();

    @RequestMapping(value = "/purchase", method = RequestMethod.POST)
    public void purchase(final Callback<Map> callback, final Map<String,Object>  requestBody){
        logger.info("purchase:{}",requestBody);
        Map result=new HashMap();
        String purchaseCode= (String) requestBody.get("pcode");
        try {
                  Map data=new HashMap();
                  data.put("expiredDate", "20501231");
                  data.put("content", "testtesttesttesttesttesttesttest");
                  data.put("pcode", purchaseCode);
                  result.put("code", "000");
                  result.put("msg", "succeed");
                  result.put("data", data);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("error:{}",e.getStackTrace());
            result.put("code","001");
            result.put("msg","error");
        }
        callback.accept(result);
    }




    @RequestMapping(value = "/purchase/unsync", method = RequestMethod.POST)
    public void purchase2(final Callback<Map> callback, final Map<String,Object>  requestBody){
        logger.info("purchase:{}",requestBody);
        Map result=new HashMap();
        String purchaseCode= (String) requestBody.get("pcode");
        try {
            Thread x=new Thread(){
                @Override
                public void run() {

                    try {
                        sleep(15000);
                        Map data=new HashMap();
                        data.put("expiredDate", "20501231");
                        data.put("content", "testtesttesttesttesttesttesttest");
                        data.put("pcode", purchaseCode);
                        String sendStr= JacksonUtils.mapToJson(data);
                        String response= httpUtils.sendJsonBody(Common.getPropertiesKey(Common.MARKET_ADDRESS),HTTPUtils.METHOD_POST,new HashMap<>(),sendStr.getBytes("utf-8"));
                        logger.info("\n response info {}",response);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            };
            x.start();
            result.put("code", "000");
            result.put("msg", "succeed");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("error:{}",e.getStackTrace());
            result.put("code","001");
            result.put("msg","error");
        }
        callback.accept(result);
    }
}
