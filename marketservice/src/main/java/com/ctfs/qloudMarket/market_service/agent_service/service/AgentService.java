package com.ctfs.qloudMarket.market_service.agent_service.service;

import com.ctfs.qloudMarket.market_service.agent_service.dao.AgentDao;
import com.ctfs.qloudMarket.market_service.agent_service.pojo.AgentInfo;
import com.ctfs.qloudMarket.market_service.util.BaseService;
import com.ctfs.qloudMarket.market_service.util.DESUtils;
import com.ctfs.qloudMarket.market_service.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/9/18
 * Time: 17:46
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class AgentService extends BaseService {
    private static Logger logger= LoggerFactory.getLogger(AgentService.class);
    private AgentDao agentDao=new AgentDao();
    public static String generatorToken(String ip){
        String res=null;
        Random random=new Random();
        StringBuffer fac=new StringBuffer(ip);
        fac.append(DateUtils.getCurrentDateTime());
        fac.append(random.nextInt(99999));
        res=DESUtils.getEncryptString(fac.toString());
        return res;
    }

    /**
     *
     * @param token
     * @param ipaddress
     * @return
     * @throws Exception
     */
    public boolean checkAgentValidate(String token,String ipaddress) throws Exception {
        AgentInfo agentInfo=null;
        Connection conn=null;
        boolean result=false;
        String date=DateUtils.getCurrentDate(DateUtils.DATESHORTFORMAT);

        try {
            conn=this.getConnection();
            result= agentDao.checkToken(conn,token,ipaddress,date)>0?true:false;
        } catch (Exception e) {
            e.printStackTrace();
            throw  e;
        } finally {
            if(conn!=null) {
                conn.close();
            }
        }
        return result;
    }
}
