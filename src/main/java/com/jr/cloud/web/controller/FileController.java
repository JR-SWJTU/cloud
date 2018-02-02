package com.jr.cloud.web.controller;

import com.jr.cloud.annotation.Authorization;
import com.jr.cloud.entity.DelRecord;
import com.jr.cloud.entity.UploadHistory;
import com.jr.cloud.entity.UploadRecord;
import com.jr.cloud.mapper.UploadRecordMapper;
import com.jr.cloud.service.IFileDelRecService;
import com.jr.cloud.service.IFileService;
import com.jr.cloud.service.IFileUploadHisService;
import com.jr.cloud.service.IFileUploadRecService;
import com.jr.cloud.util.FileUtil;
import com.jr.cloud.util.JsonResult;
import com.jr.cloud.util.enums.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;
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
    @Resource
    private IFileDelRecService fileDelRecService;
    @Autowired
    private IFileUploadHisService fileUploadHisService;
    @Autowired
    private UploadRecordMapper uploadRecordMapper;

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
        Integer id = (Integer)map.get("id");
//        fileService.deleteDir( path );
        //移动文件到用户专属的文件回收站文件夹中
        fileService.moveFile(path, String.valueOf(id)+ "DelSpace");
        //在数据库中添加一条文件删除记录
        DelRecord rec = new DelRecord();
        rec.setUserId(id);
        //裁剪存储路径
        path = path.substring( path.indexOf("/")+1);
        path = path.substring( path.indexOf("/")+1);
        rec.setSavePath( String.valueOf(id)+ "DelSpace" + "/" +  path);
        //裁剪文件名称
        rec.setFileName( path.substring( path.lastIndexOf("/") + 1 ));
        rec.setDelDate( new Date(System.currentTimeMillis()));
        fileDelRecService.addDelRec(rec);
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


    /**
     * 发送分片文件前检测分片文件是否已经上传(文件断点传输重新开始传输时，需要判断哪些分片文件是以前已经上传过的)
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/fileExist", method = RequestMethod.POST)
    @Authorization
    public JsonResult fileExist(@RequestBody Map map) throws Exception{
        String fileName = (String)map.get("fileName");
        int chunkNum = (Integer) map.get("chunkNum");
        String savePath = (String) map.get("savePath");
        long chunkSize = Long.parseLong(String.valueOf((Integer)map.get("chunkSize")));
        if( FileUtil.fileExists(savePath ,  chunkNum , chunkSize) ){
            return JsonResult.build(StatusCode.SUCCESS , true);
        }else{
            return JsonResult.build(StatusCode.SUCCESS, false);
        }
    }

    /**
     * 存储分片文件
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @Authorization
    public JsonResult uploadFile(String savePath, int chunkNum, MultipartFile file) throws Exception{
        FileUtil.saveFile( savePath, chunkNum, file);
        return JsonResult.build(StatusCode.SUCCESS);
    }

    /**
     * 所有分片文件上传完成，进行分片文件合并，并添加文件下载完成记录，删除文件上传记录
     * @param rec
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/mergeFile", method = RequestMethod.POST)
    @Authorization
    public JsonResult mergeFile(@RequestBody UploadRecord  rec) throws Exception{
        String fileName =  rec.getFileName();
        int userId = rec.getUserId();
        //合并分片文件形成大文件
        FileUtil.mergeFile( rec.getSavePath(), fileName);
        //添加上传历史记录
        UploadHistory recHis = new UploadHistory(rec);
        fileUploadHisService.addUploadHistory(recHis);
        //删除正在上传记录
        uploadRecordMapper.deleteByPrimaryKey(rec.getUploadId());
        //返回服务器处理结果
        return JsonResult.build(StatusCode.SUCCESS);
    }

}
