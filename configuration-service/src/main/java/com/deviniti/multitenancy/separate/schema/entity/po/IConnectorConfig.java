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
public class IConnectorConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
}
