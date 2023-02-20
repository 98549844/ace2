package com.models.entity;


import com.models.entity.base.BaseEntity;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@EntityListeners(AuditingEntityListener.class)
@Table(name = "roles", uniqueConstraints = {@UniqueConstraint(name = "constraint_roleCode", columnNames = "roleCode")})
@Entity
public class Roles extends BaseEntity implements Serializable {
    //roleCode
    public static final String ADMIN = "ADMIN";
    public static final String DISABLE = "DISABLE";
    public static final String USER = "USER";
    public static final String VIEWER = "VIEWER";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(strategy = "identity", name = "id")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long roleId;

    @Column
    private String roleCode;

    @Column
    private String roleName;

    @Column
    private String status = "ACTIVE"; // ACTIVE || INACTIVE


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
