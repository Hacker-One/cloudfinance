package com.csft.qloudmarket.market_agent.license.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class LicenseInfo  implements java.io.Serializable  {
    private String expiredDate;  // field for save the encoded license
    private String content;         // license validate begin date
    private String productId;
    private String accountId;
    private String product;
    private String account;
    private Integer id;
}
