package com.jr.cloud.web.controller;

import com.google.gson.Gson;
import com.jr.cloud.annotation.Authorization;
import com.jr.cloud.authorization.impl.RedisServiceImpl;
import com.jr.cloud.entity.User;
import com.jr.cloud.service.IUserService;
import com.jr.cloud.util.JsonResult;
import com.jr.cloud.util.PageResult;
import com.jr.cloud.util.enums.StatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Useristrator on 2017/10/18.
 */
@Controller
@RequestMapping("users")
@ResponseBody
public class UserController {

    @Resource
    private IUserService userService;
    @Resource
    private  RedisServiceImpl redisService ;

    /**
     * 用户注册
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/register", method = RequestMethod.POST)
    public JsonResult register(@RequestBody User user) throws  Exception{
        System.out.println("注册信息：" + new Gson().toJson(user));
        userService.register(user);
        return JsonResult.build(StatusCode.SUCCESS);
    }

    /**
     * 用户登录
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public JsonResult login(@RequestBody User user) throws  Exception{
        return JsonResult.build(StatusCode.SUCCESS,userService.login(user));
    }

    /**
     * 用户登出
     * @param request
     * * @param id
     * @return
     */
    @RequestMapping(value = "/logOut/{id}",method = RequestMethod.POST)
    @Authorization
    public JsonResult logOut(HttpServletRequest request, @PathVariable Integer id) {
        String token = request.getHeader("authorization").substring("".length());
        redisService.delSingleRelationshipByKey( token );
        redisService.delSingleRelationshipByKey( String.valueOf(id));
        return JsonResult.build(StatusCode.SUCCESS);
    }

    /**
     * 添加用户信息
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping(value="", method = RequestMethod.POST)
    public JsonResult addUser(@RequestBody User user) throws Exception{
        userService.addUser(user);
        return JsonResult.build(StatusCode.SUCCESS);
    }

    /**批量删除用户*/
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    @Authorization
    public JsonResult deleteDepartment(HttpServletRequest request , @RequestBody Map map) {
        List ids =  (List)map.get("ids");
        userService.deleteUser(ids);
        String token = request.getHeader("authorization").substring("".length());
        redisService.delSingleRelationshipByKey( token );
        redisService.delSingleRelationshipByKey( String.valueOf(ids.get(0)));
        return JsonResult.ok("");
    }

    /**
     * 查询单个用户的信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Authorization
    public JsonResult queryUser(@PathVariable Integer id){
        return JsonResult.build(StatusCode.SUCCESS, userService.getUserById(id));
    }

    /**
     * 分页查询用户信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public JsonResult queryUsers(Integer pageNum, Integer pageSize){
        PageResult users = userService.getUserForPage(pageNum, pageSize);
        return JsonResult.build(StatusCode.SUCCESS, users);
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.PATCH)
    public JsonResult updateUser(@RequestBody User user) throws Exception{
        userService.updateUser(user);
        return JsonResult.build(StatusCode.SUCCESS);
    }



}
