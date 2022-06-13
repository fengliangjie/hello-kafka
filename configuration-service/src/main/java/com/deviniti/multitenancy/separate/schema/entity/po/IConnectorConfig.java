package com.deviniti.multitenancy.separate.schema.entity.po;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author: liangjie.feng
 * @date: 2022/6/8 11:34 AM
 *
 * IConnector config
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "iconnector_config")
@EqualsAndHashCode(callSuper=false)
@SequenceGenerator(name="seq_gen", sequenceName="iconnector_config_id_seq", allocationSize = 1, initialValue = 1)
public class IConnectorConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @Id
    @GeneratedValue(generator="seq_gen")
    @Column(name = "id")
    private Long id;
}
