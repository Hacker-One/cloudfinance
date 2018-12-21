package com.ctfs.qloudMarket.market_service.artifact_service.dao;

import com.ctfs.qloudMarket.market_service.artifact_service.pojo.ArtifactInfo;
import com.ctfs.qloudMarket.market_service.artifact_service.pojo.ArtifactPojo;
import com.ctfs.qloudMarket.market_service.artifact_service.pojo.ChartInfo;
import com.ctfs.qloudMarket.market_service.artifact_service.pojo.ImageInfo;
import com.qloudfin.qbank.db.JdbcPool;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/8/6
 * Time: 17:14
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class ArtifactInfoDao {
    private static Logger logger= LoggerFactory.getLogger(ArtifactInfoDao.class);

    private static String ADD_ARTIFACT_SQL="insert  into tb_artifact_info(artifact_id,name,provider_id,logo_path,price,version,introduce_info,image_path,chart_path) values(?,?,?,?,?,?,?,?,?) ";

    private static String ADD_IMAGE_SQL="insert into tb_ms_imageinfo(product_id,image_name,tag) values(?,?,?)";

    private static String ADD_CHART_SQL="insert into tb_ms_chartinfo(product_id,chart_name) value(?,?) ";

    private static String QUERY_CHARTS_SQL="select id,product_id,chart_name,is_del from tb_ms_chartinfo where is_del='N' and product_id=? ";

    private static String QUERY_IMAGES_SQL="select id,product_id,image_name,tag ,is_del from tb_ms_imageinfo where is_del='N' and product_id=? ";

    private static String LDEL_IMAGE_SQL="update tb_ms_imageinfo set is_del='Y' where id=? ";

    private static String LDEL_CHART_SQL="update tb_ms_chartinfo set is_del='Y' where id=? ";



    private static String  ADD_ARTIFACT="insert into tb_artifact(name,url,product_id,artifact_type,tag) values(?,?,?,?,?) ";

    private static  String QUERY_ARTIFACT_BY_PRODUCT="select a.id,a.name,a.url,a.product_id,a.artifact_type,b.`name` as typeName,a.tag as tag from tb_artifact a LEFT OUTER JOIN tb_artifact_type b on a.artifact_type=b.id where a.status='N'and a.product_id=? ";

    private static String UPDATE_ARTIFACT="update tb_artifact set name=?,url=?,product_id=?,artifact_type=?,tag=?  where id=? ";

    private static String QUERY_ARTIFACT_PERMISSION="select count(1) as size from tb_artifact a,tb_order b where a.url=? and a.`status`='N' and a.artifact_type=? and b.account_id=? and b.order_status !='pending'  and a.product_id=b.product_id";

    private  static String QUERY_ARTIFACT_PERMISSION_BYUID= "select count(1)  from tb_user a ,tb_artifact b,tb_order c where a.account_id=c.account_id and b.product_id=c.product_id and b.`status`='N' and c.order_status !='pending' and b.url=?  and b.artifact_type=? and a.id=? ";

    public int addArtifact(Connection connection,ArtifactPojo artifactPojo) throws SQLException {
        int result=0;
        PreparedStatement pstmt = null;
        try {

            logger.info("sql:{}",ADD_ARTIFACT);
            pstmt = connection.prepareStatement(ADD_ARTIFACT);
            pstmt.setString(1, artifactPojo.getName());
            pstmt.setString(2, artifactPojo.getUrl());
            pstmt.setString(3, artifactPojo.getProductId());
            pstmt.setInt(4, artifactPojo.getType());
            pstmt.setString(5, artifactPojo.getTag());
            result=  pstmt.executeUpdate();
            logger.info("result:{}",result);
        } catch (SQLException e) {
            e.printStackTrace();
            throw  e;
        } catch (Exception e) {
            e.printStackTrace();
            throw  e;
        } finally {
            if (pstmt!=null)
                pstmt.close();
        }
        return result;
    }

    public List<ArtifactPojo> queryArtifact(Connection connection, String productId) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        List<ArtifactPojo> result=null;
        try {
            pstmt = connection.prepareStatement(QUERY_ARTIFACT_BY_PRODUCT);
            pstmt.setString(1, productId);
            rs= pstmt.executeQuery();
            result=new ArrayList<>();
            while (rs.next()){
                ArtifactPojo artifactPojo=new ArtifactPojo();
                artifactPojo.setId(rs.getInt("id"));
                artifactPojo.setProductId(rs.getString("product_id"));
                artifactPojo.setName(rs.getString("name"));
                artifactPojo.setUrl(rs.getString("url"));
                artifactPojo.setType(rs.getInt("artifact_type"));
                artifactPojo.setTypeName(rs.getString("typeName"));
                artifactPojo.setTag(rs.getString("tag"));
                result.add(artifactPojo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw  e;
        } catch (Exception e) {
            e.printStackTrace();
            throw  e;
        } finally {
            if(rs!=null)
                rs.close();
            if(pstmt!=null)
                pstmt.close();
        }
        return result;
    }


    public int upDateArtifact(Connection connection,ArtifactPojo artifactPojo) throws SQLException {
        int result=0;
        PreparedStatement pstmt = null;
        try {

            logger.info("sql:{}",UPDATE_ARTIFACT);
            pstmt = connection.prepareStatement(UPDATE_ARTIFACT);
            pstmt.setString(1, artifactPojo.getName());
            pstmt.setString(2, artifactPojo.getUrl());
            pstmt.setString(3, artifactPojo.getProductId());
            pstmt.setInt(4, artifactPojo.getType());
            pstmt.setString(5, artifactPojo.getTag());
            pstmt.setInt(6, artifactPojo.getId());

            result=  pstmt.executeUpdate();
            logger.info("result:{}",result);
        } catch (SQLException e) {
            e.printStackTrace();
            throw  e;
        } catch (Exception e) {
            e.printStackTrace();
            throw  e;
        } finally {
            if (pstmt!=null)
                pstmt.close();
        }
        return result;
    }


    public int queryArtifactPermissionUid(Connection connection,String url,int id,String userId) throws SQLException {
        int result=0;
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        try {

            logger.info("sql:{}",QUERY_ARTIFACT_PERMISSION_BYUID);
            pstmt = connection.prepareStatement(QUERY_ARTIFACT_PERMISSION_BYUID);
            pstmt.setString(1, url);
            pstmt.setInt(2, id);
            pstmt.setString(3, userId);
            rs =  pstmt.executeQuery();
            while (rs.next()){
                result=rs.getInt(1);
            }
            logger.info("result:{}",result);
        } catch (SQLException e) {
            e.printStackTrace();
            throw  e;
        } catch (Exception e) {
            e.printStackTrace();
            throw  e;
        } finally {
            if(rs!=null)
                rs.close();
            if (pstmt!=null)
                pstmt.close();
        }
        return result;
    }


    public int queryArtifactPermission(Connection connection,String url,int id,String accountId) throws SQLException {
        int result=0;
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        try {

            logger.info("sql:{}",QUERY_ARTIFACT_PERMISSION);
            pstmt = connection.prepareStatement(QUERY_ARTIFACT_PERMISSION);
            pstmt.setString(1, url);
            pstmt.setInt(2, id);
            pstmt.setString(3, accountId);
            rs =  pstmt.executeQuery();
            while (rs.next()){
                result=rs.getInt("size");
            }
            logger.info("result:{}",result);
        } catch (SQLException e) {
            e.printStackTrace();
            throw  e;
        } catch (Exception e) {
            e.printStackTrace();
            throw  e;
        } finally {
            if(rs!=null)
                rs.close();
            if (pstmt!=null)
                pstmt.close();
        }
        return result;
    }


    /**
     * query single record form artifact
     * @param artifactId
     * @return
     * @throws Exception
     */
    public ArtifactInfo getOneArtifactInfo(Connection connection,String artifactId) throws Exception {
        String sql="select artifact_id as artifactId, name as name, provider_id as  providerId," +
                   "   logo_path as logoPath,price ,version ,introduce_info as introduceInfo, " +
                   "image_path as imagePath,chart_path as chartPath " +
                   " from tb_artifact_info where is_del='N' and  artifact_id=?";
     //   JdbcPool instance = JdbcPool.getInstance();
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        ArtifactInfo result=null;
        try {
          //  connection = instance.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, artifactId);
            rs= pstmt.executeQuery();
            result=new ArtifactInfo();
            while (rs.next()){
                result.setArtifactId(rs.getString("artifactId"));
                result.setChartPath(rs.getString("chartPath"));
                result.setImagePath(rs.getString("imagePath"));
                result.setIntroduceInfo(rs.getString("introduceInfo"));
                result.setLogoPath(rs.getString("logoPath"));
                result.setName(rs.getString("name"));
                result.setPrice(rs.getBigDecimal("price"));
                result.setProviderId(rs.getString("providerId"));
                result.setVersion(rs.getString("version"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw  e;
        } catch (Exception e) {
            e.printStackTrace();
            throw  e;
        } finally {
            try {
                rs.close();
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw  e;
            }
        }
        return result;
    }

    public List<ImageInfo> getImages(Connection connection,String productId) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        List<ImageInfo> result=null;
        try {
            //  connection = instance.getConnection();
            pstmt = connection.prepareStatement(QUERY_IMAGES_SQL);
            pstmt.setString(1, productId);
            rs= pstmt.executeQuery();
            result=new ArrayList<>();
            while (rs.next()){
                ImageInfo imageInfo=new ImageInfo();
                imageInfo.setId(rs.getString("id"));
                imageInfo.setProductId(rs.getString("product_id"));
                imageInfo.setImageName(rs.getString("image_name"));
                imageInfo.setTag(rs.getString("tag"));
                imageInfo.setIsDel(rs.getString("is_del"));
                result.add(imageInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw  e;
        } catch (Exception e) {
            e.printStackTrace();
            throw  e;
        } finally {
            try {
                rs.close();
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw  e;
            }
        }
        return result;
    }

    /**
     * query chart information
     * @param connection
     * @param productId
     * @return
     * @throws Exception
     */
    public List<ChartInfo> getCharts(Connection connection,String productId) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        List<ChartInfo> result=null;
        try {
            pstmt = connection.prepareStatement(QUERY_CHARTS_SQL);
            pstmt.setString(1, productId);
            rs= pstmt.executeQuery();
            result=new ArrayList<>();
            while (rs.next()){
                ChartInfo imageInfo=new ChartInfo();
                imageInfo.setId(rs.getString("id"));
                imageInfo.setProductId(rs.getString("product_id"));
                imageInfo.setChartName(rs.getString("chart_name"));
                imageInfo.setIsDel(rs.getString("is_del"));
                result.add(imageInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw  e;
        } catch (Exception e) {
            e.printStackTrace();
            throw  e;
        } finally {
            try {
                rs.close();
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw  e;
            }
        }
        return result;
    }

    /**
     * 新增chart 信息
     * @param connection
     * @param chartInfo
     * @return
     * @throws SQLException
     */
    public int addChartInfo(Connection connection,ChartInfo chartInfo) throws SQLException {
        int result=0;
        PreparedStatement pstmt = null;
        try {

            logger.info("sql:{}",ADD_CHART_SQL);
            pstmt = connection.prepareStatement(ADD_CHART_SQL);
            pstmt.setString(1, chartInfo.getProductId());
            pstmt.setString(2, chartInfo.getChartName());
            result=  pstmt.executeUpdate();
            logger.info("result:{}",result);
        } catch (SQLException e) {
            e.printStackTrace();
            throw  e;
        } catch (Exception e) {
            e.printStackTrace();
            throw  e;
        } finally {
            if (pstmt!=null)
                pstmt.close();
        }
        return result;
    }

    /**
     * 新增镜像配置
     * @param connection
     * @param imageInfo
     * @return
     * @throws SQLException
     */
    public int addImageInfo(Connection connection,ImageInfo imageInfo) throws SQLException {
        int result=0;
        PreparedStatement pstmt = null;
        try {

            logger.info("sql:{}",ADD_IMAGE_SQL);
            pstmt = connection.prepareStatement(ADD_IMAGE_SQL);
            pstmt.setString(1, imageInfo.getProductId());
            pstmt.setString(2, imageInfo.getImageName());
            pstmt.setString(3, imageInfo.getTag());
            result=  pstmt.executeUpdate();
            logger.info("result:{}",result);
        } catch (SQLException e) {
            e.printStackTrace();
            throw  e;
        } catch (Exception e) {
            e.printStackTrace();
            throw  e;
        } finally {
            if (pstmt!=null)
                pstmt.close();
        }
        return result;
    }


    public int ldelImageOrChart(Connection connection,String type,String id) throws SQLException {
        int result=0;
        PreparedStatement pstmt = null;
        try {
            if("C".equals(type.toUpperCase())) {
                logger.info("sql:{}", LDEL_CHART_SQL);
                pstmt = connection.prepareStatement(LDEL_CHART_SQL);
            }else if ("I".equals(type.toUpperCase())){
                logger.info("sql:{}", LDEL_IMAGE_SQL);
                pstmt = connection.prepareStatement(LDEL_IMAGE_SQL);
            }else {
                return  result;
            }
            pstmt.setString(1,id);
            result=  pstmt.executeUpdate();
            logger.info("result:{}",result);
        } catch (SQLException e) {
            e.printStackTrace();
            throw  e;
        } catch (Exception e) {
            e.printStackTrace();
            throw  e;
        } finally {
            if (pstmt!=null)
                pstmt.close();
        }
        return result;
    }


    public List<ArtifactInfo> getArtifactInfo(Connection connection, String artifactId) throws Exception {
        String sql="select artifact_id as artifactId, name as name, provider_id as  providerId," +
                "   logo_path as logoPath,price ,version ,introduce_info as introduceInfo, " +
                "image_path as imagePath,chart_path as chartPath " +
                " from tb_artifact_info where is_del='N' and  artifact_id=?";
        //   JdbcPool instance = JdbcPool.getInstance();
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        List<ArtifactInfo> result=null;
        try {
            //  connection = instance.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, artifactId);
            rs= pstmt.executeQuery();
            result=new ArrayList<>();
            while (rs.next()){
                ArtifactInfo artifactInfo=new ArtifactInfo();
                artifactInfo.setArtifactId(rs.getString("artifactId"));
                artifactInfo.setChartPath(rs.getString("chartPath"));
                artifactInfo.setImagePath(rs.getString("imagePath"));
                artifactInfo.setIntroduceInfo(rs.getString("introduceInfo"));
                artifactInfo.setLogoPath(rs.getString("logoPath"));
                artifactInfo.setName(rs.getString("name"));
                artifactInfo.setPrice(rs.getBigDecimal("price"));
                artifactInfo.setProviderId(rs.getString("providerId"));
                artifactInfo.setVersion(rs.getString("version"));
                result.add(artifactInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw  e;
        } catch (Exception e) {
            e.printStackTrace();
            throw  e;
        } finally {
            try {
                rs.close();
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw  e;
            }
        }
        return result;
    }

    public int addAArtifactInfo(Connection connection,ArtifactInfo artifactInfo) throws SQLException {
     int result=0;
        PreparedStatement pstmt = null;
        try {

            logger.info("sql:{}",ADD_ARTIFACT_SQL);
            pstmt = connection.prepareStatement(ADD_ARTIFACT_SQL);
            pstmt.setString(1, artifactInfo.getArtifactId());
            pstmt.setString(2, artifactInfo.getName());
            pstmt.setString(3, artifactInfo.getProviderId());
            pstmt.setString(4, artifactInfo.getLogoPath());
            pstmt.setBigDecimal(5, artifactInfo.getPrice());
            pstmt.setString(6, artifactInfo.getVersion());
            pstmt.setString(7, artifactInfo.getIntroduceInfo());
            pstmt.setString(8, artifactInfo.getImagePath());
            pstmt.setString(9, artifactInfo.getChartPath());
            result=  pstmt.executeUpdate();
            logger.info("result:{}",result);
        } catch (SQLException e) {
            e.printStackTrace();
            throw  e;
        } catch (Exception e) {
            e.printStackTrace();
            throw  e;
        } finally {
            if (pstmt!=null)
                pstmt.close();
        }
     return result;
    }
}
