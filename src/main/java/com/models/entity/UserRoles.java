package com.models.entity;


import com.models.entity.base.BaseEntity;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@EntityListeners(AuditingEntityListener.class)
@Table(name = "user_roles", uniqueConstraints = {@UniqueConstraint(name = "constraint_userRole", columnNames = {"userId", "roleId"})})
//一个用户在一个组别不能同出现两次
@Entity
public class UserRoles extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(strategy = "identity", name = "id")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long userRolesId;

    @Column
    private Long roleId;
    @Column
    private Long userId;

    public Long getUserRolesId() {
        return userRolesId;
    }

    public void setUserRolesId(Long userRolesId) {
        this.userRolesId = userRolesId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
