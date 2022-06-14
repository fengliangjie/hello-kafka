package com.siemens.multitenancy.entity.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("Page")
public class PageParam {

    /**
     * max page size.
     */
    public static final int PAGE_SIZE_MAX = 1000;

    /**
     * page number.
     */
    @ApiModelProperty(value = "Page Number", name = "page_number")
    @NotNull(message = "{page.msg.pageNumber.notNull}")
    private int pageNumber;

    /**
     * page size.
     */
    @ApiModelProperty(value = "Page Size", name = "page_size")
    @NotNull(message = "{page.msg.pageSize.notNull}")
    // @Range(min = 1, max = PAGE_SIZE_MAX, message = "{page.msg.pageSize.max}")
    private int pageSize;

}
