package com.service;

import com.mapper.DataBaseMapper;
import com.models.entity.Columns;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname: DataBaseService
 * @Date: 6/12/2021 4:00 上午
 * @Author: garlam
 * @Description:
 */


@Service
public class DataBaseService {
    private static final Logger log = LogManager.getLogger(DataBaseService.class.getName());

    private DataBaseMapper dataBaseMapper;

    @Autowired
    public void setDataBaseMapper(DataBaseMapper dataBaseMapper) {
        this.dataBaseMapper = dataBaseMapper;
    }



    public List<String> getAllTableName (){
        String schema = "ace";
        return dataBaseMapper.getAllTableName(schema);
    }

    public List<Columns> getColumnName (String tableName){
        String schema = "ace";
        return dataBaseMapper.getColumnNameByTable(tableName, schema);
    }

}

