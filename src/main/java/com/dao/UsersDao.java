package com.dao;

import com.models.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
@Transactional
public interface UsersDao extends JpaRepository<Users, Long>, JpaSpecificationExecutor<Users> {


    //LastModifiedDate不起作用的解决办法
	/*在实体中添加注解 @EntityListeners(AuditingEntityListener.class)监听实体变化
	在自动更新时间戳字段增加 @LastModifiedDate
	在Spring boot启动类增加注解 @EnableJpaAuditing启用JPA审计(自动填充默认值)
	如果你是使用JPA的save(实体)方法去更新数据是没有问题的，如果是使用SQL/JPQL语句就会失效。比如：
	@Query("update xxx set x = ? where x = ?")，这里提供最简单的解决办法，语句里时间字段赋值CURRENT_TIMESTAMP即可*/

    @Modifying
    @Query("update Users t set t.username = :#{#users.username} , t.email =:#{#users.email}, t.mobile = :#{#users.mobile}  where t.userId=:#{#users.userId}")
    int updateUsers(@Param("users") Users users);

    Users findById(long id);

    List<Users> findByUserIdIn(List<Long> userId);

    List<Users> findUsersByUsername(String username);

    List<Users> findUsersByUsernameLikeIgnoreCaseOrderByLoginDateTime(String username);

    Users findByUserId(Long userId);

    Users findByUserAccount(String userAccount);

    List<Users> findByUserAccountOrEmailIgnoreCase(String userAccount, String email);

    List<Users> findByUserAccountNotIn(List<String> userAccounts);

    Integer countByUserAccountOrEmail(String userAccount, String email);

    Users findUsersByEmail(String email);

    void deleteAll();

    @Query(nativeQuery = true, value = "select u.userId, r.roleId, p.permissionsId, p.permissionCode, u.username, p.action, r.roleCode, u.description, u.userAccount from role_permissions rp, permissions p, roles r, user_roles ur, users u where 1 = 1 and rp.permissionsId = p.permissionsId and rp.roleId = r.roleId and ur.roleId = r.roleId and ur.userId = u.userId order by userId")
    List<Map> findUserRolePermission();

    @Query(nativeQuery = true, value = "select u.userAccount, u.username, u.enabled user_enable, u.status user_status, u.userId, ur.userRolesId, r.roleId, r.roleCode, r.roleName, r.status role_status, rp.rolePermissionsId, p.permissionsId, p.action, p.enabled permission_enable, p.permissionCode from users u, user_roles ur, roles r, role_permissions rp, permissions p where u.userId = ur.userId and r.roleId = ur.roleId and p.permissionsId = rp.permissionsId order by u.userAccount")
    List<Map> findUserRolePermissionDetail();

    @Query(nativeQuery = true, value = "select u.userId userId, u.createdBy createdBy, u.createdDate createdDate, u.lastUpdateDate lastUpdateDate, u.lastUpdatedBy lastUpdatedBy, u.version version, u.age age, u.dateOfBirth dateOfBirth, u.description description, u.domain `domain`, u.email email, u.enabled enabled, u.expireDate expireDate, u.gender gender, u.hostName hostName, u.ip ip, u.loginDateTime loginDateTime, u.mobile mobile, u.password password, u.remark remark, u.status status, u.userAccount userAccount, u.username username, r.roleId roleId, r.roleCode roleCode, r.roleName roleName, p.permissionsId permissionsId, p.action `action`, p.description permissions_description, p.permissionCode permissionCode from role_permissions rp, permissions p, roles r, user_roles ur, users u where 1 = 1 and rp.permissionsId = p.permissionsId and rp.roleId = r.roleId and ur.roleId = r.roleId and ur.userId = u.userId and u.userId = :#{#userId} order by userId")
    List<Map> findUserRolePermissionDetailById(@Param("userId") long userId);

    @Query(nativeQuery = true, value = "select u.* from users u order by u.loginDateTime desc limit :#{#limit}")
    List<Users> findUsersOrderByLoginDateTime(@Param("limit") Integer limit);


}
