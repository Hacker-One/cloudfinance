package com.csft.qloudmarket.market_agent.rancher.service;

import com.csft.qloudmarket.market_agent.util.Common;
import com.csft.qloudmarket.market_agent.util.HTTPUtils;
import com.csft.qloudmarket.market_agent.util.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/9/18
 * Time: 11:42
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class RancherService {
    private static Logger logger = LoggerFactory.getLogger(RancherService.class);
    private HTTPUtils httpUtils = new HTTPUtils();
    private Map<String, String> header = this.wrapHeader();

    /**
     * 包装请求头
     *
     * @return
     */
    private Map<String, String> wrapHeader() {
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
        return header;
    }

    @Deprecated
    public String queryCharts() throws IOException {
        String result = null;
        String url = "https://114.116.53.194:8443/v3/templateVersions";

        result = httpUtils.httpsRequest(url, HTTPUtils.METHOD_GET, header, null);
        logger.info("result:{}", result);
        return result;
    }

    /**
     * @return
     * @throws IOException
     */
    private String queryAllProjects() throws IOException {
        String result = null;
        String url = Common.getPropertiesKey(Common.MA_RAN_PROJECTS_URL_KEY);
        result = httpUtils.httpsRequest(url, HTTPUtils.METHOD_GET, header, null);
        logger.info("queryAllProjects result :{}", result);
        return result;
    }

    public String refreshCatalog() throws IOException {
        String result = null;
        String url = Common.getPropertiesKey(Common.MA_RAN_REFRESHCATALOG_URL_KEY);
        result = httpUtils.httpsRequest(url, HTTPUtils.METHOD_POST, header, null);
        logger.info("queryAllProjects result :{}", result);
        return result;
    }


    /**
     * @param appName
     * @return
     */
    private String searchAPP(String appName) throws IOException {
        String result = null;
        String url = Common.getPropertiesKey(Common.MA_RAN_CHECKAPP_URL_KEY);
        url = url.replace("{{appName}}", appName);
        url = url.replace("{{rancherVersion}}",Common.getPropertiesKey(Common.MA_RAN_VERSION_KEY));
        logger.info("searchAPP url:{}",url);
        result = httpUtils.httpsRequest(url, HTTPUtils.METHOD_GET, header, null);
        logger.info("searchAPP appname:{} result:{}", appName, result);
        return result;
    }


    /**
     * 创建一个命名空间
     *
     * @param nameSpace
     * @param projectID
     * @param clusterID
     * @return
     * @throws IOException
     */
    private String createNameSpace(String nameSpace, String projectID, String clusterID) throws IOException {
        logger.info("{}###{}####{}",nameSpace,projectID,clusterID);
        Map<String, String> senddata = new HashMap<>();
        senddata.put("type", "namespace");
        senddata.put("name", nameSpace);
        senddata.put("projectId", projectID);
        String url = Common.getPropertiesKey(Common.MA_RAN_CREATNAMESPACE_URL_KEY);
        url = url.replace("{{clusterID}}", clusterID);
        String snedStr= JacksonUtils.mapToJson(senddata);
        logger.info("createNameSpace nameSpace:{},{},{}", nameSpace,url,snedStr);

        String result = httpUtils.httpsRequest(url, HTTPUtils.METHOD_POST, header, snedStr);
        logger.info("createNameSpace result:{}", result);
        return result;
    }

    /**
     * 删除一个命名空间
     *
     * @param nameSpace
     * @param clusterID
     * @return
     * @throws IOException
     */
    private String delNameSpace(String nameSpace, String clusterID) throws IOException {
        String result = null;
        String url = Common.getPropertiesKey(Common.MA_RAN_CREATNAMESPACE_URL_KEY);
        url = url.replace("{{clusterID}}", clusterID); //clusterID from method of getProjects
        url = url + "/" + nameSpace;
        result = httpUtils.httpsRequest(url, HTTPUtils.METHOD_DELETE, header, null);
        logger.info("result:{}", result);
        return result;
    }

    /**
     * 发布一个应用
     *
     * @param nameSpace
     * @param appName
     * @param projectID
     * @param appVersion
     * @param catalog
     * @return
     * @throws IOException
     */
    private String deployApp(String nameSpace,String serverName,String appName, String projectID, String appVersion, String catalog) throws IOException {
        String result = null;
        Map<String, String> senddata = new HashMap<>();
        senddata.put("type", "namespace");
        senddata.put("prune", "false");
        senddata.put("type", "app");
        senddata.put("name", serverName);
        senddata.put("answers", "{}");
        senddata.put("targetNamespace", nameSpace);
        senddata.put("externalId",new StringBuffer("catalog://?catalog=").append(catalog).append("&template=").append(appName).append("&version=").append(appVersion).toString());
        senddata.put("projectId", projectID);
        String url = Common.getPropertiesKey(Common.MA_RAN_LAUNCHAPP_URL_KEY);
        url = url.replace("{{projectID}}", projectID); //clusterID from method of getProjects
        logger.info("deployer :{}",url);
        result = httpUtils.httpsRequest(url, HTTPUtils.METHOD_POST, header, JacksonUtils.mapToJson(senddata));
        logger.info("deployApp result:{}", result);
        return result;
    }

    public Map<String,Object>  deployApplication(String url) throws Exception {
        Map<String,Object> audeployResult=new HashMap();
        String tmp= url.substring(0,url.lastIndexOf("."));
        String appName= tmp.substring(0,tmp.lastIndexOf("-"));
        String version= tmp.substring(tmp.lastIndexOf("-")+1);
        logger.info("@@@chartInfo.name:{}@@@{}",appName,version);
        Map deploydata=new HashMap();
        deploydata.put("nameSpace",Common.getPropertiesKey(Common.MA_RAN_NAMESPACE));
        deploydata.put("serverName",appName);
        deploydata.put("appName",appName);
        deploydata.put("projectName",Common.getPropertiesKey(Common.MA_RAN_PROJECT_NAME));
        deploydata.put("version",version);
        deploydata.put("catalog",Common.getPropertiesKey(Common.MA_RAN_CATALOGNAME_KEY));
        // deployData.put("msg",processMsg.toString());
        try {
//            for(int i=0;i<10;i++) {
//                refreshCatalog();//先刷新一下仓库 否则查不到
//               logger.info("刷新{}次！！！！！",i);
//           }
           // Thread.sleep(20000);
            Map appinfo=null;
           for(int i=0;(appinfo==null&&i<10);i++) {
               logger.info("刷新{}次！！！！！",(i+1));
                refreshCatalog();
                appinfo = searchApp((String) deploydata.get("catalog"), (String) deploydata.get("appName"), (String) deploydata.get("version"));
               if(appinfo==null) {
                   Thread.sleep(10000);
               }
           }
            if (appinfo != null) {
                audeployResult  = autoDeploy((String) deploydata.get("nameSpace"), (String) deploydata.get("serverName"), (String) deploydata.get("appName"), (String) deploydata.get("projectName"), (String) deploydata.get("version"), (String) deploydata.get("catalog"));  //自动部署
                logger.info("rancher deploy Result:{}", audeployResult);
            } else {
                audeployResult.put("ERROR","application not found ");
                logger.info("rancher deploy Result ERROR \n\n\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("deployApplication EXCEPTION :{}",e.getMessage());
            throw  e;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("deployApplication EXCEPTION :{}",e.getMessage());
            throw  e;
        }
        return audeployResult;
    }

    /**
     * @param nameSpace
     * @param appName
     * @param projectName
     * @param appVersion
     * @param catalog
     * @return
     * @throws Exception
     */
    public Map<String,Object> autoDeploy(String nameSpace,String serverName,String appName, String projectName, String appVersion, String catalog) throws Exception {
        Map<String,Object> result = new HashMap<>();

        Map<String, Object> project = getDeployingProject(projectName); //1st step check project is exist!

        String infoCode = "000";
        String infoMsg = "succeed";
        String clusterID = null;
        String projectID = null;
        //String projectname=null;
        if (project != null) {
            clusterID = (String) project.get("clusterId");
            projectID = (String) project.get("id");
                String info = createNameSpace(nameSpace, projectID, clusterID);// 3th step create a namespace if it exist ,it going to  show log  and do nothing.
                logger.info("creating name space 's result :{}", info);
                String resStr = deployApp(nameSpace,serverName, appName, projectID, appVersion, catalog); // 4th step deploy the application
                logger.info("deploy info : {}",resStr);
                Map<String,Object> deployInfo=JacksonUtils.json2map(resStr);
                if(StringUtils.isEmpty((String) deployInfo.get("uuid"))){
                    infoCode="003";
                    infoMsg="deploy failed";
                }
//            } else {
//                infoCode = "002";
//                infoMsg="application not exist";
//            }
        } else {
            infoCode = "001";
            infoMsg=" project not exist";
        }
        result.put("code",infoCode);
        result.put("msg",infoMsg);
        return result;
    }

    /**
     * got a project information you wanna.
     *
     * @param projectName
     * @return
     * @throws Exception
     */
    public Map<String, Object> getDeployingProject(String projectName) throws Exception {
        Map<String, Object> result = null;
        String projects = queryAllProjects();
        Map infoData = JacksonUtils.json2map(projects);
        List<Map> dataList = (List<Map>) infoData.get("data");
        if (dataList != null && dataList.size() > 0) {
            for (Map data : dataList) {
                if (projectName.equals((String) data.get("name"))) {
                    result = data;
                }
            }
        }
        return result;
    }

    /**
     * @param catalog
     * @param appName
     * @param appVersion
     * @return
     * @throws Exception
     */
    public Map<String, Object> searchApp(String catalog, String appName, String appVersion) throws Exception {
        Map result=null;
        StringBuffer sb = new StringBuffer(catalog).append("-").append(appName).append("-").append(appVersion);
        String data = searchAPP(sb.toString());
        if(StringUtils.isNotEmpty(data)) {
            result = JacksonUtils.json2map(data);
            if (StringUtils.isEmpty((String) result.get("id"))) {
                result = null;
            }
        }
        return result;
    }


}
