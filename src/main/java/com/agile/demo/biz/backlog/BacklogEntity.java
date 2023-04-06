package com.agile.demo.biz.backlog;

import com.agile.demo.biz.project.ProjectEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "T_AGL_BACKLOG")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BacklogEntity {
    @Id
    private Long seq;

    @ManyToOne
    @JoinColumn(name = "seq", insertable = false, unique = true, updatable = false)
    private ProjectEntity project;
}
