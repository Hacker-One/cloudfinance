package com.ctfs.qloudMarket.market_service.order_service.endpoint;

import com.ctfs.qloudMarket.market_service.order_service.pojo.Order;
import com.ctfs.qloudMarket.market_service.order_service.pojo.OrderPojo;
import com.ctfs.qloudMarket.market_service.order_service.service.OrderService;
import com.ctfs.qloudMarket.market_service.util.BaseEndpoint;
import com.ctfs.qloudMarket.market_service.util.JacksonUtils;
import com.qloudfin.qloudbus.annotation.HeaderParam;
import com.qloudfin.qloudbus.annotation.PathVariable;
import com.qloudfin.qloudbus.annotation.RequestMapping;
import com.qloudfin.qloudbus.annotation.RequestMethod;
import com.qloudfin.qloudbus.reactive.Callback;
import io.advantageous.qbit.annotation.RequestParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ctfs.qloudMarket.market_service.order_service.endpoint.OrderServiceEndpoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/9/18
 * Time: 16:43
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@RequestMapping("/")
public class OrderServiceEndpoint extends BaseEndpoint {
    private static Logger logger = LoggerFactory.getLogger(OrderServiceEndpoint.class);
    private OrderService orderService = new OrderService();

    /**
     * {
     * "ip":"agent ip address",
     * "token":"token message",
     * "company":"company Name",
     * "dateTime":"YYYYmmddHHMinss",
     * "infoStr":""base64 encode str it contain ip token company Name and dateTime"
     * }
     */
//    @RequestMapping(value = "/notify", method = RequestMethod.POST)
//    public void notify(final Callback<Map> callback, final Map<String, Object> data){
//         Map<String,Object> error =new HashMap<>();
//        data.put("process",new HashMap<String,Object>());
//        try {
//            if(orderService.checkOrderRequest(data)){
//                logger.info("go to get agent order");
//                orderService.getShallSendOrder(data);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            error.put("code","901");
//            error.put("msg","500 exception");
//            ((Map)data.get("process")).put("error",error);
//        }
//        callback.accept(data);
//    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public void addOrder(final Callback<Map> callback, final Map<String, Object> requestBody,@HeaderParam("X-Qloud-Token") String token) {
        logger.info("token {} addOrder:{}",token, requestBody);
        Map result = new HashMap();
        try {
            result = (Map) this.doService(token,orderService,"orderCreater2","POST /orders",requestBody);
          //  result = orderService.orderCreater2(requestBody);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("error:{}", e.getStackTrace());
            result.put("code", "001");
            result.put("msg", "error");
        }
        callback.accept(result);

    }






    @RequestMapping(value = "/orders/{id}", method = RequestMethod.POST)
    public void updateOrder(final Callback<Map> callback, @PathVariable("id") String id, final Map<String, Object> requestBody,@HeaderParam("X-Qloud-Token") String token) {
        logger.info("token {} updateOrder:{}",token,requestBody);
        Map result = new HashMap();
        try {
            result = (Map) this.doService(token,orderService,"updateOrderStatusInfo","POST /orders/*",id,requestBody);
         //result= orderService.updateOrderStatusInfo(id,requestBody);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("error:{}", e.getStackTrace());
            result.put("code", "001");
            result.put("msg", "error");
        }
        callback.accept(result);

    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
    public void getOrder(final Callback<Map> callback, @PathVariable("id") String id,@HeaderParam("X-Qloud-Token") String token) {
        logger.info("\n token {} getOrder:{}",token, id);
        Map result = new HashMap();
        try {
            result = (Map) this.doService(token,orderService,"getOrderService","GET /orders/*",id,id);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("error:{}", e.getStackTrace());
            result.put("code", "001");
            result.put("msg", "error");
        }
        callback.accept(result);
    }


    /**
     * return a order it is license  valid;
     * @param callback
     * @param account
     * @param product
     */
    @RequestMapping(value = "/orders/{account}/{product}", method = RequestMethod.GET)
    public void getOrderByAccountAndProduct(final Callback<Map> callback, @PathVariable("account") String account,@PathVariable("product") String product,@HeaderParam("X-Qloud-Token") String token) {
        logger.info("\n token {}getOrderByAccountAndProduct:{},{}",token,account,product);
        Map result = new HashMap();
        try {
            result = (Map) this.doService(token,orderService,"getValidOrderService","GET /orders/*/*",account,product);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("error:{}", e.getStackTrace());
            result.put("code", "001");
            result.put("msg", "error");
        }
        callback.accept(result);
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public void getOrders(final Callback<Map> callback, @RequestParam("accountId") String accountId, @RequestParam("start") int start, @RequestParam("count") int count,@HeaderParam("X-Qloud-Token") String token) {
        logger.info("getOrders:{} ,{},{}", accountId, start, count);
        Map result = new HashMap();
        try {
            result = (Map) this.doService(token,orderService,"getOrderList","GET /orders ",accountId,start,count);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("error:{}", e.getStackTrace());
            result.put("code", "001");
            result.put("msg", "error");
        }
        callback.accept(result);
    }

}
