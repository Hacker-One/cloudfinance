package com.ctfs.qloudMarket.market_service.artifact_service.endPoint;

import com.ctfs.qloudMarket.market_service.artifact_service.pojo.ArtifactInfo;
import com.ctfs.qloudMarket.market_service.artifact_service.pojo.ArtifactPojo;
import com.ctfs.qloudMarket.market_service.artifact_service.service.ArtifactService;
import com.ctfs.qloudMarket.market_service.chartmuseum.service.ChartMuseumService;
import com.qloudfin.qloudbus.annotation.RequestMapping;
import com.qloudfin.qloudbus.annotation.RequestMethod;
import com.qloudfin.qloudbus.annotation.RequestParam;
import com.qloudfin.qloudbus.annotation.http.ResponseHeader;
import com.qloudfin.qloudbus.annotation.http.ResponseHeaders;
import com.qloudfin.qloudbus.reactive.Callback;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/8/24
 * Time: 17:33
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@RequestMapping("/")
public class ArtifactServiceEndPoint {
    private static Logger logger= LoggerFactory.getLogger(ArtifactServiceEndPoint.class);
    private ArtifactService artifactService=new ArtifactService();
    private ChartMuseumService chartMuseumService=new ChartMuseumService();

    //    @RequestMapping(value = "/addArtifact", method = RequestMethod.POST)
//    public void addArtifact(final Callback<Map> callback, final Map<String,Object>  requestBody){
//        Map<String,String> result=new HashMap<>();
//        try {
//            ArtifactInfo artifactInfo=new ArtifactInfo();
//            artifactInfo.setArtifactId((String) requestBody.get("artifaceId"));
//            artifactInfo.setProviderId((String) requestBody.get("providerId"));
//            artifactInfo.setVersion((String) requestBody.get("version"));
//            artifactInfo.setPrice(BigDecimal.valueOf(Long.parseLong((String) requestBody.get("price"))));
//            artifactInfo.setName((String) requestBody.get("name"));
//            artifactInfo.setLogoPath("/image/artifact123.png");
//            artifactInfo.setIntroduceInfo("test");
//            artifactInfo.setImagePath((String) requestBody.get("imagePath"));
//            artifactInfo.setChartPath((String) requestBody.get("chartPath"));
//            artifactService.addArtifactInfo(artifactInfo);
//            result.put("msg","success");
//            callback.accept(result);
//        } catch (Exception e) {
//            e.printStackTrace();
//            result.put("msg","fail");
//            callback.accept(result);
//        }
//
//    }
  @RequestMapping(value = "/artifacts", method = RequestMethod.GET)
  public void getArtifact(final Callback<Map> callback,  @RequestParam("product") String product) {
      logger.info("getArtifact:{}",product);
      Map result=new HashMap();
      try {
        List<ArtifactPojo> data=artifactService.queryArtifact(product);
        result.put("code","000");
        result.put("msg","succeed");
        result.put("data",data);
      } catch (Exception e) {
          e.printStackTrace();
          logger.info("error:{}",e.getStackTrace());
          result.put("code","001");
          result.put("msg","error");
      }
      callback.accept(result);
  }




    @RequestMapping(value = "/artifacts", method = RequestMethod.GET)
    public void getArtifactPremission(final Callback<Map> callback,  @RequestParam("accountid") String accountid, @RequestParam("type") String type, @RequestParam("path") String path) {
        logger.info("\n getArtifactPremission:{},{},{}",accountid,type,path);
        Map result=new HashMap();
        try {
            int r=artifactService.checkAccountArtifactPermission(accountid,path,type);
            if(r>0) {
                    result.put("code", "201");
                    result.put("msg", "succeed");
            }else {
                result.put("code", "404");
                result.put("msg", "fail");
            }
            result.put("data", new ArrayList<>());
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("error:{}",e.getStackTrace());
            result.put("code","001");
            result.put("msg","error");
        }
        callback.accept(result);
    }


    @RequestMapping(value = "/artifacts/charts", method = RequestMethod.GET, contentType = "application/octet-stream")
    @ResponseHeaders({ @ResponseHeader(name = "X-Qloud", value = "file"),
                       @ResponseHeader(name = "Content-Disposition", value = "attachment;filename=chart.tgz") })
    public void getChartPremission(final Callback<byte []> callback,  @RequestParam("accountid") String accountid, @RequestParam("type") String type, @RequestParam("path") String path)  throws UnsupportedEncodingException {
        logger.info("\n getChartPremission:{},{},{}",accountid,type,path);
        byte [] result=null ;
        try {
            if("chart".equals(type)) {
                int r = artifactService.checkAccountArtifactPermission(accountid, path, type);
                if (r > 0) {
                    result = chartMuseumService.downLoadChartConfig(path);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("error:{}",e.getStackTrace());
        }
        if(result!=null) {
            callback.accept(result);
        }
    }

}
