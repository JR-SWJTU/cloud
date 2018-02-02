package com.jr.cloud.service;

import com.jr.cloud.entity.UploadRecord;
import com.jr.cloud.util.PageResult;
import com.jr.cloud.web.exception.base.CustomException;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2018/1/30.
 */
public interface IFileUploadRecService {

    /**
     * 添加一条上传记录记录到数据库
     * @param rec 上传记录对象
     */
    public void addUploadRecord( UploadRecord rec) throws CustomException;

    /**
     * 删除上传记录
     * @param ids 要删除的上传记录的id的数组
     */
    public void deleteUploadRecord(List ids) throws Exception;

    /**
     * 分页查询上传记录信息
     * @param pageNum
     * @param pageSize
     * @return  PageResult 上传记录信息数组和上传记录的总条数
     */
    public PageResult getUploadRecordForPage(Integer pageNum, Integer pageSize) ;

    /**
     * 更新上传记录
     * @param rec
     */
    public void updateUploadRecord(UploadRecord rec) throws IOException;
}
