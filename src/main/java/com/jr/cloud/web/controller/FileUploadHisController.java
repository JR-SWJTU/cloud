package com.jr.cloud.web.controller;

import com.jr.cloud.service.IFileUploadHisService;
import com.jr.cloud.util.JsonResult;
import com.jr.cloud.util.PageResult;
import com.jr.cloud.util.enums.StatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by UploadRecordistrator on 2017/10/18.
 */
@Controller
@RequestMapping("fileUploadHis")
@ResponseBody
public class FileUploadHisController {

    @Resource
    private IFileUploadHisService fileUploadHisService;

    /**
     * 分页查询上传历史
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public JsonResult queryUploadHis(Integer pageNum, Integer pageSize){
        PageResult recs = fileUploadHisService.getUploadHistoryForPage(pageNum, pageSize);
        return JsonResult.build(StatusCode.SUCCESS, recs);
    }
}
