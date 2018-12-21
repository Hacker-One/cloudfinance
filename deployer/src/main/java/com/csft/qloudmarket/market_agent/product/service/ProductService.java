package com.csft.qloudmarket.market_agent.product.service;

import com.csft.qloudmarket.market_agent.license.pojo.LicenseInfo;
import com.csft.qloudmarket.market_agent.license.pojo.LicenseList;
import com.csft.qloudmarket.market_agent.license.server.LicenseService;
import com.csft.qloudmarket.market_agent.product.pojo.ArtifactPojo;
import com.csft.qloudmarket.market_agent.util.Common;
import com.csft.qloudmarket.market_agent.util.HTTPUtils;
import com.csft.qloudmarket.market_agent.util.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/10/24
 * Time: 9:46
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class ProductService {
    private static Logger logger= LoggerFactory.getLogger(ProductService.class);
    private HTTPUtils httpUtils=new HTTPUtils();


    public List<ArtifactPojo> getArtifacts(String productId) throws Exception {
        logger.info("query artifacts {}",productId);
        List<ArtifactPojo> rs = null;
        String info=queryArtifact(productId);
        Map artifacts= JacksonUtils.json2map(info);
        String status= (String) artifacts.get("code");
        if("000".equals(status)){
            List<Map> data= ( List<Map>) artifacts.get("data");
            if(data!=null&&data.size()>0){
                logger.info("\n\n\ndata {}",data);
                rs=new ArrayList<>();
                for(Map<String,Object> item:data) {
                    ArtifactPojo orderPojo = JacksonUtils.map2pojo(item, ArtifactPojo.class);
                    rs.add(orderPojo);
                }
            }
        }
        logger.info("check license");
        return rs;
    }

    private boolean checkArtifact(ArtifactPojo artifactPojo){
        boolean result=false;
        if(artifactPojo!=null){
            if((artifactPojo.getId()>0)&&
                    StringUtils.isNotEmpty(artifactPojo.getName())&&
                    StringUtils.isNotEmpty(artifactPojo.getUrl())&&
                    StringUtils.isNotEmpty(artifactPojo.getProductId())&&
                    (artifactPojo.getType()>0)&&
                    StringUtils.isNotEmpty(artifactPojo.getTypeName()) ){
                return  true;
            }
        }
        return result;
    }


    private String queryArtifact(String productId) throws Exception {
        String result=null;
        StringBuffer address=new StringBuffer(Common.getPropertiesKey(Common.MA_COMMON_MARKETADDRESS));
        address.append(Common.MA_COMMON_API_PRODUCT).append(productId).append(Common.MA_COMMON_API_PRODUCT_ARTIFACT);
        String url= address.toString();
        Map header=new HashMap();
        header.put(HTTPUtils.API_KEY,Common.getPropertiesKey(Common.AUTH_APIKEY_KEY));
        result=  httpUtils.httpsRequest(url,HTTPUtils.METHOD_GET,header,null);
        return result;
    }
}
