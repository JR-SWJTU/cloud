package com.jr.cloud.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jr.cloud.entity.DelRecord;
import com.jr.cloud.entity.DelRecordExample;
import com.jr.cloud.mapper.DelRecordMapper;
import com.jr.cloud.mapper.DelRecordMapperCustom;
import com.jr.cloud.service.IFileDelRecService;
import com.jr.cloud.util.PageResult;
import com.jr.cloud.web.exception.base.CustomException;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2018/1/28.
 */
@Service
public class FileDelRecServiceImpl implements IFileDelRecService {

    @Resource
    private DelRecordMapper delRecordMapper;
    @Resource
    private DelRecordMapperCustom delRecordMapperCustom;

    /**
     * 添加一条文件删除记录记录到数据库
     *
     * @param rec 文件删除记录对象
     */
    public void addDelRec(DelRecord rec) throws CustomException {
        delRecordMapper.insert(rec);
    }

    /**
     * 删除文件删除记录
     *
     * @param ids 要删除的文件删除记录的id的数组
     */
    public void deleteDelRec(List ids) throws Exception {
        for(int i = 0 ;  i < ids.size(); i++){
            delRecordMapper.deleteByPrimaryKey( (Integer) ids.get(i));
        }
    }

    /**
     * 分页查询文件删除记录
     *
     * @param pageNum
     * @param pageSize
     * @return PageResult 文件删除记录信息数组和文件删除记录的总条数
     */
    public PageResult getDelRecForPage(Integer pageNum, Integer pageSize) {
        if(pageNum == null || pageSize == null)
            throw  new CustomException("参数缺失");
        PageHelper.startPage(pageNum, pageSize);
        List<DelRecord> recs = delRecordMapperCustom.queryExpireRec();
        PageInfo<DelRecord> pageInfo = new PageInfo<DelRecord>(recs);
        PageResult pageResult = new PageResult();
        pageResult.setRows(recs);
        pageResult.setTotal(pageInfo.getTotal());
        return pageResult;
    }
}
