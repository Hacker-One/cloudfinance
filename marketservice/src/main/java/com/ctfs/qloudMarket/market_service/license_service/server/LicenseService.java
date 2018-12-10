package com.ctfs.qloudMarket.market_service.license_service.server;

import com.ctfs.qloudMarket.market_service.artifact_service.dao.ArtifactInfoDao;
import com.ctfs.qloudMarket.market_service.artifact_service.pojo.ArtifactInfo;
import com.ctfs.qloudMarket.market_service.license_service.dao.LicenseInfoDao;
import com.ctfs.qloudMarket.market_service.license_service.pojo.LicenseInfo;
import com.ctfs.qloudMarket.market_service.license_service.pojo.LicensePojo;
import com.ctfs.qloudMarket.market_service.provider_service.dao.ProviderInfoDao;
import com.ctfs.qloudMarket.market_service.provider_service.pojo.ProviderInfo;
import com.ctfs.qloudMarket.market_service.util.BaseService;
import com.ctfs.qloudMarket.market_service.util.DESUtils;
import com.ctfs.qloudMarket.market_service.util.JacksonUtils;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
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
    private ArtifactInfoDao artifactInfoDao=new ArtifactInfoDao();
    private ProviderInfoDao providerInfoDao=new ProviderInfoDao();
    private LicenseInfoDao licenseInfoDao=new LicenseInfoDao();
    //public
