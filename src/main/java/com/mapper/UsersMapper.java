package com.mapper;


import com.models.entity.Users;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface UsersMapper {
    int deleteByPrimaryKey(Long userid);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Long userid);

    Users selectByAccount(String account);

    List<Map> getUserRolePermissionById(Long userid);

    List<Map> findAllUserRolePermissionByMybatis();

    List<Users> findAll();

    List<Users> findUsersLikeNameByMybatis(String userName);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    int updateByAccount(Users record);

}