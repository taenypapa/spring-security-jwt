package com.agile.demo.biz.backlog;

import com.agile.demo.biz.project.ProjectEntity;
import com.agile.demo.core.base.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "T_AGL_BACKLOG")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BacklogEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "seq", insertable = false, unique = true, updatable = false)
    private ProjectEntity project;
}
