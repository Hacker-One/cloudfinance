package com.ctfs.qloudMarket.market_service.redis;

import com.ctfs.qloudMarket.market_service.util.Common;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/11/29
 * Time: 18:35
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public class RedisPool {
    private static  RedisPool instance;
    private JedisPool pool;
   // private JedisPoolConfig config;
    private RedisPool() throws URISyntaxException {
      //  config=new JedisPoolConfig();
      //  pool=new JedisPool(new GenericObjectPoolConfig(),new URI(Common.getPropertiesKey(Common.REDIS_URI)),Integer.parseInt(Common.getPropertiesKey(Common.REDIS_TIMEOUT)));
        pool=new JedisPool(new GenericObjectPoolConfig(), Common.getPropertiesKey(Common.REDIS_HOST),Integer.parseInt(Common.getPropertiesKey(Common.REDIS_PORT)),Integer.parseInt(Common.getPropertiesKey(Common.REDIS_TIMEOUT)));
    }
    public static RedisPool getInstance() throws URISyntaxException {
        if(instance==null){
            instance=new RedisPool();
        }
        return  instance;
    }

    public Jedis getResource(){
        return pool.getResource();
    }

    public void closeRedis(Jedis jedis){
        if(jedis!=null){
            jedis.close();
            pool.close();
        }
    }
}
