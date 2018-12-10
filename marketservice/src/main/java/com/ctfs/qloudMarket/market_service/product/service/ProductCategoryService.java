package com.ctfs.qloudMarket.market_service.product.service;

import com.ctfs.qloudMarket.market_service.product.dao.ProductCategoryDao;
import com.ctfs.qloudMarket.market_service.product.dao.ProductTypeDao;
import com.ctfs.qloudMarket.market_service.product.pojo.ProductCategory;
import com.ctfs.qloudMarket.market_service.product.pojo.ProductTypePojo;
import com.ctfs.qloudMarket.market_service.util.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/10/18
 * Time: 15:41
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public class ProductCategoryService extends BaseService {
    private static Logger logger = LoggerFactory.getLogger(ProductCategoryService.class);
    private ProductCategoryDao productCategoryDao=new ProductCategoryDao();

    public List<ProductCategory> getProductCategorys() throws Exception {
        logger.info("getProductCategorys");
        Connection conn=null;
        List<ProductCategory> result=null;
        try {
            conn = this.getConnection();
            result = productCategoryDao.queryProductCategorys(conn);
        }catch (Exception e){
            logger.info(e.getLocalizedMessage());
            throw  e;
        }finally {
            if(conn!=null)
                conn.close();
        }
        return result;
    }

}
