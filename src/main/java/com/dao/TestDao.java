package com.dao;

import com.models.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TestDao extends JpaRepository<Test, Long>, JpaSpecificationExecutor<Test> {

    @Query("select t from Test t where t.id = :id")
    Test findById(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("update Test t set t.userName = :#{#test.userName} ,t.userId=:#{#test.userId} , t.email =:#{#test.email}  where t.id=:#{#test.id}")
    int update(@Param("test") Test test);

    @Modifying
    @Transactional
    @Query("delete from Test t where t.id=:id")
    int insert(@Param("id") Integer id);

    @Modifying
    @Transactional
    <S extends Test> List<S> saveAll(Iterable<S> testList);

    @Modifying
    @Transactional
    <S extends Test> S save(S test);


}
