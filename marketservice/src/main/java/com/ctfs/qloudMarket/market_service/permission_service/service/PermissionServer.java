package com.ctfs.qloudMarket.market_service.permission_service.service;

import com.ctfs.qloudMarket.market_service.permission_service.dao.PermissionDao;
import com.ctfs.qloudMarket.market_service.permission_service.pojo.UserSourcePojo;
import com.ctfs.qloudMarket.market_service.redis.RedisPool;
import com.ctfs.qloudMarket.market_service.util.BaseService;
import com.ctfs.qloudMarket.market_service.util.JacksonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/11/29
 * Time: 19:06
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public class PermissionServer  extends BaseService {
    private static Logger logger = LoggerFactory.getLogger(PermissionServer.class);
    private PermissionDao permissionDao=new PermissionDao();

    /**
     *
     * @return
     * @throws SQLException
     */
    public List<UserSourcePojo> getUserResource() throws SQLException {
        logger.info("getUserResource................");
        List<UserSourcePojo> userSourcePojos=null;
        Connection connection=null;
        try {
            connection=this.getConnection();
            userSourcePojos=permissionDao.queryAllUserSource(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(connection!=null){
                connection.close();
            }
        }
        return userSourcePojos;
    }

    public void writeAllUserResource() throws Exception {
        logger.info("writeAllUserResource................");
        Jedis jedis=RedisPool.getInstance().getResource();
        List<UserSourcePojo>  userSourcePojos=getUserResource();
        for(UserSourcePojo source:userSourcePojos){
            String userId=source.getUserId();
            String sourceInfo=source.getSource();
            StringBuffer sbKey=new StringBuffer(userId).append("_<").append(sourceInfo).append(">");
            logger.info(jedis.set(sbKey.toString(), JacksonUtils.obj2json(source)));
        }
    }


    public String checkUserPermission(String userId,String source){
        logger.info("checkUserPermission................");
        Jedis jedis=RedisPool.getInstance().getResource();
        StringBuffer sbKey=new StringBuffer(userId).append("_<").append(source).append(">");
        return jedis.get(sbKey.toString());
    }


}
