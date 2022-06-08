package com.deviniti.multitenancy.separate.schema.entity.po;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author: liangjie.feng
 * @date: 2022/6/8 10:25 AM
 *
 * IConnector module
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "iconnector_module")
@EqualsAndHashCode(callSuper=false)
public class IConnectorModule implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Name
     */
    @Column(name = "name", unique = true)
    private String name;

    /**
     * Version
     */
    @Column(name = "version")
    private String version;

    /**
     * Status
     *
     * 0: Normal
     * 1: Partial abnormal
     * 2: Abnormal
     */
    @Column(name = "status")
    private Integer status;
}
