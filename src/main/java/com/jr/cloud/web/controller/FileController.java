package com.jr.cloud.web.controller;

import com.jr.cloud.annotation.Authorization;
import com.jr.cloud.service.IFileService;
import com.jr.cloud.util.JsonResult;
import com.jr.cloud.util.enums.StatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/24.
 */
@Controller
@RequestMapping("files")
@ResponseBody
public class FileController {

    @Resource
    private IFileService fileService;

    @RequestMapping(value = "/listFiles", method = RequestMethod.POST)
    @Authorization
    public JsonResult listFiles( @RequestBody Map map) throws Exception{
        String path = (String)map.get("path");
        return JsonResult.build(StatusCode.SUCCESS, fileService.listFile( path ));
    }

    @RequestMapping(value = "/makeDir", method = RequestMethod.POST)
    @Authorization
    public JsonResult makeDir( @RequestBody Map map) throws Exception{
        String path = (String)map.get("path");
        fileService.makeDir( path );
        return JsonResult.build(StatusCode.SUCCESS);
    }

    @RequestMapping(value = "/deleteDir", method = RequestMethod.POST)
    @Authorization
    public JsonResult deleteDir( @RequestBody Map map) throws Exception{
        String path = (String)map.get("path");
        fileService.deleteDir( path );
        return JsonResult.build(StatusCode.SUCCESS);
    }

    @RequestMapping(value = "/renameFile", method = RequestMethod.POST)
    @Authorization
    public JsonResult renameFile( @RequestBody Map map) throws Exception{
        String oldName = (String)map.get("oldName");
        String newName = (String)map.get("newName");
        fileService.renameFile(oldName, newName );
        return JsonResult.build(StatusCode.SUCCESS);
    }

    @RequestMapping(value = "/moveFile", method = RequestMethod.POST)
    @Authorization
    public JsonResult moveFile( @RequestBody Map map) throws Exception{
        String src = (String)map.get("src");
        String des = (String)map.get("des");
        fileService.moveFile(src, des );
        return JsonResult.build(StatusCode.SUCCESS);
    }

    @RequestMapping(value = "/copyFile", method = RequestMethod.POST)
    @Authorization
    public JsonResult copyFile( @RequestBody Map map) throws Exception{
        String src = (String)map.get("src");
        String des = (String)map.get("des");
        fileService.copyFile(src, des );
        return JsonResult.build(StatusCode.SUCCESS);
    }
}
