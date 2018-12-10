package com.csft.qloudmarket.market_agent.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/8/10
 * Time: 17:47
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public class SystemProperty {




//    /**
//     * 系统参数变量
//     */
//    private static final Map<String, String> pMap = new HashMap<String, String>();
//
//    /**
//     * 读取系统参数值
//     * @param source properties文件，不带后缀
//     * @param key 系统参数名称
//     * @return String
//     */
//    public static String getProperty(String source, String key) {
//        String object = (String) pMap.get(key);
//        if (object == null) {
//            try {
//                ResourceBundle rb = ResourceBundle.getBundle(source);
//                object = rb.getString(key);
//                pMap.put(key, object);
//            } catch (Exception e) {
//                Logger logger = LoggerFactory.getLogger(SystemProperty.class);
//                logger.error(e.getMessage());
//            }
//        }
//        return object;
//    }
//
//    /**
//     *
//     * @param key
//     * @return
//     */
//    public static String getProperty(String key) {
//        String object = (String) pMap.get(key);
//        if (object == null) {
//            try {
//                ResourceBundle rb = ResourceBundle.getBundle("telcom");
//                object = rb.getString(key);
//                pMap.put(key, object);
//            } catch (Exception e) {
//                Logger logger = LoggerFactory.getLogger(SystemProperty.class);
//                logger.error(e.getMessage());
//            }
//        }
//        return object;
//    }
}
