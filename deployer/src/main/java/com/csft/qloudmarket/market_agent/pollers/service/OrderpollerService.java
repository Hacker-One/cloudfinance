package com.csft.qloudmarket.market_agent.pollers.service;

import com.csft.qloudmarket.market_agent.chartmuseum.ChartMuseumService;
import com.csft.qloudmarket.market_agent.endPoints.PurchaseNotificationEndPoint;
import com.csft.qloudmarket.market_agent.license.pojo.LicenseInfo;
import com.csft.qloudmarket.market_agent.license.server.LicenseService;
import com.csft.qloudmarket.market_agent.order.pojo.OrderPojo;
import com.csft.qloudmarket.market_agent.product.pojo.ArtifactPojo;
import com.csft.qloudmarket.market_agent.product.service.ProductService;
import com.csft.qloudmarket.market_agent.rancher.service.RancherService;
import com.csft.qloudmarket.market_agent.registry.RegistryAccessService;
import com.csft.qloudmarket.market_agent.remoteInterface.OrderServiceEndpoint;
import com.csft.qloudmarket.market_agent.util.*;
import com.qloudfin.qloudbus.sdk.client.QloudServiceClientBuilder;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.jgroups.util.ConcurrentLinkedBlockingQueue;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/9/18
 * Time: 15:58
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class OrderpollerService {
    private static Logger logger = LoggerFactory.getLogger(OrderpollerService.class);
    private LicenseService licenseService = new LicenseService();
    private ProductService productService =new ProductService();
    private RegistryAccessService registryAccessService = new RegistryAccessService();
    private ChartMuseumService chartMuseumService = new ChartMuseumService();
    private RancherService rancherService=new RancherService();
    private HTTPUtils httpUtils=new HTTPUtils();
//    public static BlockingQueue<Map> orderQueue = new ConcurrentLinkedBlockingQueue(100);

    public void purchaseNotify(Map<String, Object> req) {
//        logger.info("agent  got a message form service ! {}",req);
//        Map result = null;
//        result = new HashMap();
//        String info="{\"saved\":false}";
//        try {
//            List<Map> products=(List<Map>)req.get("products");
//            List<Map> licensesM= (List) req.get("licenses"); //license 需要 key 以及失效时间
//
//
//            List<LicenseInfo> licenses=getLicenseInfos(licensesM);
//
//            licenseService.saveLicenses(licenses);
//            for (Map product:products){
//                String productType= (String) product.get("product_type");
//                String productId= (String) product.get("product_id");
//                List<Map> images=(List<Map>) product.get("imageInfos");
//                List<Map> charts=(List<Map>) product.get("chartInfos");
//                for(Map<String,Object> imageInfo:images) {
//                    String imageName=(String)imageInfo.get("imageName");
//                    String tag=(String)imageInfo.get("tag");
//                    logger.info("image :{},tag :{}",imageName,tag);
//                    if (imageInfo != null && (StringUtils.isNotEmpty(imageName))&&(StringUtils.isNotEmpty(tag))) {
//                        JSONObject imageOper = registryAccessService.imagePurchaseProgress(imageName, tag); //购买后触发image 文件上传
//                        logger.info("image operating  over result Info: {}\n", imageOper);
//                    }
//                }
//                for(Map<String,Object> chartInfo:charts){
//                    String chartName= (String) chartInfo.get("chartName");
//                    logger.info("@@@chartInfo.name:{}",chartName);
//                    String fileName= chartMuseumService.downLoadChartConfig(chartName, chartName); //获取文件名
//                    logger.info("fileName:{}",fileName);
//                    info = chartMuseumService.uploadChartConfig(chartName);
//                    logger.info("chart museum info:{}", info);
//                }
//            }
//            JSONObject infoJson=JSONObject.fromObject(info);
//            String saved =infoJson.get("saved")!=null?Boolean.toString((Boolean) infoJson.get("saved")):"";
//            if ("true".equals(saved)) {
//                result.put("succeed", true);
//                result.put("msg", "the service will  deploy on your cloud");
//            } else {
//                result.put("succeed", false);
//                result.put("msg", "exeption on your programs.");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            result.put("succeed", false);
//            result.put("msg", "exeption on your programs.");
//        }
    }



    public Map purchaseNotify2(String orderId) {
        logger.info("purchaseNotify2! {}",orderId);
        Map result =  new HashMap();
         Map logData=new HashMap();
         logData.put("method","notify");
        String info="{\"saved\":false}";
        //need login and get accountId
        try {
         OrderPojo orderPojo=requestOrder(orderId);
         Map header=getNexusToken();

         //  if(orderPojo!=null){

         if(orderPojo!=null&& header!=null){

             logData.put("orderInfo",orderPojo);

            String accountId= orderPojo.getAccountId();
            String productId= orderPojo.getProductId();
           List<ArtifactPojo> artifacts= productService.getArtifacts(productId);
           if(artifacts!=null&&artifacts.size()>0){
               for(ArtifactPojo artifactPojo:artifacts) {
                   String typeName = artifactPojo.getTypeName().toLowerCase();
                   if (Common.MA_COMMON_ARTIFACT_TYPE_DOCKER.equals(typeName)) {
                       String tag = artifactPojo.getTag();
                       logger.info("{}SSSS{}", artifactPojo.getUrl(), tag);
                       JSONObject imageOper = registryAccessService.imagePurchaseProgressNexus(artifactPojo.getUrl(),tag,header); //购买后触发image 文件上传
                       logger.info("image operating  over result Info: {}\n", imageOper);

                       logData.put("reg_" + artifactPojo.getUrl() + "_" + tag, imageOper);
                   }
               }
               //循环两次的原因是因为 image 优先于 chart
               for(ArtifactPojo artifactPojo:artifacts) {
                   String typeName = artifactPojo.getTypeName().toLowerCase();
                   logger.info("\n\n\nTYPE:{}\n\n\n\n", typeName);
                   if (Common.MA_COMMON_ARTIFACT_TYPE_CHART.equals(typeName)) {
                       logger.info("@@@chartInfo.name:{}", artifactPojo.getUrl());
                       String fileName = chartMuseumService.downLoadChartConfig(accountId, typeName, artifactPojo.getUrl(), artifactPojo.getUrl()); //获取文件名
                       logger.info("Chart fileName:{}", fileName);
                       info = chartMuseumService.uploadChartConfig(artifactPojo.getUrl());
                       logger.info("chart museum info:{}", info);

                       Map data = rancherService.deployApplication(artifactPojo.getUrl());

                       logData.put("DEPLOY_INFO", data);

                       if(!"000".equals((String)data.get("code"))){
                           info="{\"saved\":false}";
                       }
                     //  logData.put()
                   }
               }
           }
           LicenseInfo licenseInfo=   licenseService.getLicense(productId,accountId);
           licenseService.saveLicenses(licenseInfo); //save the license
             logData.put("LICENSE_INFO","SAVE");
         }
            JSONObject infoJson=JSONObject.fromObject(info);
            String saved =infoJson.get("saved")!=null?Boolean.toString((Boolean) infoJson.get("saved")):"";
            if ("true".equals(saved)) {
                result.put("succeed", true);
                result.put("msg", "the service will  deploy on your cloud");
                result.put("data",logData);
            } else {
                result.put("succeed", false);
                result.put("msg", "exeption on your programs.");
                result.put("data",logData);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("ERROR {}",e.getStackTrace());
            result.put("succeed", false);
            result.put("msg", "exeption on your programs.");
            result.put("data",logData);
        }
        return result;
    }



    private LicenseInfo getLicenseInfos( Map licensesM){
        LicenseInfo license =null;
        if(licensesM!=null&&licensesM.size()>0) {
            license=  new LicenseInfo();
                LicenseInfo licenseInfo = new LicenseInfo();
                licenseInfo.setProductId((String) licensesM.get("productId"));
                licenseInfo.setAccountId((String) licensesM.get("accountId"));
                licenseInfo.setExpiredDate((String) licensesM.get("expiredDate"));
                licenseInfo.setContent((String) licensesM.get("content"));
        }
        return license;
    }

   // private final String SERVICE_CONF = "market-service.conf";
    // 接⼝口服务根路路径
  //  private final String SERVICE_ADDR = "/orders";
    // 接⼝口类
    //private final String SERVICE_CLS = "com.csft.qloudmarket.market_agent.remoteInterface.OrderServiceEndpoint";

    private QloudServiceClientBuilder<OrderServiceEndpoint> client = null;
    private OrderServiceEndpoint purchaseNotifyInf = null;// 接⼝口配置
    /**
     * 获取客户端代理理
     */
//    private void initProxy() {
//        client = QloudServiceClientBuilder.serviceClientBuilder(SERVICE_CONF, SERVICE_ADDR, SERVICE_CLS);
//        purchaseNotifyInf = client.getProxy();
//    }

//    private void closeProxy() {
//        client.flush();
//        client.close();
//    }


    public void requestOrder() throws InterruptedException {

        logger.info("begin queryOrder");
        Map rs = new HashMap();
   //     initProxy(); //initial the remote interface invoking
        final CountDownLatch latch = new CountDownLatch(1);
        Map agentData=new HashMap();
//        String dateTime=DateUtils.getCurrentDate(DateUtils.DATESHORTFORMAT);
//        agentData.put("ip",Common.getPropertiesKey(Common.MA_COMMON_IPADDRESS));
//        agentData.put("token",Common.getPropertiesKey(Common.MA_COMMON_TOKEN));
//        agentData.put("company",Common.getPropertiesKey(Common.MA_COMMON_COMPANY));
//        agentData.put("customerId",Common.getPropertiesKey(Common.MA_COMMON_CUSTOMERID));
//        agentData.put("dateTime",dateTime);
//        agentData.put("infoStr", DESUtils.getEncryptString((new StringBuffer(Common.getPropertiesKey(Common.MA_COMMON_IPADDRESS))).append(Common.getPropertiesKey(Common.MA_COMMON_COMPANY)).append(Common.getPropertiesKey(Common.MA_COMMON_CUSTOMERID)).append(Common.getPropertiesKey(Common.MA_COMMON_TOKEN)).toString()));
//       // logger.info("@@@{}", JacksonUtils.mapToJson(agentData));
//        purchaseNotifyInf.notify(response -> {
//        }, agentData).then(done -> {
//            try {
//                     logger.info("\n\n done: {}",done);
//                     orderQueue.put((Map) done);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            latch.countDown();
//        }).catchError(e -> {
//            latch.countDown();
//        }).invoke();
//        latch.await();
        logger.info("OVER !!!!!!");
    }



    public OrderPojo requestOrder(String orderId) throws Exception {
        logger.info("begin queryOrder");

        OrderPojo rs = null;
        String info=queryOrder(orderId);
        logger.info("\n data: {}",info);
        Map order= JacksonUtils.json2map(info);
        String status= (String) order.get("code");
        if("000".equals(status)){
           Map data= (Map) order.get("data");
           if(data!=null){
               OrderPojo orderPojo= JacksonUtils.map2pojo(data,OrderPojo.class);
               if(checkOrderData(orderPojo)){
                   rs=orderPojo;
               }
           }
        }
        logger.info("check order");
        return rs;
    }

    private boolean checkOrderData(OrderPojo orderPojo){
       boolean result=false;
       if(orderPojo!=null){
           if(StringUtils.isNotEmpty(orderPojo.getId())&&
              StringUtils.isNotEmpty(orderPojo.getAccountId())&&
              StringUtils.isNotEmpty(orderPojo.getCreateDate())&&
              StringUtils.isNotEmpty(orderPojo.getOrderStatus())&&
              StringUtils.isNotEmpty(orderPojo.getProductId())&&
              StringUtils.isNotEmpty(orderPojo.getPurchaseCode())&&
              (orderPojo.getLicenseId()>0)){
              return  true;
           }
       }
       return result;
    }

    public String queryOrder(String id) throws Exception {
        String result=null;
      StringBuffer address=new StringBuffer(Common.getPropertiesKey(Common.MA_COMMON_MARKETADDRESS));
       address.append(Common.MA_COMMON_API_ORDER).append(id);
       String url= address.toString();
       logger.info("\n\n {}",url);
        result=  httpUtils.get(url,null,null);
        return result;
    }


    public void notifyServer(String id,Map data) throws Exception {
       boolean r= (boolean)data.get("succeed");
        StringBuffer address=new StringBuffer(Common.getPropertiesKey(Common.MA_COMMON_MARKETADDRESS));
        address.append(Common.MA_COMMON_API_ORDER).append(id);
        String url= address.toString();
       Map sended=new HashMap();
        JSONObject result=null;
       if(r){
           sended.put("msg","deployed");
           sended.put("data",data);

       }else {
           sended.put("msg","undeployed");
           sended.put("data",data);
       }
        String sendstr=JacksonUtils.mapToJson(sended);
        result=  httpUtils.sendJsonBody(url,HTTPUtils.METHOD_POST,sendstr.getBytes("utf-8"));
        logger.info("result:",result);
    }

    private Map getNexusToken() throws Exception {
        Map tokenInfo=  registryAccessService.getNexusRequestToken(Common.getPropertiesKey(Common.MARKET_USERNAME),Common.getPropertiesKey(Common.MARKET_PWD));
        Map requestHeader=null;
        if(tokenInfo!=null&&"ok".equals(((String) tokenInfo.get("status")).toLowerCase())){
            requestHeader=new HashMap();
            requestHeader.put("X-Market-Token",tokenInfo.get("access_token"));
        }
        return requestHeader;
    }
}
