package com.ctfs.qloudMarket.market_service.artifact_service.dao;

import com.ctfs.qloudMarket.market_service.artifact_service.pojo.ArtifactPojo;
import com.ctfs.qloudMarket.market_service.artifact_service.pojo.ArtifactTypePojo;
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
 * Date: 2018/10/18
 * Time: 11:32
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public class ArtifactTypeDao {
    private static Logger logger= LoggerFactory.getLogger(ArtifactTypeDao.class);

    private static String INSERT_ARTIFACT_TYPE="insert into tb_artifact_type(name,des) values(?,?)";

    private static String QUERY_ARTIFACT_TYPE_NAME="select id,name,des from tb_artifact_type where name=? and status='N' ";

    private static String QUERY_ARTIFACT_TYPES="select id,name,des from tb_artifact_type where status='N' ";



    public int addArtifactType(Connection connection, ArtifactTypePojo artifactTypePojo) throws SQLException {
        int result=0;
        PreparedStatement pstmt = null;
        try {

            logger.info("sql:{}",INSERT_ARTIFACT_TYPE);
            pstmt = connection.prepareStatement(INSERT_ARTIFACT_TYPE);
            pstmt.setString(1, artifactTypePojo.getName());
            pstmt.setString(2, artifactTypePojo.getDes());
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

    public ArtifactTypePojo queryArtifactTypeByName (Connection connection,String name) throws Exception {
        logger.info("\nArtifactTypePojo:{}\n",name);
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        ArtifactTypePojo result=null;
        try {
            pstmt = connection.prepareStatement(QUERY_ARTIFACT_TYPE_NAME);
            pstmt.setString(1,name);
            rs= pstmt.executeQuery();
            result=new ArtifactTypePojo();
            while (rs.next()){
                result.setId(rs.getInt("id"));
                result.setName(rs.getString("name"));
                result.setDes(rs.getString("des"));
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

    public  List<ArtifactTypePojo> queryArtifactType(Connection connection) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        List<ArtifactTypePojo> result=null;
        try {
            pstmt = connection.prepareStatement(QUERY_ARTIFACT_TYPES);
            rs= pstmt.executeQuery();
            result=new ArrayList<>();
            while (rs.next()){
                ArtifactTypePojo artifactPojo=new ArtifactTypePojo();
                artifactPojo.setId(rs.getInt("id"));
                artifactPojo.setName(rs.getString("name"));
                artifactPojo.setDes(rs.getString("des"));
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


}
