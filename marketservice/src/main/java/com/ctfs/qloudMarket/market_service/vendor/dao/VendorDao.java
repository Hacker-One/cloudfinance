package com.ctfs.qloudMarket.market_service.vendor.dao;

import com.ctfs.qloudMarket.market_service.order_service.pojo.Order;
import com.ctfs.qloudMarket.market_service.order_service.pojo.OrderPojo;
import com.ctfs.qloudMarket.market_service.vendor.pojo.VendorPojo;
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
 * Date: 2018/8/23
 * Time: 15:21
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class VendorDao {
    private static Logger logger = LoggerFactory.getLogger(VendorDao.class);

    private static final String INSERT_VENDOR="insert into tb_vendor(name,email,contact_phone,url,hook_addr,display_name) values(?,?,?,?,?,?)" ;

    private static final String QUERY_VENDOR="select id,name,email,contact_phone,url,hook_addr,display_name from  tb_vendor where id=?  and status='N' ";


  //  private static final String addLicense="update set license_id=?,order_status=? where id=?";

  //  private static final String updateStatus="update set order_status=? where id=?";





    public int addVendor(Connection conn, VendorPojo vendorPojo) throws SQLException {
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            //  connection = instance.getConnection();
            pstmt = conn.prepareStatement(INSERT_VENDOR);
            pstmt.setString(1, vendorPojo.getName());
            pstmt.setString(2, vendorPojo.getEmail());
            pstmt.setString(3, vendorPojo.getContact());
            pstmt.setString(4, vendorPojo.getUrl());
            pstmt.setString(5, vendorPojo.getHook());
            pstmt.setString(6, vendorPojo.getDisplayName());
            result = pstmt.executeUpdate();
            logger.info("operate result:{}", result);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(pstmt!=null)
                pstmt.close();
        }
        return result;
    }



    public VendorPojo getVendor(Connection conn, int id) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        VendorPojo result = null;
        try {
            pstmt = conn.prepareStatement(QUERY_VENDOR);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                result=new VendorPojo();
                result.setContact(rs.getString("contact_phone"));
                result.setId(rs.getInt("id"));
                result.setName(rs.getString("name"));
                result.setEmail(rs.getString("email"));
                result.setUrl(rs.getString("url"));
                result.setHook(rs.getString("hook_addr"));
                result.setDisplayName(rs.getString("display_name"));
            }
            logger.info("operate result:{}", result);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (rs != null)
                rs.close();
            if (pstmt != null)
                pstmt.close();
        }
        return result;
    }


}
