package com.ctfs.qloudMarket.market_service.util;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
    public static final String CHART_ENCODE="utf-8";
    public static final String PURCHASR_EVENT_TOP_KEY="marketAgent.purchaseEvent.topic";
    public static final String CHART_ADDRESS="chartAddress";
    public static final String AUTH_BASE_URL="authBaseAddress";
    public static final String AUTH_APIKEY_KEY="auth_apiKey";

    public static final String AUTH_USER_PATH="/users";
    public static final String AUTH_TOKEN_PATH="/token";
    public static final String AUTH_TOKEN_JWT_PATH="/jwt";

    public static final String API_GATEWAY_ADD_KEY="api_gateway_address";
    public static final String REDIS_HOST="redis_host";
    public static final String REDIS_PORT="redis_port";
    public static final String REDIS_URI="redis_uri";
    public static final String REDIS_TIMEOUT="redis_timeout";

    public static final String SAMPLE_ENVIROMENT_DEPLOYER_ADDRESS_KEY="sample_enviroment_deployer_address";

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

    public static String getPropertiesKey(String key,String def){
        return commonValue.getProperty(key,def);
    }

    public static String getPropertiesKey(String key){
        return commonValue.getProperty(key);
    }

}
