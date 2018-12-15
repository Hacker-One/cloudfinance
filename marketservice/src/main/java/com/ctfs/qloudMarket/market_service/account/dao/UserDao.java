package com.ctfs.qloudMarket.market_service.account.dao;

import com.ctfs.qloudMarket.market_service.account.pojo.AccountPojo;
import com.ctfs.qloudMarket.market_service.account.pojo.UserPojo;
import com.ctfs.qloudMarket.market_service.artifact_service.pojo.ArtifactPojo;
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
 * Date: 2018/10/16
 * Time: 14:55
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class UserDao {
    private static Logger logger= LoggerFactory.getLogger(UserDao.class);
    private static String INSERT_USER="insert into tb_user(id,name,account_id,pwd) values(?,?,?,?) ";
    private static String QUERY_USER_ACCOUNT="select b.id,b.name,b.hook,b.contact_num,b.email from tb_user a,tb_account b where a.id=? and a.`status`='N' and b.`status`='N' and a.account_id=b.id";

    private static String USER_LOGIN="select id,name,account_id from tb_user where id=? and status='N' ";
    /**
     * add user
     * @param connection
     * @param userPojo
     * @return
     * @throws SQLException
     */
    public int addUser(Connection connection, UserPojo userPojo) throws SQLException {
        int result=0;
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(INSERT_USER);
            pstmt.setString(1, userPojo.getId());
            pstmt.setString(2, userPojo.getName());
            pstmt.setString(3, userPojo.getAccount());
            pstmt.setString(4,userPojo.getPwd());
            result= pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw  e;
        } catch (Exception e) {
            e.printStackTrace();
            throw  e;
        } finally {
            try {
                if(pstmt!=null)
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw  e;
            }
        }
        return result;
    }


    public AccountPojo getAccount(Connection connection, String userId) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        AccountPojo result=null;
        try {
            pstmt = connection.prepareStatement(QUERY_USER_ACCOUNT);
            pstmt.setString(1, userId);
            rs= pstmt.executeQuery();
            result=new AccountPojo();
            while (rs.next()){
                result.setId(rs.getString("id"));
                result.setName(rs.getString("name"));
                result.setHook(rs.getString("hook"));
                result.setContact(rs.getString("contact_num"));
                result.setEmail(rs.getString("email"));
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



    public UserPojo userLogin(Connection connection, String userId) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        UserPojo result=null;
        try {
            pstmt = connection.prepareStatement(USER_LOGIN);
            pstmt.setString(1, userId);
            rs= pstmt.executeQuery();
            if (rs.next()){
                result=new UserPojo();
                result.setId(rs.getString("id"));
                result.setName(rs.getString("name"));
                result.setAccount(rs.getString("account_id"));
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
