package com.jr.cloud.mapper;

import com.jr.cloud.entity.UploadHistory;
import com.jr.cloud.entity.UploadHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UploadHistoryMapper {
    int countByExample(UploadHistoryExample example);

    int deleteByExample(UploadHistoryExample example);

    int deleteByPrimaryKey(Integer uploadId);

    int insert(UploadHistory record);

    int insertSelective(UploadHistory record);

    List<UploadHistory> selectByExample(UploadHistoryExample example);

    UploadHistory selectByPrimaryKey(Integer uploadId);

    int updateByExampleSelective(@Param("record") UploadHistory record, @Param("example") UploadHistoryExample example);

    int updateByExample(@Param("record") UploadHistory record, @Param("example") UploadHistoryExample example);

    int updateByPrimaryKeySelective(UploadHistory record);

    int updateByPrimaryKey(UploadHistory record);
}