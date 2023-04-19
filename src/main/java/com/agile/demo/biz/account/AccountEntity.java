package com.agile.demo.biz.account;

import com.agile.demo.core.base.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "T_CM_ACCOUNT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountEntity extends BaseEntity {

    @Column(unique = true)
    private String userId;
    private String password;

    private String role;

    private String name;
    private String phone;
    private String email;
}
