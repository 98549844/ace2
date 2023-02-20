package com.mapper;

import com.models.entity.AccessLog;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AccessLogMapper {
    int deleteByPrimaryKey(Long logId);

    int insert(AccessLog record);

    int insertSelective(AccessLog record);

    AccessLog selectByPrimaryKey(Long logId);

    List<AccessLog> selectAll();

    int updateByPrimaryKeySelective(AccessLog record);

    Long updateByPrimaryKey(AccessLog record);
}