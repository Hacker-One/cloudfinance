package com.ctfs.qloudMarket.market_service.purchase_service.endpoint;

import com.ctfs.qloudMarket.market_service.artifact_service.pojo.ArtifactInfo;
import com.ctfs.qloudMarket.market_service.artifact_service.pojo.ChartInfo;
import com.ctfs.qloudMarket.market_service.artifact_service.pojo.ImageInfo;
import com.ctfs.qloudMarket.market_service.artifact_service.service.ArtifactService;
import com.ctfs.qloudMarket.market_service.common.KafkaTopics;
import com.ctfs.qloudMarket.market_service.license_service.pojo.LicenseInfo;
import com.ctfs.qloudMarket.market_service.license_service.server.LicenseService;
import com.ctfs.qloudMarket.market_service.order_service.pojo.Order;
import com.ctfs.qloudMarket.market_service.order_service.service.OrderService;
import com.ctfs.qloudMarket.market_service.util.Common;
import com.ctfs.qloudMarket.market_service.util.JacksonUtils;
import com.ctfs.qloudMarket.market_service.util.QLoudException;
import com.ctfs.qloudMarket.market_service.websocket_interface.PurchaseNotifyInf;
import com.qloudfin.qloudbus.annotation.OnEvent;
import com.qloudfin.qloudbus.annotation.QloudbusEventManager;
import com.qloudfin.qloudbus.annotation.RequestMapping;
import com.qloudfin.qloudbus.annotation.RequestMethod;
import com.qloudfin.qloudbus.events.EventManager;
import com.qloudfin.qloudbus.reactive.Callback;
import com.qloudfin.qloudbus.sdk.client.QloudServiceClientBuilder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/8/11
 * Time: 13:46
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@RequestMapping("/purchaseService")
//@RequestMapping("/event")
public class PurchaseServiceEndPoint {
    private static Logger logger = LoggerFactory.getLogger(PurchaseServiceEndPoint.class);
    private ArtifactService artifactService = new ArtifactService();
    private LicenseService licenseService = new LicenseService();
 //   private QloudServiceClientBuilder<PurchaseNotifyInf> client = null;
 //   private PurchaseNotifyInf purchaseNotifyInf = null;// 接⼝口配置
    private OrderService orderService=new OrderService();
    @QloudbusEventManager
    private EventManager manager;


//    private final String SERVICE_CONF = "market-agent.conf";
//    // 接⼝口服务根路路径
//    private final String SERVICE_ADDR = "/purchase";
//    // 接⼝口类
//    private final String SERVICE_CLS = "com.ctfs.qloudMarket.market_service.websocket_interface.PurchaseNotifyInf";


    /**
     * 获取客户端代理理
     */
//    private void initProxy() {
//        client = QloudServiceClientBuilder.serviceClientBuilder(SERVICE_CONF, SERVICE_ADDR, SERVICE_CLS);
//        purchaseNotifyInf = client.getProxy();
//    }

    /**
     * 关闭客户端
     */
//    private void closeProxy() {
//        client.flush();
//        client.close();
//    }

//    @RequestMapping(value = "/purchase", method = RequestMethod.POST)
//    public void purchaseTest(final Callback<Map> callback, final Map<String, Object> data) {
//        logger.info("request Data: {}",data);
//        Map result=new HashMap();
//        try {
//            result.put("code","000");
//            result.put("msg","succeed");
//         //   Map data=JacksonUtils.json2map(datainfo);
//            if (checkRequestInfo(data)){
//                Order order=wrapOrder(data);
//                orderService.createOrder(order);
//            } else {
//                throw new QLoudException("A002", "Input message is null!");
//            }
//        } catch (Exception e) {
//
//            logger.info("EXCEPTION: {}",e.getMessage());
//            result.put("code","101");
//            result.put("msg","faild");
//            callback.accept(result);
//
//        }
//        callback.accept(result);
//    }

