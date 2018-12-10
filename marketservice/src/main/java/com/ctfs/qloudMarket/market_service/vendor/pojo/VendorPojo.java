package com.ctfs.qloudMarket.market_service.vendor.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/10/16
 * Time: 16:31
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VendorPojo {
    private Integer id;
    private String name;
    private String displayName;
    private String email;
    private String contact;
    private String url;
    private String hook;
    private String status;
}
