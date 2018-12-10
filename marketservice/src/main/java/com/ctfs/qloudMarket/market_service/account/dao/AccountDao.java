package com.ctfs.qloudMarket.market_service.account.dao;

import com.ctfs.qloudMarket.market_service.account.pojo.AccountPojo;
import com.ctfs.qloudMarket.market_service.order_service.pojo.DeployType;
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
 * Time: 14:38
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class AccountDao {
    private static Logger logger= LoggerFactory.getLogger(AccountDao.class);
    private static String INSERT_ACCOUNT="insert into tb_account(id,name,hook,contact_num,email) values(?,?,?,?,?) ";
    private static String QUERY_ACCOUNT="select id,name,hook,contact_num,email from tb_account  WHERE status='N' and id=? ";
    private static String QUERY_ACCOUNT_PRODUCTS="select tt.* from tb_recomand_product ttm ,(SELECT tab.id,tab.name,tab.short_desc,tab.long_desc,tab.icon,tab.picture_path,tab.desc_url,tab.typeName,tab.categoryName,tab.vendorName ,tab.displayName from (\n" +
            "            select e.*,f.name as vendorName,f.display_name as displayName from \n" +
            "            (select c.*,d.name as categoryName from \n" +
            "            (select a.*,b.name as typeName from tb_product as a LEFT OUTER JOIN  tb_product_type as b on (a.type_id=b.id) ) as c \n" +
            "            LEFT OUTER JOIN tb_product_category as d on (c.category_id=d.id) ) e LEFT OUTER JOIN tb_vendor as f on (e.vendor_id=f.id) where e.status='N') as tab where 1=1 ) tt where ttm.account_id=? and ttm.product_id=tt.id ";

    private  static String QUERY_ACCOUNT_PRODUCT_TAGS="select a.* from tb_product_tag  a, tb_recomand_product b where a.product_id=b.product_id and b.account_id=? ";


   private static String QUERY_ACCOUNT_DEPLOY_ADDR="select attr_value from tb_account_attr where status='N' and attr_type='DEP_ADDR' and account_id = ?  and attr_key=? ";

   private final static  String BUY_ADDR="buy_addr";

    private final static  String TRY_ADDR="try_addr";

    public int addAccount(Connection connection, AccountPojo accountPojo) throws SQLException {
        int result=0;
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(INSERT_ACCOUNT);
            pstmt.setString(1, accountPojo.getId());
            pstmt.setString(2, accountPojo.getName());
            pstmt.setString(3, accountPojo.getHook());
            pstmt.setString(4, accountPojo.getContact());
            pstmt.setString(5, accountPojo.getEmail());
            result= pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw  e;
        } catch (Exception e) {
            e.printStackTrace();
            throw  e;
        } finally {
            try {
                if(pstmt!=null)
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw  e;
            }
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
    public AccountPojo getAccount(Connection connection, String id) throws SQLException {
        AccountPojo accountPojo=null;
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        logger.info("QUERY_ACCOUNT :{}",QUERY_ACCOUNT);
        try {
            //  connection = instance.getConnection();
            pstmt = connection.prepareStatement(QUERY_ACCOUNT);
            pstmt.setString(1, id);
            rs= pstmt.executeQuery();
            if(rs.next()){
                accountPojo=new AccountPojo();
                accountPojo.setId(rs.getString("id"));
                accountPojo.setName(rs.getString("name"));
                accountPojo.setHook(rs.getString("hook"));
                accountPojo.setContact(rs.getString("contact_num"));
                accountPojo.setEmail(rs.getString("email"));
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
        return accountPojo;
    }

    /**
     * 查询推荐商品
     * @param connection
     * @param accountID
     * @return
     * @throws Exception
     */
    public List<ProductPojo> queryRecommandProduct(Connection connection, String accountID) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        List<ProductPojo> result=null;

        try {
            String sbsql=QUERY_ACCOUNT_PRODUCTS;
            pstmt = connection.prepareStatement(sbsql);
            pstmt.setString(1, accountID);
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


    public List<ProductTagPojo> queryRecommandProductTags(Connection connection, String accountID) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        List<ProductTagPojo> result=null;

        try {
            String sbsql=QUERY_ACCOUNT_PRODUCT_TAGS;
            pstmt = connection.prepareStatement(sbsql);
            pstmt.setString(1, accountID);
            rs= pstmt.executeQuery();
            result=new ArrayList<>();
            while (rs.next()){
                ProductTagPojo artifactPojo=new ProductTagPojo();
                artifactPojo.setId(rs.getInt("id"));
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

    /**
     *
     * @param connection
     * @param account_id
     * @param addrId
     * @return
     * @throws SQLException
     */
    public String queryAccountAddr(Connection connection,String account_id,String addrId) throws SQLException {
        String url=null;
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        try {
            pstmt = connection.prepareStatement(QUERY_ACCOUNT_DEPLOY_ADDR);
            pstmt.setString(1, account_id);
            pstmt.setString(2, addrId);
            rs= pstmt.executeQuery();
            if (rs.next()){
                url=rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw  e;
        } finally {
            if(rs!=null)
                rs.close();
            if(pstmt!=null)
                pstmt.close();
        }
       return url;
    }


}
