package com.ctfs.qloudMarket.market_service.chartmuseum.service;

import com.ctfs.qloudMarket.market_service.util.Common;
import com.ctfs.qloudMarket.market_service.util.HTTPUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/10/20
 * Time: 12:17
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class ChartMuseumService  {
    private static Logger logger= LoggerFactory.getLogger(ChartMuseumService.class);

    // private  static  String BASE_MARKET_RE_URL="http://49.4.85.52:8989/charts/";
    private static String BASE_MARKET_RE_URL= Common.getPropertiesKey(Common.CHART_ADDRESS,"");
    //远端地址
    // private static   String BASE_BANK_PASS_RE_URL="http://114.116.95.141:8989/api/charts";
   // private static   String BASE_BANK_PASS_RE_URL=Common.getPropertiesKey(Common.MA_CM_TU_KEY);
  //  private FileUtil fileUtil=new FileUtil();

    /**
     *
     * @param path
     * @return
     * @throws Exception
     */
    public byte[] downLoadChartConfig(String path) throws Exception {
        byte[] result =null;
        try {
             result=getChartfromMuseum(path);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


//    public String  uploadChartConfig(String fileName){
//        HTTPUtils httpUtils=new HTTPUtils();
//        String basePath = Common.sysPath;
//        StringBuffer strName = new StringBuffer(basePath).append("charts").append(Common.SEPARATOR).append(fileName);
//        // logger.info("SDSADS{}",strName.toString());
//        String info= httpUtils.upLoadFileByAuthored(BASE_BANK_PASS_RE_URL,new File(strName.toString()));
//        return  info;
//    }


    //文件操作



    /**
     *
     * @param path
     * @return
     * @throws Exception
     */
    public byte[] getChartfromMuseum(String path) throws Exception {
        HTTPUtils httpUtils = new HTTPUtils();
        String url = new StringBuffer(BASE_MARKET_RE_URL).append(path).toString();
        logger.info("url:{}", url);
        byte[] result = httpUtils.getFileByteByGet(url, new HashMap<>());
        return result;
    }




}
