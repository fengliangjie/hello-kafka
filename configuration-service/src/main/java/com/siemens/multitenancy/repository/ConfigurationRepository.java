package com.siemens.multitenancy.repository;

import com.siemens.multitenancy.entity.po.IConnectorInfo;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author: liangjie.feng
 * @Date: 2022/05/31 3:01 PM
 */
@Repository
public interface ConfigurationRepository extends JpaRepository<IConnectorInfo, Long> {

    /**
     * find by connectorId
     * @param connectorId
     * @return
     */
    default Optional<IConnectorInfo> findByConnectorId(String connectorId) {
        return this.findOne(Example.of(IConnectorInfo.builder().connectorId(connectorId).build()));
    }

    /**
     * delete by connectorId
     * @param connectorId
     * @return
     */
    long deleteByConnectorId(String connectorId);
}
