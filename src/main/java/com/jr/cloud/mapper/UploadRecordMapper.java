package com.jr.cloud.mapper;

import com.jr.cloud.entity.UploadRecord;
import com.jr.cloud.entity.UploadRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UploadRecordMapper {
    int countByExample(UploadRecordExample example);

    int deleteByExample(UploadRecordExample example);

    int deleteByPrimaryKey(Integer uploadId);

    int insert(UploadRecord record);

    int insertSelective(UploadRecord record);

    List<UploadRecord> selectByExample(UploadRecordExample example);

    UploadRecord selectByPrimaryKey(Integer uploadId);

    int updateByExampleSelective(@Param("record") UploadRecord record, @Param("example") UploadRecordExample example);

    int updateByExample(@Param("record") UploadRecord record, @Param("example") UploadRecordExample example);

    int updateByPrimaryKeySelective(UploadRecord record);

    int updateByPrimaryKey(UploadRecord record);
}