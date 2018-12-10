package com.ctfs.qloudMarket.market_service.util;

import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/10/30
 * Time: 10:33
 * Corporation:China soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public class CommonMessage {
    private  Map<String,Map> messages;
    private static CommonMessage commonMessage;
    public static final String MESSAGE_FILE="/service-msg.conf";
    private CommonMessage(){
        messages=new HashMap();

    }
    public static CommonMessage instance(){
        if(commonMessage==null){
            commonMessage=new CommonMessage();
        }
        return commonMessage;
    }
    private void loadMessageInfos() throws IOException {
        BufferedReader bf=null;
        StringBuffer fileContent=new StringBuffer();
        try {
            bf=new BufferedReader(new FileReader(MESSAGE_FILE));
            while (bf.ready()){
                fileContent.append(bf.readLine());
            }
            //------------read file Over
           List<Map> list= JacksonUtils.json2list(fileContent.toString(),HashMap.class);
            for(Map item:list){
                messages.put((String)item.get("code"),item);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bf.close();
        }
    }

    public Map getMessageInfo(String code){
        return  messages.get(code);
    }

    public Map getMessageInfo(String code,Object... parameter){
        Map result=null;
        Map info=messages.get(code);
        if(info!=null){
            result=new HashMap();
            result.put("code",info.get("code"));
            FormattingTuple ft=  MessageFormatter.arrayFormat((String) info.get("msg"), parameter);
            result.put("msg",ft.getMessage());
        }

        return result;
    }
}
