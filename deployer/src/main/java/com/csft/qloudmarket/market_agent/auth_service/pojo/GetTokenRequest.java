package com.csft.qloudmarket.market_agent.auth_service.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/10/31
 * Time: 17:25
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTokenRequest {
    private String phone;
    private String password;
}
