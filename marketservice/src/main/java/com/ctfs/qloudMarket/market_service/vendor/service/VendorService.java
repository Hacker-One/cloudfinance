package com.ctfs.qloudMarket.market_service.vendor.service;

import com.ctfs.qloudMarket.market_service.product.pojo.ProductPojo;
import com.ctfs.qloudMarket.market_service.product.service.ProductService;
import com.ctfs.qloudMarket.market_service.util.BaseService;
import com.ctfs.qloudMarket.market_service.vendor.dao.VendorDao;
import com.ctfs.qloudMarket.market_service.vendor.pojo.VendorPojo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/10/17
 * Time: 17:30
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public class VendorService  extends BaseService {
    private static Logger logger = LoggerFactory.getLogger(VendorService.class);
    private VendorDao vendorDao=new VendorDao();


    public int addVendor(Map vendor) throws Exception {
        Connection conn=null;
        int result=0;
        try {
            VendorPojo vendorPojo=  checkProduct(vendor);
            if(vendorPojo!=null){
                conn=this.getConnection();
                result=  vendorDao.addVendor(conn,vendorPojo);
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


    public VendorPojo getVendor(int id) throws Exception {
        Connection conn=null;
        VendorPojo result=null;
        try {
            conn = this.getConnection();
            result = vendorDao.getVendor(conn, id);
        }catch (Exception e){
            logger.info(e.getLocalizedMessage());
            throw  e;
        }finally {
            if(conn!=null)
                conn.close();
        }
        return result;
    }


    public VendorPojo checkProduct(Map vendor) throws Exception {
        VendorPojo vendorPojo=null;
        if(vendor!=null) {
            //type:,category:,name:,shortdesc:,icon:,longdesc:,picture:,url
            String name = (String) vendor.get("name");
            String hook = (String) vendor.get("hook");
            String contact = (String) vendor.get("contact");
            String email = (String) vendor.get("email");
            String url = (String) vendor.get("url");
            String displayName=(String) vendor.get("disply.name");
            if(StringUtils.isNotEmpty(hook)&&
                    StringUtils.isNotEmpty(contact)&&
                    StringUtils.isNotEmpty(name)&&
                    StringUtils.isNotEmpty(email)&&
                    StringUtils.isNotEmpty(url)
                    ){
                vendorPojo=new VendorPojo();
                vendorPojo.setName(name);
                vendorPojo.setHook(hook);
                vendorPojo.setEmail(email);
                vendorPojo.setContact(contact);
                vendorPojo.setUrl(url);
                vendorPojo.setDisplayName(displayName);
            }
        }
        return vendorPojo;
    }
}
