package com.siemens.messagehandler.controller;

import com.siemens.messagehandler.constant.ConstantValus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: liangjie.feng
 * @date: 2022/6/16 1:46 PM
 */
class IConnectorRegisterControllerTest {

    @Test
    void register() {
        System.out.println(String.format(ConstantValus.CONFIG_TOPIC, "siemens_001", "conId_001"));
    }
}