//    public Map<String,Object> generateLicense(Map<String,Object> elements) throws Exception {
//
//        String artifactId= (String) elements.get("artifactId");
//        String customerName=(String) elements.get("customerName");
//        String validDate=(String) elements.get("validDate");
//        String invalidDate=(String) elements.get("invalidDate");
//        String singnature =(String) elements.get("singnature");
//        // signature will checking before generate des key
//        Connection conn =null;
//        try {
//            conn = this.getConnection();
//            ArtifactInfo artifactInfo = artifactInfoDao.getOneArtifactInfo(conn, artifactId);
//            ProviderInfo providerInfo = providerInfoDao.getProviderInfo(conn, new ProviderInfo(artifactInfo.getProviderId(), null, null, null, null));
//            artifactInfo.setProviderInfo(providerInfo);
//            JSONObject jsonInfo = JSONObject.fromObject(JacksonUtils.obj2json(artifactInfo));
//            JSONObject licenseInfoJson = new JSONObject();
//            licenseInfoJson.put("artifactIdInfo", jsonInfo);
//            licenseInfoJson.put("customerName", customerName);
//            licenseInfoJson.put("validDate", validDate);
//            licenseInfoJson.put("invalidDate", invalidDate);
//            licenseInfoJson.put("singnature", singnature);
//            LicenseInfo licenseInfo = new LicenseInfo();
//            String key = DESUtils.getDecryptString(licenseInfoJson.toString());
//            licenseInfo.setArtifactId(artifactId);
//            licenseInfo.setValidDate(validDate);
//            licenseInfo.setSignature(singnature);
//            licenseInfo.setLicenseKey(key);
//            licenseInfo.setInvalidDate(invalidDate);
//            licenseInfo.setCustomerId(customerName);
//            licenseInfoDao.putLicenseInfo(conn, licenseInfo);
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            conn.close();
//        }
//        return null;
//    }

    public LicensePojo getLicense(String productId,String accountId) throws Exception {
        logger.info("getLicense:{},{}",productId,accountId);
        Connection conn =null;
        LicensePojo licensePojo =null;
        try {
            conn = this.getConnection();
            licensePojo= licenseInfoDao.getLicense(conn, productId,accountId);
        }catch (Exception e){
            logger.info("error:{}",e.getStackTrace());
            throw  e;
        }finally {
           this.closeConnection(conn);
        }
        return licensePojo;
    }


    public LicensePojo  addLicense(Map license) throws Exception {
        logger.info("addLicense:{}",license);
        int result=0;
        LicensePojo licensePojo= checkLicense(license);
        Connection conn=null;
        try {
            if(licensePojo!=null) {
                conn = this.getConnection();
                result = licenseInfoDao.putLicense(conn, licensePojo);
                conn.commit();
            }else {
                licensePojo=null;
            }
        } catch (Exception e) {
            logger.info("error:{}",e.getStackTrace());
            throw  e;
        } finally {
            if(conn!=null) {
                conn.close();
            }
        }
        return licensePojo;
    }

    public  LicensePojo checkLicense(Map license){
        LicensePojo licensePojo=null;
        if(license!=null) {

            String expirationDate = (String) license.get("expiration.date");
            String content = (String) license.get("content");
            String product = (String) license.get("product");
            String account = (String) license.get("account");
            if(StringUtils.isNotEmpty(expirationDate)&&StringUtils.isNotEmpty(content)&&StringUtils.isNotEmpty(product)&&StringUtils.isNotEmpty(account)){
                licensePojo=new LicensePojo();
                licensePojo.setProduct(product);
                licensePojo.setAccount(account);
                licensePojo.setContent(content);
                licensePojo.setExpiredDate(expirationDate);
            }
        }
        return licensePojo;
    }





    public LicenseInfo generateLicense2(Map<String,Object> elements) throws Exception {

        String artifactId= (String) elements.get("product_id");
        String customerId= (String) elements.get("customer_id");
        String order_id=(String) elements.get("order_id");
        String deploy_address=(String) elements.get("deploy_address");
        String validDate=(String) elements.get("validDate");
        String invalidDate=(String) elements.get("invalidDate");
        // signature will checking before generate des key
        Connection conn =null;
        LicenseInfo licenseInfo =null;
        try {
            conn = this.getConnection();
          //  ProviderInfo providerInfo = providerInfoDao.getProviderInfo(conn, new ProviderInfo(providerId, null, null, null, null));
            JSONObject licenseInfoJson = new JSONObject();
            licenseInfoJson.put("artifactId", artifactId);
            licenseInfoJson.put("customerId", customerId);
            licenseInfoJson.put("validDate", validDate);
            licenseInfoJson.put("invalidDate", invalidDate);
            licenseInfoJson.put("singnature", order_id);

           // String key = DESUtils.getEncryptString(licenseInfoJson.toString());
            licenseInfo = new LicenseInfo();
            licenseInfo.setArtifactId(artifactId);
            licenseInfo.setValidDate(validDate);
            licenseInfo.setSignature(order_id);
            licenseInfo.setLicenseKey(licenseInfoJson.toString());
            licenseInfo.setInvalidDate(invalidDate);
            licenseInfo.setCustomerId(customerId);
            //logger.info("license info:{}",licenseInfo);
            int res= licenseInfoDao.putLicenseInfo(conn, licenseInfo);
            conn.commit();
        }catch (Exception e){
            e.printStackTrace();
            throw  e;
        }finally {
            conn.close();
        }
        return licenseInfo;
    }


    public LicenseInfo generateLicense(Map<String,Object> elements) throws Exception {

        String providerId= (String) elements.get("providerId");
        String artifactId= (String) elements.get("artifactId");
        String customerId=(String) elements.get("customerId");
        String validDate=(String) elements.get("validDate");
        String invalidDate=(String) elements.get("invalidDate");
        // signature will checking before generate des key
        Connection conn =null;
        LicenseInfo licenseInfo =null;
        try {
            conn = this.getConnection();
            ProviderInfo providerInfo = providerInfoDao.getProviderInfo(conn, new ProviderInfo(providerId, null, null, null, null));
            JSONObject licenseInfoJson = new JSONObject();
            licenseInfoJson.put("artifactId", artifactId);
            licenseInfoJson.put("customerId", customerId);
            licenseInfoJson.put("validDate", validDate);
            licenseInfoJson.put("invalidDate", invalidDate);
            licenseInfoJson.put("singnature", providerInfo.getSignature());

            String key = DESUtils.getEncryptString(licenseInfoJson.toString());
            licenseInfo = new LicenseInfo();
            licenseInfo.setArtifactId(artifactId);
            licenseInfo.setValidDate(validDate);
            licenseInfo.setSignature(providerInfo.getSignature());
            licenseInfo.setLicenseKey(key);
            licenseInfo.setInvalidDate(invalidDate);
            licenseInfo.setCustomerId(customerId);
            //logger.info("license info:{}",licenseInfo);
            int res= licenseInfoDao.putLicenseInfo(conn, licenseInfo);
            conn.commit();
        }catch (Exception e){
            e.printStackTrace();
            throw  e;
        }finally {
            conn.close();
        }
        return licenseInfo;
    }

    /**
     * 获取单个LicenseInfo
     * @param artifactId
     * @param customerId
     * @return
     * @throws Exception
     */
    public LicenseInfo getLicenseInfo(String artifactId,String customerId) throws Exception {
        LicenseInfo licenseInfo=new LicenseInfo();
        Connection conn =null;
        licenseInfo.setCustomerId(customerId);
        licenseInfo.setArtifactId(artifactId);
        try {
            conn = this.getConnection();
            licenseInfo=   licenseInfoDao.getLicenseInfo(conn,licenseInfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw  e;
        }finally {
            if(conn!=null){
                conn.close();
            }
        }
        return licenseInfo;
    }

}
