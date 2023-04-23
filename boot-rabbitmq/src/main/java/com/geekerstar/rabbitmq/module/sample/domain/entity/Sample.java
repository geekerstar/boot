package com.geekerstar.rabbitmq.module.sample.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author geekerstar
 * @date 2023/2/3 16:32
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Sample", description = "")
public class Sample implements Serializable  {

    private static final long serialVersionUID = -53938966317023170L;

    @ApiModelProperty(value = "${column.comment}")
    private String id;

}
