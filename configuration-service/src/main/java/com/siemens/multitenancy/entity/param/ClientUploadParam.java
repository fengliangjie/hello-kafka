package com.siemens.multitenancy.entity.param;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: liangjie.feng
 * @date: 2022/6/23 10:23
 */
@Data
public class ClientUploadParam {

    private String connectorId;
    private MultipartFile file;
}
