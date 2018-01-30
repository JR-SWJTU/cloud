package com.jr.cloud.service;

import com.jr.cloud.entity.DelRecord;
import com.jr.cloud.util.PageResult;
import com.jr.cloud.web.exception.base.CustomException;

import java.util.List;

/**
 * Created by Administrator on 2018/1/28.
 */
public interface IFileDelRecService {

    /**
     * 添加一条文件删除记录记录到数据库
     * @param rec 文件删除记录对象
     */
    public void addDelRec( DelRecord rec) throws CustomException;

    /**
     * 删除文件删除记录
     * @param ids 要删除的文件删除记录的id的数组
     */
    public void deleteDelRec(List ids) throws Exception;

    /**
     * 分页查询文件删除记录
     * @param pageNum
     * @param pageSize
     * @return  PageResult 文件删除记录信息数组和文件删除记录的总条数
     */
    public PageResult getDelRecForPage(Integer pageNum, Integer pageSize) ;


}
