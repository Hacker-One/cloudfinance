package com.ctfs.qloudMarket.market_service.license_service.endpoint;

import com.ctfs.qloudMarket.market_service.artifact_service.pojo.ArtifactPojo;
import com.ctfs.qloudMarket.market_service.license_service.pojo.LicensePojo;
import com.ctfs.qloudMarket.market_service.license_service.server.LicenseService;
import com.ctfs.qloudMarket.market_service.order_service.service.OrderService;
import com.qloudfin.qloudbus.annotation.PathVariable;
import com.qloudfin.qloudbus.annotation.RequestMapping;
import com.qloudfin.qloudbus.annotation.RequestMethod;
import com.qloudfin.qloudbus.reactive.Callback;
import io.advantageous.qbit.annotation.RequestParam;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/8/6
 * Time: 16:41
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@RequestMapping("/")
public class LicenseServiceEndPoint {
    private static Logger logger= LoggerFactory.getLogger(LicenseServiceEndPoint.class);
    private LicenseService licenseService=new LicenseService();
    private OrderService orderService=new OrderService();
    /**
     * 生成 license
     * @param callback
     * @param requestBody
     */
//    @RequestMapping(value = "/generateLicense", method = RequestMethod.POST)
//    public void generateLicense(final Callback<Map> callback, final Map<String,Object>  requestBody){
//      String artifactId= (String) requestBody.get("artifactId");
//      String customerName=(String) requestBody.get("customerName");
//      String validDate=(String) requestBody.get("validDate");
//      String invalidDate=(String) requestBody.get("invalidDate");
//      String singnature =(String) requestBody.get("singnature");
//
//    }
//
//    /**
//     * 验证license
//     * @param callback
//     * @param requestBody
//     */
//    @RequestMapping(value = "/vertifyLicense", method = RequestMethod.POST)
//    public void  vertifyLicense(final Callback<Map> callback, final Map<String,Object>  requestBody){
//
//    }
    @RequestMapping(value = "/licenses", method = RequestMethod.POST)
    public void addlicense(final Callback<Map> callback, final Map<String,Object>  requestBody){
        logger.info("addlicense:{}",requestBody);
        Map result=new HashMap();
        try {
                result = orderService.addOrderLicense(requestBody);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("error:{}",e.getStackTrace());
            result.put("code","001");
            result.put("msg","error");
        }
        callback.accept(result);
    }

    /**
     *
     * @param callback
     * @param product
     * @param account
     */
    @RequestMapping(value = "/licenses", method = RequestMethod.GET)
    public void getLicense(final Callback<Map> callback,@RequestParam("product") String product,@RequestParam("account") String account){
        logger.info("getLicense:{},{}",product,account);
        Map result=new HashMap();
        try {
            LicensePojo licensePojo=licenseService.getLicense(product,account);
            if(licensePojo!=null) {
                result.put("code", "000");
                result.put("msg", "succeed");
                result.put("data",licensePojo);
            }else {
                result.put("code", "002");
                result.put("msg", "error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("error:{}",e.getStackTrace());
            result.put("code","001");
            result.put("msg","error");
        }
        callback.accept(result);
    }




}
