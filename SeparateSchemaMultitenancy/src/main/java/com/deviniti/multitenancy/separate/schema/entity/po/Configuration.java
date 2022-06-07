package com.deviniti.multitenancy.separate.schema.entity.po;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import java.sql.Timestamp;

@Entity
@SequenceGenerator(name="seq_gen", sequenceName="configuration_id_seq", allocationSize = 1, initialValue = 1)
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Configuration extends BaseEntity{
    private String name;
    private String tenantId;
    private Timestamp timestamp;
}
