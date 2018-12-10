package com.ctfs.qloudMarket.market_service.util;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/9/12
 * Time: 14:28
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public class QLoudException extends Exception {
    private static final long serialVersionUID = -1002332725783876094L;


    private String code;


    private String message;

    private String[] prams;

    public QLoudException(String code) {

        this.code = code;
    }

    public QLoudException(String code, Throwable throwable) {

        this.code = code;
    }

    public QLoudException(String code, String pram) {

        this.code = code;
        prams=new String[]{pram};
    }
    public QLoudException(String code, String[] prams) {

        this.code = code;
        this.prams=prams;
        //this.message = message;
    }

    public QLoudException(String code, String pram, Throwable throwable) {
        this.code = code;
        prams=new String[]{pram};
    }

    public String[] getPrams() {
        return prams;
    }

    public String getCode() {
        return code;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }





}
