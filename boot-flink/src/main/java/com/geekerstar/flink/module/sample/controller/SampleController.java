package com.geekerstar.flink.module.sample.controller;

import com.geekerstar.flink.config.log.Weblog;
import com.geekerstar.flink.config.web.Response;
import com.geekerstar.flink.module.sample.domain.entity.Sample;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author geekerstar
 * @date 2023/2/3 14:25
 */
@Slf4j
@RestController
@RequestMapping("/sample")
@Api(tags = "模板案例")
@RequiredArgsConstructor
@ApiSupport(author = "Geekerstar", order = 1)
public class SampleController {

    @Weblog(description = "GET方法")
    @GetMapping("/get")
    @ApiOperation(value = "GET方法")
    @ApiOperationSupport(author = "Geekerstar", order = 1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "码值", paramType = "query", readOnly = true)
    })
    public Response<String> get(
            @RequestParam String code
    ) {
        log.info("GET请求：{}", code);
        return Response.success(code);
    }

    @Weblog(description = "POST方法")
    @PostMapping("/post")
    @ApiOperation(value = "POST方法")
    @ApiOperationSupport(author = "Geekerstar", order = 2)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sample", value = "POST方法", paramType = "body", dataType = "Sample", required = true)
    })
    public Response<String> post(
            @Validated @RequestBody Sample sample
    ) {
        log.info("POST请求：{}", sample.getId());
        return Response.success(sample.getId());
    }
}
