package com.ctfs.qloudMarket.market_service.order_service.pojo;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/8/23
 * Time: 14:46
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public enum OrderStatus {
    //001 为待支付  002 为已支付 003为 待部署 004 为部署中 005 为已部署 006 为订单完成 007 为订单异常 008订单删除
    UNPAY("001"),PAID("002"),UNDEPLOY("003"),DEPLOYING("004"),DEPLOYED("005"),ACCEPT("006"),ERROR("007"),DELETED("008");
    private OrderStatus(String c){
        code=c;
    }
    private String code;
    public String getCode(){
        return code;
    }

}
