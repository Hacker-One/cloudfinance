package com.csft.qloudmarket.market_agent.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/11/8
 * Time: 11:52
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public class ResultMaker {
    public static final String CODE_KEY="code";
    public static final String MSG_KEY="msg";
    public static final String DATA_KEY="data";
    public static final String SUCCESSCODE="000";
    public static final String SUCCESSMSG="OK";

    public static final String FAILDCODE_301="301";
    public static final String FAILDMSG_301="IO EXCEPTION";

    public static final String FAILDCODE_300="300";
    public static final String FAILDMSG_300="EXCEPTION";

    public static Map<String,Object> getResualt(){
        return new HashMap<>();
    }
}
