package com.agile.demo.core.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Id
    @Column(name="seq")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long seq;

    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    @Column(name = "saved_at", nullable = true, insertable = true, updatable = false)
    private LocalDateTime savedAt;

    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    @Column(name = "modified_at", nullable = true, insertable = true, updatable = true)
    private LocalDateTime modifiedAt;

    @PrePersist
    public void setDate() {
        this.setSavedAt(LocalDateTime.now());
        this.setModifiedAt(LocalDateTime.now());
    }

    @PreUpdate
    public void modifiedDate() {
        this.setModifiedAt(LocalDateTime.now());
    }

}