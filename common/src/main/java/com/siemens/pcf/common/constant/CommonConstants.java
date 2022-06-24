/*******************************************************************************
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2022 Siemens AG
 *
 * Licensed under the Siemens Inner Source License 1.2
 ******************************************************************************/

package com.siemens.pcf.common.constant;

/**
 * This class holds common constants.
 *
 * @author Jia He Peng
 */
public final class CommonConstants {

    /**
     * The exception message for calling constructor of a utility class.
     */
    public static final String CONSTRUCTOR_EXCEPTION = "Class cannot be instantiated";

    /**
     * The request header key of tenant ID.
     */
    public static final String TENANT_ID_HEADER = "X-TENANT-ID";

    // ------------------------------------------------------------ Constructors

    private CommonConstants() {
        throw new UnsupportedOperationException(CommonConstants.CONSTRUCTOR_EXCEPTION);
    }

}
