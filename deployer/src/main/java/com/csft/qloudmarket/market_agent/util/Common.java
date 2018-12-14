package com.csft.qloudmarket.market_agent.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Map;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/8/10
 * Time: 16:20
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class Common {
    private static Logger logger= LoggerFactory.getLogger(Common.class);
    public static final String SEPARATOR= File.separator;
    public static   String sysPath=Common.class.getResource("/").getPath();
    public static Properties commonValue=new Properties();

    public static final String MA_VOLUME_KEY="marketAgent.VOLUME";
    public static final String MA_CM_SU_KEY="marketAgent.chartMuseum.sourceUrl";
    public static final String MA_CM_TU_KEY="marketAgent.chartMuseum.targetUrl";
    public static final String MA_RE_CATALOG_KEY="marketAgent.registry.CATA_LOGS";
    public static final String MA_RE_MKRE_KEY="marketAgent.registry.BASE_MARKET_RE_URL";
    public static final String MA_RE_BKRE_KEY="marketAgent.registry.BASE_BANK_PASS_RE_URL";

    public static final String MA_RAN_CATALOGNAME_KEY="marketAgent.rancher.CATALOGNAME"; //rancher catalog name
    public static final String MA_RAN_VERSION_KEY="marketAgent.rancher.VERSION";
    public static final String MA_RAN_TOKEN_KEY="marketAgent.rancher.TOKEN";
    public static final String MA_RAN_USERNAME_KEY="marketAgent.rancher.USERNAME";
    public static final String MA_RAN_CSRF_KEY="marketAgent.rancher.CSRF";
    public static final String MA_RAN_PROJECTS_URL_KEY="marketAgent.rancher.PROJECTS.URL";
    public static final String MA_RAN_CREATNAMESPACE_URL_KEY="marketAgent.rancher.CREATNAMESPACE.URL";
    public static final String MA_RAN_CHECKAPP_URL_KEY="marketAgent.rancher.CHECKAPP.URL";
    public static final String MA_RAN_LAUNCHAPP_URL_KEY="marketAgent.rancher.LAUNCHAPP.URL";
    public static final String MA_RAN_REFRESHCATALOG_URL_KEY="marketAgent.rancher.REFRESHCATALOG.URL";
    public static final String MA_RAN_PROJECT_NAME="marketAgent.rancher.PROJECT.NAME";
    public static final String MA_RAN_NAMESPACE="marketAgent.rancher.NAMESPACE";


    public static final String MA_COMMON_MARKETADDRESS="marketAgent.common.MARKETADDRESS";
    public static final String MA_COMMON_CUSTOMERID="marketAgent.common.CUSTOMERID";
    public static final String MA_COMMON_COMPANY="marketAgent.common.COMPANY";
    public static final String MA_COMMON_TOKEN="marketAgent.common.TOKEN";

    public static final String MA_COMMON_API_PRODUCT_ARTIFACT="/artifacts";
    public static final String MA_COMMON_API_ORDER="/orders/";
    public static final String MA_COMMON_API_PRODUCT="/products/";
    public static final String MA_COMMON_API_LICENSE="/licenses";
    public static final String LICENSE_FILE_PATH="marketAgent.common.LicensePATH";
    public static final String MARKET_USERNAME="marketAgent.common.userName";
    public static final String MARKET_PWD="marketAgent.common.PASSWORD";
    public static final String MA_AUTH_BASE_URL="marketAgent.auth.BASEURL";
    public static final String MA_UPLOAD_SIZE="marketAgent.upload.SIZE";

    public static final String MA_COMMON_ARTIFACT_TYPE_DOCKER="docker";
    public static final String MA_COMMON_ARTIFACT_TYPE_CHART="chart";

    public static final String  MA_AUTH="/auth";

    public static  final String MA_DEPLOYER_TYPE_KEY="marketAgent.deploy.TYPE";
    public static  final String MA_DEPLOYER_VERSION="marketAgent.VERSION";
    public static final String AUTH_APIKEY_KEY="auth_apiKey";

    public static final String CHART_ADDRESS="chartAddress";
    public static final String AUTH_BASE_URL="authBaseAddress";
    public static final String API_GATEWAY_ADD_KEY="api_gateway_address";

    public static final String AUTH_USER_PATH="/users";
    public static final String AUTH_TOKEN_PATH="/token";
    public static final String AUTH_TOKEN_JWT_PATH="/jwt";

    static {
        try {
            logger.info("begin to initial ");
            initial();
            logger.info("initial Over");
        } catch (IOException e) {
            logger.info("there as some thing wong");
            e.printStackTrace();
        }
    }
    public static void initial() throws IOException {
        commonValue.load(new FileInputStream(Common.class.getResource("/configure.properties").getPath()));
       logger.info("size {}",commonValue.size());
    }

    public static String getPropertiesKey(String key){
        return commonValue.getProperty(key);
    }

    public static String getPropertiesKey(String key,String def){
        if(StringUtils.isEmpty(commonValue.getProperty(key))){
            return def;
        }
        return commonValue.getProperty(key);
    }
}