    /**
     * @param callback
     * @param data
     */
//    @RequestMapping(value = "/purchase", method = RequestMethod.POST)
//    public void purchase(final Callback<Map> callback, final Map<String, Object> data) {
//        logger.info("event has triggered {}", data);
//        Map rs = new HashMap();
//        initProxy(); //initial the remote interface invoking
//        try {
//            if (checkRequestInfo(data)) {
//                Map<String, Object> agentData = new HashMap<>();
//                List<Map> products = (List) data.get("products");
//                List<LicenseInfo> licenses=new ArrayList<>();
//                for (Map product : products) {
//                    String productId=(String) product.get("product_id");
//                    List<ImageInfo> imageInfos = artifactService.queryImageInfos(productId);
//                    List<ChartInfo> chartInfos = artifactService.queryChartInfos(productId);
//                    product.put("imageInfos",imageInfos);
//                    product.put("chartInfos",chartInfos);
//                    Map element = initialLicenseCondit(productId,data);
//                    LicenseInfo licenseInfo = licenseService.generateLicense2(element);//加密密钥
//                    logger.info("licenseJson:{}", licenseInfo);
//                    licenses.add(licenseInfo);
//                }
//                agentData.put("licenses", licenses);
//                agentData.put("products", products);
//                logger.info("{} request remote ........", agentData);
//                final CountDownLatch latch = new CountDownLatch(1);
//
//                purchaseNotifyInf.purchaseNotify(response -> {
//                }, agentData).then(done -> {
//                    logger.info("request Over!");
//                    latch.countDown();
//                }).catchError(e -> {
//                    latch.countDown();
//                }).invoke();
//                latch.await();
//               //     manager.send(KafkaTopics.PURCHASE_EVENT_TOPIC, JacksonUtils.mapToJson(agentData));
//                rs.put("result", true);
//                callback.accept(rs);
//
//            } else {
//                throw new QLoudException("A002", "Input message is null!");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            rs.put("result", false);
//            callback.accept(rs);
//        } finally {
//            closeProxy();
//        }

 //   }

    /**
     * @param requestInfo
     * @return * example is
     * { payment_address1=192.168.10.128,
     * user_token=JaN6FkvRri7f9FYOYHqkzHzza95xtLkJ,
     * customer_id=1,
     * order_id=53,
     * user_id=1,
     * products=[{product_type=20, product_id=50}]
     * }
     */
    private boolean checkRequestInfo(Map<String, Object> requestInfo) throws QLoudException {
        boolean result = false;
        String customer_id = null, order_id = null, deployAddress = null, company = null;
        if (requestInfo != null) {

            customer_id =String.valueOf( requestInfo.get("customer_id"));
            order_id = String.valueOf(requestInfo.get("order_id"));
            deployAddress =String.valueOf( requestInfo.get("payment_address1"));
            company = String.valueOf(requestInfo.get("company"));
            List products = (List) requestInfo.get("products");
            if (StringUtils.isNotEmpty(customer_id) &&
                    StringUtils.isNotEmpty(order_id) &&
                    StringUtils.isNotEmpty(deployAddress) &&
                    StringUtils.isNotEmpty(company) &&
                    products != null && products.size() > 0) {
                logger.info("customer_id :{},order_id:{},deployAddress:{},company:{},products:{}", customer_id, order_id, deployAddress, company, products);
                result = true;
            } else {
                throw new QLoudException("A003", "parameter error!");
            }
        } else {
            throw new QLoudException("A002", "Input message is null!");
        }
        return result;
    }

//    @OnEvent(KafkaTopics.PURCHASE_EVENT_TOPIC)
//    public void purchaseComsumer(final Consumer<Boolean> consumer, String key, String value, final String datainfo) {
//
//        try {
//            Map data=JacksonUtils.json2map(datainfo);
//            if (checkRequestInfo(data)){
//                Order order=wrapOrder(data);
//                orderService.createOrder(order);
//                consumer.accept(true);
//            } else {
//                throw new QLoudException("A002", "Input message is null!");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            consumer.accept(false);
//
//        }
//    }

