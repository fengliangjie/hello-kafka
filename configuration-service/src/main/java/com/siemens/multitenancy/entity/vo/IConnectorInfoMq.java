package com.siemens.multitenancy.entity.vo;

import com.siemens.multitenancy.entity.po.IConnectorConfig;
import io.swagger.annotations.ApiModel;
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
@ApiModel(value = "iConnector info to mq")
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
         * Status
         *
         * 0: Normal
         * 1: Partial abnormal
         * 2: Abnormal
         */
        private Integer status;

        /**
         * configs
         */
        private List<IConnectorConfig> configs;
    }
}
