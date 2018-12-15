package com.ctfs.qloudMarket.market_service.artifact_service.service;

import com.ctfs.qloudMarket.market_service.artifact_service.dao.ArtifactInfoDao;
import com.ctfs.qloudMarket.market_service.artifact_service.dao.ArtifactTypeDao;
import com.ctfs.qloudMarket.market_service.artifact_service.pojo.*;
import com.ctfs.qloudMarket.market_service.product.pojo.ProductTagPojo;
import com.ctfs.qloudMarket.market_service.util.BaseService;
import com.ctfs.qloudMarket.market_service.util.HTTPUtils;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/8/6
 * Time: 15:10
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class ArtifactService   extends BaseService {
    private static Logger logger= LoggerFactory.getLogger(ArtifactService.class);
    private ArtifactInfoDao artifactInfoDao=new ArtifactInfoDao();
    private ArtifactTypeDao artifactTypeDao=new ArtifactTypeDao();
    private HTTPUtils httpUtils=new HTTPUtils();






    public List<ArtifactPojo> queryArtifact(String productId) throws Exception {
        List<ArtifactPojo> result=null;
        Connection conn=null;
        try {
            conn=this.getConnection();
            result=  artifactInfoDao.queryArtifact(conn,productId);
        } catch (Exception e) {
            logger.info("error:{}",e.getStackTrace());
            throw  e;
        } finally {
            if(conn!=null) {
                conn.close();
            }
        }
        return  result;
    }


    public int addArtifact(Map artifact) throws Exception {
        Connection conn=null;
        int result=0;
        try {
            ArtifactPojo artifactPojo=  checkArtifact(artifact);
            if(artifactPojo!=null){
                conn=this.getConnection();
                result=   artifactInfoDao.addArtifact(conn,artifactPojo);
                conn.commit();
            }
        }catch (Exception e){
            logger.info("error:{}",e.getStackTrace());
            throw  e;
        }finally {
            if(conn!=null)
                conn.close();
        }
        return result;
    }

    public int updateArtifact(Map artifact) throws Exception {
        Connection conn=null;
        int result=0;
        try {
            ArtifactPojo artifactPojo=  checkArtifact(artifact);
            if(artifactPojo!=null){
                artifactPojo.setId(Integer.parseInt((String)artifact.get("id")));
                conn=this.getConnection();
                result= artifactInfoDao.upDateArtifact(conn,artifactPojo);
                conn.commit();
            }
        }catch (Exception e){
            logger.info("error:{}",e.getStackTrace());
            throw  e;
        }finally {
            if(conn!=null)
                conn.close();
        }
        return result;
    }




    public ArtifactPojo checkArtifact(Map artifact) throws Exception {
        ArtifactPojo artifactPojo=null;
        if(artifact!=null) {
            //type:,category:,name:,shortdesc:,icon:,longdesc:,picture:,url
            String name = (String) artifact.get("name");
            String productId = (String) artifact.get("productId");
            String type = (String) artifact.get("type");
            String url = (String) artifact.get("url");
            if(     StringUtils.isNotEmpty(name)&&
                    StringUtils.isNotEmpty(productId)&&
                    StringUtils.isNotEmpty(type)&&
                    StringUtils.isNotEmpty(url)
                    ){
                artifactPojo=new ArtifactPojo();
                //  productPojo.setId(String.valueOf(idWorker.nextId()));
                artifactPojo.setName(name);
                artifactPojo.setProductId(productId);
                artifactPojo.setType(Integer.parseInt(type));
                artifactPojo.setUrl(url);
            }
        }
        return artifactPojo;
    }


    public int checkAccountArtifactPermission(String account_id,String url,String typeName) throws SQLException {
        int result=0;
        Connection conn=null;
        try {
            conn=this.getConnection();
            ArtifactTypePojo typePojo= artifactTypeDao.queryArtifactTypeByName(conn,typeName);
           if(typePojo!=null){

               result= artifactInfoDao.queryArtifactPermission(conn,url,typePojo.getId(),account_id);
           }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("error:{}",e.getStackTrace());
        }finally {
            if(conn!=null)
                conn.close();
        }

        return result;
    }


    public int checkAccountArtifactPermissionUid(String userId,String url,String typeName) throws SQLException {
        int result=0;
        Connection conn=null;
        try {
            conn=this.getConnection();
            ArtifactTypePojo typePojo= artifactTypeDao.queryArtifactTypeByName(conn,typeName);
            if(typePojo!=null){

                result= artifactInfoDao.queryArtifactPermissionUid(conn,url,typePojo.getId(),userId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("error:{}",e.getStackTrace());
        }finally {
            if(conn!=null)
                conn.close();
        }

        return result;
    }






    //*********************************************************************************************************************
    /**
     * 查询一个产品ID;
     * @param artifactId
     * @return
     * @throws Exception
     */
    public ArtifactInfo queryAArtifactInfo( String artifactId) throws Exception {
        ArtifactInfo artifactInfo =null;
      //  artifactInfo.setArtifactId(artifactId);
        Connection conn=null;
        try {
            conn=this.getConnection();
            artifactInfo=   artifactInfoDao.getOneArtifactInfo(conn,artifactId);
        } catch (Exception e) {
            e.printStackTrace();
            throw  e;
        } finally {
            if(conn!=null) {
                conn.close();
            }
        }
        return artifactInfo;
    }

    public List<ArtifactInfo> queryArtifactInfos(String artifactId) throws Exception {
        List<ArtifactInfo> artifactInfos =null;
        //  artifactInfo.setArtifactId(artifactId);
        Connection conn=null;
        try {
            conn=this.getConnection();
            artifactInfos=   artifactInfoDao.getArtifactInfo(conn,artifactId);
        } catch (Exception e) {
            e.printStackTrace();
            throw  e;
        } finally {
            if(conn!=null) {
                conn.close();
            }
        }
        return artifactInfos;
    }


    public List<ImageInfo> queryImageInfos(String productId) throws Exception {
        List<ImageInfo> imageInfos =null;
        //  artifactInfo.setArtifactId(artifactId);
        Connection conn=null;
        try {
            conn=this.getConnection();
            imageInfos=   artifactInfoDao.getImages(conn,productId);
        } catch (Exception e) {
            e.printStackTrace();
            throw  e;
        } finally {
            if(conn!=null) {
                conn.close();
            }
        }
        return imageInfos;
    }



    public List<ChartInfo> queryChartInfos(String productId) throws Exception {
        List<ChartInfo> imageInfos =null;
        //  artifactInfo.setArtifactId(artifactId);
        Connection conn=null;
        try {
            conn=this.getConnection();
            imageInfos=   artifactInfoDao.getCharts(conn,productId);
        } catch (Exception e) {
            e.printStackTrace();
            throw  e;
        } finally {
            if(conn!=null) {
                conn.close();
            }
        }
        return imageInfos;
    }




    public int addChartInfo(ChartInfo chartInfo)throws Exception {
        int res=0;
        Connection conn=null;
        try {
            conn=this.getConnection();
            res=artifactInfoDao.addChartInfo(conn,chartInfo);
            logger.info("res:{}",res);
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw  e;
        }finally {
            if(conn!=null) {
                conn.close();
            }
        }
        return res;
    }


    public int addImageInfo(ImageInfo imageInfo)throws Exception {
        int res=0;
        Connection conn=null;
        try {
            conn=this.getConnection();
            res=artifactInfoDao.addImageInfo(conn,imageInfo);
            logger.info("res:{}",res);
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw  e;
        }finally {
            if(conn!=null) {
                conn.close();
            }
        }
        return res;
    }

    /**
     * 新增商品
     * @param artifactInfo
     * @return
     * @throws Exception
     */
    public int addArtifactInfoToDB(ArtifactInfo artifactInfo) throws Exception {
        int res=0;
        Connection conn=null;
        try {
            conn=this.getConnection();
            res=artifactInfoDao.addAArtifactInfo(conn,artifactInfo);
            logger.info("res:{}",res);
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw  e;
        }finally {
            if(conn!=null) {
                conn.close();
            }
        }
        return res;
    }

//    private QloudServiceClientBuilder<QloudHTTP> client = null;
//    private QloudHTTP proxy = null;
//
//    // 接口配置
//    private final String SERVICE_CONF = "ui.conf";

    /**
     * 获取客户端代理
     */
//    private void initProxy() {
//        client = QloudServiceClientBuilder. serviceClientBuilder (SERVICE_CONF);
//        proxy = client.getProxy();
//    }
//
//    /**
//     * 关闭客户端
//     */
//    private void closeProxy() {
//        client.flush();
//        client.close();
//    }

    /**
     * {
     "name": "测试数据1",
     "description": "A lovely mouse",
     "productID":"123",
     "providerID":"33333",
     "minPrice": 1,
     "offers": {
     "price": 1,
     "stock": 10
     }
     }
     * @param artifactInfo
     * @return
     */
    public String addArtifactToPortal(ArtifactInfo artifactInfo) throws Exception {
        //initProxy();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("name",artifactInfo.getName());
        jsonObject.put("description",artifactInfo.getIntroduceInfo());
        jsonObject.put("productID",artifactInfo.getArtifactId());
        jsonObject.put("providerID",artifactInfo.getProviderId());
        jsonObject.put("minPrice",artifactInfo.getPrice());
        JSONObject offers=new JSONObject();
        offers.put("price",artifactInfo.getPrice());
        offers.put("stock",10);
        jsonObject.put("offers",offers);
       // String result = proxy.send("api/products/seed", "POST", null, null, null, jsonObject.toString());\
        logger.info("portal :{}",jsonObject);
       Map<String,String> header= new HashMap<>();
        header.put("Content-Type","application/json");
        String result =  httpUtils.sendJsonBody("http://114.116.81.212:30808/api/products/seed",httpUtils.METHOD_POST,header,jsonObject.toString().getBytes("utf-8"));
        //String result = HttpU
        logger.info("result:{}",result);
    //   closeProxy();
        return result.toString();
    }

    public String addArtifactInfo(ArtifactInfo artifactInfo) throws Exception {
       int r= addArtifactInfoToDB(artifactInfo);
        String info=null;
       if(r>0) {
           info = addArtifactToPortal(artifactInfo);
           logger.info("XXXinfo:{}",info);
       }
       if("OK".equals(info)){
           logger.info("success");
           return "success";
       }
       return "fail";
    }


}
