package com.csft.qloudmarket.market_agent.license.pojo;

import com.csft.qloudmarket.market_agent.license.server.LicenseService;
import com.csft.qloudmarket.market_agent.util.Common;
import com.csft.qloudmarket.market_agent.util.DateUtils;
import com.csft.qloudmarket.market_agent.util.FileUtil;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/10/23
 * Time: 17:51
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public class LicenseList implements java.io.Serializable {
    private static Logger logger= LoggerFactory.getLogger(LicenseList.class);
    //private static String filePath=FileUtil.converPath(LicenseList.class.getResource("/license").getPath()+"/deployer.licenses");
    private static String filePath=new StringBuffer(Common.getPropertiesKey(Common.MA_VOLUME_KEY))
                                         .append(Common.SEPARATOR)
                                         .append("license")
                                         .append(Common.SEPARATOR)
                                         .append("deployer.licenses").toString();
    private static LicenseList instance;
    @Getter
    @Setter
    private String updateTime;
    @Getter
    @Setter
    private String createTime;
    @Getter
    @Setter
    private  Map<String,LicenseInfo> licenses;

    public static LicenseList getInstance() throws IOException, ClassNotFoundException {
        if(instance==null){
            initialMemory();
        }
        return instance;
    }

    private LicenseList(){
        licenses=new HashMap();
        updateTime= DateUtils.getCurrentDateTime();
        createTime=DateUtils.getCurrentDateTime();
    }

    /**
     * 获取一个证书
     * @param key
     * @return
     */
   public LicenseInfo getLicense(String key){
        return  licenses.get(key);
   }

    /**
     * 插入一个证书
     * @param key
     * @param licenseInfo
     */
   public void  putLicense(String key,LicenseInfo licenseInfo) throws IOException {

        this.licenses.put(key,licenseInfo);
        this.updateTime=DateUtils.getCurrentDateTime();
        writeIn();
   }

    /**
     * 删除一个 证书
     * @param key
     */
    public void  removeLicense(String key) throws IOException {

        this.licenses.remove(key);
        this.updateTime=DateUtils.getCurrentDateTime();
        writeIn();

    }

   public void clearAll() throws IOException {

        this.licenses.clear();
       this.updateTime=DateUtils.getCurrentDateTime();
        writeIn();
   }


    private static  void initialMemory() throws IOException, ClassNotFoundException {
        File file=new File(filePath);
        if(!file.exists()){
            file.createNewFile();
            instance=new LicenseList();
            instance.writeIn();
        }else {
            FileInputStream input = new FileInputStream(filePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(input);
            instance=(LicenseList)objectInputStream.readObject();
            input.close();
            objectInputStream.close();
        }
    }

    private  void writeIn() throws IOException {
        FileOutputStream output = new FileOutputStream(filePath);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(output);
        objectOutputStream.writeObject(this);
        output.close();
        objectOutputStream.close();
    }

}
