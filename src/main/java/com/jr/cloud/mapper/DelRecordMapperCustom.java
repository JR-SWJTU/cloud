package com.jr.cloud.mapper;

import com.jr.cloud.entity.DelRecord;

import java.util.List;

/**
 * Created by Administrator on 2018/1/13.
 */
public interface DelRecordMapperCustom {

    List<DelRecord> queryExpireRec();

    void delExpireRec();
}
