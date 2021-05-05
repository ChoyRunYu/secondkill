package com.secondkill.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;


/**
 * @author Choy
 * @date 2021/02/21
 * redis工具类
 */
@Component
public class RedisUtils {


    @Autowired
    private JedisPool jedisPool;



    /**
     * 向Redis中存值，永久有效
     */
    public String set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.set(key, value);
        } catch (Exception e) {
            return "0";
        } finally {
            jedis.close();
        }
    }

    /**
     * 根据传入Key获取指定Value
     */
    public String get(String key) {
        Jedis jedis = null;
        String value;
        try {
            jedis = jedisPool.getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            return "0";
        } finally {
            jedis.close();
        }
        return value;
    }

    /**
     * 校验Key值是否存在
     */
    public Boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(key);
        } catch (Exception e) {
            return false;
        } finally {
            jedis.close();
        }
    }

    /**
     * 删除指定Key-Value
     */
    public Long del(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.del(key);
        } catch (Exception e) {
            return 0L;
        } finally {
            jedis.close();
        }
    }

    /**
     * 分布式锁
     * @param key
     * @param value
     * @param time 锁的超时时间，单位：秒
     *
     * @return 获取锁成功返回"OK"，失败返回null
     */
    public String getDistributedLock(String key,String value,int time){
        Jedis jedis = null;
        String ret = "";
        try {
            jedis = jedisPool.getResource();

            ret = jedis.set(key, value, new SetParams().nx().ex(time));
            return ret;
        } catch (Exception e) {
            return null;
        } finally {
            jedis.close();
        }
    }

    /**
     * * 过期时间的
     * @param key
     * @param value
     * @param timer （秒）
            */
    public void setWithTime(String key, String value, int timer) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = jedisPool.getResource();
            jedis.select(0);
            jedis.setex(key, timer, value);
        } catch (Exception e) {
            isBroken = true;
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }


    /**
     * 释放
     * @param jedis
     * @param isBroken
     */
    public static void returnResource(Jedis jedis, boolean isBroken) {
        if (jedis == null)
            return;
        jedis.close();
    }
}
