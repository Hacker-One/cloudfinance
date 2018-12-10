package com.ctfs.qloudMarket.market_service.artifact_service.pojo;

import com.ctfs.qloudMarket.market_service.provider_service.pojo.ProviderInfo;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/8/6
 * Time: 15:12
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Data
public class ArtifactInfo {

    private String artifactId; //id

    private ProviderInfo providerInfo; //provider Info

    private String name;       //app's name

    private String providerId; //application provider's ID

    private String logoPath; //logo pictures path

    private BigDecimal price; //app's price

    private String version; //app's version

    private String introduceInfo; //information of app's introduce

    private String imagePath; // the path of your app's image

    private String chartPath; //chart museum 's path

}
