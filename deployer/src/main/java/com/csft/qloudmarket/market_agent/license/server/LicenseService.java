package com.csft.qloudmarket.market_agent.license.server;

import com.csft.qloudmarket.market_agent.license.dao.LicenseInfoDao;
import com.csft.qloudmarket.market_agent.license.pojo.LicenseInfo;
import com.csft.qloudmarket.market_agent.license.pojo.LicenseList;
import com.csft.qloudmarket.market_agent.order.pojo.OrderPojo;
import com.csft.qloudmarket.market_agent.util.*;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/8/7
 * Time: 9:53
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class LicenseService extends BaseService {
    private static Logger logger= LoggerFactory.getLogger(LicenseService.class);
   // private static LicenseList licenseList;
    private HTTPUtils httpUtils=new HTTPUtils();


 //   private LicenseInfoDao licenseInfoDao=new LicenseInfoDao();
    //public
//    public int saveLicense(LicenseInfo licenseInfo) throws Exception {
//        logger.info("begin save the license {}",licenseInfo);
//        Connection conn =null;
//        int result=0;
//        try {
//            conn = this.getConnection();
//            result=   licenseInfoDao.putLicenseInfo(conn,licenseInfo);
//            conn.commit();
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            if (conn!=null)
//            conn.close();
//        }
//        return result;
//    }
//
    public boolean saveLicenses(LicenseInfo licenseInfos) throws Exception {
        logger.info("begin save the license {}",licenseInfos);
        boolean result=false;
        try {
            LicenseList licenseList=LicenseList.getInstance();
            String productId=licenseInfos.getProduct();
            String accountId=licenseInfos.getAccount();
            String key=productId+"_"+accountId;
            licenseList.putLicense(key,licenseInfos);
            result=true;
        }catch (Exception e){
            e.printStackTrace();
            throw  e;
        }
        return result;
    }


    public LicenseInfo getLicense(String productId,String accountId) throws Exception {
        logger.info("begin queryOrder");
        LicenseInfo rs = null;
        String info=queryLicense(productId,accountId);
        Map order= JacksonUtils.json2map(info);
        String status= (String) order.get("code");
        if("000".equals(status)){
            Map data= (Map) order.get("data");
            if(data!=null){
                LicenseInfo orderPojo= JacksonUtils.map2pojo(data,LicenseInfo.class);
                if(checkLicenseData(orderPojo)){
                    rs=orderPojo;
                }
            }
        }
        logger.info("check license");
        return rs;
    }

    private boolean checkLicenseData(LicenseInfo orderPojo){
        boolean result=false;
        if(orderPojo!=null){
            if((orderPojo.getId()>0)&&
                    StringUtils.isNotEmpty(orderPojo.getAccount())&&
                    StringUtils.isNotEmpty(orderPojo.getProduct())&&
                    StringUtils.isNotEmpty(orderPojo.getExpiredDate())&&
                    StringUtils.isNotEmpty(orderPojo.getContent())){
                return  true;
            }
        }
        return result;
    }


    public String queryLicense(String productId,String accountId) throws Exception {
        String result=null;
        StringBuffer address=new StringBuffer(Common.getPropertiesKey(Common.MA_COMMON_MARKETADDRESS));
        address.append(Common.MA_COMMON_API_LICENSE).append("?product=").append(productId).append("&account=").append(accountId);
        String url= address.toString();
        result=  httpUtils.get(url,null,null);
        logger.info("{},@ {}",url,result);
        return result;
    }

//
//    /**
//     *
//     * @param key
//     * @param validDate
//     * @return
//     * @throws SQLException
//     */
//    public boolean checkLicense(String key,String validDate) throws SQLException {
//        Connection conn =null;
//        int result=0;
//        try {
//            conn = this.getConnection();
//            result= licenseInfoDao.checkLicenseInfo(conn,key,validDate);
//        }catch (Exception e){
//            e.printStackTrace();
//            return false;
//        }finally {
//            conn.close();
//        }
//        return result>0;
//    }
}
