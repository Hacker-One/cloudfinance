package com.ctfs.qloudMarket.market_service.account.pojo;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/10/16
 * Time: 14:35
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountPojo {
    private String id;
    private String name;
    private String hook;
    private String contact;
    private String email;
    private String status;


}
