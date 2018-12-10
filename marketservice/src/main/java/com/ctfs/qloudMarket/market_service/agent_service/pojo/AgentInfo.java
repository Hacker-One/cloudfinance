package com.ctfs.qloudMarket.market_service.agent_service.pojo;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/9/18
 * Time: 17:57
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Data
public class AgentInfo {
    private String id;
    private String token;
    private String ipAddress;
    private String customerId;
    private String companyName;
    private String createDate;
    private String isDel;
    private String invalidateDate;
}
