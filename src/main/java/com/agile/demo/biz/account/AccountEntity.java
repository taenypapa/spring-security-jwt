package com.agile.demo.biz.account;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_CM_ACCOUNT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountEntity {

    @Id
    private Long seq;

    @Column(name = "AC_NAME", nullable = false, updatable = false, length = 40)
    private String name;
    private String phone;
    private String email;

    private LocalDateTime savedAt;
}
