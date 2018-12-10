package com.ctfs.qloudMarket.market_service.license_service.dao;

import com.ctfs.qloudMarket.market_service.artifact_service.pojo.ArtifactInfo;
import com.ctfs.qloudMarket.market_service.license_service.pojo.LicenseInfo;
import com.ctfs.qloudMarket.market_service.license_service.pojo.LicensePojo;
import com.qloudfin.qbank.db.JdbcPool;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/8/6
 * Time: 18:19
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class LicenseInfoDao {
    private static Logger logger= LoggerFactory.getLogger(LicenseInfoDao.class);
    private static final String INSERT_LICENSE_INFO=
            "insert into tb_license_info(license_key,artifact_id,valid_date,invalid_date,signature,customer_id) values(?,?,?,?,?,?) ";
    private static final String GET_ONE_LICENSE_INFO=
            "select license_key  as licenseKey,artifact_id as artifactId,valid_date as validDate,invalid_date as invalidDate,signature,customer_id as customerId from tb_license_info where artifact_id=? and customer_id=? and is_del='N' " ;
// -------------------------------------------------------------------------------------------------------------------------------
    private static final String INSERT_LICENSE=
            "insert into tb_license(expired_date,content,product_id,account_id,create_date) values(?,?,?,?,?)";
    private static final String QUERY_LICENSE=
            "select id,expired_date,content,product_id,account_id,create_date from tb_license where product_id=? and account_id=? and status='N' order by create_date desc";

    private static final String QUERY_LICENSE_BY_ID=
            "select id,expired_date,content,product_id,account_id ,create_date from tb_license where  status='N'  and id=?";



    public LicensePojo getLicense(Connection connection, Integer id) throws Exception {

        PreparedStatement pstmt = null;
        ResultSet rs=null;
        LicensePojo result=null;
        try {
            logger.info("getLicense@sql:{}",QUERY_LICENSE_BY_ID);
            pstmt = connection.prepareStatement(QUERY_LICENSE_BY_ID);
            pstmt.setInt(1, id);
            rs= pstmt.executeQuery();
            if(rs.next()){
                result=new LicensePojo();
                result.setAccount(rs.getString("account_id"));
                result.setId(rs.getInt("id"));
                result.setExpiredDate(rs.getString("expired_date"));
                result.setContent(rs.getString("content"));
                result.setProduct(rs.getString("product_id"));
                result.setCreateDate(rs.getString("create_date"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw  e;
        } finally {
            if(rs!=null)
                rs.close();
            if(pstmt!=null)
                pstmt.close();
            //   connection.close();
        }
        return result;
    }

    public LicensePojo getLicense(Connection connection, String productId ,String accountId) throws Exception {

        PreparedStatement pstmt = null;
        ResultSet rs=null;
        LicensePojo result=null;
        try {
            logger.info("sql:{}",QUERY_LICENSE);
            pstmt = connection.prepareStatement(QUERY_LICENSE);
            pstmt.setString(1, productId);
            pstmt.setString(2, accountId);
            rs= pstmt.executeQuery();
            if(rs.next()){
                result=new LicensePojo();
                result.setAccount(rs.getString("account_id"));
                result.setId(rs.getInt("id"));
                result.setExpiredDate(rs.getString("expired_date"));
                result.setContent(rs.getString("content"));
                result.setProduct(rs.getString("product_id"));
                result.setCreateDate(rs.getString("create_date"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw  e;
        } finally {
            if(rs!=null)
                rs.close();
            if(pstmt!=null)
                pstmt.close();
             //   connection.close();
        }
        return result;
    }

    /**
     * insert an
     * @param connection
     * @param licenseInfo
     * @return
     * @throws Exception
     */
    public int putLicense(Connection connection,LicensePojo licenseInfo) throws Exception {

        PreparedStatement pstmt = null;
        int result=0;
        try {

            logger.info("sql:{}",INSERT_LICENSE);
            pstmt = connection.prepareStatement(INSERT_LICENSE);
            pstmt.setString(1, licenseInfo.getExpiredDate());
            pstmt.setString(2, licenseInfo.getContent());
            pstmt.setString(3, licenseInfo.getProduct());
            pstmt.setString(4, licenseInfo.getAccount());
            pstmt.setString(5, licenseInfo.getCreateDate());
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
     *
     * @param connection
     * @param licenseInfo
     * @return
     * @throws Exception
     */
    public LicenseInfo getLicenseInfo(Connection connection,LicenseInfo licenseInfo) throws Exception {
        JdbcPool instance = JdbcPool.getInstance();
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        LicenseInfo result=null;
        try {
            connection = instance.getConnection();
            pstmt = connection.prepareStatement(GET_ONE_LICENSE_INFO);
            pstmt.setString(1, licenseInfo.getArtifactId());
            pstmt.setString(2, licenseInfo.getCustomerId());
            rs= pstmt.executeQuery();
            result=new LicenseInfo();
            while (rs.next()){
                result.setArtifactId(rs.getString("artifactId"));
                result.setCustomerId(rs.getString("customerId"));
                result.setInvalidDate(rs.getString("invalidDate"));
                result.setLicenseKey(rs.getString("licenseKey"));
                result.setSignature(rs.getString("signature"));
                result.setValidDate(rs.getString("validDate"));
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
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw  e;
            }
        }
        return result;
    }

    /**
     * insert an
     * @param connection
     * @param licenseInfo
     * @return
     * @throws Exception
     */
    public int putLicenseInfo(Connection connection,LicenseInfo licenseInfo) throws Exception {

        PreparedStatement pstmt = null;
        int result=0;
        try {

            logger.info("sql:{}",INSERT_LICENSE_INFO);
            pstmt = connection.prepareStatement(INSERT_LICENSE_INFO);
            pstmt.setString(1, licenseInfo.getLicenseKey());
            pstmt.setString(2, licenseInfo.getArtifactId());
            pstmt.setString(3, licenseInfo.getValidDate());
            pstmt.setString(4, licenseInfo.getInvalidDate());
            pstmt.setString(5, licenseInfo.getSignature());
            pstmt.setString(6, licenseInfo.getCustomerId());
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
