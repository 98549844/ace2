package com.models.entity;

import com.models.entity.base.BaseEntity;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@EntityListeners(AuditingEntityListener.class)
@Table(name = "accessLog")
@Entity
public class AccessLog extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(strategy = "identity", name = "id")
    @Column(name = "logId")
    private Long logId;

    @Column(name = "operator")
    private String operator;
    @Column(name = "description")
    private String description;
    @Column(name = "accessTime")
    private LocalDateTime accessTime;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(LocalDateTime accessTime) {
        this.accessTime = accessTime;
    }
}
