package com.secondkill.common.utils;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * redis库存lua脚本执行
 * @author choy
 * @date 2021/03/26
 */
@Component
public class RedisLuaUtils {

    private static final Logger logger = LoggerFactory.getLogger(RedisLuaUtils.class);

    @Autowired
    private JedisPool jedisPool;
    @Value("classpath:lua/cutStock.lua")
    private Resource cutStockLua;
    @Value("classpath:lua/restoreStock.lua")
    private Resource restoreStock;

    /**
     * 扣减库存
     * @return
     */
    public String cutStock(Integer msGoodsId, Integer userId) {
        String lua = resourceToString(cutStockLua);
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            Object result = jedis.eval(lua,
                    Arrays.asList(msGoodsId.toString()),
                    Arrays.asList(userId.toString())
            );
            logger.info("返回值：{}", result.toString());
            return result.toString();
        }catch (Exception e){
            logger.warn(e.getMessage());
            return null;
        }finally {
            jedis.close();
        }
    }

    /**
     * 用户超时付款时回滚库存
     * @param msGoodsId
     * @param userId
     * @return
     */
    public String restoreStock(Integer msGoodsId, Integer userId){
        String lua = resourceToString(restoreStock);
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            jedis.eval(lua, Arrays.asList(msGoodsId.toString()), Arrays.asList(userId.toString()));
            return "true";
        }catch (Exception e){
            logger.warn(e.getMessage());
            return "false";
        }finally {
            jedis.close();
        }
    }

    /**
     * 将lua脚本转成字符串
     * @param resource
     * @return
     */
    public String resourceToString(Resource resource){
        try{
            InputStream inputStream = resource.getInputStream();
            return IOUtils.toString(inputStream, "utf8");
        }catch (IOException e){
            logger.error("找不到lua脚本");
            return null;
        }
    }
}
