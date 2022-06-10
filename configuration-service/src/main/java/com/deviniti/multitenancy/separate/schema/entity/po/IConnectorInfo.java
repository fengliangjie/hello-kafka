package com.deviniti.multitenancy.separate.schema.entity.po;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author: liangjie.feng
 * @date: 2022/6/8 11:34 AM
 *
 * IConnector info
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "iconnector_info")
@EqualsAndHashCode(callSuper=false)
public class IConnectorInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Connector id
     */
    @Column(name = "connector_id", unique = true)
    private String connectorId;

    /**
     * Status
     *
     * 0: Normal
     * 1: Partial abnormal
     * 2: Abnormal
     */
    @Column(name = "status")
    private Integer status;

    /**
     * The status of the collected data
     *
     * -1: First registration, no data was sent
     * 0: Normal
     * 1: Configure the delivery exception
     * 2: Data handling upload exceptions
     */
    @Column(name = "collect_status")
    private Integer collectStatus;

    /**
     * version
     */
    @Column(name = "version")
    private String version;

    /**
     * info The time it was generated
     */
    @Column(name = "date_time")
    private Long dateTime;

    /**
     * modules
     */
    @OneToMany
    @JoinColumn(name = "info_id", referencedColumnName = "id")
    private List<IConnectorModule> modules;

    /**
     * configs
     */
    @OneToMany
    @JoinColumn(name = "info_id", referencedColumnName = "id")
    private List<IConnectorConfig> configs;
}
