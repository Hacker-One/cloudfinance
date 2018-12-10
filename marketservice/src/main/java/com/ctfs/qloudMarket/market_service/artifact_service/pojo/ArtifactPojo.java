package com.ctfs.qloudMarket.market_service.artifact_service.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/10/16
 * Time: 15:24
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArtifactPojo {
    private Integer id;
    private String name;
    private String url;
    private String productId;
    private Integer type;
    private String typeName;
    private String status;
    private String tag;
}
