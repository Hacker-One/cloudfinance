package com.csft.qloudmarket.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/10/24
 * Time: 12:26
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public class Base64Test {
    public static final String D1_expected = "Dummy[10,26.8889,1.7976931348623157E308,5698765.999876500107347965240478515625,Hello Karl :-)]";
    public static final String D3_expected = "Dummy[15,55.0,4567.0,5698765.999876500107347965240478515625,Hello Karl Again :-)]";
    public static final String WibbleWobble1_Encoded = "V2liYmxlV29iYmxlMQ==";
    Dummy d1;
    Dummy d2;
    Dummy d3;
    Dummy d4;
    Dummy d5;
    Dummy d6;
    Dummy d7;
}
