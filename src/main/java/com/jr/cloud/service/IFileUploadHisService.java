package com.jr.cloud.service;

import com.jr.cloud.entity.UploadHistory;
import com.jr.cloud.util.PageResult;
import com.jr.cloud.web.exception.base.CustomException;

import java.util.List;

/**
 * Created by Administrator on 2018/1/31.
 */
public interface IFileUploadHisService {

    /**
     * 添加一条上传历史记录记录到数据库
     * @param rec 上传历史记录对象
     */
    public void addUploadHistory( UploadHistory rec) throws CustomException;

    /**
     * 删除上传历史记录
     * @param ids 要删除的上传历史记录的id的数组
     */
    public void deleteUploadHistory(List ids) throws Exception;

    /**
     * 分页查询上传历史记录信息
     * @param pageNum
     * @param pageSize
     * @return  PageResult 上传历史记录信息数组和上传历史记录的总条数
     */
    public PageResult getUploadHistoryForPage(Integer pageNum, Integer pageSize) ;
}
