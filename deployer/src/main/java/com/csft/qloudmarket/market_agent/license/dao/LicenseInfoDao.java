package com.csft.qloudmarket.market_agent.license.dao;

import com.csft.qloudmarket.market_agent.license.pojo.LicenseInfo;
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
//    private static final String INSERT_LICENSE_INFO=
//            "insert into tb_license_info(license_key,artifact_id,valid_date,invalid_date,signature,customer_id) values(?,?,?,?,?,?) ";
////    private static final String GET_ONE_LICENSE_INFO=
////            "select license_key  as licenseKey,artifact_id as artifactId,valid_date as validDate,invalid_date as invalidDate,signature,customer_id as customerId from tb_license_info where artifact_id=? and customer_id=? and is_del='N' " ;
//
//    private static final String CHECK_LICENSE_INFO=
//            "select count(1) from tb_license_info where key=? and ? BETWEEN valid_date and invalid_date and is_del='N' " ;
//
//
//
//    private static  final  String INVALIDATE_LICENSE="update tb_license_info set is_del='Y'where ";
//    /**
//     *
//     * @param connection
//     * @param key
//     * @param date
//     * @return
//     * @throws Exception
//     */
//    public int checkLicenseInfo(Connection connection,String key,String date) throws Exception {
//        PreparedStatement pstmt = null;
//        ResultSet rs=null;
//        int result=0;
//        try {
//            pstmt = connection.prepareStatement(CHECK_LICENSE_INFO);
//            pstmt.setString(1, key);
//            pstmt.setString(2, date);
//            rs= pstmt.executeQuery();
//            while (rs.next()){
//                result=rs.getInt(1);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw  e;
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw  e;
//        } finally {
//            try {
//                if(rs!=null)
//                rs.close();
//                if(pstmt!=null)
//                pstmt.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//                throw  e;
//            }
//        }
//        return result;
//    }
//
//    /**
//     * insert an
//     * @param connection
//     * @param licenseInfo
//     * @return
//     * @throws Exception
//     */
//    public int putLicenseInfo(Connection connection,LicenseInfo licenseInfo) throws Exception {
//        PreparedStatement pstmt = null;
//        ResultSet rs=null;
//        int result=0;
//        try {
//            logger.info("sql:{}",INSERT_LICENSE_INFO);
//            pstmt = connection.prepareStatement(INSERT_LICENSE_INFO);
//            pstmt.setString(1, licenseInfo.getLicenseKey());
//            pstmt.setString(2, licenseInfo.getArtifaceId());
//            pstmt.setString(3, licenseInfo.getValidDate());
//            pstmt.setString(4, licenseInfo.getInvalidDate());
//            pstmt.setString(5, licenseInfo.getSignature());
//            pstmt.setString(6, licenseInfo.getCustomerId());
//            result= pstmt.executeUpdate();
//            connection.commit();
//        } catch (SQLException e) {
//            throw  e;
//        } catch (Exception e) {
//            throw  e;
//        } finally {
//            if(pstmt!=null)
//                pstmt.close();
//        }
//        return result;
//    }


}
