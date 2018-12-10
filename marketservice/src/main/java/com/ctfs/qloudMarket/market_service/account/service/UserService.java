package com.ctfs.qloudMarket.market_service.account.service;

import com.ctfs.qloudMarket.market_service.account.dao.UserDao;
import com.ctfs.qloudMarket.market_service.account.pojo.AccountPojo;
import com.ctfs.qloudMarket.market_service.account.pojo.UserPojo;
import com.ctfs.qloudMarket.market_service.auth_service.pojo.AuthUser;
import com.ctfs.qloudMarket.market_service.auth_service.service.AuthService;
import com.ctfs.qloudMarket.market_service.util.BaseService;
import com.ctfs.qloudMarket.market_service.util.JacksonUtils;
import com.ctfs.qloudMarket.market_service.util.MD5;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.qloudfin.qloudbus.sdk.server.QloudServiceServer;

import java.sql.Connection;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/10/16
 * Time: 15:04
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class UserService extends BaseService {
    private static Logger logger= LoggerFactory.getLogger(UserService.class);
    private UserDao userDao=new UserDao();
    private AuthService authService=new AuthService();
    private MD5 md5 = new MD5();
    /**
     *
     * @param user
     * @return
     * @throws Exception
     */
    public int  addUser(Map user) throws Exception {
        logger.info("addAccount:{}",user);
        int result=0;
        UserPojo userPojo= JacksonUtils.map2pojo(user,UserPojo.class);
        userPojo.setPwd(md5.getMD5ofStr(userPojo.getPwd()));

        Connection conn=null;
        try {
            conn=this.getConnection();
            result=userDao.addUser(conn,userPojo);
            conn.commit();
            if(addAuthUser(userPojo)){
               result=-1;
            }
        } catch (Exception e) {
            logger.info("error:{}",e.getStackTrace());
            throw  e;
        } finally {
            if(conn!=null) {
                conn.close();
            }
        }
        return result;
    }

   public boolean addAuthUser( UserPojo userPojo) throws Exception {
        boolean result=false;
       AuthUser autherUser=new AuthUser();
       autherUser.setId(userPojo.getId());
       autherUser.setName(userPojo.getName());
       autherUser.setPassword(userPojo.getPwd());
       Map authResult=authService.createUserOnAuth(autherUser);
       String status= (String) authResult.get("status");
      if(AuthService.AUTH_STATUS_OK.equals(status)){
          result=true;
      }
      return result;
   }

    public AccountPojo getAccountByUser(String id) throws Exception {
        logger.info("getAccount:{}",id);
        AccountPojo result=null;
        Connection conn=null;
        try {
            conn=this.getConnection();
            result=userDao.getAccount(conn,id);
        } catch (Exception e) {
            logger.info("error:{}",e.getStackTrace());
            throw  e;
        } finally {
            if(conn!=null) {
                conn.close();
            }
        }
        return result;
    }


    public UserPojo userLogin(String id,String pwd) throws Exception {
        logger.info("getAccount:{}",id);
        UserPojo result=null;
        Connection conn=null;
        try {
            conn=this.getConnection();
            pwd= md5.getMD5ofStr(pwd);
            result=userDao.userLogin(conn,id,pwd);
        } catch (Exception e) {
            logger.info("error:{}",e.getStackTrace());
            throw  e;
        } finally {
            if(conn!=null) {
                conn.close();
            }
        }
        return result;
    }
}
