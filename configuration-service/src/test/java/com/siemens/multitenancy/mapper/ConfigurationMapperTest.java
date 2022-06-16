package com.siemens.multitenancy.mapper;

import com.alibaba.fastjson.JSON;
import com.siemens.multitenancy.entity.param.ClientUpdateParam;
import com.siemens.multitenancy.entity.param.IConnectorInfoParam;
import com.siemens.multitenancy.entity.po.IConnectorConfig;
import com.siemens.multitenancy.entity.po.IConnectorInfo;
import com.siemens.multitenancy.entity.po.IConnectorModule;
import com.siemens.multitenancy.entity.vo.IConnectorInfoMq;
import com.siemens.multitenancy.entity.vo.IConnectorInfoVo;
import com.siemens.multitenancy.entity.vo.PageVo;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: liangjie.feng
 * @date: 2022/6/16 12:01 PM
 */
class ConfigurationMapperTest {

    @Test
    void infoPoMapToVo() {
        IConnectorInfo iConnectorInfo = IConnectorInfo.builder()
                .id(1L).connectorId("0001")
                .status(0)
                .collectStatus(0)
                .dateTime(1655348631L)
                .version("v0.0.1")
                .modules(Collections.singletonList(IConnectorModule.builder().id(1L).status(0).name("module").version("v0.0.1").build()))
                .configs(Collections.singletonList(IConnectorConfig.builder().id(1L).name("config").build()))
                .build();
        IConnectorInfoVo iConnectorInfoVo = ConfigurationMapper.infoPoMapToVo(iConnectorInfo);
        System.out.println(JSON.toJSONString(iConnectorInfoVo));
    }

    @Test
    void infoPoMapToMq() {
        IConnectorInfo iConnectorInfo = IConnectorInfo.builder()
                .id(1L).connectorId("0001")
                .status(0)
                .collectStatus(0)
                .dateTime(1655348631L)
                .version("v0.0.1")
                .modules(Collections.singletonList(IConnectorModule.builder().id(1L).status(0).name("module").version("v0.0.1").build()))
                .configs(Collections.singletonList(IConnectorConfig.builder().id(1L).name("config").build()))
                .build();
        IConnectorInfoMq infoPoMapToMq = ConfigurationMapper.infoPoMapToMq(iConnectorInfo);
        System.out.println(JSON.toJSONString(infoPoMapToMq));
    }

    @Test
    void infoParamMapToPo() {
        IConnectorInfoParam infoParam = new IConnectorInfoParam();
        infoParam.setConnectorId("0001");
        infoParam.setDateTime(1655348631L);
        IConnectorInfoParam.Data data = new IConnectorInfoParam.Data();
        data.setStatus(0);
        data.setCollectStatus(0);
        data.setVersion("v0.0.1");
        IConnectorInfoParam.Module module = new IConnectorInfoParam.Module();
        module.setName("module");
        module.setStatus(0);
        module.setVersion("v0.0.1");
        data.setModules(Collections.singletonList(module));
        infoParam.setData(data);
        IConnectorInfo connectorInfo = ConfigurationMapper.infoParamMapToPo(infoParam);
        System.out.println(JSON.toJSONString(connectorInfo));
    }

    @Test
    void testInfoParamMapToPo() {
    }

    @Test
    void moduleParamMapToPo() {
        IConnectorInfoParam.Module module = new IConnectorInfoParam.Module();
        module.setName("module");
        module.setStatus(0);
        module.setVersion("v0.0.1");
        IConnectorModule connectorModule = ConfigurationMapper.moduleParamMapToPo(module);
        System.out.println(JSON.toJSONString(connectorModule));
    }

    @Test
    void updateParamMapToPo() {
    }

    @Test
    void configParamMapToPo() {
        ClientUpdateParam.Config config = new ClientUpdateParam.Config();
        config.setName("config");
        IConnectorConfig connectorConfig = ConfigurationMapper.configParamMapToPo(config);
        System.out.println(JSON.toJSONString(connectorConfig));
    }

