package com.mapper;

import com.models.entity.Test;
import org.springframework.stereotype.Component;

@Component
public interface TestMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteAll();

    int insert(Test record);

    int insertSelective(Test record);

    Test selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Test record);

    int updateByPrimaryKey(Test record);
}