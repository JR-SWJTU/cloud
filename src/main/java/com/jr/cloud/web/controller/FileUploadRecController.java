package com.jr.cloud.web.controller;

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
@RequestMapping("fileUploadRecs")
@ResponseBody
public class FileUploadRecController {

    @Resource
    private IUserService userService;
    @Resource
    private  RedisServiceImpl redisService ;

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
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @Authorization
    public JsonResult deleteDepartment(HttpServletRequest request , @RequestBody Map map) throws Exception {
        List ids =  (List)map.get("ids");
        userService.deleteUser(ids);
        return JsonResult.ok("");
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


}
