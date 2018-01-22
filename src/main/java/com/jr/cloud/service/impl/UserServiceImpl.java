package com.jr.cloud.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.JsonObject;
import com.jr.cloud.authorization.impl.RedisServiceImpl;
import com.jr.cloud.authorization.util.JwtUtil;
import com.jr.cloud.dto.UserInfo;
import com.jr.cloud.entity.User;
import com.jr.cloud.entity.UserExample;
import com.jr.cloud.mapper.UserMapper;
import com.jr.cloud.mapper.UserMapperCustom;
import com.jr.cloud.service.IUserService;
import com.jr.cloud.util.PageResult;
import com.jr.cloud.web.exception.base.CustomException;
import com.jr.cloud.web.exception.base.PasswdIncorException;
import com.jr.cloud.web.exception.base.ReqParmIncorException;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Useristrator on 2017/10/18.
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserMapperCustom userMapperCustom;
    @Resource
    private RedisServiceImpl redisService;

    /**
     * 注册
     *
     * @param user
     */
    public void register(User user) {

        UserExample userExample = new UserExample();
        userExample.createCriteria().andNameEqualTo(user.getPhone());
        List<User> list = new ArrayList<User>();
        list = userMapper.selectByExample(userExample);

        //账号存在
        if(list.size() != 0){
            throw new CustomException("账号已经存在");
        }

        user.setStorageSize(10240);
        user.setStorageUsed(0);
        userMapper.insert(user);

    }

    /**
     * 登录
     *
     * @param user
     */
    public JSONObject login(User user)  throws CustomException{



        UserExample userExample = new UserExample();
        userExample.createCriteria().andPhoneEqualTo(user.getPhone());
        List<User> list = new ArrayList<User>();
        list = userMapper.selectByExample(userExample);

        //账号不存在
        if(list.size() == 0){
            throw new CustomException("账号不存在");
        }

        //密码不正确
        User res = list.get(0);
        if(!res.getPwd().equals(user.getPwd())){
            throw  new PasswdIncorException();
        }

        if(redisService.getValue(String.valueOf(res.getUserId())) != null){
            throw new CustomException("重复登录");
        }

        //创建并存储token
        JSONObject jsonObject = JSONObject.fromObject(res);
        jsonObject.remove("pwd");
        String token = JwtUtil.createJWT(res.getName(), String.valueOf(res.getUserId()));
        jsonObject.put("token",token);
        redisService.createSingleRelationship(token, String.valueOf(res.getUserId()));
        redisService.createSingleRelationship(String.valueOf(res.getUserId()), token);
        return jsonObject;
    }

    /**
     * 添加一条用户记录到数据库
     *
     * @param user 用户对象
     */
    public void addUser(User user) throws CustomException{
        if(user.getName() == null || user.getPwd() ==null || user.getPhone() == null)
            throw new ReqParmIncorException();

        UserExample userExample = new UserExample();
        userExample.createCriteria().andNameEqualTo(user.getName());
        if(userMapper.selectByExample(userExample).size() != 0){
            throw new CustomException("该用户名已经存在");
        }else {
            userMapper.insert(user);
        }
    }

    /**
     * 删除用户
     *
     * @param ids 要删除的用户的id的数组
     */
    public void deleteUser(List ids) {
        for(int i = 0 ;  i < ids.size(); i++){
            if(!(ids.get(i) instanceof Long || ids.get(i) instanceof Integer))
                throw new ReqParmIncorException("参数非整型数据");
            userMapper.deleteByPrimaryKey( (Integer) ids.get(i));
        }
    }

    /**
     * 查询一条用户数据
     *
     * @param id 用户id
     * @return user 一条用户信息
     */
    public JSONObject getUserById(Integer id) {
        //创建并存储token
        JSONObject jsonObject = JSONObject.fromObject(userMapper.selectByPrimaryKey(id));
        jsonObject.remove("pwd");
        return jsonObject;
    }

    /**
     * 分页查询用户信息
     *
     * @param pageNum
     * @param pageSize
     * @return PageResult 用户信息数组和用户的总条数
     */
    public PageResult getUserForPage(Integer pageNum, Integer pageSize) {
        if(pageNum == null || pageSize == null)
            throw  new CustomException("参数缺失");
        PageHelper.startPage(pageNum, pageSize);
        List<UserInfo> users = userMapperCustom.queryUser();
        PageInfo<UserInfo> pageInfo = new PageInfo<UserInfo>(users);
        PageResult pageResult = new PageResult();
        pageResult.setRows(users);
        pageResult.setTotal(pageInfo.getTotal());
        return pageResult;
    }

    /**
     * 更新用户数据
     *
     * @param user 用户对象
     */
    public void updateUser(User user) throws Exception{

        if(user.getUserId() == null)
            throw new ReqParmIncorException();

        UserExample userExample = new UserExample();
        userExample.createCriteria().andNameEqualTo(user.getName());
        //账号存在
        if(userMapper.selectByExample(userExample).size() >= 1 ){
            throw new CustomException("用户名已经存在");
        }

        userMapper.updateByPrimaryKey(user);
    }


}
