package com.deviniti.multitenancy.separate.schema.repository;

import com.deviniti.multitenancy.separate.schema.entity.po.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: liangjie.feng
 * @Date: 2022/05/31 3:01 PM
 */
@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {
}
