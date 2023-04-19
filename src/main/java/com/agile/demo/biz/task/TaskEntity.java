package com.agile.demo.biz.task;

import com.agile.demo.biz.account.AccountEntity;
import com.agile.demo.core.base.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "T_AGL_TASK")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskEntity extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "seq", insertable = false, unique = true, updatable = false)
    private AccountEntity account;
}
