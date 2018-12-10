package com.ctfs.qloudMarket.market_service.vendor.endpoint;

import com.ctfs.qloudMarket.market_service.product.endpoint.ProductServiceEndPoint;
import com.ctfs.qloudMarket.market_service.product.pojo.ProductPojo;
import com.ctfs.qloudMarket.market_service.vendor.pojo.VendorPojo;
import com.ctfs.qloudMarket.market_service.vendor.service.VendorService;
import com.qloudfin.qloudbus.annotation.PathVariable;
import com.qloudfin.qloudbus.annotation.RequestMapping;
import com.qloudfin.qloudbus.annotation.RequestMethod;
import com.qloudfin.qloudbus.reactive.Callback;
import io.advantageous.qbit.annotation.RequestParam;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/10/16
 * Time: 13:51
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@RequestMapping("/")
public class VendorServiceEndpoint {
    private static Logger logger= LoggerFactory.getLogger(VendorServiceEndpoint.class);
    private VendorService vendorService=new VendorService();

    @RequestMapping(value = "/vendors", method = RequestMethod.POST)
    public void addVendor(final Callback<Map> callback, final Map<String,Object>  requestBody){
        logger.info("addVendor:{}",requestBody);
        Map result=new HashMap();
        try {
            int r=   vendorService.addVendor(requestBody);
            if(r>0) {
                result.put("code", "000");
                result.put("msg", "succeed");
            }else {
                result.put("code","002");
                result.put("msg","error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("error:{}",e.getStackTrace());
            result.put("code","001");
            result.put("msg","error");
        }
        callback.accept(result);
    }


    @RequestMapping(value = "/vendors/{id}", method = RequestMethod.GET)
    public void getVendor(final Callback<Map> callback, @PathVariable("id") String id ){
        logger.info("getProduct:{}",id);
        Map result=new HashMap();
        try {
            VendorPojo vendorPojo=   vendorService.getVendor(Integer.parseInt(id));
            if(vendorPojo!=null) {
                result.put("code", "000");
                result.put("msg", "succeed");
                result.put("data",vendorPojo);
            }else {
                result.put("code","002");
                result.put("msg","error");
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
