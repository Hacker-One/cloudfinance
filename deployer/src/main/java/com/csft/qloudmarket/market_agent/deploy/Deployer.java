package com.csft.qloudmarket.market_agent.deploy;

import com.csft.qloudmarket.market_agent.chartmuseum.ChartMuseumService;
import com.csft.qloudmarket.market_agent.license.pojo.LicenseInfo;
import com.csft.qloudmarket.market_agent.license.server.LicenseService;
import com.csft.qloudmarket.market_agent.pollers.queue.DeployQueue;
import com.csft.qloudmarket.market_agent.rancher.service.RancherService;
import com.csft.qloudmarket.market_agent.registry.RegistryAccessService;
import com.csft.qloudmarket.market_agent.util.Common;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/9/19
 * Time: 20:11
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public class Deployer implements java.lang.Runnable {
    private static Logger logger = LoggerFactory.getLogger(Deployer.class);
    private LicenseService licenseService = new LicenseService();
    private RegistryAccessService registryAccessService = new RegistryAccessService();
    private ChartMuseumService chartMuseumService = new ChartMuseumService();
    private RancherService rancherService=new RancherService();
    private Common common=new Common();
    private Map<String,Object> operData;

    public Deployer(Map operData){
       this.operData=operData;
    }


    public Map<String,Object> purchaseNotify(Map<String, Object> req) {
//        logger.info("agent  got a message form service ! {}",req);
//        Map result = null;
//        result = new HashMap();
//        String info="{\"saved\":false}";
//        StringBuffer processMsg=new StringBuffer("begin deploy ....\n");
//        try {
//
//            List<Map> products=(List<Map>)req.get("products");
//            List<Map> licensesM= (List) req.get("licenses"); //license 需要 key 以及失效时间
//
//
//            List<LicenseInfo> licenses=getLicenseInfos(licensesM);
//            processMsg.append("begin save license .. \n ");
//
//            licenseService.saveLicenses(licenses);                //获取license 保存license
//
//            String appName=null;
//            String version="1.0.0";
//
//            for (Map product:products){  //循环遍历发布产品
//
//                processMsg.append("begin deploy products...\n ");
//
//                String productType= (String) product.get("product_type");
//                String productId= (String) product.get("product_id");
//
//                List<Map> images=(List<Map>) product.get("imageInfos");
//                List<Map> charts=(List<Map>) product.get("chartInfos");
//
//                processMsg.append("begin deploy images...\n ");
//
//                for(Map<String,Object> imageInfo:images) {
//                    processMsg.append("images ");
//                    String imageName=(String)imageInfo.get("imageName");
//                    String tag=(String)imageInfo.get("tag");
//
//                    processMsg.append(imageName).append(":").append(tag).append("\n");
//
//                    logger.info("image :{},tag :{}",imageName,tag);
//                    if (imageInfo != null && (StringUtils.isNotEmpty(imageName))&&(StringUtils.isNotEmpty(tag))) {
//                        JSONObject imageOper = registryAccessService.imagePurchaseProgress(imageName, tag); //购买后触发image 文件上传
//
//                        processMsg.append(imageOper);
//                        processMsg.append("\n");
//                    }
//                }
//
//
//                processMsg.append("begin deploy charts...\n ");
//
//
//                for(Map<String,Object> chartInfo:charts){
//
//                    String chartName= (String) chartInfo.get("chartName");
//
//                    processMsg.append(chartName).append("\n");
//
////                    logger.info("%%%%%%chartName:{}",chartName);
//                    String tmp= chartName.substring(0,chartName.lastIndexOf("."));
//                    String x[]=tmp.split("-");
//                    appName=x[0];
//                    version=x[1];
//                    logger.info("@@@chartInfo.name:{}",chartName);
//                    String fileName= chartMuseumService.downLoadChartConfig(chartName, chartName); //获取文件名
//                    logger.info("fileName:{}",fileName);
//                    info = chartMuseumService.uploadChartConfig(chartName);
//                    logger.info("chart museum info:{}", info);
//                }
//
//                 // Map audeployResult = rancherService.autoDeploy("test", "grafana", appName, "qloud", version, "dest-market");  //自动部署
//                //logger.info("deploy Result:{}",audeployResult);
//            }
//            JSONObject infoJson=JSONObject.fromObject(info);
//            String saved =infoJson.get("saved")!=null?Boolean.toString((Boolean) infoJson.get("saved")):"";
//            if ("true".equals(saved)) {
//
//                Map deployData=new HashMap();
//                deployData.put("nameSpace","test");
//                deployData.put("serverName",appName);
//                deployData.put("appName",appName);
//                deployData.put("projectName","qloud");
//                deployData.put("version",version);
//                deployData.put("catalog","dest-market");
//                deployData.put("msg",processMsg.toString());
//
//                DeployQueue.deployQueue.put(deployData);
//
//                result.put("succeed", true);
//                result.put("msg", "the service will  deploy on your cloud");
//            } else {
//
//                result.put("succeed", false);
//                result.put("msg", "ERRORRRRRRR");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.info("ERRORRDSADSA{}",e.getMessage());
//            result.put("succeed", false);
//            result.put("msg", "exeption on your programs.");
//        }
      return null;
    }

    private List<LicenseInfo> getLicenseInfos( List<Map> licensesM){
        List<LicenseInfo> licenses =null;
//        if(licensesM!=null&&licensesM.size()>0) {
//            licenses=  new ArrayList<>();
//            for (Map<String, Object> item : licensesM) {
//                LicenseInfo licenseInfo = new LicenseInfo();
//                licenseInfo.setLicenseKey((String) item.get("licenseKey"));
//                licenseInfo.setInvalidDate((String) item.get("invalidDate"));
//                licenseInfo.setArtifaceId((String) item.get("artifactId"));
//                licenseInfo.setValidDate((String) item.get("validDate"));
//                licenseInfo.setCustomerId((String) item.get("customerId"));
//                licenseInfo.setSignature((String) item.get("signature"));
//                licenses.add(licenseInfo);
//            }
//        }
        return licenses;
    }

    @Override
    public void run() {
         logger.info("operdate :{}",operData);
         Map data= purchaseNotify(operData);
         logger.info("result data:{}",data);
    }
}
