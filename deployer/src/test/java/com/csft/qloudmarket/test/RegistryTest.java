package com.csft.qloudmarket.test;

import com.csft.qloudmarket.market_agent.pollers.service.OrderpollerService;
import com.csft.qloudmarket.market_agent.registry.RegistryAccessService;
import com.csft.qloudmarket.market_agent.util.Common;
import com.csft.qloudmarket.market_agent.util.FileUtil;
import com.csft.qloudmarket.market_agent.util.HTTPUtils;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/11/5
 * Time: 15:20
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public class RegistryTest {
    private static Logger logger = LoggerFactory.getLogger(RegistryTest.class);
   private   RegistryAccessService registryAccessService = new RegistryAccessService();
    @Test
    public void checkQueryCharts() {
        try {
            FileUtil fileUtil=new FileUtil();
            logger.info(fileUtil.checkAndCreatDirector(Common.getPropertiesKey(Common.MA_VOLUME_KEY)));
            logger.info( fileUtil.checkAndCreatDirector(new StringBuffer(Common.getPropertiesKey(Common.MA_VOLUME_KEY)).append(Common.SEPARATOR).append("temple").toString()));
            logger.info(fileUtil.checkAndCreatDirector(new StringBuffer(Common.getPropertiesKey(Common.MA_VOLUME_KEY)).append(Common.SEPARATOR).append("temple").append(Common.SEPARATOR).append("registry").toString()));
            logger.info( fileUtil.checkAndCreatDirector(new StringBuffer(Common.getPropertiesKey(Common.MA_VOLUME_KEY)).append(Common.SEPARATOR).append("temple").append(Common.SEPARATOR).append("charts").toString()));
            logger.info( fileUtil.checkAndCreatDirector(new StringBuffer(Common.getPropertiesKey(Common.MA_VOLUME_KEY)).append(Common.SEPARATOR).append("license").toString()));
            JSONObject imageOper= registryAccessService.imagePurchaseProgress("qloudtest/standalone-chrome","latest");
            logger.info("ssimage operating  over result Info: {}\n", imageOper);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testString(){
        String url="qloud-dop-1.0.1.tgz";
        String tmp= url.substring(0,url.lastIndexOf("."));
        String appName= tmp.substring(0,tmp.lastIndexOf("-"));
        String version= tmp.substring(tmp.lastIndexOf("-")+1);
        logger.info("@@@chartInfo.name:{}@@@{}",appName,version);
    }

    @Test
    public void ordernotify(){
        OrderpollerService orderpollerService=new OrderpollerService();
        Map data=new HashMap();
        data.put("succeed",true);
        data.put("data","1232321321test");
        try {
            orderpollerService.notifyServer("2a1c298d-02b8-4701-9653-0f57a15f4551",data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void splitFile() throws IOException {

        JSONObject result = null;
        HTTPUtils httpUtils = new HTTPUtils();
        FileInputStream fStream=null;
        String name="qlouddop/gerrit";
        try {
            JSONObject obj = registryAccessService.applyUpload(name); // apply and get uuid
            String message = (String) obj.get("message");
            String location = (String) obj.get("Location");
            logger.info("{},{}",message,location);
//            if ("Accepted".equals(message.trim())) {
//                File file=new File("E:\\qcloudCode\\qloudMarket\\market-deployer\\target\\test-classes\\temple\\registry\\qlouddop\\gerrit\\2.15.3\\37_sha256_c432c1aab6335df5a9ff6237f8d19627f95ea7dc3f5709c555b2a28cd8df4d0a");
//                String layerName = registryAccessService.toLayerName(file.getName());
//                fStream = new FileInputStream(file);
//                int bufferSize = 1024*1024;
//                byte[] buffer = new byte[bufferSize];
//                int length = -1;
//                int begin = 0;
//                int end =0;
//             //   long readpoint=0;
//                boolean isLast=false;
//
//                logger.info("file length:{}",location);
//
//               while ((length = fStream.read(buffer)) != -1) {
//                    logger.info("\n******************************************begin:{}",length);
//                    end=begin+length-1;
//                   logger.info("\n end:{}\n length:{}",end,length);
//                    if(length<bufferSize&&end==(file.length()-1)){
//                        isLast=true;
//                        location = registryAccessService.appednDigets(location, layerName);
//                    }
//                    logger.info("\nlocation:{}\nlength:{}\nbegin:{}\nend:{}\nisLast:{}",location,length,begin,end,isLast);
//                     result = httpUtils.uploadLayerChunk(location, length, begin, end, buffer, isLast,length);
//                     begin=end+1;
//                   logger.info("result:{}", result);
//                   location= (String) ((List)result.get("location")).get(0);
//                    logger.info("******************************************end:{}",length);
//
//                }
          //  }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.info("EXCEPTION:{}",e.getStackTrace());
           throw e;
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("EXCEPTION:{}",e.getStackTrace());
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(fStream!=null){
                fStream.close();
            }
        }
    }


}
