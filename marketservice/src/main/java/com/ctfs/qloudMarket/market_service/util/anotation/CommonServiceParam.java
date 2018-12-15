package com.ctfs.qloudMarket.market_service.util.anotation;



import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/12/12
 * Time: 19:09
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommonServiceParam {
    String methodName();
    String path();
}
