package com.geekerstar.function.module.xxljob.controller;

import com.geekerstar.function.config.web.Response;
import com.geekerstar.function.module.xxljob.entity.TaskAddDTO;
import com.geekerstar.function.module.xxljob.service.XxlJobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author geekerstar
 * @date 2023/4/4 15:43
 */
@Slf4j
@RestController
@RequestMapping("/xxljob")
@Api(tags = "定时任务")
@RequiredArgsConstructor
public class XxlJobController {

    private final XxlJobService xxlJobService;

    @PostMapping("/create")
    @ApiOperation(value = "创建定时任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskAddDTO", value = "创建定时任务入参", paramType = "body", dataType = "TaskAddDTO", required = true)
    })
    public Response<String> taskCreate(
            @Validated @RequestBody TaskAddDTO taskAddDTO
    ) {
        xxlJobService.taskCreate(taskAddDTO);
        return Response.success();
    }
}
