package com.ctfs.qloudMarket.market_service.product.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/10/16
 * Time: 17:12
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductTypePojo {
    private Integer id;
    private String name;
    private String desc;
    private String status;
}
