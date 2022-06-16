package com.siemens.multitenancy.mapper;

import com.siemens.multitenancy.entity.param.ClientUpdateParam;
import com.siemens.multitenancy.entity.param.IConnectorInfoParam;
import com.siemens.multitenancy.entity.po.IConnectorConfig;
import com.siemens.multitenancy.entity.po.IConnectorInfo;
import com.siemens.multitenancy.entity.po.IConnectorModule;
import com.siemens.multitenancy.entity.vo.IConnectorInfoMq;
import com.siemens.multitenancy.entity.vo.IConnectorInfoVo;
import com.siemens.multitenancy.entity.vo.PageVo;
import com.siemens.multitenancy.entity.vo.PaginationVo;
import lombok.NonNull;
import org.springframework.data.domain.Page;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: liangjie.feng
 * @date: 2022/5/31 5:20 PM
 */
public class ConfigurationMapper {

    /**
     * IConnectorInfo -> IConnectorInfoVo
     *
     * @param connectorInfo
     * @return
     */
    public static IConnectorInfoVo infoPoMapToVo(IConnectorInfo connectorInfo) {
        LocalDateTime localDateTime = Instant.ofEpochSecond(connectorInfo.getDateTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        return IConnectorInfoVo.builder()
                .id(connectorInfo.getId())
                .connectorId(connectorInfo.getConnectorId())
                .status(connectorInfo.getStatus())
                .collectStatus(connectorInfo.getCollectStatus())
                .version(connectorInfo.getVersion())
                .dateTime(localDateTime)
                .enable(connectorInfo.getEnable())
                .modules(connectorInfo.getModules())
                .configs(connectorInfo.getConfigs())
                .build();
    }

    /**
     * IConnectorInfo -> IConnectorInfoMq
     *
     * @param connectorInfo
     * @return
     */
    public static IConnectorInfoMq infoPoMapToMq(IConnectorInfo connectorInfo) {
        return IConnectorInfoMq.builder()
                .connectorId(connectorInfo.getConnectorId())
                .dateTime(connectorInfo.getDateTime())
                .data(IConnectorInfoMq.Data.builder()
                        .enable(connectorInfo.getEnable())
                        .configs(connectorInfo.getConfigs()).build())
                .build();
    }

    /**
     * insert action
     *
     * IConnectorInfoParam -> IConnectorInfo
     *
     * @param infoParam
     * @return
     */
    public static IConnectorInfo infoParamMapToPo(IConnectorInfoParam infoParam) {
        IConnectorInfoParam.Data data = infoParam.getData();
        List<IConnectorModule> modules = data.getModules().stream().map(ConfigurationMapper::moduleParamMapToPo).collect(Collectors.toList());
        return IConnectorInfo.builder()
                .connectorId(infoParam.getConnectorId())
                .status(data.getStatus())
                .collectStatus(data.getCollectStatus())
                .version(data.getVersion())
                .dateTime(infoParam.getDateTime())
                .modules(modules)
                .configs(new ArrayList<>())
                .build();
    }

    /**
     * update action
     *
     * IConnectorInfoParam -> IConnectorInfo
     *
     * @param info
     * @param infoParam
     */
    public static void infoParamMapToPo(IConnectorInfo info, IConnectorInfoParam infoParam) {
        IConnectorInfoParam.Data data = infoParam.getData();
        List<IConnectorModule> modules = data.getModules().stream().map(ConfigurationMapper::moduleParamMapToPo).collect(Collectors.toList());
        info.setStatus(data.getStatus());
        info.setCollectStatus(data.getCollectStatus());
        info.setVersion(data.getVersion());
        info.setDateTime(infoParam.getDateTime());
        info.getModules().clear();
        info.getModules().addAll(modules);
    }

    /**
     * IConnectorInfoParam.Module -> IConnectorModule
     *
     * @param moduleParam
     * @return
     */
    public static IConnectorModule moduleParamMapToPo(IConnectorInfoParam.Module moduleParam) {
        return IConnectorModule.builder()
                .name(moduleParam.getName())
                .version(moduleParam.getVersion())
                .status(moduleParam.getStatus())
                .build();
    }

    /**
     * update action
     *
     * ClientUpdateParam -> IConnectorInfo
     *
     * @param info
     * @param updateParam
     */
    public static void updateParamMapToPo(IConnectorInfo info, ClientUpdateParam updateParam) {
        List<IConnectorConfig> configs = updateParam.getConfigs().stream().map(ConfigurationMapper::configParamMapToPo).collect(Collectors.toList());
        info.getConfigs().clear();
        info.getConfigs().addAll(configs);
    }

    /**
     * ClientUpdateParam.Config -> IConnectorConfig
     *
     * @param configParam
     * @return
     */
    public static IConnectorConfig configParamMapToPo(ClientUpdateParam.Config configParam) {
        return IConnectorConfig.builder()
                .name(configParam.getName())
                .build();
    }

    public static <T, R> PageVo<R> convert2VoPage(@NonNull Page<T> page,
                                                  @NonNull Function<? super T, ? extends R> mapper) {
        return PageVo.<R> builder()
                .paginationVo(PaginationVo.builder().pageSize(page.getSize()).pageNumber(page.getNumber())
                        .pageTotal(page.getTotalPages()).build())
                .items(page.getContent().stream().map(mapper).collect(Collectors.toList())).build();
    }
}