    /**
     * 封装order
     * @param data
     * @return
     * @throws Exception
     */
    public Order wrapOrder(Map data) throws Exception {
        Map<String, Object> agentData = new HashMap<>();
        Order order=new Order();
        order.setIpAddress((String)data.get("payment_address1"));
      //  order.setUserToken((String)data.get("user_token"));
        order.setUserToken("");
        order.setCustomerId((String)data.get("customer_id"));
        order.setId((String)data.get("order_id"));
        List<Map> products = (List) data.get("products");
        List<LicenseInfo> licenses=new ArrayList<>();

        for (Map product : products) {
            String productId=(String) product.get("product_id");
            List<ImageInfo> imageInfos = artifactService.queryImageInfos(productId);
            List<ChartInfo> chartInfos = artifactService.queryChartInfos(productId);
            product.put("imageInfos",imageInfos);
            product.put("chartInfos",chartInfos);
            // license
            Map element = initialLicenseCondit(productId,data);
            LicenseInfo licenseInfo = licenseService.generateLicense2(element);//加密密钥
            // String licenseJson = JacksonUtils.obj2json(licenseInfo);
            logger.info("licenseJson:{}", licenseInfo);
            licenses.add(licenseInfo);
        }
        agentData.put("licenses", licenses);
        agentData.put("products", products);
        String deployInfo=JacksonUtils.mapToJson(agentData);
        order.setOrderText(deployInfo);
        logger.info(" order: {}", order);
        return order;
    }


//    @Deprecated
//    private void purchaseComsumerBAK(final Consumer<Boolean> consumer, String key, String value, final String data) {
//        logger.info("event has triggered {} : {} :{}", data, key, value);
//        initProxy();
//        try {
//            if (StringUtils.isNotEmpty(value)) {
//                logger.debug("EXAMPLE_SIMPLE_TOPIC-consumer, data.size: {}", value);
//                JSONArray jsonData = JSONArray.fromObject(value);
//                //  jsonData.to
//                JSONObject info = (JSONObject) jsonData.get(0);
//                String productId = (String) info.get("productId");
//                logger.info("productId:{}", productId);
//                // String providerID = (String) info.get("providerID");
//                //String productName = (String) info.get("name");
//                Map<String, Object> agentData = new HashMap<>();
//
//                ArtifactInfo artifactInfo = artifactService.queryAArtifactInfo(productId);
//                if (artifactInfo != null && StringUtils.isNotEmpty(artifactInfo.getArtifactId())) {
//                    Map element = initialLicenseCondit(artifactInfo);
//                    LicenseInfo licenseInfo = licenseService.generateLicense(element);
//                    String licenseJson = JacksonUtils.obj2json(licenseInfo);
//                    logger.info("licenseJson:{}", licenseJson);
//                    agentData.put("license", licenseJson);
//                    String imageInfo = artifactInfo.getImagePath();
//                    if (StringUtils.isNotEmpty(imageInfo)) {
//                        String[] imageAndTag = imageInfo.split(":");
//                        JSONObject jsonImageAndTag = new JSONObject();
//                        jsonImageAndTag.put("name", imageAndTag[0]);
//                        jsonImageAndTag.put("tag", imageAndTag[1]);
//                        agentData.put("imageTag", jsonImageAndTag.toString());
//                    }
//                    agentData.put("chart", artifactInfo.getChartPath());
//                    final CountDownLatch latch = new CountDownLatch(1);
//                    logger.info("{} request remote ........", agentData);
//
//                    purchaseNotifyInf.purchaseNotify(response -> {
//                    }, agentData).then(done -> {
//                        logger.info("request Over!");
//                        latch.countDown();
//                    }).catchError(e -> {
//                        latch.countDown();
//                    }).invoke();
//                    latch.await();
////                    manager.send(KafkaTopics.PURCHASE_EVENT_TOPIC, JacksonUtils.mapToJson(agentData));
//                    consumer.accept(true);
//                } else {
//                    consumer.accept(false);
//                }
//
//
//            } else {
//                consumer.accept(false);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            consumer.accept(false);
//        } finally {
//            closeProxy();
//        }
//        //  consumer.accept(true);
//    }

    /**
     * 检查 是否有该客户的订单
     *
     * @param callback
     * @param data
     */
//    @RequestMapping(value = "/checkOrder", method = RequestMethod.POST)
//    public void checkOrder(final Callback<Map> callback, final Map data) {
//        logger.info("event has triggered {}", data);
//        Map rs = new HashMap();
//    }

    /**
     * @param artifactInfo
     * @return
     */
    private Map<String, String> initialLicenseCondit(ArtifactInfo artifactInfo) {
        Map elements = new HashMap();
        elements.put("providerId", artifactInfo.getProviderId());
        elements.put("artifactId", artifactInfo.getArtifactId());
        elements.put("customerId", "tester");
        elements.put("validDate", "20180801");
        elements.put("invalidDate", "20181231");
        return elements;
    }

    private Map<String, String> initialLicenseCondit(String productId,Map data) {
        Map elements = new HashMap();
        elements.put("product_id", productId);
        elements.put("customer_id",String.valueOf(data.get("customer_id")));
        elements.put("order_id",String.valueOf( data.get("order_id")));
        elements.put("deploy_address",String.valueOf( data.get("deploy_address")));
        elements.put("validDate", "20180801");
        elements.put("invalidDate", "20181231");
        return elements;
    }
}
