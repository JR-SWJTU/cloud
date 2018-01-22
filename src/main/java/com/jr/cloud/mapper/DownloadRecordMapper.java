package com.jr.cloud.mapper;

import com.jr.cloud.entity.DownloadRecord;
import com.jr.cloud.entity.DownloadRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DownloadRecordMapper {
    int countByExample(DownloadRecordExample example);

    int deleteByExample(DownloadRecordExample example);

    int deleteByPrimaryKey(Integer downloadId);

    int insert(DownloadRecord record);

    int insertSelective(DownloadRecord record);

    List<DownloadRecord> selectByExample(DownloadRecordExample example);

    DownloadRecord selectByPrimaryKey(Integer downloadId);

    int updateByExampleSelective(@Param("record") DownloadRecord record, @Param("example") DownloadRecordExample example);

    int updateByExample(@Param("record") DownloadRecord record, @Param("example") DownloadRecordExample example);

    int updateByPrimaryKeySelective(DownloadRecord record);

    int updateByPrimaryKey(DownloadRecord record);
}