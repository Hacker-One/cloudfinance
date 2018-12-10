package com.csft.qloudmarket.test;

import com.csft.qloudmarket.market_agent.registry.RegistryAccessService;
import com.csft.qloudmarket.market_agent.util.Common;
import com.csft.qloudmarket.market_agent.util.FileUtil;
import com.csft.qloudmarket.market_agent.util.HTTPUtils;
import com.csft.qloudmarket.market_agent.util.JacksonUtils;
import io.advantageous.boon.core.Sys;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/8/8
 * Time: 15:14
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class TestApi {
    private static Logger logger = LoggerFactory.getLogger(Test.class);
    private HTTPUtils httpUtils = new HTTPUtils();
    String aSite="http://116.196.122.126:5000/v2/";
    String bSite="http://192.168.11.22:5000/v2/";


    @Test
    public void testHttpCataLogs() {
        RegistryAccessService registryAccessService = new RegistryAccessService();
        try {
            String info = registryAccessService.queryRegistryCatalogs();
            logger.info("info {}", info);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testQuerytags() {
        // RegistryAccessService  registryAccessService=new RegistryAccessService();
        String url = aSite+"pause/tags/list";
        try {
            String info = httpUtils.get(url, null, null);
            logger.info("info {}", info);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testQueryManifest() {
        // RegistryAccessService  registryAccessService=new RegistryAccessService();
        FileUtil fileUtil=new FileUtil();
        try {
            JSONObject jsonObject =JSONObject.fromObject(queryManifest("flannel", "1.0.2"));
            String name = (String) jsonObject.get("name");
            String tag = (String) jsonObject.get("tag");
            JSONArray array = (JSONArray) jsonObject.get("fsLayers");
            String basePath = TestApi.class.getResource("/").getPath();
            logger.info("basePath :{}", basePath);
            StringBuffer strName = new StringBuffer(basePath).append(name);
            logger.info("strName :{}", strName);
            checkAndCreatDirector(strName.toString());
            strName.append(File.separator).append(tag);
            checkAndCreatDirector(strName.toString());
            int i=1;
            for (Object obj : array) {
                JSONObject json = (JSONObject) obj;
                String blobStr = (String) json.get("blobSum");
                String fileName = blobStr.replace(':', '_');
                logger.info("blob:{} @fileName :{}", blobStr, i+fileName);
                //TestApi testApi = new TestApi();
                RegistryAccessService registryAccessService = new RegistryAccessService();
//                try {
//                    byte[] data = registryAccessService.pullCodeByBlob(name, blobStr);
//                    logger.info("data.length:{}", data.length);
//                    fileUtil.writeFile(strName.toString(), i+fileName, data);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                i++;
            }

            logger.info("info {}", jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void writeFile(String path, String filename, byte[] data) throws IOException {

        ByteBuffer byteBuffer = ByteBuffer.wrap(data);
        byteBuffer.put(data);
        FileOutputStream outputStream = new FileOutputStream(new File((new StringBuffer(path)).append(File.separator).append(filename).toString()));
        FileChannel fileChannel = outputStream.getChannel();
        byteBuffer.flip();
        fileChannel.write(byteBuffer);
        byteBuffer.clear();
        fileChannel.close();
        fileChannel.close();
    }


    /**
     * 通过get协议获取到
     *
     * @param name
     * @param blobCode
     * @return
     * @throws Exception
     */
    public byte[] pullCodeByBlob(String name, String blobCode) throws Exception {
        HTTPUtils httpUtils2 = new HTTPUtils();
        String url = new StringBuffer("http://116.196.122.126:5000/v2/").append(name).append("/blobs/").append(blobCode).toString();
        logger.info("url:{}", url);
//        byte[] result = httpUtils2.getFileByteByGet(url, new HashMap<>());
        return null;
    }

    public byte[] queryManifest(String imageName, String tag) throws Exception {

        String url = new StringBuffer(aSite).append(imageName).append("/manifests/").append(tag).toString();
       //  JSONObject jsonObject = null;
        //byte [] info = httpUtils.getFileByteByGet(url, new HashMap<>());
        // jsonObject = JSONObject.fromObject(info);
     //   Map result= JacksonUtils.json2map(info);
        return null;
    }

    @Test
    public void checkfileExist() {
        //String url = "http://116.196.122.126:5000/v2/registry/blobs/sha256:a3ed95caeb02ffe68cdd9fd84406680ae93d633cb16422d00e8a7c22955b55d4";
        checkBlobExist("pause-amd64","sha256:cf92024299791de93ad205151ab24e535c218bbea6465fd8f79c2611db913a50");
        //        try{
//            String info = httpUtils.head(url, new HashMap<>(), null);
//            logger.info("info:{}", info);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public boolean checkBlobExist(String image, String blob) {
        HTTPUtils httpUtils = new HTTPUtils();
        String url = new StringBuffer("http://116.196.122.126:5000/v2/").append(image).append("/blobs/").append(blob).toString();
        try {
            String info = httpUtils.head(url, new HashMap<>(), null);
            logger.info(info);
            if ("200".equals(info)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Test public  void  testApply(){
        logger.info("{}",applyUpload(""));
    }

    public JSONObject applyUpload(String name) {
        String url = new StringBuffer(aSite).append(name).append("/blobs/uploads/").toString();
        JSONObject info = null;
        try {
            //  String info=  httpUtils.post(url,new HashMap<>(),null);
            info = httpUtils.getHeadInnfo(url, "POST", null, null);
//            logger.info("info:{}", info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }

    @Test
    public void uploadfile() throws Exception {
      //  JSONObject jsonObject= queryManifest("pause","3.1");
     //   logger.info("{}",jsonObject);
        String basePath = TestApi.class.getResource("/").getPath();
//        logger.info("basePath :{}", basePath);
        String name = "test5";
        StringBuffer strName = new StringBuffer(basePath).append("pause/3.1/");
        String rootPath = strName.toString();
//        logger.info("rootPath :{}", rootPath);
        File f = new File(rootPath);
        //List<String> filePath=new ArrayList<>();
        File fs[] = f.listFiles();
        for (File file : fs) {
            if (file.isFile()) {
                JSONObject obj = applyUpload(name); // apply and get uuid

                String uuid = (String) obj.get("uuid");
               String location = (String) obj.get("Location");
               String code = (String) obj.get("code");
                String message = (String) obj.get("message");
              //  logger.info("message:{},uuid:{}", message,uuid);
                if ("Accepted".equals(message)) {

                 uploadfileTo(file, name, uuid,location);

                }
//                String x= uploadProgress("192.168.122.126",name,uuid);
//                logger.info("XXXX {}",x);
            }
            break;
        }
    //    JSONObject j= queryManifest(name,"3.1");
//        logger.info("j info {}",j.toString());
   //     String re=uploadManifest(name,"3.1",j.toString());
      //  logger.info("re info :{}"+re);
    }


    @Test
    public void testUploadFile(){
        File file=new File("E:\\qcloudCode\\qloud-market\\qloudMarket-market-agent\\target\\test-classes\\pause-amd64\\3.1\\sha256_a3ed95caeb02ffe68cdd9fd84406680ae93d633cb16422d00e8a7c22955b46d4");
       String info=checkLayer("pause-amd64","sha256:a3ed95caeb02ffe68cdd9fd84406680ae93d633cb16422d00e8a7c22955b46d4");
       logger.info("info XXXX{}",info);
        uploadfileTo(file, "pause-amd64", "5d441800-2dca-4248-9cf2-6893c037cf59","http://192.168.11.22:5000/v2/pause-amd64/blobs/uploads/5d441800-2dca-4248-9cf2-6893c037cf59?_state=s8v_JDxICobVTzddbpZMOjNM1H-zFVFZu5OK3zw3K7x7Ik5hbWUiOiJwYXVzZS1hbWQ2NCIsIlVVSUQiOiI1ZDQ0MTgwMC0yZGNhLTQyNDgtOWNmMi02ODkzYzAzN2NmNTkiLCJPZmZzZXQiOjAsIlN0YXJ0ZWRBdCI6IjIwMTgtMDgtMTBUMDM6MDc6NDkuNTAzNjY0NzJaIn0%3D");
    }

    public String uploadProgress(String host,String name,String uuid){
        String url = new StringBuffer(aSite).append(name).append("/blobs/uploads/").append(uuid).toString();
        logger.info("url {}",url);
        String result=null;
        try {
            Map header=new HashMap();
            header.put("Host",host);
            JSONObject head=  httpUtils.getHeadInnfo(url,"GET",null,null);
            result=head.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String uploadCompleted(String name,String uuid,String digest){
        String url = new StringBuffer(aSite).append(name).append("/blobs/uploads/").append(uuid).toString();
        logger.info("url {}",url);
        String result=null;
        try {
            Map header=new HashMap();
            //header.put("Host",host);
            JSONObject head=  httpUtils.getHeadInnfo(url,"GET",null,null);
            result=head.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     *
     * @param name
     * @param digest
     * @return
     */
    public String checkLayer(String name,String digest){
        String url = new StringBuffer(aSite).append(name).append("/blobs/").append(digest).toString();
        String result=null;
        try {
           JSONObject head=  httpUtils.getHeadInnfo(url,"HEAD",null,null);
            result=head.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void uploadfileTo(File file, String name, String uuid,String location) {

        String fname = file.getPath();

        fname = fname.substring(fname.lastIndexOf(File.separator) + 1).replace("_", ":");
     //   String url = new StringBuffer(aSite).append(name).append("/blobs/uploads/").append(uuid).append("?digest=").append(fname).toString();

       // logger.info("fname:{}", fname);
      //  String requrl = url + "?digest=" + fname;
//        logger.info("@@@@@:{}",requrl);
        String value = httpUtils.uploadFile(location, file, location);
        logger.info("value {}",value);
        // uploadOver(name,uuid,fname);
    }

    public void uploadOver(String name, String uuid, String blobs) {
        String url = new StringBuffer(aSite).append(name).append("/blobs/uploads/").append(uuid).append("?digest=").append(blobs).toString();
        try {
            JSONObject j = httpUtils.getHeadInnfo(url, "POST", null, null);
            logger.info("j: {}", j);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void checkUploadManifest(){
        String name="qloud/busybox-curl";
        String tag="latest";
        try {

            byte [] obj= queryManifest(name,tag);
         //   System.out.println(new String(obj,"utf-8"));
           // obj=JSONObject.fromObject(obj).toString();
         //   logger.info("after {}",obj);
        // obj=  StringEscapeUtils.escapeHtml4(obj);
          String info=  uploadManifest(name,tag,obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String uploadManifest(String name,String tag, byte[] info) throws Exception {
        String url = new StringBuffer(bSite).append(name).append("/manifests/").append(tag).toString();
        logger.info("url: {}",url);
        Map header=new HashMap();
       JSONObject response=httpUtils.sendJsonBody(url,"PUT",info);
        return response.toString();
    }

    public String checkAndCreatDirector(String filePath) {
        File file = new File(filePath);
        if (!checkDirectorExist(file)) {
            file.mkdir();
        }
        return file.getPath();
    }

    public boolean checkDirectorExist(File file) {
        return file.exists();
    }


    /***
     {
     "schemaVersion":1,
     "name":"registry",
     "tag":"2",
     "architecture":"amd64",
     "fsLayers":[
     {
     "blobSum":"sha256:a3ed95caeb02ffe68cdd9fd84406680ae93d633cb16422d00e8a7c22955b46d4"
     }
     ],
     "history":[
     {
     "v1Compatibility":{
     "architecture":"amd64",
     "config":{
     "Hostname":"",
     "Domainname":"",
     "User":"",
     "AttachStdin":false,
     "AttachStdout":false,
     "AttachStderr":false,
     "ExposedPorts":{
     "5000/tcp":{

     }
     },
     "Tty":false,
     "OpenStdin":false,
     "StdinOnce":false,
     "Env":[
     "PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin"
     ],
     "Cmd":[
     "/etc/docker/registry/config.yml"
     ],
     "ArgsEscaped":true,
     "Image":"sha256:57d82d7f56a9a6bde75e164a9862d3ce70d41732f2980f9a428c4a581f1b2cd4",
     "Volumes":{
     "/var/lib/registry":{

     }
     },
     "WorkingDir":"",
     "Entrypoint":[
     "/entrypoint.sh"
     ],
     "OnBuild":[

     ],
     "Labels":null
     },
     "container":"59a4111b2bd54f2e9e687a794988180f4701d8c0f5568642b1b63d4833308f0c",
     "container_config":{
     "Hostname":"59a4111b2bd5",
     "Domainname":"",
     "User":"",
     "AttachStdin":false,
     "AttachStdout":false,
     "AttachStderr":false,
     "ExposedPorts":{
     "5000/tcp":{

     }
     },
     "Tty":false,
     "OpenStdin":false,
     "StdinOnce":false,
     "Env":[
     "PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin"
     ],
     "Cmd":[
     "/bin/sh",
     "-c",
     "#(nop) ",
     "CMD ["/etc/docker/registry/config.yml"]"
     ],
     "ArgsEscaped":true,
     "Image":"sha256:57d82d7f56a9a6bde75e164a9862d3ce70d41732f2980f9a428c4a581f1b2cd4",
     "Volumes":{
     "/var/lib/registry":{

     }
     },
     "WorkingDir":"",
     "Entrypoint":[
     "/entrypoint.sh"
     ],
     "OnBuild":[

     ],
     "Labels":{

     }
     },
     "created":"2018-07-06T17:33:34.808659708Z",
     "docker_version":"17.06.2-ce",
     "id":"c41c465be2d3c2000a905429f7ecefb4eb455b516909e460650cbed8c8a9b0d8",
     "os":"linux",
     "parent":"66307965d1b4ce938d342718ff5241e4643568407588705784b30f3f35951975",
     "throwaway":true
     }
     },
     ],
     "signatures":[
     {
     "header":{
     "jwk":{
     "crv":"P-256",
     "kid":"QCRS:MXXT:GAJD:JWYK:Y7MX:6UL4:VTI2:QLOT:JEEQ:LJ67:CBDW:WNRS",
     "kty":"EC",
     "x":"_ofTwKPKfunncXI2ey8VT8Ryt_uyH8eQ7wC2cS6rYcY",
     "y":"nai8vjfqm6FOl1LXGLxHQFz049iAtMp2glol0FwfzCA"
     },
     "alg":"ES256"
     },
     "signature":"M4kD7VFIgIA0bKSCRlz8TavA41LIrBrohOHVbK170WgddljVcpJwDF0B7lUlC09lTBoIwH9eZVsfn-ia8974RQ",
     "protected":"eyJmb3JtYXRMZW5ndGgiOjYyMTIsImZvcm1hdFRhaWwiOiJDbjAiLCJ0aW1lIjoiMjAxOC0wOC0wOFQwODoxMzowOVoifQ"
     }
     ]
     }
     */

    @Test
    public void testPropertie(){
        //Common common=null;
        logger.info(Common.getPropertiesKey(Common.MA_RE_CATALOG_KEY));
        logger.info(Common.getPropertiesKey(Common.MA_RE_BKRE_KEY));
        try {
         logger.info(HTTPUtils.enodeString("http://192.168.11.22:5000/v2/xxxx/busybox/blobs/uploads/c33a947a-d4a6-41e3-bf00-19168624f800?_state=uXYyomPFpFukPR79T_RBCI6k4RqgGtkZnYmIcxP3OVV7Ik5hbWUiOiJ4eHh4L2J1c3lib3giLCJVVUlEIjoiYzMzYTk0N2EtZDRhNi00MWUzLWJmMDAtMTkxNjg2MjRmODAwIiwiT2Zmc2V0IjowLCJTdGFydGVkQXQiOiIyMDE4LTA4LTIxVDExOjExOjA5LjIwNTY5ODQ0NVoifQ%3D%3D"));
            logger.info(HTTPUtils.enodeString("12345688dfsadas"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPROXY(){
        try {
            logger.info("\n ADSADSA:{}",httpUtils.get("https://192.168.1.164",null,null));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
