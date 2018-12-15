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
import java.util.HashMap;
import java.util.Hashtable;
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
        userPojo.setPwd(userPojo.getPwd());
        Connection conn=null;
        try {
            conn=this.getConnection();
            if(addAuthUser(userPojo)){
                userPojo.setPwd(md5.getMD5ofStr(userPojo.getPwd()));
                result=userDao.addUser(conn,userPojo);

            }
            conn.commit();
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

   public Map<String,Object> checkUserAccount(String userid) throws Exception {
       Map result=new HashMap();
       AccountPojo accountPojo=  getAccountByUser(userid);
           //   Map data= JacksonUtils.json2map(JacksonUtils.obj2json(accountPojo));
           result.put("code","000");
           result.put("msg","succeed");
           result.put("data",accountPojo);
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



    public UserPojo getUser(String id) throws Exception {
        logger.info("getUser:{}",id);
        UserPojo userPojo=null;
        Connection conn=null;
        try {
            conn=this.getConnection();
            userPojo=userDao.userLogin(conn,id);
        } catch (Exception e) {
            logger.info("error:{}",e.getStackTrace());
            throw  e;
        } finally {
            if(conn!=null) {
                conn.close();
            }
        }
        return userPojo;
    }


    public Map userLogin(String id,String pwd) throws Exception {
        logger.info("getAccount:{}",id);
        Map result=new HashMap();
        UserPojo userPojo=null;
        Connection conn=null;
        try {
            conn=this.getConnection();
            userPojo=userDao.userLogin(conn,id);
            AuthUser authUser=new AuthUser();
            authUser.setId(id);
            authUser.setPassword(pwd);
            if(userPojo!=null) {
                Map loginRs = authService.getLoginToken(authUser);
                if(loginRs!=null&&"ok".equals(((String) loginRs.get("status")).toLowerCase())){
                    result.put("user",userPojo);
                    result.put("access_token",loginRs.get("access_token"));
                }
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
}
