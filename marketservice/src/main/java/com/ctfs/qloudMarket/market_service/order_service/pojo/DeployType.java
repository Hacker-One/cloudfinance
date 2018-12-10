package com.ctfs.qloudMarket.market_service.order_service.pojo;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/11/20
 * Time: 17:35
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public enum DeployType {
    tyBuy("buy"),tyTry("try");
    DeployType(String name){
      this.name=name;
    }
    private String name;

    public String getName() {
        return name;
    }


    public static DeployType getDeployType(String na){
         for(DeployType dt:DeployType.values()){
             if(dt.getName().equals(na)){
                 return  dt;
             }
         }
         return tyTry;
    }
}
