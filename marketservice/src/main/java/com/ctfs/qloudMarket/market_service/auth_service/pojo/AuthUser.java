package com.ctfs.qloudMarket.market_service.auth_service.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/10/31
 * Time: 16:48
 * Corporation:China soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthUser {
    private String id;
    private String name;
    private String smscode;
    private String password;
    private String oldphone;
}
