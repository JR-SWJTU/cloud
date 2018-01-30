package com.jr.cloud.mapper;

import com.jr.cloud.entity.DelRecord;
import com.jr.cloud.entity.DelRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DelRecordMapper {
    int countByExample(DelRecordExample example);

    int deleteByExample(DelRecordExample example);

    int deleteByPrimaryKey(Integer delId);

    int insert(DelRecord record);

    int insertSelective(DelRecord record);

    List<DelRecord> selectByExample(DelRecordExample example);

    DelRecord selectByPrimaryKey(Integer delId);

    int updateByExampleSelective(@Param("record") DelRecord record, @Param("example") DelRecordExample example);

    int updateByExample(@Param("record") DelRecord record, @Param("example") DelRecordExample example);

    int updateByPrimaryKeySelective(DelRecord record);

    int updateByPrimaryKey(DelRecord record);
}