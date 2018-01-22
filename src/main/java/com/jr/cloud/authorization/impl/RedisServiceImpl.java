package com.jr.cloud.authorization.impl;


import com.jr.cloud.authorization.RedisService;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Token管理的基础类
 * @author ScienJus
 * @date 2015/10/27.
 */
@Service
public class RedisServiceImpl implements RedisService {

    private int expireSeconds =1800;

    private boolean singleTokenWithUser = true;

    /**
     * Jedis连接池
     */
    protected JedisPool jedisPool;

    public int getExpireSeconds() {
        return expireSeconds;
    }

    public void setExpireSeconds(int expireSeconds) {
        this.expireSeconds = expireSeconds;
    }

    public boolean isSingleTokenWithUser() {
        return singleTokenWithUser;
    }

    public void setSingleTokenWithUser(boolean singleTokenWithUser) {
        this.singleTokenWithUser = singleTokenWithUser;
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }


    /**
     * 删除redis内的一条记录
     * @param key
     */
    public void delSingleRelationshipByKey(String key){
        jedisPool.getResource().del(key);
    }

    /**
     * 在redis内创建一条记录
     * @param key
     * @param value
     */
    public void createSingleRelationship(String key, String value ) {
        jedisPool.getResource().setex(key, expireSeconds, value);
    }

    /**
     * 获取key对应的value
     * @param key
     * @return
     */
    public String getValue(String key) {
        Jedis jedis = jedisPool.getResource();
        return jedis.get(key);
    }

    /**
     * 获取key对应的value
     * @param key
     * @return
     */
    public String getValue(String key, boolean flushExpireAfterOperation) {
        Jedis jedis = jedisPool.getResource();
        //如果需要每次操作都刷新记录的存储时间，则进行刷新
        if(flushExpireAfterOperation == true){
            jedis.expire(key, expireSeconds);
        }
        return jedis.get(key);
    }



}
