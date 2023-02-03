package com.geekerstar.function.module.distributed_lock.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (TbStock)表实体类
 *
 * @author makejava
 * @since 2023-02-02 17:40:28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "TbStock", description = "")
public class TbStock implements Serializable {

    private static final long serialVersionUID = -53938966317023170L;

    @ApiModelProperty(value = "${column.comment}")
    private Long id;

    @ApiModelProperty(value = "${column.comment}")
    private String productCode;

    @ApiModelProperty(value = "${column.comment}")
    private String warehouse;

    @ApiModelProperty(value = "${column.comment}")
    private Integer count;

    @ApiModelProperty(value = "${column.comment}")
    private Integer version;
}

