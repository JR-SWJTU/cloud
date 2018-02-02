package com.jr.cloud.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jr.cloud.entity.UploadHistory;
import com.jr.cloud.entity.UploadHistoryExample;
import com.jr.cloud.mapper.UploadHistoryMapper;
import com.jr.cloud.service.IFileService;
import com.jr.cloud.service.IFileUploadHisService;
import com.jr.cloud.service.IFileUploadRecService;
import com.jr.cloud.util.PageResult;
import com.jr.cloud.web.exception.base.CustomException;
import com.jr.cloud.web.exception.base.ReqParmIncorException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/1/30.
 */
@Service
public class FileUploadHisServiceImpl implements IFileUploadHisService {
    
    @Resource
    private UploadHistoryMapper recMapper;
    @Resource
    private IFileService fileService;
    
    /**
     * 添加一条上传历史记录记录到数据库
     *
     * @param rec 上传历史记录对象
     */
    public void addUploadHistory(UploadHistory rec) throws CustomException {
        if(rec.getUserId() == null || rec.getFileName() ==null || rec.getFileSize() == null)
            throw new ReqParmIncorException();
        recMapper.insert(rec);
    }

    /**
     * 删除上传历史记录
     *
     * @param ids 要删除的上传历史记录的id的数组
     */
    public void deleteUploadHistory(List ids) throws Exception {
        int id;
        UploadHistory rec = null;
        for(int i = 0 ;  i < ids.size(); i++){
            if(!(ids.get(i) instanceof Long || ids.get(i) instanceof Integer))
                throw new ReqParmIncorException("参数非整型数据");
            id = (Integer) ids.get(i);
            //删除上传历史记录
            recMapper.deleteByPrimaryKey( id);
        }
    }

    /**
     * 分页查询上传历史记录信息
     *
     * @param pageNum
     * @param pageSize
     * @return PageResult 上传历史记录信息数组和上传历史记录的总条数
     */
    public PageResult getUploadHistoryForPage(Integer pageNum, Integer pageSize) {
        if(pageNum == null || pageSize == null)
            throw  new CustomException("参数缺失");
        PageHelper.startPage(pageNum, pageSize);
        List<UploadHistory> recs = recMapper.selectByExample(new UploadHistoryExample());
        PageInfo<UploadHistory> pageInfo = new PageInfo<UploadHistory>(recs);
        PageResult pageResult = new PageResult();
        pageResult.setRows(recs);
        pageResult.setTotal(pageInfo.getTotal());
        return pageResult;
    }
}
