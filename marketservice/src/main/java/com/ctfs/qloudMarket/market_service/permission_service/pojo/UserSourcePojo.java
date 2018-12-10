package com.ctfs.qloudMarket.market_service.permission_service.pojo;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/11/29
 * Time: 18:59
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Data
public class UserSourcePojo {
    private String accountId;
    private String userId;
    private String roleId;
    private String source;
    private String sourceType;
}
