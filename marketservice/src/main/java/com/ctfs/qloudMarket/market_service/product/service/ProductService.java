package com.ctfs.qloudMarket.market_service.product.service;

import com.ctfs.qloudMarket.market_service.license_service.pojo.LicensePojo;
import com.ctfs.qloudMarket.market_service.order_service.pojo.Order;
import com.ctfs.qloudMarket.market_service.order_service.pojo.OrderPojo;
import com.ctfs.qloudMarket.market_service.order_service.service.OrderService;
import com.ctfs.qloudMarket.market_service.product.dao.ProductDao;
import com.ctfs.qloudMarket.market_service.product.dao.ProductPictureDao;
import com.ctfs.qloudMarket.market_service.product.dao.ProductTagDao;
import com.ctfs.qloudMarket.market_service.product.pojo.ProductPicture;
import com.ctfs.qloudMarket.market_service.product.pojo.ProductPojo;
import com.ctfs.qloudMarket.market_service.product.pojo.ProductTagPojo;
import com.ctfs.qloudMarket.market_service.util.BaseService;
import com.ctfs.qloudMarket.market_service.util.IdWorker;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/10/17
 * Time: 15:10
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public class ProductService extends BaseService {
    private static Logger logger = LoggerFactory.getLogger(ProductService.class);
    private ProductDao productDao=new ProductDao();
    private ProductPictureDao productPictureDao=new ProductPictureDao();
    private ProductTagDao productTagDao=new ProductTagDao();

    IdWorker idWorker=IdWorker.getFlowIdWorkerInstance();
    public int addProduct(Map product) throws Exception {
        Connection conn=null;
        int result=0;
        try {
            logger.info("check \n");
            ProductPojo productPojo=  checkProduct(product);
            logger.info("ssss22");
            if(productPojo!=null){
                logger.info("ssss");
                productPojo.setId(String.valueOf(idWorker.nextId()));
                conn=this.getConnection();
                result= productDao.addProduct(conn,productPojo);
                conn.commit();
            }
        }catch (Exception e){
            logger.info("error {}",e.getStackTrace());
            throw  e;
        }finally {
            if(conn!=null)
                conn.close();
        }
        return result;
    }

    /**
     * 更新product
     * @param product
     * @return
     * @throws Exception
     */
    public int updateProduct(Map product) throws Exception {
        Connection conn=null;
        int result=0;
        try {
            ProductPojo productPojo=  checkProduct(product);
            if(productPojo!=null){
                productPojo.setId((String) product.get("id"));
                conn=this.getConnection();
                result= productDao.updateProduct(conn,productPojo);
                conn.commit();
            }
        }catch (Exception e){
            logger.info(e.getLocalizedMessage());
            throw  e;
        }finally {
            if(conn!=null)
                conn.close();
        }
        return result;
    }



    public ProductPojo getProduct(String productId) throws Exception {
        Connection conn=null;
        ProductPojo result=null;
        try {
            conn = this.getConnection();
            result = productDao.getProduct(conn, productId);
            if(result!=null) {
                List<ProductPicture> pictures = productPictureDao.getProductPictures(conn, result.getId());
                List<ProductTagPojo> tags = productTagDao.queryProductTags(conn, result.getId());
                result.setPictures(pictures);
                result.setTags(tags);
            }
        }catch (Exception e){
            logger.info(e.getLocalizedMessage());
            throw  e;
        }finally {
            if(conn!=null)
                conn.close();
        }
        return result;
    }

    public Map<String,Object> getProducts(Map<String,Object> data) throws Exception {
        Connection conn=null;
        Map<String,Object> result=null;
        List<ProductPojo> list=null;
        List<ProductTagPojo> tags=null;
        try {
            conn = this.getConnection();
            int size=productDao.queryProductsCount(conn, data);
            list = productDao.queryProducts(conn, data);
            tags = productDao.queryProductTags(conn,data);
           // conn.commit();
            result=new HashMap<>();
            result.put("size:",size);
            result.put("products:",list);
            result.put("tags:",tags);
        }catch (Exception e){
            logger.info(e.getLocalizedMessage());
            throw  e;
        }finally {
            if(conn!=null)
                conn.close();
        }
        return result;
    }

    public int deleteProduct(String productId) throws Exception {
        Connection conn=null;
        int result=0;
        try {
                conn=this.getConnection();
                result= productDao.delProduct(conn,productId);
                conn.commit();
        }catch (Exception e){
            logger.info(e.getLocalizedMessage());
            throw  e;
        }finally {
            if(conn!=null)
                conn.close();
        }
        return result;
    }



    public ProductPojo checkProduct(Map product) throws Exception {
        ProductPojo productPojo=null;
        if(product!=null) {
            //type:,category:,name:,shortdesc:,icon:,longdesc:,picture:,url
            String type = (String) product.get("type");
            String category = (String) product.get("category");
            String name = (String) product.get("name");
            String shortdesc = (String) product.get("shortdesc");
            String icon = (String) product.get("icon");
            String longdesc = (String) product.get("longdesc");
            String picture = (String) product.get("picture");
            String url = (String) product.get("url");
            String vendor = (String) product.get("vendor");
            if(StringUtils.isNotEmpty(type)&&
                    StringUtils.isNotEmpty(category)&&
                    StringUtils.isNotEmpty(name)&&
                    StringUtils.isNotEmpty(shortdesc)&&
                    StringUtils.isNotEmpty(icon)&&
                    StringUtils.isNotEmpty(longdesc)&&
                    StringUtils.isNotEmpty(picture)&&
                    StringUtils.isNotEmpty(url)&&
                    StringUtils.isNotEmpty(vendor)
                    ){
                productPojo=new ProductPojo();
              //  productPojo.setId(String.valueOf(idWorker.nextId()));
                productPojo.setName(name);
                productPojo.setType(Integer.parseInt(type));
                productPojo.setCategory(Integer.parseInt(category));
                productPojo.setShortDesc(shortdesc);
                productPojo.setIcon(icon);
                productPojo.setLongDesc(longdesc);
                productPojo.setVendor(Integer.parseInt(vendor));
                productPojo.setPicture(picture);
                productPojo.setDescUrl(url);
            }
        }
        return productPojo;
    }

    public boolean  isProductSale(String id) throws SQLException {
        boolean result=false;
        Connection conn=null;
        try {
            conn = this.getConnection();
            int r= productDao.queryProductsIsSale(conn,id);
            if(r>0){
                result=true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(conn!=null){
                conn.close();
            }
        }

        return result;
    }
}