    @Test
    void convert2VoPage() {
        Page<IConnectorInfo> page = new Page<IConnectorInfo>() {
            @Override
            public int getTotalPages() {
                return 10;
            }

            @Override
            public long getTotalElements() {
                return 100;
            }

            @Override
            public <U> Page<U> map(Function<? super IConnectorInfo, ? extends U> converter) {
                return null;
            }

            @Override
            public int getNumber() {
                return 10;
            }

            @Override
            public int getSize() {
                return 1;
            }

            @Override
            public int getNumberOfElements() {
                return 0;
            }

            @Override
            public List<IConnectorInfo> getContent() {
                return new ArrayList<>() {
                    {
                        add(IConnectorInfo.builder()
                                .id(1L)
                                .dateTime(1655348631L)
                                .version("v0.0.1")
                                .connectorId("0001")
                                .status(0)
                                .collectStatus(0)
                                .modules(new ArrayList<>() {
                                    {
                                        add(IConnectorModule.builder().id(1L).name("module1").status(0).version("v0.0.1").build());
                                        add(IConnectorModule.builder().id(2L).name("module2").status(0).version("v0.0.2").build());
                                        add(IConnectorModule.builder().id(3L).name("module3").status(0).version("v0.0.1").build());
                                    }
                                })
                                .configs(new ArrayList<>() {
                                    {
                                        add(IConnectorConfig.builder().id(1L).name("config1").build());
                                        add(IConnectorConfig.builder().id(2L).name("config2").build());
                                    }
                                }).build());
                        add(IConnectorInfo.builder()
                                .id(2L)
                                .dateTime(1655348631L)
                                .version("v0.0.2")
                                .connectorId("0002")
                                .status(0)
                                .collectStatus(0)
                                .modules(new ArrayList<>() {
                                    {
                                        add(IConnectorModule.builder().id(1L).name("module4").status(0).version("v0.0.1").build());
                                        add(IConnectorModule.builder().id(2L).name("module2").status(0).version("v0.0.2").build());
                                        add(IConnectorModule.builder().id(3L).name("module3").status(0).version("v0.0.1").build());
                                    }
                                })
                                .configs(new ArrayList<>() {
                                    {
                                        add(IConnectorConfig.builder().id(1L).name("config1").build());
                                        add(IConnectorConfig.builder().id(2L).name("config2").build());
                                    }
                                }).build());
                        add(IConnectorInfo.builder()
                                .id(3L)
                                .dateTime(1655348631L)
                                .version("v0.0.4")
                                .connectorId("0004")
                                .status(0)
                                .collectStatus(0)
                                .modules(new ArrayList<>() {
                                    {
                                        add(IConnectorModule.builder().id(1L).name("module1").status(0).version("v0.0.1").build());
                                        add(IConnectorModule.builder().id(2L).name("module2").status(0).version("v0.0.2").build());
                                        add(IConnectorModule.builder().id(3L).name("module3").status(0).version("v0.0.1").build());
                                    }
                                })
                                .configs(new ArrayList<>() {
                                    {
                                        add(IConnectorConfig.builder().id(1L).name("config1").build());
                                        add(IConnectorConfig.builder().id(2L).name("config2").build());
                                    }
                                }).build());
                    }
                };
            }

            @Override
            public boolean hasContent() {
                return false;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public boolean isFirst() {
                return false;
            }

            @Override
            public boolean isLast() {
                return false;
            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Pageable nextPageable() {
                return null;
            }

            @Override
            public Pageable previousPageable() {
                return null;
            }

            @Override
            public Iterator<IConnectorInfo> iterator() {
                return null;
            }
        };
        PageVo<IConnectorInfoVo> pageVo = ConfigurationMapper.convert2VoPage(page, ConfigurationMapper::infoPoMapToVo);
        System.out.println(JSON.toJSONString(pageVo));
    }
}