package com.siemens.multitenancy.entity.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

import java.util.List;

/**
 * page info.
 *
 * @author pdai
 * @param <T>
 *            page content item.
 */
@Getter
@Setter
@Builder
public class PageVo<T> {

    /**
     * pagination.
     */
    @JsonProperty("pagination")
    private PaginationVo paginationVo;

    /**
     * page content.
     */
    @Singular
    private List<T> items;

}
