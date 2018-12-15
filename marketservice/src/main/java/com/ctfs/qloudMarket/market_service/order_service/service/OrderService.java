package com.ctfs.qloudMarket.market_service.order_service.service;

import com.ctfs.qloudMarket.market_service.account.dao.AccountDao;
import com.ctfs.qloudMarket.market_service.account.pojo.AccountPojo;
import com.ctfs.qloudMarket.market_service.order_service.pojo.DeployType;
import com.ctfs.qloudMarket.market_service.account.service.AccountService;
import com.ctfs.qloudMarket.market_service.agent_service.service.AgentService;
import com.ctfs.qloudMarket.market_service.license_service.dao.LicenseInfoDao;
import com.ctfs.qloudMarket.market_service.license_service.pojo.LicensePojo;
import com.ctfs.qloudMarket.market_service.license_service.server.LicenseService;
import com.ctfs.qloudMarket.market_service.order_service.dao.OrderDao;
import com.ctfs.qloudMarket.market_service.order_service.pojo.Order;
import com.ctfs.qloudMarket.market_service.order_service.pojo.OrderPojo;
import com.ctfs.qloudMarket.market_service.product.dao.ProductDao;
import com.ctfs.qloudMarket.market_service.product.pojo.ProductPojo;
import com.ctfs.qloudMarket.market_service.util.*;
import com.ctfs.qloudMarket.market_service.vendor.dao.VendorDao;
import com.ctfs.qloudMarket.market_service.vendor.pojo.VendorPojo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/8/23
 * Time: 16:04
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class OrderService extends BaseService {

    private static Logger logger = LoggerFactory.getLogger(OrderService.class);
    private OrderDao orderDao=new OrderDao();
    private ProductDao productDao=new ProductDao();
    private VendorDao vendorDao=new VendorDao();
    private AccountService accountService=new AccountService();
    private AccountDao accountDao=new AccountDao();
    private AgentService agentService=new AgentService();
    private LicenseInfoDao licenseInfoDao=new LicenseInfoDao();
    private LicenseService licenseService=new LicenseService();
    private static String ORDER_TYPE_BUY="buy";
    private static String ORDER_TYPE_TRY="try";
    private HTTPUtils httpUtils=new HTTPUtils();
    private static String SAMPLE_DEPLOYER_ADDRESS=Common.getPropertiesKey(Common.SAMPLE_ENVIROMENT_DEPLOYER_ADDRESS_KEY);

    /**
     * create order
     * @param order
     * @return
     */
    public int createOrder(Order order) throws Exception {
        Connection conn=null;
        int result=0;
        try {
            conn=this.getConnection();
            result= orderDao.insertOrder(conn,order);
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


    public OrderPojo getOrderByPcode(String pcode) throws Exception {
        Connection conn=null;
        OrderPojo result=null;
        try {
            conn = this.getConnection();
            result = orderDao.queryOrderByPcode(conn,pcode);
        }catch (Exception e){
            logger.info(e.getLocalizedMessage());
            throw  e;
        }finally {
            if(conn!=null)
                conn.close();
        }
        return result;
    }


    public Map getOrderService(String orderId) throws Exception {
        Map result=new HashMap();
        OrderPojo orderPojo = getOrder(orderId);
        result.put("code", "000");
        result.put("msg", "succeed");
        result.put("data", orderPojo);
        return result;
    }

    public OrderPojo getOrder(String orderId) throws Exception {
        Connection conn=null;
        OrderPojo result=null;
        try {
            conn = this.getConnection();
            result = orderDao.queryOrder(conn, orderId);
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


    public OrderPojo getOrder(String account,String product) throws Exception {
        Connection conn=null;
        OrderPojo result=null;
        try {
            conn = this.getConnection();
            result = orderDao.queryOrder(conn, account,product);
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



    public Map<String,Object> getValidOrderService(String account,String product) throws Exception {
        Map<String,Object> result=new HashMap<>();
        OrderPojo orderPojo =getValidOrder(account,product);
        result.put("code", "000");
        result.put("msg", "succeed");
        result.put("data", orderPojo);
        return result;
    }


    /**
     * 查询一个证书有效的订单
     * @param account
     * @param product
     * @return
     * @throws Exception
     */
    public OrderPojo getValidOrder(String account,String product) throws Exception {
        Connection conn=null;
        OrderPojo result=null;
        try {
            conn = this.getConnection();
            result = orderDao.queryValidOrder(conn, account,product);
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



    public Map<String,Object> getOrderList(String accountId,int begin,int count) throws Exception {
        Map<String,Object> result=new HashMap<>();
        Map<String, Object> orderPojo = getOrders(accountId, begin, count);
            result.put("code", "000");
            result.put("msg", "succeed");
            result.put("data", orderPojo);
        return result;
    }

    /**
     * query orders
     * @param accountId
     * @return
     * @throws Exception
     */
    public Map<String,Object> getOrders(String accountId,int begin,int count) throws Exception {
        Connection conn=null;
        Map<String,Object> result=null;
        List<OrderPojo> list=null;
        try {
            conn = this.getConnection();
            int size=orderDao.queryOrderCount(conn, accountId);
            list = orderDao.queryOrders(conn, accountId,begin,count);
            conn.commit();
            result=new HashMap<>();
            result.put("size:",size);
            result.put("orders:",list);
        }catch (Exception e){
            logger.info(e.getLocalizedMessage());
            throw  e;
        }finally {
            if(conn!=null)
                conn.close();
        }
        return result;
    }

//    public LicensePojo requestLicense(OrderPojo orderPojo) throws Exception {
//        LicensePojo licensePojo=null;
//        Connection conn=null;
//        String productId= orderPojo.getProductId();
//        String pcode=orderPojo.getPurchaseCode();
//        try {
//            conn = this.getConnection();
//            ProductPojo productPojo= productDao.getProduct(conn,productId);
//            if(productPojo!=null){
//               int vendorId= productPojo.getVendor();
//               VendorPojo vendorPojo= vendorDao.getVendor(conn,vendorId);
//               String hookAddress=vendorPojo.getHook();
//               if(StringUtils.isNotEmpty(hookAddress)){
//                   licensePojo= getLicense(pcode,hookAddress);
//               }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw  e;
//        }
//        return licensePojo;
//    }

//    public LicensePojo requestLicense(String pcode, String hookAddress) throws Exception {
//        LicensePojo licensePojo=null;
//        try {
//             licensePojo= getLicense(pcode,hookAddress);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw  e;
//        }
//        return licensePojo;
//    }



    private LicensePojo getLicense(String pcode,String hookAddress) throws Exception {
        LicensePojo licensePojo=null;
        try {
            Map sendData=new HashMap();
            sendData.put("pcode",pcode);
            String sendStr= JacksonUtils.mapToJson(sendData);
            String response= httpUtils.sendJsonBody(hookAddress,HTTPUtils.METHOD_POST,new HashMap<>(),sendStr.getBytes("utf-8"));
            logger.info("\n response info {}",response);
            Map licenseData=JacksonUtils.json2map(response);
            if("000".equals((String)licenseData.get("code"))){
                licensePojo=new LicensePojo();
                licensePojo.setDeploy(false);
                Map data= (Map) licenseData.get("data");
                if(data!=null&&data.size()>0){
                   // logger.info("\n\n\n\n {}\n\n\n\n {}",data.get("expiredDate"),data.get("content"));
                    licensePojo.setDeploy(true);
                    licensePojo.setExpiredDate((String) data.get("expiredDate"));
                    licensePojo.setContent((String) data.get("content"));
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            throw  e;
        }
        return licensePojo;
    }

    /**
     * check order
     * @param orderPojo
     * @return
     * @throws Exception
     */
    private Map<String,Object> checkOrderRequire(OrderPojo orderPojo) throws Exception {
        Map<String,Object> result=new HashMap();
        result.put("R",false);
        result.put("code","001");
        result.put("msg","error");
        Connection conn=null;
        try {

            VendorPojo vendorPojo=null;
            AccountPojo accountPojo= null;
            ProductPojo productPojo=null;
            LicensePojo licensePojo=null;
            String accountId=orderPojo.getAccountId();
            String productId=orderPojo.getProductId();
            String url =SAMPLE_DEPLOYER_ADDRESS;  //默认为 体验区的环境

            conn=this.getConnection();
            //check account exist
            accountPojo=accountDao.getAccount(conn,accountId);
            if(accountPojo==null){
                result.put("code","202");
                result.put("msg","用户不存在");
                return result;
            }
            //check product exist
            productPojo= productDao.getProduct(conn,productId);
            if(productPojo==null){
                result.put("code","209");
                result.put("msg","商品不存在");
                return result;
            }
            //check product is could sale
            if(productDao.queryProductsIsSale(conn,productId)>0){
                result.put("code","212");
                result.put("msg","商品不能购买");
                return result;
            }
            //check product's vendor exist
            int vendorId=productPojo.getVendor();
            vendorPojo= vendorDao.getVendor(conn,vendorId);
            if(vendorPojo==null){
                result.put("code","210");
                result.put("msg","供应商不存在");
                return result;
            }
            OrderPojo checkOrder=getValidOrder(accountId,productId); //get last created order that is not pending
            logger.info("old ORDER :{}",checkOrder);
            if(checkOrder!=null){
                   if(ORDER_TYPE_BUY.equals(checkOrder.getOrderType())&&ORDER_TYPE_TRY.equals(orderPojo.getOrderType())){
                        result.put("code","213");
                        result.put("msg","已经购买的商品不支持试用！");
                        return result;
                   }
                if(ORDER_TYPE_TRY.equals(checkOrder.getOrderType())&&ORDER_TYPE_TRY.equals(orderPojo.getOrderType())){
                    result.put("code","215");
                    result.put("msg","你已经有一个验商品在体验区");
                    return result;
                }
                if(ORDER_TYPE_TRY.equals(checkOrder.getOrderType())&&ORDER_TYPE_BUY.equals(orderPojo.getOrderType())){
                    checkOrder=null;
                }

            }
            if(DeployType.tyBuy.getName().equals(orderPojo.getOrderType())) {
                url = accountDao.queryAccountAddr(conn, accountId,orderPojo.getAddrId()); //非体验区从账户属性表中获取部署地址
            }
            orderPojo.setDeployAddr(url);
            accountPojo.setHook(url);
            result.put("R",true);
            result.put("code","000");
            result.put("msg","ok");
            result.put("vendor",vendorPojo);
            result.put("account",accountPojo);
            result.put("oldorder",checkOrder);
        } catch (Exception e)  {
            logger.info(e.getLocalizedMessage());
            throw  e;
        }finally {
          if(conn!=null)
             conn.close();
        }

        return result;
    }


    public Map<String,Object> deployerOrder(OrderPojo pojo,AccountPojo accountPojo,LicensePojo licensePojo) throws Exception {
        String accountId=pojo.getAccountId();
        String productId=pojo.getProductId();
        Map result=new HashMap();
        if(licensePojo!=null) {
            if(licensePojo.isDeploy()) {
                licensePojo.setPcode(pojo.getPurchaseCode());
                licensePojo.setAccount(accountId);
                licensePojo.setProduct(productId);
                licensePojo.setCreateDate(DateUtils.getCurrentDateTime());
                if (updateOrderLicense(pojo.getId(), licensePojo)) {
                    if (StringUtils.isNotEmpty(accountPojo.getHook())) {
                        logger.info("\n notify deployer");
                        result = notifyOrder(pojo.getId(), accountPojo.getHook());
                    } else {
                        result.put("code", "000");
                        result.put("msg", "订单成功，请手动进行商品下载。");
                    }
                } else {
                    result.put("code", "205");
                    result.put("msg", "更新订单证书失败");
                }
            }else {
                result.put("code", "000");
                result.put("msg", "请等待证书同步");
            }
        }else{
            result.put("code","204");
            result.put("msg","获取产品证书失败");
        }
        return result;
    }

    /**
     *  需要修改 去掉pcode 在vender service 保存 product and account
     * @param license
     * @return
     * @throws Exception
     */
    public Map addOrderLicense(Map license) throws Exception {
        Map result=new HashMap();

        try {
            if (license != null && license.size() > 0) { //保存证书
               String pcode=  (String)license.get("pcode");
               logger.info("pcode:{}",pcode);
              if(StringUtils.isNotEmpty(pcode)) {
                //  OrderPojo orderPojo = getOrderByPcode(pcode);
                  OrderPojo orderPojo = getOrderByPcode(pcode);
                  if (orderPojo != null) {
//                      license.put("account",orderPojo.getAccountId());
//                      license.put("product",orderPojo.getProductId());
                      //封装一下license
                      LicensePojo   licensePojo=  new LicensePojo();
                      licensePojo.setExpiredDate((String) license.get("expiredDate"));
                      licensePojo.setContent((String) license.get("content"));
                      licensePojo.setDeploy(true);
                      AccountPojo accountPojo = accountService.getAccount(orderPojo.getAccountId());
                     result= deployerOrder(orderPojo, accountPojo, licensePojo);
                  } else {
                      result.put("code", "212");
                      result.put("msg", "order not found");
                  }
              }else {
                  result.put("code", "211");
                  result.put("msg", "Pcode is null");
              }
            } else {
                result.put("code", "201");
                result.put("msg", "订单信息错误");
            }
        }catch (Exception e){
           throw e;
        }
        return result;
    }



    public Map orderCreater2(Map order) throws Exception {

        OrderPojo orderPojo=  checkOrder(order);
        Map result=new HashMap();
        result.put("code","006");
        result.put("msg","operate error");
        if(orderPojo!=null) {
           // String orderType=orderPojo.getOrderType();
            String pcode= orderPojo.getPurchaseCode();
            Map res= checkOrderRequire(orderPojo);
            if((boolean)res.get("R")){
                OrderPojo pojo =null;
                OrderPojo oldOrder= (OrderPojo) res.get("oldorder");
                LicensePojo licensePojo=null;
                if(oldOrder==null){ //如果旧的order 不存在 则创建
                    pojo = createOrder(orderPojo);
                }
                if(oldOrder!=null){
                    pojo=oldOrder;
                }
                if(pojo!=null){
                    VendorPojo vendorPojo= (VendorPojo) res.get("vendor");
                    AccountPojo accountPojo= (AccountPojo) res.get("account");
                    if(oldOrder==null) {
                        if (DeployType.tyBuy.getName().equals(pojo.getOrderType())) {
                            licensePojo = getLicense(pcode, vendorPojo.getHook()); //获取证书
                        } else {
                            licensePojo = new LicensePojo();
                            licensePojo.setDeploy(true);
                            licensePojo.setContent((String) order.get("pcode"));
                            licensePojo.setExpiredDate(DateUtils.getAroundDateByHour(-24, DateUtils.getCurrentDateTime(), DateUtils.DATESHORTFORMAT));
                        }
                        result = deployerOrder(pojo,accountPojo,licensePojo);//如果属于新创建订单 则走新订单流程
                    }else {
                        if (DeployType.tyTry.getName().equals(oldOrder.getOrderType())) {
                            licensePojo = getLicense(pcode, vendorPojo.getHook()); //获取证书
                            result = deployerOrder(pojo,accountPojo,licensePojo);
                        } else {
                            logger.info("old order exist notify deployer");
                            result = notifyOrder(pojo.getId(), accountPojo.getHook());//如果是第二次部署则走这个流程
                        }
                    }
                }else {
                    result.put("code","203");
                    result.put("msg","创建订单失败");
                }
            }else {
                result.put("code",res.get("code"));
                result.put("msg",res.get("msg"));
            }
        }else {
//            logger.info("");
            result.put("code","201");
            result.put("msg","订单信息错误");
        }
        return result;
    }



    private boolean updateOrderLicense(String orderId,LicensePojo licensePojo) throws Exception {
        Connection conn=null;
        boolean result=false;
        int r=0;
        try {
            conn = this.getConnection();
                r = licenseInfoDao.putLicense(conn, licensePojo);
                if (r > 0) {
                    licensePojo = licenseInfoDao.getLicenseByPcode(conn, licensePojo.getPcode());
                    r += orderDao.addLicense(conn, licensePojo.getId(), "paid", orderId);
                }
            conn.commit();
            result=  r>1?true:false;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }finally {
            if(conn!=null)
                conn.close();
        }
        return result;
    }

    private boolean insertLicense(LicensePojo licensePojo) throws Exception {
        Connection conn=null;
        boolean result=false;
        try {
            conn = this.getConnection();
         int r=   licenseInfoDao.putLicense(conn,licensePojo);
            conn.commit();
            result=  r>0?true:false;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }finally {
           this.closeConnection(conn);
        }
        return result;
    }

    private  LicensePojo queryLicense(String productId,String account) throws Exception {
        Connection conn=null;
        LicensePojo licensePojo=null;
        try {
            conn = this.getConnection();
            licensePojo = licenseInfoDao.getLicense(conn, productId, account);
        }catch (Exception e){
            logger.info(e.getLocalizedMessage());
            throw  e;
        }finally {
            if(conn!=null)
                conn.close();
        }
        return licensePojo;
    }

    private boolean updateOrderByAccountAndProduct(String account,String product) throws Exception {
        boolean result=false;
        Connection conn=null;
        try {
            conn = this.getConnection();
            int r=orderDao.updateOrder(conn, account,product);
            conn.commit();
            if(r>0){
                result=true;
            }
        } catch (Exception e){
            logger.info(e.getLocalizedMessage());
            throw  e;
        }finally {
            if(conn!=null)
                conn.close();
        }
        return result;
    }



    private boolean updateOrder(String orderId,int licenseId) throws Exception {
        Connection conn=null;
        boolean result=false;
        try {
               conn = this.getConnection();
               OrderPojo orderPojo = orderDao.queryOrder(conn, orderId);
               orderPojo.setOrderStatus("paid");
               int r= orderDao.addLicense(conn,licenseId,"paid",orderId);
               conn.commit();
               if(r>0){
                   result=true;
               }
        } catch (Exception e){
            logger.info(e.getLocalizedMessage());
            throw  e;
        }finally {
            if(conn!=null)
                conn.close();
        }
        return result;
    }


    public Map<String,Object> updateOrderStatusInfo(String id,Map requestBody) throws Exception {
        String msg = (String) requestBody.get("msg");
        Map result = new HashMap();
        String data = JacksonUtils.mapToJson((Map)requestBody.get("data"));
        if (org.apache.commons.lang.StringUtils.isNotEmpty(id) && requestBody != null) {
            if (updateOrderStatus(id, data, msg)) {
                result.put("code", "000");
                result.put("msg", "success");
            } else {
                result.put("code", "003");
                result.put("msg", "update status error");
            }
        }else {
            result.put("code", "002");
            result.put("msg", "parameter error");
        }
        return result;
    }


    public boolean updateOrderStatus(String orderId,String deployInfo,String status) throws Exception {
        Connection conn=null;
        boolean result=false;
        try {
            conn = this.getConnection();
            int r= orderDao.updateDeployStatus(conn,status,orderId,deployInfo);
            conn.commit();
            if(r>0){
                result=true;
            }
        } catch (Exception e){
            logger.info(e.getLocalizedMessage());
            throw  e;
        }finally {
            if(conn!=null)
                conn.close();
        }
        return result;
    }


    public OrderPojo createOrder(OrderPojo order) throws Exception {
        Connection conn=null;
        OrderPojo result=null;
        try {
                order.setId(getOrderID());
               // orderPojo.setCreateDate(DateUtils.getCurrentDateTime());
                order.setOrderStatus("pending");
                conn = this.getConnection();
               int r = orderDao.addOrder(conn, order);

                if(r>0){
                    result=order;
                }
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

    public OrderPojo checkOrder(Map order) throws Exception {
        OrderPojo orderPojo=null;
        if(order!=null) {
            String product = (String) order.get("product");
            String account = (String) order.get("account");
            String productName = (String) order.get("productName");
            String orderType=(String)order.get("orderType");
            String pcode = (String) order.get("pcode");
            if(StringUtils.isNotEmpty(productName)&& StringUtils.isNotEmpty(pcode)&& StringUtils.isNotEmpty(product)&&StringUtils.isNotEmpty(account)&&StringUtils.isNotEmpty(orderType)){
                orderPojo=new OrderPojo();
                orderPojo.setProductId(product);
                orderPojo.setAccountId(account);
                if(DeployType.tyBuy.getName().equals(orderType)) {
                    orderPojo.setPurchaseCode(pcode);
                }else {
                    orderPojo.setPurchaseCode("test-"+UUID.randomUUID());
                }
                orderPojo.setProductName(productName);
                orderPojo.setOrderType(orderType);
            }
        }
        return orderPojo;
    }







//    private Order initialOrder(String customerId,String productId){
//        Order order =null;
//        /**
//         * there must check customer is exist and product is  exist
//         */
//        CustomerInfo customerInfo=null;
//        ArtifactInfo artifactInfo=null;
//        if(true) { //defaule validate the customer  and  product
//
//            order=new Order();
//            order.setOrderId(getOrderID());
//            order.setCustomerId(customerId);
//            order.setProductId(productId);
//            //set customer info   and artifactInfo
//            //calculate the artifact's price
//
//        }
//        return order;
//    }



//    public String validOrder(Order order){
//        String result=null;
//        if(StringUtils.isNotEmpty(order.getCustomerId())&&StringUtils.isNotEmpty(order.getProductId())&&(order.getTotalFee()!=null)){
//            result="0000";
//        }else{
//            result="0002";
//        }
//        return  result;
//    }

    private String getOrderID(){
        return UUID.randomUUID().toString();
    }


    public void getShallSendOrder(Map<String,Object> data) throws Exception {
        String ipAddress= (String) data.get("ip");
        String customerId=(String)data.get("customerId");
        Order order=new Order();
        order.setIpAddress(ipAddress);
        order.setCustomerId(customerId);
        Connection conn=null;
        Order result=null;
        Map<String,Object> resultData=new HashMap<>();
        resultData.put("name","orderInfo");
        try {
            conn=this.getConnection();
            result= orderDao.queryUNDeployOrder(conn,order);
            if(result!=null){
                logger.info("order found");
                result.setStatus("002");
                int count= orderDao.updateStatus(conn,result);
                conn.commit();
                if(count<1){
                    logger.info("count <0");
                    result.setStatus("001");
                    resultData.put("msg"," update order status error");
                    resultData.put("code","103");
                }else {
                    resultData.put("msg","check over");
                    resultData.put("code","000");
                    logger.info("order.getOrderText() {}",result.getOrderText());
                  //  Map orderData=   JacksonUtils.json2map(order.getOrderText());
                    resultData.put("data",result.getOrderText());

                }
            }else{
                resultData.put("msg"," order not exist");
                resultData.put("code","101");
            }
        }catch (Exception e){
            logger.info(e.getLocalizedMessage());
            throw  e;
        }finally {
            if(conn!=null)
                conn.close();
        }
        ((Map)data.get("process")).put("order",resultData);
      //  return data;
    }


    public boolean checkOrderRequest(Map<String,Object> data) throws Exception {
        logger.info("checkOrderRequest parameter:{}",data);
        boolean result=false;
        String ip= (String) data.get("ip");
        String token=(String)data.get("token");
        String company=(String)data.get("company");
        String dateTime=(String)data.get("dateTime");
        String customerId=(String)data.get("customerId");
        String remoteStr=(String) data.get("infoStr");
        Map<String,Object> resultData=new HashMap<>();
        resultData.put("name","checkRequest");
        if(StringUtils.isNotEmpty(ip)&&StringUtils.isNotEmpty(token)&&StringUtils.isNotEmpty(company)&&StringUtils.isNotEmpty(dateTime)) {
            String localStr = DESUtils.getEncryptString((new StringBuffer(ip)).append(company).append(customerId).append(token).toString());
           // if (remoteStr.equals(localStr)) {
            if (true) {
               if(agentService.checkAgentValidate(token,ip)){
                   result=true;
                   resultData.put("msg","check over");
                   resultData.put("code","000");
               }else {
                   result=false;
                   resultData.put("msg"," token has invalidate");
                   resultData.put("code","005");
               }
            }else {
                result=false;
                resultData.put("msg"," some parameter are illegal or is null");
                resultData.put("code","011");
            }
        }else {
            result=false;
            resultData.put("msg"," some parameter are illegal or is null");
            resultData.put("code","001");
        }
        logger.info("resultData {}",resultData);
        ((Map)data.get("process")).put("check",resultData);
        return result;
    }

    private Map notifyOrder(String orderId,String accountAddr) throws Exception {
        Map rs=null;
        logger.info("\n\n\n\n\nORDERID:{}\n{}\n\n",orderId,accountAddr);
        try {
//            Map sendData=new HashMap();
//            sendData.put("pcode",pcode);
//            String sendStr= JacksonUtils.mapToJson(sendData);
            StringBuffer sb=new StringBuffer(accountAddr);
            sb.append("?orderId=").append(orderId);
            String response= httpUtils.sendJsonBody(sb.toString(),HTTPUtils.METHOD_GET,new HashMap<>(),null);
            logger.info("\n response info {}",response);
            rs=JacksonUtils.json2map(response);
        }catch (Exception e) {
            e.printStackTrace();
            throw  e;
        }
        return rs;
    }

}
