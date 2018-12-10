package com.ctfs.qloudMarket.market_service.order_service.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/10/16
 * Time: 16:03
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderPojo {
    private String id;
    private String createDate;
    private String orderStatus;
    private String purchaseCode;
    private String accountId;
    private String productId;
    private String productName;
    private Integer licenseId;
    private String deployMsg;
    private String orderType;
    private String addrId;
    private String deployAddr;

}
