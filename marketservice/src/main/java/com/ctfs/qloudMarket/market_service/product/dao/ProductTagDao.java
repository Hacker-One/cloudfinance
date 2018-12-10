package com.ctfs.qloudMarket.market_service.product.dao;

import com.ctfs.qloudMarket.market_service.product.pojo.ProductPojo;
import com.ctfs.qloudMarket.market_service.product.pojo.ProductTagPojo;
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
public class ProductTagDao {
    private static Logger logger = LoggerFactory.getLogger(ProductTagDao.class);

    private static String ADD_PRODUCT_TAG="insert into tb_product_tag(product_id,name) values(?,?)";
    private static String UPDATE_PRODUCT_TAG="update  tb_product_tag set name=? where id=? and product_id=?";
    private static String GET_PRODUCT_TAG="select id,product_id,name from tb_product_tag  where name=?";
    private static String GET_PRODUCT_TAGS="select id,product_id,name from tb_product_tag where  product_id=?";
    private static String DELETE_PRODUCT_TAG="delete from  tb_product_tag where product_id=? and  name=? ";



    public int addProductTag(Connection connection, ProductTagPojo productTagPojo) throws SQLException {
        int result=0;
        PreparedStatement pstmt = null;
        try {

            logger.info("sql:{}",ADD_PRODUCT_TAG);
            pstmt = connection.prepareStatement(ADD_PRODUCT_TAG);
            pstmt.setString(1, productTagPojo.getProductId());
            pstmt.setString(2, productTagPojo.getName());
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



    public ProductTagPojo getProductTag(Connection connection, String name) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        ProductTagPojo result=null;
        try {
            pstmt = connection.prepareStatement(GET_PRODUCT_TAG);
            pstmt.setString(1, name);
            rs= pstmt.executeQuery();
            result=new ProductTagPojo();
            while (rs.next()){
                result.setId(rs.getInt("id"));
                result.setName(rs.getString("name"));
                result.setProductId(rs.getString("product_id"));
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
     * 根据productID查询tag列表
     * @param connection
     * @param productId
     * @return
     * @throws Exception
     */
    public List<ProductTagPojo> queryProductTags(Connection connection, String productId) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        List<ProductTagPojo> result=null;
        StringBuffer sbsql=new StringBuffer(GET_PRODUCT_TAGS);
        try {
            pstmt = connection.prepareStatement(sbsql.toString());
            pstmt.setString(1, productId);
            rs= pstmt.executeQuery();
            result=new ArrayList<>();
            while (rs.next()){
                ProductTagPojo productTagPojo=new ProductTagPojo();
                productTagPojo.setId(rs.getInt("id"));
                productTagPojo.setName(rs.getString("name"));
                productTagPojo.setProductId(rs.getString("product_id"));
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


    public int updateProductTag(Connection connection,ProductTagPojo productTagPojo) throws SQLException {
        int result=0;
        PreparedStatement pstmt = null;
        try {

            logger.info("sql:{}",UPDATE_PRODUCT_TAG);
            pstmt = connection.prepareStatement(UPDATE_PRODUCT_TAG);
            pstmt.setString(1, productTagPojo.getName());
            pstmt.setInt(2, productTagPojo.getId());
            pstmt.setString(3, productTagPojo.getProductId());
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
     * 删除一个商品tag
     * @param connection
     * @param productId
     * @param name
     * @return
     * @throws SQLException
     */
    public int delProductTag(Connection connection,String productId,String name) throws SQLException {
        int result=0;
        PreparedStatement pstmt = null;
        try {

            logger.info("sql:{}",DELETE_PRODUCT_TAG);
            pstmt = connection.prepareStatement(DELETE_PRODUCT_TAG);
            pstmt.setString(1, productId);
            pstmt.setString(2, name);
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
