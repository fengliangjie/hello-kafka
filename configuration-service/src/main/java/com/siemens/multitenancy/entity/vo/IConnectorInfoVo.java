package com.siemens.multitenancy.entity.vo;

import com.siemens.multitenancy.entity.po.IConnectorConfig;
import com.siemens.multitenancy.entity.po.IConnectorModule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: liangjie.feng
 * @date: 2022/5/31 5:35 PM
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IConnectorInfoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    private Long id;

    /**
     * Connector id
     */
    private String connectorId;

    /**
     * Status
     *
     * 0: Normal
     * 1: Partial abnormal
     * 2: Abnormal
     */
    private Integer status;

    /**
     * The status of the collected data
     *
     * -1: First registration, no data was sent
     * 0: Normal
     * 1: Configure the delivery exception
     * 2: Data handling upload exceptions
     */
    private Integer collectStatus;

    /**
     * version
     */
    private String version;

    /**
     * info The time it was generated
     */
    private LocalDateTime dateTime;

    /**
     * Whether data collection is allowed
     */
    private Boolean enable;

    /**
     * modules
     */
    private List<IConnectorModule> modules;

    /**
     * configs
     */
    private List<IConnectorConfig> configs;
}
