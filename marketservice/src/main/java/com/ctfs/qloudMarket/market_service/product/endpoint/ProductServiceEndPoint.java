package com.ctfs.qloudMarket.market_service.product.endpoint;

import com.ctfs.qloudMarket.market_service.artifact_service.pojo.ArtifactPojo;
import com.ctfs.qloudMarket.market_service.artifact_service.service.ArtifactService;
import com.ctfs.qloudMarket.market_service.order_service.pojo.OrderPojo;
import com.ctfs.qloudMarket.market_service.product.pojo.*;
import com.ctfs.qloudMarket.market_service.product.service.*;
import com.qloudfin.qloudbus.annotation.PathVariable;
import com.qloudfin.qloudbus.annotation.RequestMapping;
import com.qloudfin.qloudbus.annotation.RequestMethod;
import com.qloudfin.qloudbus.reactive.Callback;
import io.advantageous.qbit.annotation.RequestParam;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/10/16
 * Time: 11:48
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@RequestMapping("/")
public class ProductServiceEndPoint {
    private static Logger logger= LoggerFactory.getLogger(ProductServiceEndPoint.class);
    private  ProductService productService=new ProductService();
    private  ProductTagService productTagService=new ProductTagService();
    private  ArtifactService artifactService=new ArtifactService();
    private  ProductTypeService productTypeService=new ProductTypeService();
    private  ProductCategoryService productCategoryService=new ProductCategoryService();
    private  ProductPictureService productPictureService=new ProductPictureService();
    /**
     * 新增产品
     * @param callback
     * @param requestBody
     */
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public void addProduct(final Callback<Map> callback, final Map<String,Object>  requestBody){
        logger.info("addProduct:{}",requestBody);
        Map result=new HashMap();
        try {
            int r=   productService.addProduct(requestBody);
            if(r>0){
                result.put("code", "000");
                result.put("msg", "succeed");
            }else {
                result.put("code","002");
                result.put("msg","error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("error:{}",e.getStackTrace());
            result.put("code","001");
            result.put("msg","error");
        }
        callback.accept(result);
    }

    /**
     * 更新产品
     * @param callback
     * @param id
     * @param requestBody
     */
    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public void updateProduct(final Callback<Map> callback, @PathVariable("id") String id , final Map<String,Object>  requestBody){
        logger.info("updateProduct:{}",requestBody);
        Map result=new HashMap();
        try {
            requestBody.put("id",id);
            int r=   productService.updateProduct(requestBody);
            if(r>0) {
                result.put("code", "000");
                result.put("msg", "succeed");
            }else {
                result.put("code","002");
                result.put("msg","error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("error:{}",e.getStackTrace());
            result.put("code","001");
            result.put("msg","error");
        }
        callback.accept(result);
    }


    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public void getProduct(final Callback<Map> callback,  @PathVariable("id") String id ){
        logger.info("getProduct:{}",id);
        Map result=new HashMap();
        try {
            ProductPojo productPojo=   productService.getProduct(id);
            if(productPojo!=null) {
                result.put("code", "000");
                result.put("msg", "succeed");
                result.put("data",productPojo);
            }else {
                result.put("code","002");
                result.put("msg","error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("error:{}",e.getStackTrace());
            result.put("code","001");
            result.put("msg","error");
        }
        callback.accept(result);
    }


    @RequestMapping(value = "/products/list", method = RequestMethod.POST)
    public void getProducts(final Callback<Map> callback,final Map<String,Object>  requestBody){
        logger.info("getProductList:{}",requestBody);
        Map result=new HashMap();
        try {
            Map<String,Object> products=   productService.getProducts(requestBody);
                result.put("code", "000");
                result.put("msg", "succeed");
                result.put("data",products);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("error:{}",e.getStackTrace());
            result.put("code","001");
            result.put("msg","error");
        }
        callback.accept(result);
    }


//    @RequestMapping(value = "/products", method = RequestMethod.GET)
//    public void getProducts(final Callback<Map> callback,  @RequestParam("name") String name,@RequestParam("start") int start,@RequestParam("count") int count){
//        logger.info("getProducts:{} ,{},{}",name,start,count);
//        Map result=new HashMap();
//        try {
//            Map<String,Object> products=   productService.getProducts(name,start,count);
//            if(products!=null&&products.size()>0) {
//                result.put("code", "000");
//                result.put("msg", "succeed");
//                result.put("data",products);
//            }else {
//                result.put("code","002");
//                result.put("msg","error");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.info("error:{}",e.getStackTrace());
//            result.put("code","001");
//            result.put("msg","error");
//        }
//        callback.accept(result);
//    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public void delProducts(final Callback<Map> callback, @PathVariable("id") String id){
        logger.info("delProducts:{}",id);
        Map result=new HashMap();
        try {
            int r=   productService.deleteProduct(id);
            if(r>0) {
                result.put("code", "000");
                result.put("msg", "succeed");
            }else {
                result.put("code","002");
                result.put("msg","error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("error:{}",e.getStackTrace());
            result.put("code","001");
            result.put("msg","error");
        }
        callback.accept(result);
    }

    @RequestMapping(value = "/products/{id}/tags", method = RequestMethod.POST)
    public void addTag(final Callback<Map> callback, @PathVariable("id") String id,final Map<String,Object>  requestBody){
        logger.info("addTag:{}",requestBody);
        Map result=new HashMap();
        try {
            requestBody.put("productId",id);
            int r=   productTagService.addProductTag(requestBody);
            if(r>0) {
                result.put("code", "000");
                result.put("msg", "succeed");
            }else {
                result.put("code","002");
                result.put("msg","error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("error:{}",e.getStackTrace());
            result.put("code","001");
            result.put("msg","error");
        }
        callback.accept(result);
    }

    @RequestMapping(value = "/products/{id}/tags", method = RequestMethod.PUT)
    public void updateTag(final Callback<Map> callback, @PathVariable("id") String id,final Map<String,Object>  requestBody){
        logger.info("updateTag:{}",requestBody);
        Map result=new HashMap();
        try {
            requestBody.put("productId",id);
            int r=   productTagService.updateProductTag(requestBody);
            if(r>0) {
                result.put("code", "000");
                result.put("msg", "succeed");
            }else {
                result.put("code","002");
                result.put("msg","error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("error:{}",e.getStackTrace());
            result.put("code","001");
            result.put("msg","error");
        }
        callback.accept(result);
    }

    @RequestMapping(value = "/products/{id}/tags", method = RequestMethod.GET)
    public void getTag(final Callback<Map> callback, @PathVariable("id") String id){
        logger.info("getTag:{}",id);
        Map result=new HashMap();
        try {
            List<ProductTagPojo> productTags=  productTagService.getProductTags(id);
            if(productTags!=null&&productTags.size()>0) {
                result.put("code", "000");
                result.put("msg", "succeed");
                result.put("data",productTags);
            }else {
                result.put("code","002");
                result.put("msg","error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("error:{}",e.getStackTrace());
            result.put("code","001");
            result.put("msg","error");
        }
        callback.accept(result);
    }

    @RequestMapping(value = "/products/{id}/tags/{name}", method = RequestMethod.DELETE)
    public void delTag(final Callback<Map> callback, @PathVariable("id") String id,@PathVariable("name") String name){
        logger.info("delProducts:{}",id);
        Map result=new HashMap();
        try {
            int r=   productTagService.deleteProductTag(id,name);
            if(r>0) {
                result.put("code", "000");
                result.put("msg", "succeed");
            }else {
                result.put("code","002");
                result.put("msg","error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("error:{}",e.getStackTrace());
            result.put("code","001");
            result.put("msg","error");
        }
        callback.accept(result);
    }


    @RequestMapping(value = "/products/{id}/pictures", method = RequestMethod.POST)
    public void addPicture(final Callback<Map> callback, @PathVariable("id") String id,final Map<String,Object>  requestBody){
        logger.info("addPicture:{}",requestBody);
        Map result=new HashMap();
        try {
            requestBody.put("productId",id);
            int r=   productPictureService.addProductPicture(requestBody);
            if(r>0) {
                result.put("code", "000");
                result.put("msg", "succeed");
            }else {
                result.put("code","002");
                result.put("msg","error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("error:{}",e.getStackTrace());
            result.put("code","001");
            result.put("msg","error");
        }
        callback.accept(result);
    }

    @RequestMapping(value = "/products/{id}/pictures", method = RequestMethod.PUT)
    public void updatePicture(final Callback<Map> callback, @PathVariable("id") String id,final Map<String,Object>  requestBody){
        logger.info("updatePicture:{}",requestBody);
        Map result=new HashMap();
        try {
            requestBody.put("productId",id);
            int r=   productPictureService.updateProductPicture(requestBody);
            if(r>0) {
                result.put("code", "000");
                result.put("msg", "succeed");
            }else {
                result.put("code","002");
                result.put("msg","error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("error:{}",e.getStackTrace());
            result.put("code","001");
            result.put("msg","error");
        }
        callback.accept(result);
    }

    @RequestMapping(value = "/products/{id}/pictures", method = RequestMethod.GET)
    public void getPictures(final Callback<Map> callback, @PathVariable("id") String id){
        logger.info("getPicture:{}",id);
        Map result=new HashMap();
        try {
            List<ProductPicture> productTags=  productPictureService.getProductPicture(id);
            if(productTags!=null&&productTags.size()>0) {
                result.put("code", "000");
                result.put("msg", "succeed");
                result.put("data",productTags);
            }else {
                result.put("code","002");
                result.put("msg","error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("error:{}",e.getStackTrace());
            result.put("code","001");
            result.put("msg","error");
        }
        callback.accept(result);
    }

    @RequestMapping(value = "/products/{id}/pictures/{pid}", method = RequestMethod.DELETE)
    public void delPicture(final Callback<Map> callback, @PathVariable("id") String id,@PathVariable("pid") String pid){
        logger.info("delPicture:{}",id);
        Map result=new HashMap();
        try {
            int r=   productPictureService.deleteProductTag(Integer.parseInt(pid));
            if(r>0) {
                result.put("code", "000");
                result.put("msg", "succeed");
            }else {
                result.put("code","002");
                result.put("msg","error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("error:{}",e.getStackTrace());
            result.put("code","001");
            result.put("msg","error");
        }
        callback.accept(result);
    }

    @RequestMapping(value = "/products/{id}/artifacts", method = RequestMethod.POST)
    public void addArtifact(final Callback<Map> callback, @PathVariable("id") String id,final Map<String,Object>  requestBody){
        logger.info("addTag:{}",requestBody);
        Map result=new HashMap();
        try {
            requestBody.put("productId",id);
            int r=   artifactService.addArtifact(requestBody);
            if(r>0) {
                result.put("code", "000");
                result.put("msg", "succeed");
            }else {
                result.put("code","002");
                result.put("msg","error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("error:{}",e.getStackTrace());
            result.put("code","001");
            result.put("msg","error");
        }
        callback.accept(result);
    }

    @RequestMapping(value = "/products/{id}/artifacts", method = RequestMethod.PUT)
    public void updateArtifact(final Callback<Map> callback, @PathVariable("id") String id,final Map<String,Object>  requestBody){
        logger.info("addProduct:{}",requestBody);
        Map result=new HashMap();
        try {
            requestBody.put("productId",id);
            int r=   artifactService.updateArtifact(requestBody);
            if(r>0) {
                result.put("code", "000");
                result.put("msg", "succeed");
            }else {
                result.put("code","002");
                result.put("msg","error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("error:{}",e.getStackTrace());
            result.put("code","001");
            result.put("msg","error");
        }
        callback.accept(result);
    }

    @RequestMapping(value = "/products/{id}/artifacts", method = RequestMethod.GET)
    public void getArtifacts(final Callback<Map> callback, @PathVariable("id") String id){
        logger.info("getArtifact:{}",id);
        Map result=new HashMap();
        try {
            List<ArtifactPojo> data=artifactService.queryArtifact(id);
            result.put("code","000");
            result.put("msg","succeed");
            result.put("data",data);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("error:{}",e.getStackTrace());
            result.put("code","001");
            result.put("msg","error");
        }
        callback.accept(result);
    }


    @RequestMapping(value = "/products/types", method = RequestMethod.GET)
    public void getTypes(final Callback<Map> callback){
        logger.info("getTypes:");
        Map result=new HashMap();
        try {
            List<ProductTypePojo> productTypes=  productTypeService.getProductTags();
            if(productTypes!=null&&productTypes.size()>0) {
                result.put("code", "000");
                result.put("msg", "succeed");
                result.put("data",productTypes);
            }else {
                result.put("code","002");
                result.put("msg","error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("error:{}",e.getStackTrace());
            result.put("code","001");
            result.put("msg","error");
        }
        callback.accept(result);
    }


    @RequestMapping(value = "/products/categorys", method = RequestMethod.GET)
    public void getProductCategorys(final Callback<Map> callback){
        logger.info("getTypes:");
        Map result=new HashMap();
        try {
            List<ProductCategory> productTypes=  productCategoryService.getProductCategorys();
            if(productTypes!=null&&productTypes.size()>0) {
                result.put("code", "000");
                result.put("msg", "succeed");
                result.put("data",productTypes);
            }else {
                result.put("code","002");
                result.put("msg","error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("error:{}",e.getStackTrace());
            result.put("code","001");
            result.put("msg","error");
        }
        callback.accept(result);
    }

}
