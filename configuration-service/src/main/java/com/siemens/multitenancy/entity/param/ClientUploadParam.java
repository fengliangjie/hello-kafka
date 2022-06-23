package com.siemens.multitenancy.entity.param;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author: liangjie.feng
 * @date: 2022/6/23 10:23
 */
@Data
public class ClientUploadParam {

    @NotBlank(message = "connectorId can't be blank")
    private String connectorId;
    @NotNull(message = "file can't be null")
    private MultipartFile file;
}
