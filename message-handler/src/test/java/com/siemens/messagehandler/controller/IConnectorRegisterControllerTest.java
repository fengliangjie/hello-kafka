package com.siemens.messagehandler.controller;

import com.siemens.messagehandler.kafkaproducer.KafkaProducer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import static com.siemens.messagehandler.constant.ConstantValus.CONNECTOR_ID;
import static com.siemens.messagehandler.constant.ConstantValus.TENANT_ID;

/**
 * @author: liangjie.feng
 * @date: 2022/6/15 3:45 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class IConnectorRegisterControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private IConnectorRegisterController controller;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void register() throws Exception {
        String data = "{\"dateTime\":1654505999,\"data\":{\"configs\":[{\"name\":\"config-001\",\"id\":3},{\"name\":\"config-002\",\"id\":4},{\"name\":\"config-003\",\"id\":5}],\"status\":1},\"connectorId\":\"conId-001\"}";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(TENANT_ID, "siemens_001");
        params.add(CONNECTOR_ID, "conId-001");
        String uri = UriComponentsBuilder.fromUriString("/api/v1/iconnector/registration")
                .queryParams(params)
                .toUriString();
        mockMvc.perform(MockMvcRequestBuilders
                .post(uri, data));

    }
}