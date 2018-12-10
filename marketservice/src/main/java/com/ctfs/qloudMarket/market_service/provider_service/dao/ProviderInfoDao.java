package com.ctfs.qloudMarket.market_service.provider_service.dao;

import com.ctfs.qloudMarket.market_service.provider_service.pojo.ProviderInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/8/7
 * Time: 9:45
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public class ProviderInfoDao {


    private static final String GET_PROVIDER_INFO="select provider_id as providerId,name,logo_path as logoPath,signature,corporation_info as corporationInfo from tb_provider_info where provider_id=? and is_del ='N' ";

    /**
     * get an provider information
     * @param connection
     * @param providerInfo
     * @return
     * @throws Exception
     */
    public ProviderInfo getProviderInfo(Connection connection, ProviderInfo providerInfo) throws Exception {
        //   JdbcPool instance = JdbcPool.getInstance();
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        ProviderInfo result=null;
        try {
            //  connection = instance.getConnection();
            pstmt = connection.prepareStatement(GET_PROVIDER_INFO);
            pstmt.setString(1, providerInfo.getProviderId());
            rs= pstmt.executeQuery();
            result=new ProviderInfo();
            while (rs.next()){
                result.setCorporationInfo(rs.getString("corporationInfo"));
                result.setSignature(rs.getString("signature"));
                result.setLogoPath(rs.getString("logoPath"));
                result.setName(rs.getString("name"));
                result.setProviderId(rs.getString("providerId"));
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
}
