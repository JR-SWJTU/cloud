package com.jr.cloud.web.controller;

import com.jr.cloud.annotation.Authorization;
import com.jr.cloud.entity.UploadRecord;
import com.jr.cloud.service.IFileUploadRecService;
import com.jr.cloud.util.FileSystemUtil;
import com.jr.cloud.util.JsonResult;
import com.jr.cloud.util.PageResult;
import com.jr.cloud.util.enums.StatusCode;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
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
@RequestMapping("fileUploadRecs")
@ResponseBody
public class FileUploadRecController {

    @Resource
    private IFileUploadRecService recService;

    /**
     * 添加上传记录信息,同时创建存储分片文件的临时文件夹
     * @param rec
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/startUpload", method = RequestMethod.POST)
    public JsonResult startUpload(@RequestBody UploadRecord rec) throws Exception{
        recService.addUploadRecord(rec);
        String fullUri = FileSystemUtil.uri + rec.getSavePath();
        FileSystem fs = FileSystemUtil.getFileSystem(FileSystemUtil.uri);
        Path path = new Path(fullUri);
        //判断分片存储的临时文件夹是否存在，不存在则创建
        if( !fs.exists( path)){
            fs.mkdirs( path);
        }
        return JsonResult.build(StatusCode.SUCCESS, rec.getUploadId());
    }

    /**
     * 暂停上传文件，更新数据库中的上传记录（更新已经上传的文件大小）
     * @param rec
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/stopUpload", method = RequestMethod.POST)
    public JsonResult stopUpload(@RequestBody UploadRecord rec) throws Exception{
        recService.updateUploadRecord(rec);
        return JsonResult.build(StatusCode.SUCCESS);
    }

    /**批量删除上传记录*/
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @Authorization
    public JsonResult deleteDepartment(HttpServletRequest request , @RequestBody Map map) throws Exception {
        List ids =  (List)map.get("ids");
        recService.deleteUploadRecord(ids);
        return JsonResult.ok("");
    }

    /**
     * 分页查询上传记录信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public JsonResult queryUploadRecords(Integer pageNum, Integer pageSize){
        PageResult recs = recService.getUploadRecordForPage(pageNum, pageSize);
        return JsonResult.build(StatusCode.SUCCESS, recs);
    }


}
