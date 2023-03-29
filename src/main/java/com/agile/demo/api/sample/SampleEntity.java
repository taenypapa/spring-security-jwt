package com.agile.demo.api.sample;

import com.agile.demo.core.base.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="T_SAMPLE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SampleEntity extends BaseEntity {

    @Column(name="sample_title")
    private String title;
}
