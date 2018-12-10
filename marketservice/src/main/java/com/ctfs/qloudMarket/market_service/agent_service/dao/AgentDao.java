package com.ctfs.qloudMarket.market_service.agent_service.dao;

import com.ctfs.qloudMarket.market_service.agent_service.pojo.AgentInfo;
import com.ctfs.qloudMarket.market_service.artifact_service.pojo.ArtifactInfo;
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
 * Date: 2018/9/18
 * Time: 17:24
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class AgentDao {

    private static Logger logger= LoggerFactory.getLogger(AgentDao.class);

    private static String QUERY_AGENT_INFO_TOKEN_IP="select  count(1)  from tb_ms_agent_info WHERE token=? and ip_address=? and ? BETWEEN create_date and invalidate_date and is_del='N' ";
    private static String QUERY_AGENT_INFO_IP_NAME="select id  ,token,ip_address as ipAddress ,customer_id as customerId,company_name as companyName,create_date as createDate ,invalidate_date as invalidateDate from tb_ms_agent_info  WHERE company_name=? and ip_address=? and ? BETWEEN create_date and invalidate_date and is_del='N' ";


    public AgentInfo getAAgentInfo(Connection connection, String companyName ,String ipAddress,String nowDate) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        AgentInfo result=null;
        try {
            //  connection = instance.getConnection();
            pstmt = connection.prepareStatement(QUERY_AGENT_INFO_IP_NAME);
            pstmt.setString(1, companyName);
            pstmt.setString(2, ipAddress);
            pstmt.setString(3, nowDate);
            rs= pstmt.executeQuery();
            result=new AgentInfo();
            while (rs.next()){
                result.setCompanyName(rs.getString("companyName"));
                result.setCreateDate(rs.getString("createDate"));
                result.setCustomerId(rs.getString("customerId"));
                result.setId(rs.getString("id"));
                result.setIpAddress(rs.getString("ipAddress"));
                result.setToken(rs.getString("token"));
                result.setInvalidateDate(rs.getString("invalidateDate"));

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

    public int checkToken(Connection connection, String token,String ipAddress,String nowDate) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        int dataCount=0;
        try {
            //  connection = instance.getConnection();
            pstmt = connection.prepareStatement(QUERY_AGENT_INFO_TOKEN_IP);
            pstmt.setString(1, token);
            pstmt.setString(2, ipAddress);
            pstmt.setString(3, nowDate);
            rs= pstmt.executeQuery();
            while (rs.next()){
                dataCount=rs.getInt(1);
                //logger.info("@@@@@@@@@@@@@@@@  {}",dataCount);
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
        return dataCount;
    }
}
