package com.ctfs.qloudMarket.market_service.order_service.pojo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/8/23
 * Time: 14:35
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Data
public class Order {
    private String id;
    private String ipAddress;
    private String customerId;
    private String status;
    private String timeStamp;
    private String orderText;
    private String userToken;
}
