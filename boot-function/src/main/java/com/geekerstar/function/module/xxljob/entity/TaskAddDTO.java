package com.geekerstar.function.module.xxljob.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author geekerstar
 * @date 2023/4/4 15:45
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "TaskAddDTO", description = "创建定时任务")
public class TaskAddDTO {

    @ApiModelProperty(value = "任务名称")
    private String taskName;

    @ApiModelProperty(value = "任务间隔")
    private String taskPeriod;

    @ApiModelProperty(value = "任务参数")
    private String taskParam;

    @ApiModelProperty(value = "任务执行器")
    private String taskHandler;
}
