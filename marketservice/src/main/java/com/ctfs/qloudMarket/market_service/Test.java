package com.ctfs.qloudMarket.market_service;

import com.ctfs.qloudMarket.market_service.agent_service.service.AgentService;
import com.ctfs.qloudMarket.market_service.util.DateUtils;
import com.ctfs.qloudMarket.market_service.util.anotation.CommonServiceParam;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/8/6
 * Time: 14:59
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    public static void main(String [] args){
//        List<Map> list=new ArrayList<>();
//        for(int i=0;i<10;i++){
//            Map<String,Object> item=new HashMap<>();
//            item.put("id","a"+i);
//            list.add(item);
//        }
//        Random r=new Random();
//        for(Map item:list){
//            item.put("name","dsa"+r.nextInt(100));
//        }
//        for (Map item:list){
//            System.out.println(item.get("name"));
//        }

      // System.out.println( AgentService.generatorToken("192.168.11.22"));
        System.out.println(   DateUtils.getCurrentDate(DateUtils.DATESHORTFORMAT));
        Test test=new Test();
        test.test2();
    }

    @CommonServiceParam(methodName = "getAccountProducts",path ="/accounts/*/products")
    public void test(){
//        boolean hasAnnotation = Test..isAnnotationPresent(CommonServiceParam.class);
//        if ( hasAnnotation ) {
//            CommonServiceParam commonServiceParam = Test.class.getAnnotation(CommonServiceParam.class); //获取类的注解
//           System.out.println("id:"+commonServiceParam.methodName());
//           System.out.println("msg:"+commonServiceParam.path());
//        }
    }
   // @CommonServiceParam(methodName = "getAccountProducts",path ="/accounts/*/products")
    public void test2(){
        this.test();
    }
}
