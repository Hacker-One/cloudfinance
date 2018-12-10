package com.ctfs.qloudMarket.market_service.product.dao;

import com.ctfs.qloudMarket.market_service.artifact_service.pojo.ArtifactPojo;
import com.ctfs.qloudMarket.market_service.product.pojo.ProductPojo;
import com.ctfs.qloudMarket.market_service.product.pojo.ProductTagPojo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/10/16
 * Time: 16:48
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class ProductDao {
    private static Logger logger = LoggerFactory.getLogger(ProductDao.class);

    private static final String INSERT_PRODUCT="insert into tb_product(id,name,type_id,vendor_id,category_id,short_desc,icon,long_desc,picture_path,desc_url) values(?,?,?,?,?,?,?,?,?,?)";

    private static final String  UPDATE_PRODUCT="update tb_product set name=?,type_id=?,vendor_id=?,category_id=?,short_desc=?,icon=?,long_desc=?,picture_path=?,desc_url=? where id=? and status='N'";

   private static final String QUERY_PRODUCT="select id,name,type_id,vendor_id,category_id,short_desc,icon,long_desc,picture_path,desc_url from tb_product where id=? and status='N' ";

//    private static final String QUERY_PRODUCT="select id,name,type_id,vendor_id,category_id,short_desc,icon,long_desc,picture_path,desc_url from tb_product where id=? ";

    private static final String QUERY_PRODUCT_LIST="SELECT tab.id,tab.name,tab.short_desc,tab.long_desc,tab.icon,tab.picture_path,tab.desc_url,tab.typeName,tab.categoryName,tab.vendorName ,tab.displayName from (\n" +
            "select e.*,f.name as vendorName,f.display_name as displayName from \n" +
            "(select c.*,d.name as categoryName from \n" +
            "(select a.*,b.name as typeName from tb_product as a LEFT OUTER JOIN  tb_product_type as b on (a.type_id=b.id) ) as c\n" +
            "LEFT OUTER JOIN tb_product_category as d on (c.category_id=d.id) ) e LEFT OUTER JOIN tb_vendor as f on (e.vendor_id=f.id) where e.status='N') as tab where 1=1 ";

//    private static final String QUERY_PRODUCT_LIST="SELECT tab.id,tab.name,tab.short_desc,tab.long_desc,tab.icon,tab.picture_path,tab.desc_url,tab.typeName,tab.categoryName,tab.vendorName ,tab.displayName from (\n" +
//            "select e.*,f.name as vendorName,f.display_name as displayName from \n" +
//            "(select c.*,d.name as categoryName from \n" +
//            "(select a.*,b.name as typeName from tb_product as a LEFT OUTER JOIN  tb_product_type as b on (a.type_id=b.id) ) as c\n" +
//            "LEFT OUTER JOIN tb_product_category as d on (c.category_id=d.id) ) e LEFT OUTER JOIN tb_vendor as f on (e.vendor_id=f.id) ) as tab where 1=1 ";

    private static final String QUERY_PRODUCT_LIST_COUNT_BEGIN="select count(1) as size from ( ";
    private static final String QUERY_PRODUCT_LIST_COUNT_END=" ) as co";

    private static final String DELETE_PRODUCT="update tb_product set status='Y' where id=? ";


    private static final String QUERY_SEARCH_PRODUCT_BEGIN="select tab2.id as product_id ,x.name as name ,x.id as tag_id  from ( ";

    private static final String QUERY_SEARCH_PRODUCT_END=" ) as tab2 LEFT OUTER JOIN tb_product_tag x on  (tab2.id=x.product_id) ";

    private static final String limit=" limit ?,? ";

    private static final String QUERY_PRODUCT_SALE="select count(1) as size from tb_product_attr where product_id=? and LOWER(attr_key)='is_sale' and lower(attr_value)='true' and status='N' ";

    public int addProduct(Connection connection, ProductPojo productPojo) throws SQLException {
        int result=0;
        PreparedStatement pstmt = null;
        try {

            logger.info("sql:{}",INSERT_PRODUCT);
            pstmt = connection.prepareStatement(INSERT_PRODUCT);
            pstmt.setString(1, productPojo.getId());
            pstmt.setString(2, productPojo.getName());
            pstmt.setInt(3, productPojo.getType());
            pstmt.setInt(4, productPojo.getVendor());
            pstmt.setInt(5, productPojo.getCategory());
            pstmt.setString(6, productPojo.getShortDesc());
            pstmt.setString(7, productPojo.getIcon());
            pstmt.setString(8, productPojo.getLongDesc());
            pstmt.setString(9, productPojo.getPicture());
            pstmt.setString(10, productPojo.getDescUrl());
           // pstmt.setString(6, productPojo.getShortDesc());
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



    public ProductPojo getProduct(Connection connection, String productId) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        ProductPojo result=null;
        try {
            pstmt = connection.prepareStatement(QUERY_PRODUCT);
            pstmt.setString(1, productId);
            rs= pstmt.executeQuery();
            if (rs.next()){
                result=new ProductPojo();
                result.setId(rs.getString("id"));
                result.setName(rs.getString("name"));
                result.setType(rs.getInt("type_id"));
                result.setVendor(rs.getInt("vendor_id"));
                result.setCategory(rs.getInt("category_id"));
                result.setShortDesc(rs.getString("short_desc"));
                result.setLongDesc(rs.getString("long_desc"));
                result.setIcon(rs.getString("icon"));
                result.setPicture(rs.getString("picture_path"));
                result.setDescUrl(rs.getString("desc_url"));
            }
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


    public List<ProductTagPojo> queryProductTags(Connection connection, Map<String,Object> data) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        List<ProductTagPojo> result=null;

        try {
            StringBuffer ss=new StringBuffer(QUERY_SEARCH_PRODUCT_BEGIN);
            String sbsql=createSqlStatement(true,QUERY_PRODUCT_LIST,data);
            ss.append(sbsql).append(QUERY_SEARCH_PRODUCT_END);
            pstmt = connection.prepareStatement(ss.toString());
            Integer start=Integer.parseInt((String)data.get("start"));
            Integer count=Integer.parseInt((String)data.get("count"));
            pstmt.setInt(1, start);
            pstmt.setInt(2, count);
            rs= pstmt.executeQuery();
            result=new ArrayList<>();
            while (rs.next()){
                ProductTagPojo artifactPojo=new ProductTagPojo();
                artifactPojo.setId(rs.getInt("tag_id"));
                artifactPojo.setName(rs.getString("name"));
                artifactPojo.setProductId(rs.getString("product_id"));
                result.add(artifactPojo);
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

    public List<ProductPojo> queryProducts(Connection connection,Map<String,Object> data) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        List<ProductPojo> result=null;

        try {
            String sbsql=createSqlStatement(true,QUERY_PRODUCT_LIST,data);
            pstmt = connection.prepareStatement(sbsql);
            Integer start=Integer.parseInt((String)data.get("start"));
            Integer count=Integer.parseInt((String)data.get("count"));
            pstmt.setInt(1, start);
            pstmt.setInt(2, count);
            rs= pstmt.executeQuery();
            result=new ArrayList<>();
            while (rs.next()){
                ProductPojo artifactPojo=new ProductPojo();
                artifactPojo.setId(rs.getString("id"));
                artifactPojo.setName(rs.getString("name"));
                artifactPojo.setTypeName(rs.getString("typeName"));
                artifactPojo.setVendorName(rs.getString("vendorName"));
                artifactPojo.setVendorDisplay(rs.getString("displayName"));
                artifactPojo.setCategoryName(rs.getString("categoryName"));
                artifactPojo.setShortDesc(rs.getString("short_desc"));
                artifactPojo.setLongDesc(rs.getString("long_desc"));
                artifactPojo.setIcon(rs.getString("icon"));
                artifactPojo.setPicture(rs.getString("picture_path"));
                artifactPojo.setDescUrl(rs.getString("desc_url"));
                result.add(artifactPojo);
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
     * @param page
     * @param bassql
     * @param data
     * @return
     * @throws Exception
     *
     */
    private String  createSqlStatement(boolean page,String bassql,Map<String,Object> data) throws Exception {
        String sql=null;
        try {
            Object nameObj =  data.get("name");
            Object typeNameObj =  data.get("typeName");
            Object categoryNameObj = data.get("categoryName");
            Object vendorNameObj = data.get("vendorName");
            Object descObj=data.get("desc");
            Object ascObj=data.get("asc");
            Object startObj =data.get("start");
            Object countObj =  data.get("count");
            StringBuffer sqlsb =page?new StringBuffer(bassql):new StringBuffer(QUERY_PRODUCT_LIST_COUNT_BEGIN).append(bassql);
           if(startObj!=null&&countObj!=null){
               if(nameObj!=null){
                   String name=(String)nameObj;
                   sqlsb.append(" and tab.name like concat('%','"+name+"','%') ");
               }

               if(typeNameObj!=null){
                   String type=(String)typeNameObj;
                   sqlsb.append(" and tab.typeName like concat('%','"+type+"','%') ");
               }

               if(categoryNameObj!=null){
                   String category=(String)categoryNameObj;
                   sqlsb.append(" and tab.categoryName like concat('%','"+category+"','%') ");
               }

               if(vendorNameObj!=null){
                   String vendor=(String)vendorNameObj;
                   sqlsb.append(" and tab.vendorName like concat('%','"+vendor+"','%') ");
               }
               if(page) {
                   StringBuffer temp=new StringBuffer();
                   if (descObj != null) {
                        String desc=(String) descObj;
                        temp.append(" ").append(desc).append(" desc ,");
                   }
                   if(ascObj!=null){

                       String asc=(String) ascObj;
                       temp.append(" ").append(asc).append(" asc ,");
                   }
                  if(descObj!=null||ascObj!=null){
                      sqlsb.append(" order by ").append(temp);
                      sqlsb=new StringBuffer(sqlsb.substring(0,sqlsb.length()-1));
                  }else
                  {
                      sqlsb.append(" order by id asc");
                  }
                   sqlsb.append(limit);
//                   Integer start=Integer.parseInt((String)startObj);
//                   Integer count=Integer.parseInt((String)countObj);

                   sql=sqlsb.toString();
                   logger.info("\n sql {}",sql);
               }else {
                   sqlsb.append(QUERY_PRODUCT_LIST_COUNT_END);
                   sql=sqlsb.toString();
                   logger.info("\n count sql {}",sql);
               }

           }
           else {
               throw  new Exception();
           }
        }catch (Exception e){
            throw  e;
        }
        return sql;
    }

    public int queryProductsCount(Connection connection, Map<String,Object> data) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        int result=0;
        try {
            String  sql=createSqlStatement(false,QUERY_PRODUCT_LIST,data);
            pstmt=connection.prepareStatement(sql);
            rs= pstmt.executeQuery();
            while (rs.next()){
                result=rs.getInt("size");
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


    public int updateProduct(Connection connection,ProductPojo productPojo) throws SQLException {
        int result=0;
        PreparedStatement pstmt = null;
        try {

            logger.info("sql:{}",UPDATE_PRODUCT);
            pstmt = connection.prepareStatement(UPDATE_PRODUCT);
            pstmt.setString(1, productPojo.getName());
            pstmt.setInt(2, productPojo.getType());
            pstmt.setInt(3, productPojo.getVendor());
            pstmt.setInt(4, productPojo.getCategory());
            pstmt.setString(5, productPojo.getShortDesc());
            pstmt.setString(6, productPojo.getIcon());
            pstmt.setString(7, productPojo.getLongDesc());
            pstmt.setString(8, productPojo.getPicture());
            pstmt.setString(9, productPojo.getDescUrl());
            pstmt.setString(10, productPojo.getId());
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


    public int delProduct(Connection connection,String id) throws SQLException {
        int result=0;
        PreparedStatement pstmt = null;
        try {

            logger.info("sql:{}",DELETE_PRODUCT);
            pstmt = connection.prepareStatement(DELETE_PRODUCT);
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



    public int queryProductsIsSale(Connection connection, String pid) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        int result=0;
        try {
           // String  sql=QUERY_PRODUCT_SALE;
            pstmt=connection.prepareStatement(QUERY_PRODUCT_SALE);
            pstmt.setString(1,pid);
            rs= pstmt.executeQuery();
            while (rs.next()){
                result=rs.getInt("size");
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
}
