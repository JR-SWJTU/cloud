package com.jr.cloud.timedTask;


import com.jr.cloud.entity.DelRecord;
import com.jr.cloud.mapper.DelRecordMapperCustom;
import com.jr.cloud.service.IFileDelRecService;
import com.jr.cloud.service.IFileService;
import com.jr.cloud.util.PageResult;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

public class DeleteExpireDelFile implements Job{

    private static Logger _log = LoggerFactory.getLogger(DeleteExpireDelFile.class);

    @Resource
    private IFileDelRecService fileDelRecService;
    @Resource
    private IFileService fileService;
    @Resource
    private DelRecordMapperCustom delRecordMapperCustom;

    //定时任务,每天定时检查删除文件记录表中过期的文件，并将其彻底删除
    public void execute(JobExecutionContext context)
            throws JobExecutionException {

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
