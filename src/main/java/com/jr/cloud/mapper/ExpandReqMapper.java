package com.jr.cloud.mapper;

import com.jr.cloud.entity.ExpandReq;
import com.jr.cloud.entity.ExpandReqExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExpandReqMapper {
    int countByExample(ExpandReqExample example);

    int deleteByExample(ExpandReqExample example);

    int deleteByPrimaryKey(Integer expandReqId);

    int insert(ExpandReq record);

    int insertSelective(ExpandReq record);

    List<ExpandReq> selectByExample(ExpandReqExample example);

    ExpandReq selectByPrimaryKey(Integer expandReqId);

    int updateByExampleSelective(@Param("record") ExpandReq record, @Param("example") ExpandReqExample example);

    int updateByExample(@Param("record") ExpandReq record, @Param("example") ExpandReqExample example);

    int updateByPrimaryKeySelective(ExpandReq record);

    int updateByPrimaryKey(ExpandReq record);
}