package com.ctfs.qloudMarket.market_service.permission_service.dao;

import com.ctfs.qloudMarket.market_service.order_service.dao.OrderDao;
import com.ctfs.qloudMarket.market_service.order_service.pojo.OrderPojo;
import com.ctfs.qloudMarket.market_service.permission_service.pojo.UserSourcePojo;
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
 * Date: 2018/11/29
 * Time: 18:53
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public class PermissionDao {

    private static Logger logger = LoggerFactory.getLogger(PermissionDao.class);

    private static final String QUERY_ALL_SOURCE="select a.account_id,a.id,c.role_id,c.source,c.source_type from tb_user a,tb_user_role b ,tb_source c where  a.id=b.user_id and b.role_id=c.role_id and a.status='N' and b.status='N' and c.status='N' ";
    /**
     * query all user source
     * @param conn
     * @return
     * @throws SQLException
     */
    public List<UserSourcePojo> queryAllUserSource(Connection conn) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<UserSourcePojo> result = null;
        StringBuffer sb=new StringBuffer(QUERY_ALL_SOURCE);
        try {
            logger.info("queryAllUserSource sql:{}",sb);
            pstmt = conn.prepareStatement(sb.toString());
            rs = pstmt.executeQuery();
            result=new ArrayList<>();
            while (rs.next()) {
                UserSourcePojo data=new UserSourcePojo();
                data.setAccountId(rs.getString("account_id"));
                data.setUserId(rs.getString("id"));
                data.setRoleId(rs.getString("role_id"));
                data.setSource(rs.getString("source"));
                data.setSourceType(rs.getString("source_type"));
                result.add(data);
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
