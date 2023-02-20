package com.models.entity;

import com.models.entity.base.BaseEntity;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@EntityListeners(AuditingEntityListener.class)
@Table(name = "permissions", uniqueConstraints = {@UniqueConstraint(name = "constraint_permissionCode", columnNames = "permissionCode")})
@Entity
public class Permissions extends BaseEntity implements Serializable {

    //permissionCode
    public static final String ALL = "0";
    public static final String INSERT = "1";
    public static final String UPDATE = "2";
    public static final String DELETE = "3";
    public static final String SELECT = "4";
    public static final String SELECT_UPDATE_INSERT = "8";
    public static final String DENY = "10";


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(strategy = "identity", name = "id")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long permissionsId;
    @Column
    private String permissionCode;
    @Column
    private String action;
    @Column
    private String description;
    @Column
    private boolean enabled = true;


    public Long getPermissionsId() {
        return permissionsId;
    }

    public void setPermissionsId(Long permissionsId) {
        this.permissionsId = permissionsId;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
