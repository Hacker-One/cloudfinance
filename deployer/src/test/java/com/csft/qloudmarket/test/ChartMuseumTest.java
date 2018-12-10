package com.csft.qloudmarket.test;

import com.csft.qloudmarket.market_agent.chartmuseum.ChartMuseumService;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/8/10
 * Time: 15:41
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public class ChartMuseumTest {
    private static Logger logger = LoggerFactory.getLogger(ChartMuseumTest.class);
    private ChartMuseumService chartMuseumService=new ChartMuseumService();

    @Test
    public void testGetChartFile() throws Exception {
     //  String info= chartMuseumService.downLoadChartConfig("grafana-0.1.0.tgz","grafana-0.1.0.tgz");
        String   info= chartMuseumService.uploadChartConfig("grafana-0.1.0.tgz");
       logger.info("info:{}",info);
    }
    @Test
    public void testSubString(){
        String a="d:/sdsa/dsad/abc";
        logger.info(a.substring(a.lastIndexOf("/")+1));
        logger.info((new File("E:\\a")).getName());
    }
    @Test
    public void converToJsonObj(){
        String info="{\n" +
                "    \"errors\": [\n" +
                "        {\n" +
                "            \"code\": \"BLOB_UPLOAD_UNKNOWN\",\n" +
                "            \"message\": \"blob upload unknown to registry\",\n" +
                "            \"detail\": {}\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        logger.info(String.valueOf(JSONObject.fromObject(info)));
    }
}
