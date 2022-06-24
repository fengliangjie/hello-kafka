/*******************************************************************************
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2022 Siemens AG
 *
 * Licensed under the Siemens Inner Source License 1.2
 ******************************************************************************/

package com.siemens.pcf.common.exception;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

/**
 * This class is for ExceptionData wrapper.
 *
 * @author pdai
 */
@Data
@Builder
public class ExceptionData {

    /**
     * errors.
     */
    @Singular
    private final List<Object> errors;

}
