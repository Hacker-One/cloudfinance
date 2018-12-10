package com.csft.qloudmarket.test;

import com.csft.qloudmarket.market_agent.rancher.service.RancherService;
import com.csft.qloudmarket.market_agent.util.Common;
import com.csft.qloudmarket.market_agent.util.HTTPUtils;
import com.csft.qloudmarket.market_agent.util.JacksonUtils;
import io.netty.handler.codec.http.HttpUtil;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpURL;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/9/17
 * Time: 18:53
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public class RancherTest {
    private static Logger logger = LoggerFactory.getLogger(RancherTest.class);
    private static HTTPUtils httpUtils = new HTTPUtils();

    @Test
    public void checkQueryCharts() {
        String url = "https://192.168.1.164/v3/template/Chartrepo-gerrit-1.0.1";
        Map<String, String> header = new HashMap<>();
        /**
         * [{"key":"Accept-Language","value":"en-US,en;q=0.5",
         *   "description":"",
         *   "enabled":true},
         *   {"key":"content-type","value":"application/json","description":"","enabled":true},
         *   {"key":"x-api-action-links","value":"actionLinks","description":"","enabled":true},
         *   {"key":"x-api-no-challenge","value":"true","description":"","enabled":true},
         *   {"key":"x-api-csrf","value":"8e852d0359","description":"","enabled":true},
         *   {"key":"Cookie","value":"R_USERNAME=chenxiaoji; CSRF=8e852d0359; R_SESS=token-r7j2k:s7qmhtjtcr86js2wnfbf6vs8fq8fcnnktntw78b6rd77zhlcbqzkv9","description":"","enabled":true}]
         */

        String csrf = Common.getPropertiesKey(Common.MA_RAN_CSRF_KEY);
        String userName = Common.getPropertiesKey(Common.MA_RAN_USERNAME_KEY);
        String token = Common.getPropertiesKey(Common.MA_RAN_TOKEN_KEY);
        header.put("Accept-Language", "en-US,en;q=0.5");
        header.put("content-type", "application/json");
        header.put("x-api-action-links", "actionLinks");
        header.put("x-api-no-challenge", "true");
        header.put("x-api-csrf", csrf);
        header.put("Cookie", "R_USERNAME=" + userName + "; CSRF=" + csrf + "; R_SESS=" + token);
        try {
            String result = httpUtils.httpsRequest(url, HTTPUtils.METHOD_GET, header, null);
            logger.info("result:{}", result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //https://114.116.53.194:8443/v3/templateVersions/dest-market-grafana-1.14.0?rancherVersion=v2.0.6
    @Test
    public void checkQueryCharts2() {
        String url = Common.getPropertiesKey(Common.MA_RAN_CHECKAPP_URL_KEY);
        url = url.replace("{{appName}}", "chartrepo-gerrit-1.0.1");
        url = url.replace("{{rancherVersion}}", Common.getPropertiesKey(Common.MA_RAN_VERSION_KEY));
        Map<String, String> header = new HashMap<>();
        /**
         * [{"key":"Accept-Language","value":"en-US,en;q=0.5",
         *   "description":"",
         *   "enabled":true},
         *   {"key":"content-type","value":"application/json","description":"","enabled":true},
         *   {"key":"x-api-action-links","value":"actionLinks","description":"","enabled":true},
         *   {"key":"x-api-no-challenge","value":"true","description":"","enabled":true},
         *   {"key":"x-api-csrf","value":"8e852d0359","description":"","enabled":true},
         *   {"key":"Cookie","value":"R_USERNAME=chenxiaoji; CSRF=8e852d0359; R_SESS=token-r7j2k:s7qmhtjtcr86js2wnfbf6vs8fq8fcnnktntw78b6rd77zhlcbqzkv9","description":"","enabled":true}]
         */

        String csrf = Common.getPropertiesKey(Common.MA_RAN_CSRF_KEY);
        String userName = Common.getPropertiesKey(Common.MA_RAN_USERNAME_KEY);
        String token = Common.getPropertiesKey(Common.MA_RAN_TOKEN_KEY);
        header.put("Accept-Language", "en-US,en;q=0.5");
        header.put("content-type", "application/json");
        header.put("x-api-action-links", "actionLinks");
        header.put("x-api-no-challenge", "true");
        header.put("x-api-csrf", csrf);
        header.put("Cookie", "R_USERNAME=" + userName + "; CSRF=" + csrf + "; R_SESS=" + token);
        try {
            logger.info("url:{}",url);
            String result = httpUtils.httpsRequest(url, HTTPUtils.METHOD_GET, header, null);
            logger.info("result:{}", result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取所有project
     */
    @Test
    public void getProjects() {
        String url = Common.getPropertiesKey(Common.MA_RAN_PROJECTS_URL_KEY);
//       url= url.replace("{{appName}}","dest-market-grafana-1.14.0");
//       url= url.replace("{{rancherVersion}}",Common.MA_RAN_VERSION_KEY);
        Map<String, String> header = new HashMap<>();

        String csrf = Common.getPropertiesKey(Common.MA_RAN_CSRF_KEY);
        String userName = Common.getPropertiesKey(Common.MA_RAN_USERNAME_KEY);
        String token = Common.getPropertiesKey(Common.MA_RAN_TOKEN_KEY);
        header.put("Accept-Language", "en-US,en;q=0.5");
        header.put("content-type", "application/json");
        header.put("x-api-action-links", "actionLinks");
        header.put("x-api-no-challenge", "true");
        header.put("x-api-csrf", csrf);
        header.put("Cookie", "R_USERNAME=" + userName + "; CSRF=" + csrf + "; R_SESS=" + token);
        try {
            String result = httpUtils.httpsRequest(url, HTTPUtils.METHOD_GET, header, null);
            Map data = JacksonUtils.json2map(result);
            logger.info("data:{}",result);
            logger.info("data.data.size:{}", ((List) data.get("data")).size());
            logger.info("data.data[0].name:{}", ((Map) ((List) data.get("data")).get(0)).get("name"));
            logger.info("data.data[0].id:{}", ((Map) ((List) data.get("data")).get(0)).get("id"));
            logger.info("data.data[0].clusterId:{}", ((Map) ((List) data.get("data")).get(0)).get("clusterId"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建一个命名空间
     */
    @Test
    public void testCreateNameSpace() {

        Map<String, String> senddata = new HashMap<>();
        senddata.put("type", "namespace");
        senddata.put("name", "market-grafana");
        senddata.put("projectId", "c-4ttws:p-ghqb4");

        String url = Common.getPropertiesKey(Common.MA_RAN_CREATNAMESPACE_URL_KEY);
        // url= url.replace("{{appName}}","dest-market-grafana-1.14.0");
        url = url.replace("{{clusterID}}", "c-4ttws"); //clusterID from method of getProjects
        Map<String, String> header = new HashMap<>();
        /**
         * [{"key":"Accept-Language","value":"en-US,en;q=0.5",
         *   "description":"",
         *   "enabled":true},
         *   {"key":"content-type","value":"application/json","description":"","enabled":true},
         *   {"key":"x-api-action-links","value":"actionLinks","description":"","enabled":true},
         *   {"key":"x-api-no-challenge","value":"true","description":"","enabled":true},
         *   {"key":"x-api-csrf","value":"8e852d0359","description":"","enabled":true},
         *   {"key":"Cookie","value":"R_USERNAME=chenxiaoji; CSRF=8e852d0359; R_SESS=token-r7j2k:s7qmhtjtcr86js2wnfbf6vs8fq8fcnnktntw78b6rd77zhlcbqzkv9","description":"","enabled":true}]
         */

        String csrf = Common.getPropertiesKey(Common.MA_RAN_CSRF_KEY);
        String userName = Common.getPropertiesKey(Common.MA_RAN_USERNAME_KEY);
        String token = Common.getPropertiesKey(Common.MA_RAN_TOKEN_KEY);
        header.put("Accept-Language", "en-US,en;q=0.5");
        header.put("content-type", "application/json");
        header.put("x-api-action-links", "actionLinks");
        header.put("x-api-no-challenge", "true");
        header.put("x-api-csrf", csrf);
        header.put("Cookie", "R_USERNAME=" + userName + "; CSRF=" + csrf + "; R_SESS=" + token);
        try {
            String result = httpUtils.httpsRequest(url, HTTPUtils.METHOD_POST, header, JacksonUtils.mapToJson(senddata));
            logger.info("result:{}", result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除一个命名空间
     */
    @Test
    public void testDelNameSpace() {


        String url = Common.getPropertiesKey(Common.MA_RAN_CREATNAMESPACE_URL_KEY);
        // url= url.replace("{{appName}}","dest-market-grafana-1.14.0");
        url = url.replace("{{clusterID}}", "c-4ttws"); //clusterID from method of getProjects
        url = url + "/market-grafana2";
        Map<String, String> header = new HashMap<>();
        /**
         * [{"key":"Accept-Language","value":"en-US,en;q=0.5",
         *   "description":"",
         *   "enabled":true},
         *   {"key":"content-type","value":"application/json","description":"","enabled":true},
         *   {"key":"x-api-action-links","value":"actionLinks","description":"","enabled":true},
         *   {"key":"x-api-no-challenge","value":"true","description":"","enabled":true},
         *   {"key":"x-api-csrf","value":"8e852d0359","description":"","enabled":true},
         *   {"key":"Cookie","value":"R_USERNAME=chenxiaoji; CSRF=8e852d0359; R_SESS=token-r7j2k:s7qmhtjtcr86js2wnfbf6vs8fq8fcnnktntw78b6rd77zhlcbqzkv9","description":"","enabled":true}]
         */

        String csrf = Common.getPropertiesKey(Common.MA_RAN_CSRF_KEY);
        String userName = Common.getPropertiesKey(Common.MA_RAN_USERNAME_KEY);
        String token = Common.getPropertiesKey(Common.MA_RAN_TOKEN_KEY);
        header.put("Accept-Language", "en-US,en;q=0.5");
        header.put("content-type", "application/json");
        header.put("x-api-action-links", "actionLinks");
        header.put("x-api-no-challenge", "true");
        header.put("x-api-csrf", csrf);
        header.put("Cookie", "R_USERNAME=" + userName + "; CSRF=" + csrf + "; R_SESS=" + token);
        try {
            String result = httpUtils.httpsRequest(url, HTTPUtils.METHOD_DELETE, header, null);
            logger.info("result:{}", result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发布应用
     */
    @Test
    public void testDeployApp() {
        Map<String, String> senddata = new HashMap<>();
        senddata.put("type", "namespace");
        senddata.put("prune", "false");
        senddata.put("type", "app");
        senddata.put("name", "market-grafana");
        senddata.put("answers", "{}");
        senddata.put("targetNamespace", "market-grafana");
        senddata.put("externalId", "catalog://?catalog=dest&template=market-grafana&version=1.14.0");
        senddata.put("projectId", "c-4ttws:p-ghqb4");

        String url = Common.getPropertiesKey(Common.MA_RAN_LAUNCHAPP_URL_KEY);
        // url= url.replace("{{appName}}","dest-market-grafana-1.14.0");
        url = url.replace("{{projectID}}", "c-4ttws:p-ghqb4"); //clusterID from method of getProjects
        Map<String, String> header = new HashMap<>();
        /**
         * [{"key":"Accept-Language","value":"en-US,en;q=0.5",
         *   "description":"",
         *   "enabled":true},
         *   {"key":"content-type","value":"application/json","description":"","enabled":true},
         *   {"key":"x-api-action-links","value":"actionLinks","description":"","enabled":true},
         *   {"key":"x-api-no-challenge","value":"true","description":"","enabled":true},
         *   {"key":"x-api-csrf","value":"8e852d0359","description":"","enabled":true},
         *   {"key":"Cookie","value":"R_USERNAME=chenxiaoji; CSRF=8e852d0359; R_SESS=token-r7j2k:s7qmhtjtcr86js2wnfbf6vs8fq8fcnnktntw78b6rd77zhlcbqzkv9","description":"","enabled":true}]
         */
        String csrf = Common.getPropertiesKey(Common.MA_RAN_CSRF_KEY);
        String userName = Common.getPropertiesKey(Common.MA_RAN_USERNAME_KEY);
        String token = Common.getPropertiesKey(Common.MA_RAN_TOKEN_KEY);
        header.put("Accept-Language", "en-US,en;q=0.5");
        header.put("content-type", "application/json");
        header.put("x-api-action-links", "actionLinks");
        header.put("x-api-no-challenge", "true");
        header.put("x-api-csrf", csrf);
        header.put("Cookie", "R_USERNAME=" + userName + "; CSRF=" + csrf + "; R_SESS=" + token);
        try {
            String result = httpUtils.httpsRequest(url, HTTPUtils.METHOD_POST, header, JacksonUtils.mapToJson(senddata));
            logger.info("result:{}", result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public  void  tes(){
//        String chartName="grafana-1.14.0.tgz";
//        String tmp= chartName.substring(0,chartName.lastIndexOf("."));
//        String x[]=tmp.split("-");
//        String appName=x[0];
//        String version=x[1];
//        logger.info("{}@@{}",appName,version);

        String aaa=" {\"success\":true,\"uploaded\":[]}";
        JSONObject jsonObject=JSONObject.fromObject(aaa);
        logger.info(jsonObject.get("success").getClass().getName());
    }
    @Test
    public  void testRefresh(){
        RancherService   rancherService=new RancherService();
        try {
            rancherService.refreshCatalog();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeploy() throws Exception {
        //chartrepo-gerrit-1.0.1

        RancherService   rancherService=new RancherService();
        rancherService.deployApplication("qloud-dop-1.0.1.tgz");
    }
}
