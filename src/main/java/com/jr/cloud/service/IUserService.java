package com.jr.cloud.service;

import com.jr.cloud.entity.User;
import com.jr.cloud.util.PageResult;
import com.jr.cloud.web.exception.base.CustomException;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * Created by Useristrator on 2018/1/13.
 */
public interface IUserService {
    /**
     * 注册
     * @param user
     */
    void register(User user) throws CustomException, Exception;

    /**
     * 登录
     * @param user
     */
    JSONObject login(User user) throws CustomException;

    /**
     * 添加一条用户记录到数据库
     * @param user 用户对象
     */
    public void addUser( User user) throws CustomException;

    /**
     * 删除用户
     * @param ids 要删除的用户的id的数组
     */
    public void deleteUser(List ids) throws Exception;

    /**
     * 查询一条用户数据
     * @param id 用户id
     * @return JSONObject 一条用户信息
     */
    public JSONObject getUserById(Integer id);

    /**
     * 分页查询用户信息
     * @param pageNum
     * @param pageSize
     * @return  PageResult 用户信息数组和用户的总条数
     */
    public PageResult getUserForPage(Integer pageNum, Integer pageSize) ;

    /**
     * 更新用户数据
     * @param user 用户对象
     */
    public void updateUser(User user) throws Exception;
}
