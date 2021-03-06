package com.siemens.multitenancy.entity.param;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * page request param.
 *
 * @author: liangjie.feng
 * @Date: 2022/05/31 2:51 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageParam {

    /**
     * max page size.
     */
    public static final int PAGE_SIZE_MAX = 1000;

    /**
     * page number.
     */
    @NotNull(message = "{page.msg.pageNumber.notNull}")
    private int pageNumber;

    /**
     * page size.
     */
    @NotNull(message = "{page.msg.pageSize.notNull}")
    private int pageSize;

}
