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
    @Column(name = "ID")
    private Long id;

    /**
     * Connector id
     */
    @Column(name = "CONNECTOR_ID", unique = true)
    private String connectorId;

    /**
     * Status
     *
     * 0: Normal
     * 1: Partial abnormal
     * 2: Abnormal
     */
    @Column(name = "STATUS")
    private Integer status;

    /**
     * The status of the collected data
     *
     * -1: First registration, no data was sent
     * 0: Normal
     * 1: Configure the delivery exception
     * 2: Data handling upload exceptions
     */
    @Column(name = "COLLECT_STATUS")
    private Integer collectStatus;

    /**
     * version
     */
    @Column(name = "VERSION")
    private String version;

    /**
     * info The time it was generated
     */
    @Column(name = "DATE_TIME")
    private Long dateTime;

    /**
     * modules
     */
    @OneToMany
    @JoinColumn(name = "INFO_ID", referencedColumnName = "ID")
    private List<IConnectorModule> modules;

    /**
     * configs
     */
    @OneToMany
    @JoinColumn(name = "INFO_ID", referencedColumnName = "ID")
    private List<IConnectorConfig> configs;
}
