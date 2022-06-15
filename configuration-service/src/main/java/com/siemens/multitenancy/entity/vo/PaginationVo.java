package com.siemens.multitenancy.entity.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "Pagination")
public class PaginationVo {

    /**
     * page size.
     */
    @ApiModelProperty("Current page size.")
    @JsonProperty("pageSize")
    private int pageSize;

    /**
     * current page number.
     */
    @ApiModelProperty("Current page number.")
    @JsonProperty("pageNumber")
    private int pageNumber;

    /**
     * total page number.
     */
    @ApiModelProperty("Total page number.")
    @JsonProperty("pageTotal")
    private int pageTotal;


}
