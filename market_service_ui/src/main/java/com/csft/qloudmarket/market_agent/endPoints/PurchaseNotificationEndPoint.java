package com.csft.qloudmarket.market_agent.endPoints;

import com.csft.qloudmarket.market_agent.chartmuseum.ChartMuseumService;
import com.csft.qloudmarket.market_agent.license.pojo.LicenseInfo;
import com.csft.qloudmarket.market_agent.license.pojo.LicenseList;
import com.csft.qloudmarket.market_agent.license.server.LicenseService;
import com.csft.qloudmarket.market_agent.pollers.event.EventListenerThread;
import com.csft.qloudmarket.market_agent.pollers.runner.IAgentRunner;
import com.csft.qloudmarket.market_agent.pollers.runner.OrderQuestRunner;
import com.csft.qloudmarket.market_agent.pollers.service.OrderpollerService;
import com.csft.qloudmarket.market_agent.registry.RegistryAccessService;
import com.csft.qloudmarket.market_agent.util.Common;
import com.csft.qloudmarket.market_agent.util.FileUtil;
import com.qloudfin.qloudbus.annotation.RequestMapping;
import com.qloudfin.qloudbus.annotation.RequestMethod;
import com.qloudfin.qloudbus.reactive.Callback;
import io.advantageous.qbit.annotation.RequestParam;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/8/8
 * Time: 14:48
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@RequestMapping("/deployer")
public class PurchaseNotificationEndPoint {
    private static Logger logger = LoggerFactory.getLogger(PurchaseNotificationEndPoint.class);
    private static LicenseList licenseList;
    private LicenseService licenseService = new LicenseService();
    private RegistryAccessService registryAccessService = new RegistryAccessService();
    private ChartMuseumService chartMuseumService = new ChartMuseumService();
    private EventListenerThread eventListener= EventListenerThread.getInstance();
    private Common common=new Common();
//    static {
//        OrderListener orderListener=new OrderListener();
//        orderListener.start();
//        DeployListener deployListener=new DeployListener();
//        deployListener.start();
//    }
    static {
        try {
            FileUtil fileUtil=new FileUtil();
            logger.info("\n {} \n" ,fileUtil.checkAndCreatDirector(new StringBuffer(Common.getPropertiesKey(Common.MA_VOLUME_KEY)).append(Common.SEPARATOR).append("temple").toString()));
            logger.info("\n {} \n" , fileUtil.checkAndCreatDirector(new StringBuffer(Common.getPropertiesKey(Common.MA_VOLUME_KEY)).append(Common.SEPARATOR).append("license").toString()));
            logger.info("\n {} \n" ,fileUtil.checkAndCreatDirector(new StringBuffer(Common.getPropertiesKey(Common.MA_VOLUME_KEY)).append(Common.SEPARATOR).append("temple").append(Common.SEPARATOR).append("registry").toString()));
            logger.info("\n {} \n" , fileUtil.checkAndCreatDirector(new StringBuffer(Common.getPropertiesKey(Common.MA_VOLUME_KEY)).append(Common.SEPARATOR).append("temple").append(Common.SEPARATOR).append("charts").toString()));
            licenseList= LicenseList.getInstance();
        } catch (IOException e) {
            logger.info("create ERROR");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            logger.info("create ERROR");
            e.printStackTrace();
        }
    }

     @RequestMapping(value = "/notify", method = RequestMethod.GET)
    public void  acknowledge(final Callback<Map> callback,@RequestParam("orderId") String orderId){
         logger.info("acknowledge : {}",orderId);
         Map result=new HashMap();
      //   OrderpollerService orderpollerService=new OrderpollerService();
     //    orderpollerService.purchaseNotify2(orderId);

//       //  listener
//         //eventListener.
         IAgentRunner orderQuestRunner=null;

           for(IAgentRunner x:eventListener.getRunner()){
               if("X".equals(x.getWorkerStatus())){
                   orderQuestRunner=x;
                   break;
               }
               if("R".equals(x.getWorkerStatus())){
                   orderQuestRunner=x;
                   if(orderId.equals(orderQuestRunner.getName())){
                       result.put("msg",orderId+" is processing");
                       result.put("code","101");
                       callback.accept(result);
                       return;
                   }
               }
           }
           if(orderQuestRunner!=null) {
               orderQuestRunner.setWorkerStatus("D");
               orderQuestRunner.setName(orderId);
           }else {
               orderQuestRunner= new OrderQuestRunner(orderId);
               eventListener.getRunner().add(orderQuestRunner);
           }
         eventListener.startP();
         result.put("msg","order will deploy!");
         result.put("code","000");
         callback.accept(result);
     }


    /**
     * when  user has bought  this interface  acknowledge info
     *
     * @param callback
     * @param req
     */
    //@RequestMapping(value = "/notify", method = RequestMethod.POST)
    public void purchaseNotify(final Callback<Map> callback, final Map<String, Object> req) {
//        logger.info("agent  got a message form service ! {}",req);
//        Map result = null;
//        result = new HashMap();
//        String info="{\"saved\":false}";
//        try {
//            List<Map> products=(List<Map>)req.get("products");
//            List<Map> licensesM= (List) req.get("licenses"); //license 需要 key 以及失效时间
//
//            LicenseInfo licenses=getLicenseInfos(licensesM);
//
//            licenseService.saveLicenses(licenses);
//            for (Map product:products){
//               String productType= (String) product.get("product_type");
//               String productId= (String) product.get("product_id");
//               List<Map> images=(List<Map>) product.get("imageInfos");
//               List<Map> charts=(List<Map>) product.get("chartInfos");
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
//                   String fileName= chartMuseumService.downLoadChartConfig(chartName, chartName); //获取文件名
//                    logger.info("fileName:{}",fileName);
//                   info = chartMuseumService.uploadChartConfig(chartName);
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
//        callback.accept(result);
    }




    private LicenseInfo getLicenseInfos( Map licensesM){
        LicenseInfo licenses =null;
        if(licensesM!=null&&licensesM.size()>0) {
            licenses=new LicenseInfo();
                LicenseInfo licenseInfo = new LicenseInfo();
                licenseInfo.setContent((String) licensesM.get("content"));
                licenseInfo.setExpiredDate((String) licensesM.get("expiredDate"));
                licenseInfo.setAccountId((String) licensesM.get("accountId"));
                licenseInfo.setProductId((String) licensesM.get("productId"));
        }
        return licenses;
    }

}
