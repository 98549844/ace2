package com.dao;

import com.models.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Classname: MenuDao
 * @Date: 13/11/2021 12:53 上午
 * @Author: garlam
 * @Description:
 */


@Repository
@Transactional
public interface MenuDao extends JpaRepository<Menu, Long>, JpaSpecificationExecutor<Menu> {


    @Modifying
    List<Menu> getDistinctByParentId(Long rootParentId);


}
