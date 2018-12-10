package com.csft.qloudmarket.test;

import com.csft.qloudmarket.market_agent.license.pojo.LicenseInfo;
import com.csft.qloudmarket.market_agent.license.server.LicenseService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/11/8
 * Time: 9:41
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public class LicenseTest {
    private static Logger logger = LoggerFactory.getLogger(LicenseTest.class);
    private LicenseService licenseService = new LicenseService();
    @Test
    public void testSaveLicense(){

        try {
            LicenseInfo licenseInfo=   licenseService.getLicense("571828364302618630","MYCMB");
           if(licenseService.saveLicenses(licenseInfo)){
               logger.info("saved");
           } //save the license
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
