package com.jr.cloud.timedTask;

import com.jr.cloud.entity.DelRecord;
import com.jr.cloud.mapper.DelRecordMapperCustom;
import com.jr.cloud.service.IFileDelRecService;
import com.jr.cloud.service.IFileService;
import com.jr.cloud.util.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/1/31.
 */
public class DelExpireFile  {

    private static final Logger _log = LoggerFactory.getLogger(DelExpireFile.class);

    @Resource
    private IFileDelRecService fileDelRecService;
    @Resource
    private DelRecordMapperCustom delRecordMapperCustom;
    @Resource
    private IFileService fileService;

    public void execute() {
        PageResult res = null;
        res.setTotal((long)100);
        for(int i = 0 ;  res.getTotal() == 100 ;i++){
            res = fileDelRecService.getDelRecForPage(i, 100);
            List<DelRecord> lists = res.getRows();
            for(int j = 0 ; j < lists.size(); j++){
                try {
                    //删除所有过期文件
                    fileService.deleteDir(lists.get(j).getFileName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        //删除所有过期的文件删除记录
        delRecordMapperCustom.delExpireRec();


        System.out.println("定时任务执行了");

        _log.error("定时删除文件任务执行时间: " + new Date());
    }
}
