package com.ctfs.qloudMarket.market_service.order_service.dao;

import com.ctfs.qloudMarket.market_service.order_service.pojo.Order;
import com.ctfs.qloudMarket.market_service.order_service.pojo.OrderPojo;
import com.ctfs.qloudMarket.market_service.util.DateUtils;
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
 * Date: 2018/8/23
 * Time: 15:21
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class OrderDao {
    private static Logger logger = LoggerFactory.getLogger(OrderDao.class);

    //创建订单

    private static final String INSERT_TB_MS_ORDER = "insert into tb_ms_order(id,customer_id,ip_address,user_token,order_text) values(?,?,?,?,?)";
    //更改订单状态
    private static final String UPDATE_ORDER_STATUS = "update tb_ms_order set status=? where id=? ";
    //查询待部署的订单
    private static final String QUERY_UNDEPLOY_ORDER_BY_CUSTOMER = "select id,customer_id,ip_address,user_token,order_text from tb_ms_order where customer_id=? and ip_address=? and status='001' LIMIT 0,1 ";


    private static final String INSERT_ORDER="insert into tb_order(id,order_status,purchase_code,account_id,product_id,product_name,order_type) values(?,?,?,?,?,?,?)";

    private static final String QUERY_ORDER="select id,create_time,order_status,purchase_code,account_id,product_id,license_id,product_name from  tb_order where id=? ";

    private static final String UPDATE_ORDER_STATUS_ACCOUNT_PRODUCT="update tb_order set  order_status='paid' where  order_status !='pending'  and account_id=? and product_id=? ";

    private static final String QUERY_ORDER_ACCOUNT_PRODUCT="select id,create_time,order_status,purchase_code,account_id,product_id,license_id,product_name from  tb_order where order_status !='pending'  and account_id=? and product_id=?  order by license_id desc limit 0,1";

    private static final String QUERY_ORDER_BYPCODE="select id,create_time,order_status,purchase_code,account_id,product_id,license_id,product_name from  tb_order where order_status='pending' and purchase_code=? ";

    private static final String QUERY_ORDER_ACCOUNT="select id,create_time,order_status,purchase_code,account_id,product_id,license_id,product_name from  tb_order where account_id=? ";

    private static final String QUERY_ORDER_ACCOUNT_SIZE="select count(1) as size from  tb_order where account_id=? ";

    private static final String addLicense="update tb_order set license_id=?,order_status=? where id=?";

    private static final String updateStatus="update tb_order set order_status=? where id=?";

    private static final String updateDeployInfo="update tb_order set order_status=? , deploy_msg=? where id=? ";

    private static  final String QUERY_VALID_ORDER="select a.id,a.create_time,a.order_status,a.purchase_code,a.account_id,a.product_id,a.license_id,a.product_name,a.order_type from  tb_order a , (select id FROM tb_license where ?<expired_date)  b  where a.license_id=b.id and a.order_status !='pending'  and a.account_id=? and a.product_id=? order by a.create_time desc";

    private static final String limit="  limit ?,?";



    public int addOrder(Connection conn, OrderPojo order) throws SQLException {
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            //  connection = instance.getConnection();
            pstmt = conn.prepareStatement(INSERT_ORDER);
            pstmt.setString(1, order.getId());
//            pstmt.setString(2, order.getCreateDate());
            pstmt.setString(2, order.getOrderStatus());
            pstmt.setString(3, order.getPurchaseCode());
            pstmt.setString(4, order.getAccountId());
            pstmt.setString(5, order.getProductId());
          //  pstmt.setInt(7, order.getLicenseId());
            pstmt.setString(6, order.getProductName());
            pstmt.setString(7, order.getOrderType());
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


    public int queryOrderCount(Connection conn, String accountId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int result = 0;
        try {
            pstmt = conn.prepareStatement(QUERY_ORDER_ACCOUNT_SIZE);
            pstmt.setString(1, accountId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                result=rs.getInt("size");
            }
            logger.info("queryOrderCount:{}", result);
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

    public List<OrderPojo> queryOrders(Connection conn, String accountId,int begin,int count) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<OrderPojo> result = null;
        StringBuffer sb=new StringBuffer(QUERY_ORDER_ACCOUNT);
        sb.append(limit);
        try {
            pstmt = conn.prepareStatement(sb.toString());
            pstmt.setString(1, accountId);
            pstmt.setInt(2, begin);
            pstmt.setInt(3, count);
            rs = pstmt.executeQuery();
            result=new ArrayList<>();
            while (rs.next()) {
                OrderPojo data=new OrderPojo();
                data.setAccountId(rs.getString("account_id"));
                data.setId(rs.getString("id"));
                data.setCreateDate(rs.getString("create_time"));
                data.setOrderStatus(rs.getString("order_status"));
                data.setPurchaseCode(rs.getString("purchase_code"));
                data.setProductId(rs.getString("product_id"));
                data.setLicenseId(rs.getInt("license_id"));
                data.setProductName(rs.getString("product_name"));
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


    public OrderPojo queryOrderByPcode(Connection conn, String pcode) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        OrderPojo result = null;
        try {
            pstmt = conn.prepareStatement(QUERY_ORDER_BYPCODE);
            pstmt.setString(1, pcode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                result=new OrderPojo();
                result.setAccountId(rs.getString("account_id"));
                result.setId(rs.getString("id"));
                result.setCreateDate(rs.getString("create_time"));
                result.setOrderStatus(rs.getString("order_status"));
                result.setPurchaseCode(rs.getString("purchase_code"));
                result.setProductId(rs.getString("product_id"));
                result.setLicenseId(rs.getInt("license_id"));
                result.setProductName(rs.getString("product_name"));
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



    public int updateOrder(Connection conn, String account,String product) throws SQLException {
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            pstmt = conn.prepareStatement(UPDATE_ORDER_STATUS_ACCOUNT_PRODUCT);
            pstmt.setString(1, account);
            pstmt.setString(2, product);
            result = pstmt.executeUpdate();
            logger.info("operate result:{}", result);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (pstmt != null)
                pstmt.close();
        }
        return result;
    }

    /**
     * 获取用户生效的订单
     * @param conn
     * @param account
     * @param product
     * @return
     * @throws SQLException
     */
    public OrderPojo queryValidOrder(Connection conn, String account,String product) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        OrderPojo result = null;
        try {
            pstmt = conn.prepareStatement(QUERY_VALID_ORDER);
            pstmt.setString(1, DateUtils.getCurrentDate(DateUtils.DATESHORTFORMAT));
            pstmt.setString(2, account);
            pstmt.setString(3, product);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result=new OrderPojo();
                result.setAccountId(rs.getString("account_id"));
                result.setId(rs.getString("id"));
                result.setCreateDate(rs.getString("create_time"));
                result.setOrderStatus(rs.getString("order_status"));
                result.setPurchaseCode(rs.getString("purchase_code"));
                result.setProductId(rs.getString("product_id"));
                result.setLicenseId(rs.getInt("license_id"));
                result.setProductName(rs.getString("product_name"));
                result.setOrderType(rs.getString("order_type"));
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


    public OrderPojo queryOrder(Connection conn, String account,String product) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        OrderPojo result = null;
        try {
            pstmt = conn.prepareStatement(QUERY_ORDER_ACCOUNT_PRODUCT);
            pstmt.setString(1, account);
            pstmt.setString(2, product);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result=new OrderPojo();
                result.setAccountId(rs.getString("account_id"));
                result.setId(rs.getString("id"));
                result.setCreateDate(rs.getString("create_time"));
                result.setOrderStatus(rs.getString("order_status"));
                result.setPurchaseCode(rs.getString("purchase_code"));
                result.setProductId(rs.getString("product_id"));
                result.setLicenseId(rs.getInt("license_id"));
                result.setProductName(rs.getString("product_name"));
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


    public OrderPojo queryOrder(Connection conn, String id) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        OrderPojo result = null;
        try {
            pstmt = conn.prepareStatement(QUERY_ORDER);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result=new OrderPojo();
                result.setAccountId(rs.getString("account_id"));
                result.setId(rs.getString("id"));
                result.setCreateDate(rs.getString("create_time"));
                result.setOrderStatus(rs.getString("order_status"));
                result.setPurchaseCode(rs.getString("purchase_code"));
                result.setProductId(rs.getString("product_id"));
                result.setLicenseId(rs.getInt("license_id"));
                result.setProductName(rs.getString("product_name"));
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


    public int addLicense(Connection conn, int LicenseId ,String order_status,String orderId) throws SQLException {
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            //  connection = instance.getConnection();
            pstmt = conn.prepareStatement(addLicense);
            pstmt.setInt(1, LicenseId);
            pstmt.setString(2, order_status);
            pstmt.setString(3, orderId);
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


    public int updateStatus(Connection conn, String order_status,String orderId) throws SQLException {
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            pstmt = conn.prepareStatement(updateStatus);
            pstmt.setString(1, order_status);
            pstmt.setString(2, orderId);
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


    public int updateDeployStatus(Connection conn, String order_status,String orderId,String deployInfo) throws SQLException {
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            pstmt = conn.prepareStatement(updateDeployInfo);
            pstmt.setString(1, order_status);
            pstmt.setString(2, deployInfo);
            pstmt.setString(3, orderId);
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



//-----------------------------------------------------------------------------------------------------------------------



    /**
     * 新增一个订单
     *
     * @param conn
     * @param order
     * @return
     */
    public int insertOrder(Connection conn, Order order) throws SQLException {
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            //  connection = instance.getConnection();
            pstmt = conn.prepareStatement(INSERT_TB_MS_ORDER);
            pstmt.setString(1, order.getId());
            pstmt.setString(2, order.getCustomerId());
            pstmt.setString(3, order.getIpAddress());
            pstmt.setString(4, order.getUserToken());
            pstmt.setString(5, order.getOrderText());
            result = pstmt.executeUpdate();
            logger.info("operate result:{}", result);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
        return result;
    }


    /**
     * modify order status
     *
     * @param conn
     * @param order
     * @return
     * @throws SQLException
     */
    public int updateStatus(Connection conn, Order order) throws SQLException {
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            pstmt = conn.prepareStatement(UPDATE_ORDER_STATUS);
            pstmt.setString(1, order.getStatus());
            pstmt.setString(2, order.getId());
            result = pstmt.executeUpdate();
            logger.info("operate result:{}", result);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (pstmt != null)
                pstmt.close();
        }
        return result;
    }

    /**
     * 查询未部署订单
     *
     * @param conn
     * @param order
     * @return
     * @throws SQLException
     */
    public Order queryUNDeployOrder(Connection conn, Order order) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Order result = null;
        try {
            pstmt = conn.prepareStatement(QUERY_UNDEPLOY_ORDER_BY_CUSTOMER);
            pstmt.setString(1, order.getCustomerId());
            pstmt.setString(2, order.getIpAddress());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                result=new Order();
                result.setCustomerId(rs.getString("customer_id"));
                result.setId(rs.getString("id"));
                result.setIpAddress(rs.getString("ip_address"));
                result.setUserToken(rs.getString("user_token"));
                result.setOrderText(rs.getString("order_text"));
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
