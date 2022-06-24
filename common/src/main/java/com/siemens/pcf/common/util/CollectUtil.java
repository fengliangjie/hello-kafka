/*******************************************************************************
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2022 Siemens AG
 *
 * Licensed under the Siemens Inner Source License 1.2
 ******************************************************************************/

package com.siemens.pcf.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pdai
 */
public final class CollectUtil {

    /**
     * no instance.
     */
    private CollectUtil() {
    }

    /**
     * generate default list.
     *
     * @param size
     *            size
     * @param defaultVal
     *            default value
     * @param <T>
     *            type
     * @return list
     */
    public static <T> List<T> defaultList(int size, T defaultVal) {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(defaultVal);
        }
        return list;
    }

}
