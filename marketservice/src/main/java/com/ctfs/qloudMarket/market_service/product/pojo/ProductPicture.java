package com.ctfs.qloudMarket.market_service.product.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/10/19
 * Time: 16:12
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPicture {
   private Integer id;
   private String productId;
   private String url;
   private String status;
   private Integer sort;
}
