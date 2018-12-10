package com.ctfs.qloudMarket.market_vendor.license.util;

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
    public static final String MARKET_ADDRESS="marketAddress";
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

}
