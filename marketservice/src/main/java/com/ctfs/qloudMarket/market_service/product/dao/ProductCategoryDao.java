package com.ctfs.qloudMarket.market_service.product.dao;

import com.ctfs.qloudMarket.market_service.product.pojo.ProductCategory;
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
public class ProductCategoryDao {
    private static Logger logger = LoggerFactory.getLogger(ProductCategoryDao.class);

    private static String ADD_PRODUCT_TYPE="insert into tb_product_category(name,des) values(?,?)";
    private static String UPDATE_PRODUCT_TYPE="update  tb_product_category set name=?,des=? where id=?";
    private static String GET_PRODUCT_TYPE="select id,name,des from tb_product_category  where id=?";
    private static String GET_PRODUCT_TYPES="select id,des,name from tb_product_category where status='N'";
    private static String DELETE_PRODUCT_TYPE="update tb_product_category set status='Y' where  id=? ";



    public int addProductCategory(Connection connection, ProductCategory productTypePojo) throws SQLException {
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



    public ProductCategory getProductCategory(Connection connection, String id) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        ProductCategory result=null;
        try {
            pstmt = connection.prepareStatement(GET_PRODUCT_TYPE);
            pstmt.setString(1, id);
            rs= pstmt.executeQuery();
            result=new ProductCategory();
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
    public List<ProductCategory> queryProductCategorys(Connection connection) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        List<ProductCategory> result=null;
        StringBuffer sbsql=new StringBuffer(GET_PRODUCT_TYPES);
        try {
            pstmt = connection.prepareStatement(sbsql.toString());
            rs= pstmt.executeQuery();
            result=new ArrayList<>();
            while (rs.next()){
                ProductCategory productTagPojo=new ProductCategory();
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
    public int updateProductCategory(Connection connection,ProductCategory productTypePojo) throws SQLException {
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
    public int delProductCategory(Connection connection,String id) throws SQLException {
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
