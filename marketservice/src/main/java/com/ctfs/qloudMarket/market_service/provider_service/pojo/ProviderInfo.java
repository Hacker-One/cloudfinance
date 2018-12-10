package com.ctfs.qloudMarket.market_service.provider_service.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/8/6
 * Time: 15:32
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProviderInfo {

    private String providerId;

    private String name;

    private String logoPath;

    private String signature;

    private String corporationInfo;


}
