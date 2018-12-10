package com.csft.qloudmarket.market_agent.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/8/11
 * Time: 11:30
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class FileUtil {
    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
    public String writeFile(String path, String filename, byte[] data) throws IOException {
        String storage=(new StringBuffer(path)).append(File.separator).append(filename).toString();
        ByteBuffer byteBuffer = ByteBuffer.wrap(data);
        byteBuffer.put(data);
        FileOutputStream outputStream = new FileOutputStream(new File(storage));
        FileChannel fileChannel = outputStream.getChannel();
        byteBuffer.flip();
        fileChannel.write(byteBuffer);
        byteBuffer.clear();
        fileChannel.close();
        return storage;
    }

    /**
     * write file
     * @param path
     * @param filename
     * @param inputStream
     * @return
     * @throws IOException
     */
    public String writeFile(String path, String filename, InputStream inputStream) throws IOException {
        String storage=(new StringBuffer(path)).append(File.separator).append(filename).toString();
        logger.info("storage:{}",storage);
        File file= new File(storage);
      //  file.createNewFile();
        FileOutputStream outputStream = new FileOutputStream(file);
        ReadableByteChannel readableByteChannel= Channels.newChannel(inputStream);
        ByteBuffer byteBuffer =ByteBuffer.allocate(1024);
        FileChannel fileChannel = outputStream.getChannel();
        while (readableByteChannel.read(byteBuffer)>-1) {
            byteBuffer.flip();
            fileChannel.write(byteBuffer);
            byteBuffer.clear();
        }

        readableByteChannel.close();
        inputStream.close();
        fileChannel.close();
        return storage;
    }



    public String checkAndCreatDirector(String filePath) {
        logger.info("checkAndCreatDirector filePath:{}",filePath);
        File file = new File(filePath);
        if (!checkDirectorExist(file)) {
            file.mkdir();
        }
        return file.getPath();
    }
    public boolean checkDirectorExist(File file) {
        return file.exists();
    }

    public boolean checkFileConsistant(String filename,String sha256) throws IOException, NoSuchAlgorithmException {
        boolean res=false;
                File file =new File(filename);
                if(file.exists()&&file.isFile()){
                   String sha= getFileSHA256(file);
                   if(sha.equals(sha256)){
                       res=true;
                   }
                }

            return res;
    }

    public static String converPath(String path){
        if("\\".equals(File.separator)){
            path=path.substring(1);
            path=path.replace('/','\\');
            return path;
        }
        if("/".equals(File.separator)){
            path=path.replace('\\','/');
            return path;
        }
        return null;
    }

    public String getFileSHA256(File file) throws IOException, NoSuchAlgorithmException {
        if (!file.isFile()){
            System.err.println("not file");
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in=null;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }finally {
            if(in!=null){
                in.close();
            }
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }

}
