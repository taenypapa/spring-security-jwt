package com.agile.demo.biz.task;

import com.agile.demo.biz.account.AccountEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "T_AGL_TASK")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskEntity {
    @Id
    private Long seq;

    @OneToOne
    @JoinColumn(name = "seq", insertable = false, unique = true, updatable = false)
    private AccountEntity account;
}
