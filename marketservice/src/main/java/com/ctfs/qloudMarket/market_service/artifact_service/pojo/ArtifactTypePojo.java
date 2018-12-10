package com.ctfs.qloudMarket.market_service.artifact_service.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/10/18
 * Time: 11:38
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtifactTypePojo {
    private Integer id;
    private String name;
    private String des;
    private String status;
}
