package com.jr.cloud.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jr.cloud.entity.UploadRecord;
import com.jr.cloud.entity.UploadRecordExample;
import com.jr.cloud.mapper.UploadRecordMapper;
import com.jr.cloud.service.IFileService;
import com.jr.cloud.service.IFileUploadRecService;
import com.jr.cloud.util.FileSystemUtil;
import com.jr.cloud.util.PageResult;
import com.jr.cloud.web.exception.base.CustomException;
import com.jr.cloud.web.exception.base.ReqParmIncorException;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/1/30.
 */
@Service
public class FileUploadRecServiceImpl implements IFileUploadRecService {
    
    @Resource
    private UploadRecordMapper recMapper;
    @Resource
    private IFileService fileService;
    
    /**
     * 添加一条上传记录记录到数据库
     *
     * @param rec 上传记录对象
     */
    public void addUploadRecord(UploadRecord rec) throws CustomException {
        if(rec.getUserId() == null || rec.getFileName() ==null || rec.getFileSize() == null)
            throw new ReqParmIncorException();
        rec.setUploadedSize((long)0);
        rec.setUploadedSliceNum(0);
        rec.setSavePath(rec.getSavePath() +  rec.getFileName().substring( 0, rec.getFileName().lastIndexOf(".")));
        rec.setUploadDate( new Date(System.currentTimeMillis()));
        recMapper.insert(rec);
    }

    /**
     * 删除上传记录
     *
     * @param ids 要删除的上传记录的id的数组
     */
    public void deleteUploadRecord(List ids) throws Exception {
        int id;
        UploadRecord rec = null;
        for(int i = 0 ;  i < ids.size(); i++){
            if(!(ids.get(i) instanceof Long || ids.get(i) instanceof Integer))
                throw new ReqParmIncorException("参数非整型数据");
            id = (Integer) ids.get(i);
            rec = recMapper.selectByPrimaryKey(id);
            //删除保存的临时文件夹及其中的分片文件
            fileService.deleteDir(  rec.getFileName() );
            //删除上传记录
            recMapper.deleteByPrimaryKey( id);
        }
    }

    /**
     * 分页查询上传记录信息
     *
     * @param pageNum
     * @param pageSize
     * @return PageResult 上传记录信息数组和上传记录的总条数
     */
    public PageResult getUploadRecordForPage(Integer pageNum, Integer pageSize) {
        if(pageNum == null || pageSize == null)
            throw  new CustomException("参数缺失");
        PageHelper.startPage(pageNum, pageSize);
        List<UploadRecord> recs = recMapper.selectByExample(new UploadRecordExample());
        PageInfo<UploadRecord> pageInfo = new PageInfo<UploadRecord>(recs);
        PageResult pageResult = new PageResult();
        pageResult.setRows(recs);
        pageResult.setTotal(pageInfo.getTotal());
        return pageResult;
    }

    /**
     * 更新上传记录
     *
     * @param rec
     */
    public void updateUploadRecord(UploadRecord rec) throws IOException {
        FileSystem fs = FileSystemUtil.getFileSystem( rec.getSavePath());
        long size = 0;
        //获取分片文件列表并统计已上传的分片文件大小
        FileStatus[] fileStatuses = fs.listStatus(new Path(FileSystemUtil.uri + rec.getSavePath()));
        for(int i = 0 ; i < fileStatuses.length; i++){
            size += fileStatuses[i].getLen();
        }
        rec.setUploadedSize(size);
        System.out.print(rec.getUploadId());
        //更新上传记录到数据库
        recMapper.updateByPrimaryKey(rec);
    }
}
