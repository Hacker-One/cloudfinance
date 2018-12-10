package com.ctfs.qloudMarket.market_service.license_service.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/10/16
 * Time: 15:45
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LicensePojo {
    private Integer id;
    private String expiredDate;
    private String content;
    private String product;
    private String account;
    private String status;
    private boolean isDeploy;
    private String pcode;
    private String createDate;
}
