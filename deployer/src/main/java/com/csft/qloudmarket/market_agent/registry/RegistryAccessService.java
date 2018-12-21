package com.csft.qloudmarket.market_agent.registry;

import com.csft.qloudmarket.market_agent.auth_service.pojo.AuthUser;
import com.csft.qloudmarket.market_agent.auth_service.service.AuthService;
import com.csft.qloudmarket.market_agent.util.*;
import io.netty.handler.codec.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/8/8
 * Time: 14:57
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class RegistryAccessService {
    private static Logger logger = LoggerFactory.getLogger(RegistryAccessService.class);

    // private static String CATA_LOGS = "http://116.196.122.126:5000/v2/_catalog";
    private static String CATA_LOGS = Common.getPropertiesKey(Common.MA_RE_CATALOG_KEY);
    //商店地址
    // private  static  String BASE_MARKET_RE_URL="http://116.196.122.126:5000/v2/";
    //private static String BASE_MARKET_RE_URL = "http://116.196.122.126:5000/v2/";
    private static String BASE_MARKET_RE_URL = Common.getPropertiesKey(Common.MA_RE_MKRE_KEY);
    //远端地址
    // private static String BASE_BANK_PASS_RE_URL = "http://192.168.11.22:5000/v2/";
    private static String BASE_BANK_PASS_RE_URL = Common.getPropertiesKey(Common.MA_RE_BKRE_KEY);


    private static String BASE_MARKET_DOCKER_AUTH = Common.getPropertiesKey(Common.MA_RE_BKRE_KEY);

    private static final String BLOBS_KEY = "blobSum";

    private static final String FSLAYER_KEY = "fsLayers";

    private static final String FILE_O_CHART = ":";

    private static final String FILE_C_CHART = "_";

    private static final String TEMPLE_REGISTRY_PATH = "temple/registry/";

    private FileUtil fileUtil = new FileUtil();

    private AuthService authService=new AuthService();

    /**
     * query  catalog of market's image
     *
     * @return
     * @throws Exception
     */
    public String queryRegistryCatalogs() throws Exception {
        HTTPUtils httpUtils = new HTTPUtils();
        String result = httpUtils.get(CATA_LOGS, new HashMap<>(), "");
        return result;
    }

    /**
     * @param imageName
     * @param tag
     * @return
     * @throws Exception
     */
    public byte[] queryManifest(String imageName, String tag,String token) throws Exception {
        //imageName = HTTPUtils.enodeString(imageName);
        String url = new StringBuffer(BASE_MARKET_RE_URL).append(imageName).append("/manifests/").append(tag).toString();
        HTTPUtils httpUtils = new HTTPUtils();
        JSONObject jsonObject = null;
         Map header= new HashMap<>();
        header.put("Authorization",token);
      byte[] maniData=  httpUtils.httpsRequestRebyte(url,HTTPUtils.METHOD_GET,header,"");
     //  logger.info("maniDAta:{}",new String(maniData));
        return maniData;
    }



    public byte[] queryManifestNexus(String imageName, String tag,Map header) throws Exception {
        //imageName = HTTPUtils.enodeString(imageName);
        String url = new StringBuffer(BASE_MARKET_RE_URL).append(imageName).append("/manifests/").append(tag).toString();
        HTTPUtils httpUtils = new HTTPUtils();
        JSONObject jsonObject = null;
        byte[] maniData=  httpUtils.httpsRequestRebyte(url,HTTPUtils.METHOD_GET,header,"");
        //  logger.info("maniDAta:{}",new String(maniData));
        return maniData;
    }

    public JSONObject imagePurchaseProgress(String name, String tag) throws Exception {
        JSONObject result = new JSONObject();
        result.put("type","registry");
        result.put("name",name+":"+tag);
       // try {
        logger.info("...........................................imagePurchaseProgress {}. {}",name,tag);
        String scope=name+":pull";
           String tokeninfo= getImageToken(Common.getPropertiesKey(Common.MARKET_USERNAME),Common.getPropertiesKey(Common.MARKET_PWD),scope);
           Map tokenMap=  JacksonUtils.json2map(tokeninfo);
      //  String token="";
            String token="Bearer "+(String) tokenMap.get("token");
            logger.info("\n token:{}",token);
            byte[] sManifest = queryManifest(name, tag, token);
            JSONObject jsonObject = JSONObject.fromObject(new String(sManifest, "utf-8"));
            logger.info("\nbegin to pull image********************************\n{}",jsonObject);
            jsonObject = pullMarketCode(jsonObject,token);
            logger.info("\n previous info :{}\nPull Over begin to push image********************************",jsonObject);
            JSONObject pushRes = pushImage(jsonObject);

            logger.info("push OVER    {}",pushRes);
            if (Boolean.valueOf((Boolean) pushRes.get("success"))) {

                logger.info("upload manifest :{}",new String(sManifest));

                JSONObject info = uploadManifest(name, tag, sManifest);// upload Manifest

                if ((Boolean) info.get("success")) {
                    logger.info("@@@@@success to execute !!!!!!!");
                    result.put("msg","success");
                } else {
                    logger.info("@@@@@failed to execute !!!!!!!");
                    result.put("msg","failed");

                }
            } else {
                logger.info("@@@@@@upload registry failed to execute !!!!!!!");
                result.put("msg","failed");
            }
        logger.info("...........................................imagePurchaseProgress {}. {}  ---- OVER !!!!!!",name,tag);
        return result;
    }




    public JSONObject imagePurchaseProgressNexus(String name, String tag,Map requestHeader) throws Exception {
        JSONObject result = new JSONObject();
        result.put("type","registry");
        result.put("name",name+":"+tag);
        // try {
        logger.info("...........................................imagePurchaseProgress {}.{}",name,tag);
        String scope=name+":pull";
      //  Map tokeninfo= getNexusRequestToken(Common.getPropertiesKey(Common.MARKET_USERNAME),Common.getPropertiesKey(Common.MARKET_PWD));
      //  Map tokenMap=  JacksonUtils.json2map(tokeninfo);
        //  String token="";
     //   String token=(String) tokenMap.get("token");
//        Map requestHeader=new HashMap();
//        requestHeader.put("X-Market-Token",token);
        byte[] sManifest = queryManifestNexus(name, tag, requestHeader);
        JSONObject jsonObject = JSONObject.fromObject(new String(sManifest, "utf-8"));
        logger.info("\nbegin to pull image********************************\n{}",jsonObject);
        jsonObject = pullMarketCodeNexus(jsonObject,requestHeader);
        logger.info("\n previous info :{}\nPull Over begin to push image********************************",jsonObject);
       // JSONObject pushRes = pushImageNexus(jsonObject,requestHeader);
        JSONObject pushRes =pushImage(jsonObject);// pushImageNexus(jsonObject,requestHeader);
        logger.info("push OVER    {}",pushRes);
        if (Boolean.valueOf((Boolean) pushRes.get("success"))) {

            logger.info("upload manifest :{}",new String(sManifest));

            //JSONObject info = uploadManifestNexus(name, tag, sManifest,requestHeader);// upload Manifest
            JSONObject info = uploadManifest(name, tag, sManifest);// upload Manifest
            if ((Boolean) info.get("success")) {
                logger.info("@@@@@success to execute !!!!!!!");
                result.put("msg","success");
            } else {
                logger.info("@@@@@failed to execute !!!!!!!");
                result.put("msg","failed");

            }
        } else {
            logger.info("@@@@@@upload registry failed to execute !!!!!!!");
            result.put("msg","failed");
        }
        logger.info("...........................................imagePurchaseProgress {}. {}  ---- OVER !!!!!!",name,tag);
        return result;
    }





    /**
     * 创建存放层的目录
     *
     * @param name
     * @param tag
     * @return directory Path
     */
    public String createPulledDirectory(String name, String tag) {
        String basePath =  Common.getPropertiesKey(Common.MA_VOLUME_KEY);
        String [] pathinfo= name.split("/");
        logger.info("pathinfo.length:{}",pathinfo);
        StringBuffer strName = new StringBuffer(basePath).append(Common.SEPARATOR).append(TEMPLE_REGISTRY_PATH);
        for(int i=0;i<pathinfo.length;i++){
            strName.append(pathinfo[i]);
            logger.info("strName :{}", strName);
            fileUtil.checkAndCreatDirector(strName.toString());
            if(i<(pathinfo.length-1)){
                strName.append(Common.SEPARATOR);
            }
        }

        strName.append(Common.SEPARATOR).append(tag);
        String result = fileUtil.checkAndCreatDirector(strName.toString());
        return result;
    }

    public String createPulledDirectoryLocal() {
        String basePath = Common.getPropertiesKey(Common.MA_VOLUME_KEY);
      //  String [] pathinfo= name.split("/");
    //    logger.info("pathinfo.length:{}",pathinfo);
        StringBuffer strName = new StringBuffer(basePath).append(Common.SEPARATOR).append(TEMPLE_REGISTRY_PATH);
//        for(int i=0;i<pathinfo.length;i++){
//            strName.append(pathinfo[i]);
//            logger.info("strName :{}", strName);
//            fileUtil.checkAndCreatDirector(strName.toString());
//            if(i<(pathinfo.length-1)){
//                strName.append(Common.SEPARATOR);
//            }
//        }
      //  strName.append(Common.SEPARATOR).append(tag);
        String result = fileUtil.checkAndCreatDirector(strName.toString());
        return result;
    }



    public JSONArray downloadLayersNexus(String name, String savePath, JSONArray LayerInfo,Map header) throws Exception {
        JSONArray result = null;
        if (LayerInfo != null && LayerInfo.size() > 0) {
            int i = 0;
            result = new JSONArray();
            for (Object obj : LayerInfo) {
                JSONObject json = (JSONObject) obj;
                String blobStr = (String) json.get(BLOBS_KEY);
                if(!checkBlobExistNexus(name,blobStr,header)) {
                    logger.info("{} 's blob {} not exist on target registry ",name,blobStr);
                    String fileName = (new StringBuffer().append(i).append(FILE_C_CHART).append(blobStr.replace(FILE_O_CHART, FILE_C_CHART))).toString(); // like 0@sha256_?  1@sha256_? .....
                    logger.info(" blob:{} @fileName :{},savePath: {}", blobStr, fileName, savePath);
                    result.add(pullCodeByBlobNexus(savePath, name, fileName, blobStr, header));
                    i++;
                }else
                {
                    logger.info("{} 's blob {} exist on target registry ",name,blobStr);
                }
            }
        }
        return result;
    }

    public JSONArray downloadLayers(String name, String savePath, JSONArray LayerInfo,String token) throws Exception {
        JSONArray result = null;
        if (LayerInfo != null && LayerInfo.size() > 0) {
            int i = 0;
            result = new JSONArray();
            for (Object obj : LayerInfo) {
                    JSONObject json = (JSONObject) obj;
                    String blobStr = (String) json.get(BLOBS_KEY);
                if(!checkBlobExist(name,blobStr)) {
                    logger.info("{} 's blob {} not exist on target registry ",name,blobStr);
                    String fileName = (new StringBuffer().append(i).append(FILE_C_CHART).append(blobStr.replace(FILE_O_CHART, FILE_C_CHART))).toString(); // like 0@sha256_?  1@sha256_? .....
                    logger.info(" blob:{} @fileName :{},savePath: {}", blobStr, fileName, savePath);
                    result.add(pullCodeByBlob(savePath, name, fileName, blobStr, token));
                    i++;
                }else
                {
                    logger.info("{} 's blob {} exist on target registry ",name,blobStr);
                }
            }
        }
        return result;
    }

    /**
     * @param savingPath
     * @param name
     * @param blobCode
     * @return filePath
     */
    public JSONObject pullCodeByBlob(String savingPath, String name, String fileName, String blobCode,String token) throws Exception {
        JSONObject result = null;
        HttpURLConnection httpConnection = null;
        HTTPUtils httpUtils = new HTTPUtils();
        //name = HTTPUtils.enodeString(name);
        String url = new StringBuffer(BASE_MARKET_RE_URL).append(name).append("/blobs/").append(blobCode).toString();
        logger.info("url:{}", url);
        try {
            result = new JSONObject();
            Map header=new HashMap();
              header.put("Authorization",token);
            String path = (new StringBuffer(savingPath)).append(File.separator).append(fileName).toString();
         File f=new File(path);
    //       if(!fileUtil.checkFileConsistant(path,blobCode)) { // down load layer .if it is not exist
         if(!f.exists()){
                httpConnection = httpUtils.createConnection(url, HTTPUtils.METHOD_GET, header, null);
                fileUtil.writeFile(savingPath, fileName, httpConnection.getInputStream());
           }else{
                logger.info(" file {} exist ! next file !",fileName);
           }
            result.put("filePath", path);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (httpConnection != null)
                httpConnection.disconnect();
        }
        return result;
    }




    public JSONObject pullCodeByBlobNexus(String savingPath, String name, String fileName, String blobCode,Map header) throws Exception {
        JSONObject result = null;
        HttpURLConnection httpConnection = null;
        HTTPUtils httpUtils = new HTTPUtils();
        //name = HTTPUtils.enodeString(name);
        String url = new StringBuffer(BASE_MARKET_RE_URL).append(name).append("/blobs/").append(blobCode).toString();
        logger.info("url:{}", url);
        try {
            result = new JSONObject();
            String path = (new StringBuffer(savingPath)).append(File.separator).append(fileName).toString();
            File f=new File(path);
            //       if(!fileUtil.checkFileConsistant(path,blobCode)) { // down load layer .if it is not exist
            if(!f.exists()){
                httpConnection = httpUtils.createConnection(url, HTTPUtils.METHOD_GET, header, null);
                fileUtil.writeFile(savingPath, fileName, httpConnection.getInputStream());
            }else{
                logger.info(" file {} exist ! next file !",fileName);
            }
            result.put("filePath", path);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (httpConnection != null)
                httpConnection.disconnect();
        }
        return result;
    }

    /**
     * @param jsonObject
     * @return
     */
    public JSONObject pullMarketCode(JSONObject jsonObject,String token) throws Exception {
        JSONObject result = null;
        try {
            String name = (String) jsonObject.get("name");
            String tag = (String) jsonObject.get("tag");
            JSONArray array = (JSONArray) jsonObject.get(FSLAYER_KEY);
           String strName = createPulledDirectory(name, tag);
         //    String strName =createPulledDirectoryLocal(); //all image layer put in one fold
            JSONArray filePath = downloadLayers(name, strName, array,token);//down load image return filePathList
            jsonObject.put("filePaths", filePath); //save filePaths
            logger.info("info {}", jsonObject);
            result = jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }



    public JSONObject pullMarketCodeNexus(JSONObject jsonObject,Map header) throws Exception {
        JSONObject result = null;
        try {
            String name = (String) jsonObject.get("name");
            String tag = (String) jsonObject.get("tag");
            JSONArray array = (JSONArray) jsonObject.get(FSLAYER_KEY);
            String strName = createPulledDirectory(name, tag);
            //    String strName =createPulledDirectoryLocal(); //all image layer put in one fold
            JSONArray filePath = downloadLayersNexus(name, strName, array,header);//down load image return filePathList
            jsonObject.put("filePaths", filePath); //save filePaths
            logger.info("info {}", jsonObject);
            result = jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }

    /**
     * 申请上传
     *
     * @param name
     * @return
     */
    public JSONObject applyUpload(String name) throws Exception {
      //  name = HTTPUtils.enodeString(name);
        logger.info("\n applyUpload");
        String url = new StringBuffer(BASE_BANK_PASS_RE_URL).append(name).append("/blobs/uploads/").toString();
        HTTPUtils httpUtils = new HTTPUtils();
        JSONObject info = null;
        info = httpUtils.getHeadInnfo(url, HTTPUtils.METHOD_POST, null, null);
        logger.info("\n apply OVER");
        return info;
    }


    public JSONObject applyUploadNexus(String name,Map header) throws Exception {
        //  name = HTTPUtils.enodeString(name);
        logger.info("\n applyUpload");
        String url = new StringBuffer(BASE_BANK_PASS_RE_URL).append(name).append("/blobs/uploads/").toString();
        HTTPUtils httpUtils = new HTTPUtils();
        JSONObject info = null;
        info = httpUtils.getHeadInnfoNexus(url, HTTPUtils.METHOD_POST, header, null);
        logger.info("\n apply OVER");
        return info;
    }




    /**
     * 检查上传进度
     *
     * @param name
     * @param uuid
     * @return
     */
    // public String uploadProgress(String host,String name,String uuid){
    public String uploadProgress(String name, String uuid) throws Exception {
        name = HTTPUtils.enodeString(name);
        String url = new StringBuffer(BASE_BANK_PASS_RE_URL).append(name).append("/blobs/uploads/").append(uuid).toString();
        HTTPUtils httpUtils = new HTTPUtils();
        logger.info("url {}", url);
        String result = null;

        Map header = new HashMap();
        //  header.put("Host",host);
        JSONObject head = httpUtils.getHeadInnfo(url, HTTPUtils.METHOD_GET, null, null);
        result = head.toString();
        return result;
    }


    public String uploadCompleted(String name, String uuid, String digest) throws Exception {
        name = HTTPUtils.enodeString(name);
        String url = new StringBuffer(BASE_BANK_PASS_RE_URL).append(name).append("/blobs/uploads/").append(uuid).toString();
        HTTPUtils httpUtils = new HTTPUtils();
        logger.info("url {}", url);
        String result = null;

        Map header = new HashMap();
        //header.put("Host",host);
        JSONObject head = httpUtils.getHeadInnfo(url, HTTPUtils.METHOD_GET, null, null);
        result = head.toString();
        return result;
    }


    private File checkFile(String path) {
        File file = new File(path);
        if (file != null && file.exists() && file.isFile()) {
            return file;
        }
        return null;
    }

    /**
     * upload a single layer
     *
     * @param name
     * @param file
     * @return
     * @throws IOException
     */
    private JSONObject uploadImage(String name, File file) throws Exception {
        JSONObject resultInfo = new JSONObject();
        resultInfo.put("name",name);
        JSONObject obj = applyUpload(name); // apply and get uuid
        String message = (String) obj.get("message");
        String location = (String) obj.get("Location");
        logger.info("MESSGAE:{} ,LOCATION {}", message, location);
        JSONObject result = null;
        if ("Accepted".equals(message.trim())) {
            String layerName = toLayerName(file.getName());
            String url = appednDigets(location, layerName);
            //before upload file you need check the blob exist at server if it not exist you can proceed next step
            //String layerName = toLayerName(file.getName());

            logger.info("url :{} \n name:{} \n layerName:{}\n", url, name, layerName);

           // if (!checkBlobExist(name, layerName)) {
                result = uploadfileTo(url, file); //upload file
               logger.info("\n {} upload info:{}",file.getPath(),result);
                String info = (String) result.get("success");
                if ("true".equals(info)) {
                    resultInfo.put("status", "success");
                } else {
                    resultInfo.put("status", "failed");
                }
        //    } else {
      //          resultInfo.put("status", "continue");
       //     }
        } else {
            resultInfo.put("status", "failed");
        }
        return resultInfo;
    }


    private JSONObject uploadImageBlockNexus(String name, File file,Map header) throws IOException {
        JSONObject result = null;
        HTTPUtils httpUtils = new HTTPUtils();
        FileInputStream fStream = null;
        try {
            logger.info("begin apply ");
            JSONObject obj = applyUploadNexus(name,header); // apply and get uuid
            String message = (String) obj.get("message");
            String location = (String) obj.get("Location");
            logger.info("requeser apply upload ");
            if ("Accepted".equals(message.trim())) {
                logger.info("Accepted");
                String layerName = toLayerName(file.getName());
                fStream = new FileInputStream(file);
                logger.info("set upload size");
                int bufferSize = Integer.parseInt(Common.getPropertiesKey(Common.MA_UPLOAD_SIZE,"102400"));
                byte[] buffer = new byte[bufferSize];
                int length = -1;
                int begin = 0;
                int end = 0;
                boolean isLast = false;
                logger.info("file length:{}", location);
                while ((length = fStream.read(buffer)) != -1) {
                    logger.info("\n******************************************begin:{}", length);
                    end = begin + length - 1;
                    if (length < bufferSize && end == (file.length() - 1)) {
                        isLast = true;
                        location = appednDigets2(location, layerName);
                    }
                    String baseurl=Common.getPropertiesKey(Common.MA_RE_BKRE_KEY);
                    location=baseurl.substring(0,(baseurl.length()-4))+location;
                    logger.info("\nlocation:{}\nlength:{}\nbegin:{}\nend:{}\nisLast:{}", location, length, begin, end, isLast);
                    result = httpUtils.uploadLayerChunkNexus(location, length, begin, end, buffer, isLast, length,header);
                    begin = end + 1;
                    logger.info("result:{}", result);
                    location = (String) ((List) result.get("location")).get(0);
                    logger.info("******************************************end:{}", length);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.info("EXCEPTION:{}", e.getStackTrace());
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("EXCEPTION:{}", e.getStackTrace());
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fStream != null) {
                fStream.close();
            }
        }
        return result;
    }


    private JSONObject uploadImageBlock(String name, File file) throws IOException {
        JSONObject result = null;
        HTTPUtils httpUtils = new HTTPUtils();
        FileInputStream fStream = null;
        try {
            logger.info("begin apply ");
            JSONObject obj = applyUpload(name); // apply and get uuid
            String message = (String) obj.get("message");
            String location = (String) obj.get("Location");
            logger.info("requeser apply upload ");
            if ("Accepted".equals(message.trim())) {
                logger.info("Accepted");
                String layerName = toLayerName(file.getName());
                fStream = new FileInputStream(file);
                logger.info("set upload size");
                int bufferSize = Integer.parseInt(Common.getPropertiesKey(Common.MA_UPLOAD_SIZE,"102400"));
                byte[] buffer = new byte[bufferSize];
                int length = -1;
                int begin = 0;
                int end = 0;
                boolean isLast = false;
                logger.info("file length:{}", location);
                while ((length = fStream.read(buffer)) != -1) {
                    logger.info("\n******************************************begin:{}", length);
                    end = begin + length - 1;
                    if (length < bufferSize && end == (file.length() - 1)) {
                        isLast = true;
                        location = appednDigets(location, layerName);
                    }
                    logger.info("\nlocation:{}\nlength:{}\nbegin:{}\nend:{}\nisLast:{}", location, length, begin, end, isLast);
                    result = httpUtils.uploadLayerChunk(location, length, begin, end, buffer, isLast, length);
                    begin = end + 1;
                    logger.info("result:{}", result);
                    location = (String) ((List) result.get("location")).get(0);
                    logger.info("******************************************end:{}", length);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.info("EXCEPTION:{}", e.getStackTrace());
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("EXCEPTION:{}", e.getStackTrace());
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fStream != null) {
                fStream.close();
            }
        }
        return result;
    }



    private JSONArray uploadImagesNexus(JSONArray fileList, String name, Boolean rollback,Map header) {
        JSONArray uploaded = new JSONArray(); //retain the uuid that has been uploaded.
        //Map resultInfo =null;
        // logger.info("fileList{}",fileList);
        if (fileList != null && fileList.size() > 0) {
            for (int i = 0; i < fileList.size(); i++) {
                JSONObject jsonObject = (JSONObject) fileList.get(i);
                String path = (String) jsonObject.get("filePath");
                File file = checkFile(path);
                logger.info("file Path:{}@ file exist", path,file.exists());
                if (file != null) {
                    logger.info("*************push ****************");
                    JSONObject singleRS = null;
                    int tryT=1;
                    do {
                    try {

                            logger.info("\n file {}\n {} upload",file.getName(),tryT);
                            singleRS = uploadImageBlockNexus(name, file, header);
                            tryT=5;

                    } catch (IOException e) {
                        e.printStackTrace();
                        logger.info("\n uploadImagesNexus ERROR :{}",e.getStackTrace());
                        tryT++;
                    }
                    } while (tryT<5);
                    // JSONObject singleRS = uploadImage(name, file);
                    if ("success".equals((String) singleRS.get("status"))) {
                        uploaded.add(singleRS);
                    } else {
                        rollback = true;
//                        break;
                        continue;
                    }
                } else {
                    logger.info("file not exist");
                }
            }
        } else {
            return uploaded;
        }
        return uploaded;
    }


    /**
     * @param fileList
     * @param name
     * @throws IOException
     */
    private JSONArray uploadImages(JSONArray fileList, String name, Boolean rollback) throws Exception {
        JSONArray uploaded = new JSONArray(); //retain the uuid that has been uploaded.
        //Map resultInfo =null;
        // logger.info("fileList{}",fileList);
        if (fileList != null && fileList.size() > 0) {
            for (int i = 0; i < fileList.size(); i++) {
                JSONObject jsonObject = (JSONObject) fileList.get(i);
                String path = (String) jsonObject.get("filePath");
                File file = checkFile(path);
                 logger.info("file Path:{}@ file exist", path,file.exists());
                if (file != null) {
                    logger.info("*************push ****************");
                    JSONObject singleRS = uploadImageBlock(name, file);
                   // JSONObject singleRS = uploadImage(name, file);
                    if ("success".equals((String) singleRS.get("status"))) {
                        uploaded.add(singleRS);
                    } else {
                        rollback = true;
//                        break;
                        continue;
                    }
                } else {
                    logger.info("file not exist");
                }
            }
        } else {
            return uploaded;
        }
        return uploaded;
    }

    /**
     * PUSH镜像操作
     *
     * @param maniFest
     * @throws Exception
     */
    public JSONObject pushImage(JSONObject maniFest) throws Exception {
        JSONObject result = new JSONObject();
        String name = (String) maniFest.get("name");
        //  String tag = (String) maniFest.get("tag");
        JSONArray fileList = (JSONArray) maniFest.get("filePaths");
        //put your code
        Boolean rollback = false;
        logger.info("\n fileList.SIZE:{}", fileList.size());
        JSONArray uploaded = uploadImages(fileList, name, rollback);
        logger.info("\n uploaded:{}", uploaded);
        if (rollback) {
            //go rollback proceed
            result.put("success", false);
            return result;
        }
        result.put("success", true);
        result.put("uploaded", uploaded);
        return result;
    }

    public JSONObject pushImageNexus(JSONObject maniFest,Map header) throws Exception {
        JSONObject result = new JSONObject();
        String name = (String) maniFest.get("name");
        //  String tag = (String) maniFest.get("tag");
        JSONArray fileList = (JSONArray) maniFest.get("filePaths");
        //put your code
        Boolean rollback = false;
        logger.info("\n fileList.SIZE:{}", fileList.size());
        JSONArray uploaded = uploadImagesNexus(fileList, name, rollback,header);
        logger.info("\n uploaded:{}", uploaded);
        if (rollback) {
            //go rollback proceed
            result.put("success", false);
            return result;
        }
        result.put("success", true);
        result.put("uploaded", uploaded);
        return result;
    }







    /**
     * layerFileName convert to stander layerName
     *
     * @param fileName
     * @return
     */
    public static String toLayerName(String fileName) {

        return fileName.substring(fileName.indexOf(FILE_C_CHART)+1).replace(FILE_C_CHART, FILE_O_CHART);
    }

    /**
     * FileName convert to stander layerFileName
     *private static final String FILE_O_CHART = ":";

     private static final String FILE_C_CHART = "_";
     * @param fileName
     * @return
     */
    public static String toFileName(String fileName) {
        return fileName.replace(FILE_O_CHART, FILE_C_CHART);
    }

    /**
     * @param locationUrl
     * @param digest
     * @return
     */
    public static String appednDigets(String locationUrl, String digest) {
        StringBuffer sb = new StringBuffer(locationUrl);
        sb.append("&digest=").append(digest.replace(FILE_C_CHART, FILE_O_CHART));
        return sb.toString();
    }

    public static String appednDigets2(String locationUrl, String digest) {
        StringBuffer sb = new StringBuffer(locationUrl);
        sb.append("?digest=").append(digest.replace(FILE_C_CHART, FILE_O_CHART));
        return sb.toString();
    }


    /**
     * @param url
     * @param file
     * @return
     */
    public JSONObject uploadfileTo(String url, File file) throws IOException {
        JSONObject result = null;
        HTTPUtils httpUtils = new HTTPUtils();
        result = httpUtils.uploadLayerBlock(url, file);
        return result;
    }


    /**
     * 检查上传是否完成
     *
     * @param name
     * @param uuid
     * @param blobs
     * @return
     */
    public JSONObject uploadOver(String name, String uuid, String blobs) {
        String url = new StringBuffer(BASE_BANK_PASS_RE_URL).append(name).append("/blobs/uploads/").append(uuid).append("?digest=").append(blobs).toString();
        HTTPUtils httpUtils = new HTTPUtils();
        JSONObject result = null;
        try {
            result = httpUtils.getHeadInnfo(url, HTTPUtils.METHOD_POST, null, null);
            logger.info("uploadOver result: {}", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public JSONObject uploadManifest(String name, String tag, byte[] info) throws Exception {
        String url = new StringBuffer(BASE_BANK_PASS_RE_URL).append(name).append("/manifests/").append(tag).toString();
        HTTPUtils httpUtils = new HTTPUtils();
        JSONObject response = httpUtils.sendJsonBody(url, HTTPUtils.METHOD_PUT, info);
        return response;
    }


    public JSONObject uploadManifestNexus(String name, String tag, byte[] info,Map header) throws Exception {
        String url = new StringBuffer(BASE_BANK_PASS_RE_URL).append(name).append("/manifests/").append(tag).toString();
        HTTPUtils httpUtils = new HTTPUtils();
        JSONObject response = httpUtils.sendJsonBodyNexus(url, HTTPUtils.METHOD_PUT, info,header);
        return response;
    }


    public boolean checkBlobExistNexus(String image, String digest,Map header) throws UnsupportedEncodingException {
        HTTPUtils httpUtils = new HTTPUtils();
        // image= httpUtils.enodeString(image);
        String url = new StringBuffer(BASE_BANK_PASS_RE_URL).append(image).append("/blobs/").append(digest).toString();
        logger.info("checkBloburl:{}", url);
        try {
            int info = httpUtils.gethttpsResponseCode(url,HTTPUtils.METHOD_HEAD, header, null);
            logger.info("exist info {}", info);
            if (info==200||info==201) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }



    public boolean checkBlobExist(String image, String digest) throws UnsupportedEncodingException {
        HTTPUtils httpUtils = new HTTPUtils();
       // image= httpUtils.enodeString(image);
        String url = new StringBuffer(BASE_BANK_PASS_RE_URL).append(image).append("/blobs/").append(digest).toString();
        logger.info("checkBloburl:{}", url);
        try {
            int info = httpUtils.gethttpsResponseCode(url,HTTPUtils.METHOD_HEAD, new HashMap<>(), null);
            logger.info("exist info {}", info);
            if (info==200||info==201) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }



//    public boolean checkSourceBlobExist(String image, String digest) throws UnsupportedEncodingException {
//        HTTPUtils httpUtils = new HTTPUtils();
//        // image= httpUtils.enodeString(image);
//        String url = new StringBuffer(BASE_MARKET_RE_URL).append(image).append("/blobs/").append(digest).toString();
//        logger.info("checkBloburl:{}", url);
//        try {
//            String info = httpUtils.httpsRequest(url,HTTPUtils.METHOD_HEAD, new HashMap<>(), null);
//            logger.info("exist info {}", info);
//            if ("200".equals(info)) {
//                return true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//        return false;
//    }

    public Map getNexusRequestToken(String userName,String pwd) throws Exception {
        AuthUser authUser=new AuthUser();
        authUser.setId(userName);
        authUser.setPassword(pwd);
       return   authService.getLoginToken(authUser);
    }



    public String getImageToken(String userName,String pwd,String scop) throws Exception {
        String response=null;
        logger.info("getImageToken \n \n");
        StringBuffer sb=new StringBuffer(userName).append(":").append(pwd);
        try {
            logger.info("\n sb.toString() {} \n \n",sb.toString());
            String auth= Base64.encode(sb.toString().getBytes("utf-8"));
            String authorStr="Basic "+auth;
            logger.info("authorStr2 {}\n {} \n{}  \n",authorStr,Common.getPropertiesKey(Common.MA_AUTH_BASE_URL),Common.MA_AUTH);
            String url = new StringBuffer(Common.getPropertiesKey(Common.MA_AUTH_BASE_URL)).append(Common.MA_AUTH).toString();
            logger.info("auth url {} \n \n",url);
            HTTPUtils httpUtils = new HTTPUtils();
            Map header = new HashMap();
            header.put("Content-Type","application/x-www-form-urlencoded");
           // header.put("cache-control","no-cache");

            header.put("Authorization",authorStr);
            logger.info("AUTH {}",authorStr);
         //   header.put("Authorization","Basic Y2hlbnhpYW9qaToxMjM0NTY=");
            //URLEncoder.encode()
            String requestBody="service=Docker registry&access_type=offline&grant_type=password&scope=repository:"+scop;
            response = httpUtils.proxyHttpRequest(url, HTTPUtils.METHOD_POST, header, requestBody);
            logger.info("\n\nresponse{}",response);
        } catch (Exception e) {
            e.printStackTrace();
            throw  e;
        }
        return response;
    }

}
