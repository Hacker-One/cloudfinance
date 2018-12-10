package com.ctfs.qloudMarket.market_service.product.service;

import com.ctfs.qloudMarket.market_service.product.dao.ProductTagDao;
import com.ctfs.qloudMarket.market_service.product.pojo.ProductPojo;
import com.ctfs.qloudMarket.market_service.product.pojo.ProductTagPojo;
import com.ctfs.qloudMarket.market_service.util.BaseService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/10/17
 * Time: 16:41
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public class ProductTagService extends BaseService {
    private static Logger logger = LoggerFactory.getLogger(ProductTagService.class);
    private ProductTagDao productTagDao=new ProductTagDao();

    public int addProductTag(Map productTags) throws Exception {
        Connection conn=null;
        int result=0;
        try {
            ProductTagPojo productTagPojo=  checkProductTag(productTags);
            if(productTagPojo!=null){
                conn=this.getConnection();
                result=   productTagDao.addProductTag(conn,productTagPojo);
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

    /**
     * 更新product
     * @param productTag
     * @return
     * @throws Exception
     */
    public int updateProductTag(Map productTag) throws Exception {
        Connection conn=null;
        int result=0;
        try {
            ProductTagPojo productTagPojo=  checkProductTag(productTag);
            if(productTagPojo!=null){
                productTagPojo.setId(Integer.parseInt((String)productTag.get("id")));
                conn=this.getConnection();
                result= productTagDao.updateProductTag(conn,productTagPojo);
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

    public List<ProductTagPojo> getProductTags(String productId) throws Exception {
        Connection conn=null;
        List<ProductTagPojo> result=null;
        try {
            conn = this.getConnection();
            result = productTagDao.queryProductTags(conn, productId);
        }catch (Exception e){
            logger.info(e.getLocalizedMessage());
            throw  e;
        }finally {
            if(conn!=null)
                conn.close();
        }
        return result;
    }


    public int deleteProductTag(String productId,String name) throws Exception {
        Connection conn=null;
        int result=0;
        try {
            conn=this.getConnection();
            result= productTagDao.delProductTag(conn,productId,name);
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


    public ProductTagPojo checkProductTag(Map productTag) throws Exception {
        ProductTagPojo productTagPojo=null;
        if(productTag!=null) {
            //type:,category:,name:,shortdesc:,icon:,longdesc:,picture:,url
            String name = (String) productTag.get("name");
            String productId = (String) productTag.get("productId");
            if(StringUtils.isNotEmpty(name)&&
                    StringUtils.isNotEmpty(productId)
                    ){
                productTagPojo=new ProductTagPojo();
                //  productPojo.setId(String.valueOf(idWorker.nextId()));
                productTagPojo.setName(name);
                productTagPojo.setProductId(productId);
            }
        }
        return productTagPojo;
    }

}
