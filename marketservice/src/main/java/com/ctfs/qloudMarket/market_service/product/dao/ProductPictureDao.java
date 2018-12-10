package com.ctfs.qloudMarket.market_service.product.dao;

import com.ctfs.qloudMarket.market_service.product.pojo.ProductPicture;
import com.ctfs.qloudMarket.market_service.product.pojo.ProductTypePojo;
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
 * Date: 2018/10/19
 * Time: 16:12
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public class ProductPictureDao {
    private static Logger logger = LoggerFactory.getLogger(ProductPictureDao.class);
    private static String ADD_PRODUCT_PICTURE="insert into tb_product_picture(product_id,url,sort) values(?,?,?)";
    private static String UPDATE_PRODUCT_PICTURE="update  tb_product_picture set product_id=?,url=?,sort=? where id=?";
    private static String GET_PRODUCT_PICTURE="select id,product_id,url,sort from tb_product_picture  where status='N' and id=?";
    private static String GET_PRODUCT_PICTURES="select id,product_id,url,sort from tb_product_picture where status='N' and product_id=? order by sort";
    private static String DELETE_PRODUCT_PICTURE="update tb_product_picture set status='Y' where  id=? ";



    public int addProductPicture(Connection connection, ProductPicture productPicture) throws SQLException {
        int result=0;
        PreparedStatement pstmt = null;
        try {

            logger.info("sql:{}",ADD_PRODUCT_PICTURE);
            pstmt = connection.prepareStatement(ADD_PRODUCT_PICTURE);
            pstmt.setString(1, productPicture.getProductId());
            pstmt.setString(2, productPicture.getUrl());
            pstmt.setInt(3, productPicture.getSort());
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



    public ProductPicture getProductPicture(Connection connection, String id) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        ProductPicture result=null;
        try {
            pstmt = connection.prepareStatement(GET_PRODUCT_PICTURE);
            pstmt.setString(1, id);
            rs= pstmt.executeQuery();
            result=new ProductPicture();
            while (rs.next()){
                result.setId(rs.getInt("id"));
                result.setProductId(rs.getString("product_id"));
                result.setUrl(rs.getString("url"));
                result.setSort(rs.getInt("sort"));
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
    public List<ProductPicture> getProductPictures(Connection connection,String productId) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        List<ProductPicture> result=null;
        StringBuffer sbsql=new StringBuffer(GET_PRODUCT_PICTURES);
        try {
            pstmt = connection.prepareStatement(sbsql.toString());
            pstmt.setString(1,productId);
            rs= pstmt.executeQuery();
            result=new ArrayList<>();
            while (rs.next()){
                ProductPicture productPicture=new ProductPicture();
                productPicture.setId(rs.getInt("id"));
                productPicture.setProductId(rs.getString("product_id"));
                productPicture.setUrl(rs.getString("url"));
                productPicture.setSort(rs.getInt("sort"));
                result.add(productPicture);
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
     * @param productPicture
     * @return
     * @throws SQLException
     */
    public int updateProductPicture(Connection connection,ProductPicture productPicture) throws SQLException {
        int result=0;
        PreparedStatement pstmt = null;
        try {

            logger.info("sql:{}",UPDATE_PRODUCT_PICTURE);
            pstmt = connection.prepareStatement(UPDATE_PRODUCT_PICTURE);
            pstmt.setString(1, productPicture.getProductId());
            pstmt.setString(2,productPicture.getUrl());
            pstmt.setInt(3,productPicture.getSort());
            pstmt.setInt(4, productPicture.getId());
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
    public int delProductPicture(Connection connection,int id) throws SQLException {
        int result=0;
        PreparedStatement pstmt = null;
        try {

            logger.info("sql:{}",DELETE_PRODUCT_PICTURE);
            pstmt = connection.prepareStatement(DELETE_PRODUCT_PICTURE);
            pstmt.setInt(1, id);
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
