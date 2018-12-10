package com.ctfs.qloudMarket.market_service.product.dao;

import com.ctfs.qloudMarket.market_service.product.pojo.ProductTagPojo;
import com.ctfs.qloudMarket.market_service.product.pojo.ProductTypePojo;
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
 * Time: 17:29
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class ProductTypeDao {
    private static Logger logger = LoggerFactory.getLogger(ProductTypeDao.class);

    private static String ADD_PRODUCT_TYPE="insert into tb_product_type(name,des) values(?,?)";
    private static String UPDATE_PRODUCT_TYPE="update  tb_product_type set name=?,des=? where id=?";
    private static String GET_PRODUCT_TYPE="select id,name,des from tb_product_type  where id=?";
    private static String GET_PRODUCT_TYPES="select id,des,name from tb_product_type where status='N'";
    private static String DELETE_PRODUCT_TYPE="update tb_product_type set status='Y' where  id=? ";



    public int addProductType(Connection connection, ProductTypePojo productTypePojo) throws SQLException {
        int result=0;
        PreparedStatement pstmt = null;
        try {

            logger.info("sql:{}",ADD_PRODUCT_TYPE);
            pstmt = connection.prepareStatement(ADD_PRODUCT_TYPE);
            pstmt.setString(1, productTypePojo.getName());
            pstmt.setString(2, productTypePojo.getDesc());
            result=  pstmt.executeUpdate();
            logger.info("result:{}",result);
        } catch (SQLException e) {
            e.printStackTrace();
            throw  e;
        } catch (Exception e) {
            e.printStackTrace();
            throw  e;
        } finally {
            if (pstmt!=null)
                pstmt.close();
        }
        return result;
    }



    public ProductTypePojo getProductType(Connection connection, String id) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        ProductTypePojo result=null;
        try {
            pstmt = connection.prepareStatement(GET_PRODUCT_TYPE);
            pstmt.setString(1, id);
            rs= pstmt.executeQuery();
            result=new ProductTypePojo();
            while (rs.next()){
                result.setId(rs.getInt("id"));
                result.setName(rs.getString("name"));
                result.setDesc(rs.getString("des"));
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

    /**
     *
     * @param connection
     * @return
     * @throws Exception
     */
    public List<ProductTypePojo> queryProductTypes(Connection connection) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        List<ProductTypePojo> result=null;
        StringBuffer sbsql=new StringBuffer(GET_PRODUCT_TYPES);
        try {
            pstmt = connection.prepareStatement(sbsql.toString());
            rs= pstmt.executeQuery();
            result=new ArrayList<>();
            while (rs.next()){
                ProductTypePojo productTagPojo=new ProductTypePojo();
                productTagPojo.setId(rs.getInt("id"));
                productTagPojo.setName(rs.getString("name"));
                productTagPojo.setDesc(rs.getString("des"));
                result.add(productTagPojo);
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

    /**
     *
     * @param connection
     * @param productTypePojo
     * @return
     * @throws SQLException
     */
    public int updateProductType(Connection connection,ProductTypePojo productTypePojo) throws SQLException {
        int result=0;
        PreparedStatement pstmt = null;
        try {

            logger.info("sql:{}",UPDATE_PRODUCT_TYPE);
            pstmt = connection.prepareStatement(UPDATE_PRODUCT_TYPE);
            pstmt.setString(1, productTypePojo.getName());
            pstmt.setString(2, productTypePojo.getDesc());
            pstmt.setInt(3, productTypePojo.getId());
            result=  pstmt.executeUpdate();
            logger.info("result:{}",result);
        } catch (SQLException e) {
            e.printStackTrace();
            throw  e;
        } catch (Exception e) {
            e.printStackTrace();
            throw  e;
        } finally {
            if (pstmt!=null)
                pstmt.close();
        }
        return result;
    }

    /**
     *
     * @param connection
     * @param id
     * @return
     * @throws SQLException
     */
    public int delProduct(Connection connection,String id) throws SQLException {
        int result=0;
        PreparedStatement pstmt = null;
        try {

            logger.info("sql:{}",DELETE_PRODUCT_TYPE);
            pstmt = connection.prepareStatement(DELETE_PRODUCT_TYPE);
            pstmt.setString(1, id);
            result=  pstmt.executeUpdate();
            logger.info("result:{}",result);
        } catch (SQLException e) {
            e.printStackTrace();
            throw  e;
        } catch (Exception e) {
            e.printStackTrace();
            throw  e;
        } finally {
            if (pstmt!=null)
                pstmt.close();
        }
        return result;
    }
}
