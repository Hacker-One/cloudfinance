package com.csft.qloudmarket.market_agent.user.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/10/16
 * Time: 14:54
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPojo {
    private String id;
    private String account;
    private String name;
    private String status;
    private String pwd;
}
