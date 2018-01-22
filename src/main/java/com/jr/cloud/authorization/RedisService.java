package com.jr.cloud.authorization;

/**
 * 对Token进行管理的接口
 * @author ScienJus
 * @date 2015/7/31.
 */
public interface RedisService {

    /**
     * 通过key删除关联关系
     * @param key
     */
    void delSingleRelationshipByKey(String key);

    /**
     * 创建关联关系
     * @param key
     * @param value
     */
    void createSingleRelationship(String key, String value);

    /**
     * 通过key获得对应的value
     * @param key
     * @return
     */
    String getValue(String key);
}
