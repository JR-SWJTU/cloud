package com.jr.cloud.web.controller;

import com.jr.cloud.annotation.Authorization;
import com.jr.cloud.service.IFileDelRecService;
import com.jr.cloud.util.JsonResult;
import com.jr.cloud.util.PageResult;
import com.jr.cloud.util.enums.StatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by UploadRecordistrator on 2017/10/18.
 */
@Controller
@RequestMapping("fileDelRecs")
@ResponseBody
public class FileDelRecController {

    @Resource
    private IFileDelRecService fileDelRecService;
    

    /**批量删除回收站记录*/
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @Authorization
    public JsonResult deleteDelRec(HttpServletRequest request , @RequestBody Map map) throws Exception {
        List ids =  (List)map.get("ids");
        fileDelRecService.deleteDelRec(ids);
        return JsonResult.ok("");
    }

    /**
     * 分页查询回收站记录信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public JsonResult queryDelRecords(Integer pageNum, Integer pageSize){
        PageResult recs = fileDelRecService.getDelRecForPage(pageNum, pageSize);
        return JsonResult.build(StatusCode.SUCCESS, recs);
    }


}
