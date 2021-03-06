package com.siemens.multitenancy.entity.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * page info.
 *
 * @author klin
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaginationVo {

    /**
     * page size.
     */
    @JsonProperty("pageSize")
    private int pageSize;

    /**
     * current page number.
     */
    @JsonProperty("pageNumber")
    private int pageNumber;

    /**
     * total page number.
     */
    @JsonProperty("pageTotal")
    private int pageTotal;


}
