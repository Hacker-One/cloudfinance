package com.ctfs.qloudMarket.market_service.product.service;

import com.ctfs.qloudMarket.market_service.product.dao.ProductPictureDao;
import com.ctfs.qloudMarket.market_service.product.pojo.ProductPicture;
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
 * Date: 2018/10/19
 * Time: 16:38
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public class ProductPictureService extends BaseService {
    private static Logger logger = LoggerFactory.getLogger(ProductPictureService.class);
    private ProductPictureDao productPictureDao=new ProductPictureDao();

    /**
     *
     * @param productPicture
     * @return
     * @throws Exception
     */
    public int addProductPicture(Map productPicture) throws Exception {
        Connection conn=null;
        int result=0;
        try {
            ProductPicture productPicture1=  checkProductPicture(productPicture);
            if(productPicture1!=null){
                conn=this.getConnection();
                result=   productPictureDao.addProductPicture(conn,productPicture1);
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
    public int updateProductPicture(Map productTag) throws Exception {
        Connection conn=null;
        int result=0;
        try {
            ProductPicture productPicture1=  checkProductPicture(productTag);
            if(productPicture1!=null){
                productPicture1.setId(Integer.parseInt((String)productTag.get("id")));
                conn=this.getConnection();
                result= productPictureDao.updateProductPicture(conn,productPicture1);
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

    public List<ProductPicture> getProductPicture(String productId) throws Exception {
        Connection conn=null;
        List<ProductPicture> result=null;
        try {
            conn = this.getConnection();
            result = productPictureDao.getProductPictures(conn, productId);
        }catch (Exception e){
            logger.info(e.getLocalizedMessage());
            throw  e;
        }finally {
            if(conn!=null)
                conn.close();
        }
        return result;
    }


    public int deleteProductTag(int id) throws Exception {
        Connection conn=null;
        int result=0;
        try {
            conn=this.getConnection();
            result= productPictureDao.delProductPicture(conn,id);
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


    public ProductPicture checkProductPicture(Map productPic) throws Exception {
        ProductPicture productPicture=null;
        if(productPic!=null) {
            //type:,category:,name:,shortdesc:,icon:,longdesc:,picture:,url
            String url = (String) productPic.get("url");
            String sort= (String) productPic.get("sort");
            String productId=(String)productPic.get("productId");
            if(StringUtils.isNotEmpty(url)&&
                    StringUtils.isNotEmpty(productId)&&
                    StringUtils.isNotEmpty(sort)
                    ){
                productPicture=new ProductPicture();
                //  productPojo.setId(String.valueOf(idWorker.nextId()));
                productPicture.setUrl(url);
                productPicture.setProductId(productId);
                productPicture.setSort(Integer.parseInt(sort));
            }
        }
        return productPicture;
    }
}
