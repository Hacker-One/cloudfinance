package com.csft.qloudmarket.market_agent.chartmuseum;

import com.csft.qloudmarket.market_agent.util.Common;
import com.csft.qloudmarket.market_agent.util.FileUtil;
import com.csft.qloudmarket.market_agent.util.HTTPUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/8/10
 * Time: 16:31
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class ChartMuseumService  {
    private static Logger logger= LoggerFactory.getLogger(ChartMuseumService.class);

   // private  static  String BASE_MARKET_RE_URL="http://49.4.85.52:8989/charts/";
    private  static  String BASE_MARKET_RE_URL=  Common.getPropertiesKey(Common.MA_CM_SU_KEY);
    //远端地址
   // private static   String BASE_BANK_PASS_RE_URL="http://114.116.95.141:8989/api/charts";
    private static   String BASE_BANK_PASS_RE_URL=Common.getPropertiesKey(Common.MA_CM_TU_KEY);
    private FileUtil fileUtil=new FileUtil();

    /**
     * 获取chart 文件
     * @param path
     * @param name
     * @return
     */
    public String  downLoadChartConfig(String accountId,String type,String path,String name) throws Exception {
        String fileName=null;
        try {
            byte[] result=getChartfromMuseum(path,accountId,type);
            String basePath = Common.getPropertiesKey(Common.MA_VOLUME_KEY);

            StringBuffer strName = new StringBuffer(basePath).append(Common.SEPARATOR).append("temple").append(Common.SEPARATOR).append("charts");
            logger.info("strName: {}",strName);
            fileUtil.checkAndCreatDirector(strName.toString());
            logger.info("begin download Chart {}",strName.toString());
            fileName=   fileUtil.writeFile(strName.toString(),name, result);
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    public String  uploadChartConfig(String fileName){
        HTTPUtils httpUtils=new HTTPUtils();
        String basePath = Common.getPropertiesKey(Common.MA_VOLUME_KEY);
        StringBuffer strName = new StringBuffer(basePath).append(Common.SEPARATOR).append("temple").append(Common.SEPARATOR).append("charts").append(Common.SEPARATOR).append(fileName);
       // logger.info("SDSADS{}",strName.toString());
        String info= httpUtils.upLoadFileByAuthored(BASE_BANK_PASS_RE_URL,new File(strName.toString()));
        return  info;
    }


    //文件操作



    /**
     *
     * @param path
     * @return
     * @throws Exception
     */
    public byte[] getChartfromMuseum(String path,String accountID,String type) throws Exception {
        HTTPUtils httpUtils = new HTTPUtils();
        String url = new StringBuffer(BASE_MARKET_RE_URL).append("?accountid=").append(accountID).append("&type=").append(type).append("&path=").append(path).toString();
        logger.info("url:{}", url);
        byte[] result = httpUtils.getFileByteByGet(url, new HashMap<>(),new HashMap<String,String>());
        return result;
    }




}
