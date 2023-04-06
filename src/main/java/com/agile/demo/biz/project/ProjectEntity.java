package com.agile.demo.biz.project;


import com.agile.demo.biz.backlog.BacklogEntity;
import com.agile.demo.core.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "T_AGL_ACCOUNT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectEntity {

    @Id
    private Long seq;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<BacklogEntity> backlogs;
}
