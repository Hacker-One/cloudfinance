package com.ctfs.qloudMarket.market_service.product.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/10/16
 * Time: 16:44
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPojo {
    private String id;
    private String name;
    private String shortDesc;
    private String icon;
    private String longDesc;
    private String picture;
    private String descUrl;
    private Integer type;
    private String typeName;
    private Integer vendor;
    private String vendorName;
    private String status;
    private Integer category;
    private String categoryName;
    private String vendorDisplay;
    private List<ProductTagPojo> tags;
    private List<ProductPicture> pictures;
}
