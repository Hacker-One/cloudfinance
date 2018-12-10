package com.ctfs.qloudMarket.market_service.util;

import com.qloudfin.qbank.db.JdbcPool;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/8/7
 * Time: 10:09
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseService {
    public  Connection getConnection() throws Exception {
        JdbcPool instance = JdbcPool.getInstance();
        return instance.getConnection();
    }

    public void  closeConnection(Connection connection) throws SQLException {
        if(connection!=null) {
            connection.close();
        }
    }
}
