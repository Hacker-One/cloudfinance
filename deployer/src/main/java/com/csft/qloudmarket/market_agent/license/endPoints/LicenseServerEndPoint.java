package com.csft.qloudmarket.market_agent.license.endPoints;

import com.csft.qloudmarket.market_agent.license.pojo.LicenseInfo;
import com.csft.qloudmarket.market_agent.license.pojo.LicenseList;
import com.csft.qloudmarket.market_agent.license.server.LicenseService;
import com.csft.qloudmarket.market_agent.pollers.runner.IAgentRunner;
import com.csft.qloudmarket.market_agent.pollers.runner.OrderQuestRunner;
import com.csft.qloudmarket.market_agent.util.Common;
import com.csft.qloudmarket.market_agent.util.ResultMaker;
import com.qloudfin.qloudbus.annotation.PathVariable;
import com.qloudfin.qloudbus.annotation.RequestMapping;
import com.qloudfin.qloudbus.annotation.RequestMethod;
import com.qloudfin.qloudbus.reactive.Callback;
import io.advantageous.qbit.annotation.RequestParam;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/11/8
 * Time: 11:39
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@RequestMapping("/")
public class LicenseServerEndPoint {
    private static Logger logger = LoggerFactory.getLogger(LicenseServerEndPoint.class);
    private LicenseService licenseService = new LicenseService();


    @RequestMapping(value = "/license/{licenseKey}", method = RequestMethod.GET)
    public void  getLicense(final Callback<Map> callback, @PathVariable("licenseKey")String key){
        logger.info("getLicense : {}",key);
        Map result= ResultMaker.getResualt();
        try {
            LicenseList licenseList=LicenseList.getInstance();
            LicenseInfo license= licenseList.getLicense(key);
            if(license!=null){

                result.put(ResultMaker.MSG_KEY,ResultMaker.SUCCESSMSG);
                result.put(ResultMaker.CODE_KEY,ResultMaker.SUCCESSCODE);
                result.put(ResultMaker.DATA_KEY,license);
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("ERROR :{}",e.getMessage());
            result.put(ResultMaker.MSG_KEY,ResultMaker.FAILDCODE_301);
            result.put(ResultMaker.CODE_KEY,ResultMaker.FAILDMSG_301);
            result.put(ResultMaker.DATA_KEY,null);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            logger.info("ERROR :{}",e.getMessage());
            result.put(ResultMaker.MSG_KEY,ResultMaker.FAILDCODE_300);
            result.put(ResultMaker.CODE_KEY,ResultMaker.FAILDMSG_300);
            result.put(ResultMaker.DATA_KEY,null);
        }
        callback.accept(result);
    }

    /**
     * 刷新所有的license.
     * @param callback
     */
    @RequestMapping(value = "/license/refesh", method = RequestMethod.GET)
    public void  refreshLicense(final Callback<Map> callback){
        logger.info("refreshLicense : {}", Common.getPropertiesKey(Common.MA_COMMON_CUSTOMERID));
        Map result= ResultMaker.getResualt();
        try {
            LicenseList licenseList=LicenseList.getInstance();
            licenseList.clearAll();
            /**
             * 调用查询用户所有license 接口查询
             */
            result.put(ResultMaker.MSG_KEY,ResultMaker.SUCCESSMSG);
            result.put(ResultMaker.CODE_KEY,ResultMaker.SUCCESSCODE);
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("ERROR :{}",e.getMessage());
            result.put(ResultMaker.MSG_KEY,ResultMaker.FAILDCODE_301);
            result.put(ResultMaker.CODE_KEY,ResultMaker.FAILDMSG_301);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            logger.info("ERROR :{}",e.getMessage());
            result.put(ResultMaker.MSG_KEY,ResultMaker.FAILDCODE_300);
            result.put(ResultMaker.CODE_KEY,ResultMaker.FAILDMSG_300);
        }
        callback.accept(result);
    }
}
