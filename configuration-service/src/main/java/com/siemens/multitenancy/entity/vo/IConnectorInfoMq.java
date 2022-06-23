package com.siemens.multitenancy.entity.vo;

import com.siemens.multitenancy.entity.po.IConnectorConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: liangjie.feng
 * @date: 2022/6/8 3:17 PM
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IConnectorInfoMq {

    private static final long serialVersionUID = 1L;

    /**
     * Connector id
     */
    private String connectorId;

    /**
     * info The time it was generated
     */
    private Long dateTime;

    private Data data;

    @Builder
    @lombok.Data
    public static class Data {
        /**
         * Whether data collection is allowed
         */
        private Boolean enable;

        /**
         * configs
         */
        private List<IConnectorConfig> configs;
    }
}
