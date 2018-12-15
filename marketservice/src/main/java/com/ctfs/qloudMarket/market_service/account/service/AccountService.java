package com.ctfs.qloudMarket.market_service.account.service;

import com.ctfs.qloudMarket.market_service.account.dao.AccountDao;
import com.ctfs.qloudMarket.market_service.account.pojo.AccountPojo;
import com.ctfs.qloudMarket.market_service.product.pojo.ProductPojo;
import com.ctfs.qloudMarket.market_service.product.pojo.ProductTagPojo;
import com.ctfs.qloudMarket.market_service.util.BaseService;
import com.ctfs.qloudMarket.market_service.util.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/10/16
 * Time: 15:03
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class AccountService   extends BaseService {
    private static Logger logger= LoggerFactory.getLogger(AccountService.class);

    private AccountDao accountDao=new AccountDao();

    public Map addAccount(Map account){
        logger.info("addAccount:{}",account);
        Map result=new HashMap();
        result.put("code","001");
        result.put("msg"," Error");
        AccountPojo accountPojo= JacksonUtils.map2pojo(account,AccountPojo.class);
        try {
            int r=  addAccountDB(accountPojo);
            if(r>0){
                result.put("code","000");
                result.put("msg","succeed");
            }else {
                result.put("code","002");
                result.put("msg","operate Error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int  addAccountDB(AccountPojo accountPojo ) throws Exception {
        logger.info("addAccountDB:{}",accountPojo);
        int result=0;
        Connection conn=null;
        try {
            conn=this.getConnection();
            result=accountDao.addAccount(conn,accountPojo);
            conn.commit();
        } catch (Exception e) {
            logger.info("error:{}",e.getStackTrace());
            throw  e;
        } finally {
            if(conn!=null) {
                conn.close();
            }
        }
        return result;
    }


    public Map<String,Object> getAccountById(String id) throws Exception {
        Map result=new HashMap();
        AccountPojo accountPojo= getAccount(id);
        if(accountPojo!=null){
            Map data= JacksonUtils.json2map(JacksonUtils.obj2json(accountPojo));
            result.put("code","000");
            result.put("msg","succeed");
            result.put("data",data);
        }else {
            result.put("code","002");
            result.put("msg","error");
        }
        return result;
    }

    public AccountPojo getAccount(String id) throws Exception {
        logger.info("getAccount:{}",id);
        AccountPojo result=null;
        Connection conn=null;
        try {
            conn=this.getConnection();
            result=accountDao.getAccount(conn,id);
        } catch (Exception e) {
            logger.info("error:{}",e.getStackTrace());
            throw  e;
        } finally {
            if(conn!=null) {
                conn.close();
            }
        }
        return result;
    }

    public Map<String,Object> getAccountProducts(String id) throws Exception {
        logger.info("getAccount:{}",id);
        Map<String,Object> result=null;
        Connection conn=null;
        result=new HashMap<>();
        try {
            conn=this.getConnection();
            List<ProductPojo> products=accountDao.queryRecommandProduct(conn,id);
            List<ProductTagPojo>  tags= accountDao.queryRecommandProductTags(conn,id);
            result.put("code","000");
            result.put("msg","succeed");
            if(products!=null&&products.size()>0) {
                Map<String,Object> data=new HashMap<>();
                data.put("products", products);
                data.put("tags", tags);
                result.put("data",data);
            }
        } catch (Exception e) {
            logger.info("error:{}",e.getStackTrace());
            throw  e;
        } finally {
            if(conn!=null) {
                conn.close();
            }
        }
        return result;
    }

}
