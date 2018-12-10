import com.ctfs.qloudMarket.market_service.order_service.pojo.Order;
import com.ctfs.qloudMarket.market_service.order_service.service.OrderService;
import com.ctfs.qloudMarket.market_service.purchase_service.endpoint.PurchaseServiceEndPoint;
import com.ctfs.qloudMarket.market_service.util.HTTPUtils;
import com.ctfs.qloudMarket.market_service.util.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/9/20
 * Time: 9:36
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class TestOrder {
    private static Logger logger = LoggerFactory.getLogger(TestOrder.class);
    PurchaseServiceEndPoint purchaseServiceEndPoint=new PurchaseServiceEndPoint();
    HTTPUtils httpUtils=new HTTPUtils();
    OrderService orderService=new OrderService();
    @Test
    public void testWrapOrder(){
        String input=" { \n" +
                " \"payment_address1\":\"192.168.10.128\",\n" +
                " \"user_token\":\"JaN6FkvRri7f9FYOYHqkzHzza95xtLkJ\",\n" +
                " \"customer_id\":\"1\",\n" +
                " \"order_id\":\"53\",\n" +
                " \"user_id\":\"1\",\n" +
                " \"products\":[{\"product_type\":\"20\", \"product_id\":\"50\"}]\n" +
                " }";
      try {
          Map data=  JacksonUtils.json2map(input);
          Order order=  purchaseServiceEndPoint.wrapOrder(data);
          logger.info("Order:{}",order);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void queryLicense()  {
        Map sendData=new HashMap();
        sendData.put("pcode","12345621");
        String sendStr= JacksonUtils.mapToJson(sendData);
        try {
            String response= httpUtils.sendJsonBody("http://127.0.0.1:8100/purchase", HTTPUtils.METHOD_POST,new HashMap<>(),sendStr.getBytes("utf-8"));
            Map licenseData=JacksonUtils.json2map(response);
            logger.info("{},\n {}",response,licenseData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testSB(){
        StringBuffer sss=new StringBuffer("dsadsadsa,");
        logger.info("{}",sss.substring(0,sss.length()-1));
    }
    @Test
    public  void testDB() throws Exception {
        OrderService orderService=new OrderService();
        orderService.updateOrderStatus("2a1c298d-02b8-4701-9653-0f57a15f4551","info ","deployer");
    }
    @Test
    public  void testQUERYTOKEN() throws Exception {
        String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ7XCJuYW1lXCI6bnVsbCxcImlkXCI6XCJoYWlzaGVuZ1wiLFwiYXBwbGljYXRpb25JZFwiOlwiNGNiYTM5ZDUtZjZiMC0xMWU4LWI3YzgtZjJmNTE0MGFlZDEzXCIsXCJ1dWlkXCI6XCJiOGVlZjA3OC1mNmNmLTExZTgtYjdjOC1mMmY1MTQwYWVkMTNcIn0iLCJhdWQiOiJodHRwczpcL1wvcWxvdWRiaXQuY29tIiwibmJmIjoxNTQzODMxNjQ0LCJpc3MiOiJxbG91ZGJpdCIsImV4cCI6MTU0NDQzNjQ0NCwiaWF0IjoxNTQzODMxNjQ0LCJqdGkiOiJhOWNkYzVjOC0zODgzLTQwNmUtYTFmMi04ZTMyNjYzMmRkMDkifQ.FO94UOul8Geb81xOrJgW7u-83EyN5MFfgwKKAclSULycgNfScJSF-EaQXoAa2SmG8wGGYlcS6NZmOJe3OQF8TlVfAP8kxy3f5kR65dfb-kjLAVhtX2RzuTR1Q0uqpaYuC2XE05zSmyfyUPohXbuBCYDV2qpaLVU2gSEnbNSeXQr2q3mUVSUWRjbqc_sGOebkcbLe72BnkAWuB5h5qSfb_f97xMH24D8y9fkcxnuT2NFBaDelSZ02zWhvk08jOANkMwgg0lXv9DtML_E7asvCQeWejbb5DdxPhYadNJb8difW9QkvjQQSPk4cPOL3XzdmJX6ATGkrtytLo1jfdHXWFw";
        Map result=null;
        Map head=new HashMap();
        logger.info("\n X-Qloud-Token:{}",token);
        head.put("X-Qloud-Token",token);
        String info= httpUtils.proxyHttpRequest("http://192.168.1.145:30578/authc/jwt" , HTTPUtils.METHOD_GET, head,null);
        logger.info("info:{}",info);
        //        if(StringUtils.isNotEmpty(info)){
//            result=JacksonUtils.json2map(info);
//        }
    }
    @Test
    public void testOrder(){
        Map<String,Object> data=new HashMap<>();
        /**
         *
          "product":"571910296180887552",
         "account":"10001",
         "pcode":"345678900-4567890-23668",
         "productName":"QloudTest - Selenium",
         "orderType":"buy",
         "addrId":"1"
         */
        data.put("product","571828364302618624");
        data.put("account","10001");
        data.put("pcode","345678900-4567890-8888");
        data.put("productName","QloudDOP");
        data.put("orderType","buy");
        try {
            logger.info("result:{}",orderService.orderCreater2(data));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
