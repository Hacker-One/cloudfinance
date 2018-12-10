package com.ctfs.qloudMarket.market_service.license_service.pojo;

import com.ctfs.qloudMarket.market_service.artifact_service.pojo.ArtifactInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/8/6
 * Time: 15:30
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */

/**
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LicenseInfo {
    private String licenseKey;  // field for save the encoded license
    private ArtifactInfo artifactInfo; //artifact info
    private String validDate;         // license validate begin date
    private String invalidDate;      // license validate end date
    private String signature;        // signature from provider's signature
    private String customerId;      //customer's name
    private String artifactId;
}
