package com.agile.demo.biz.project;


import com.agile.demo.biz.backlog.BacklogEntity;
import com.agile.demo.core.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "T_AGL_ACCOUNT")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectEntity extends BaseEntity{

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<BacklogEntity> backlogs;
}
