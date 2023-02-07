package com.geekerstar.sharding.module.split.controller;

import com.geekerstar.sharding.config.log.Weblog;
import com.geekerstar.sharding.config.web.Response;
import com.geekerstar.sharding.module.split.entity.TbStock;
import com.geekerstar.sharding.module.split.service.SplitService;
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
 * @date 2023/2/7 19:04
 */
@Slf4j
@RestController
@RequestMapping("/split")
@Api(tags = "读写分离")
@RequiredArgsConstructor
@ApiSupport(author = "Geekerstar", order = 1)
public class SplitController {

    private final SplitService splitService;

    @Weblog(description = "查询")
    @GetMapping("/select")
    @ApiOperation(value = "查询")
    @ApiOperationSupport(author = "Geekerstar", order = 1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", paramType = "query", readOnly = true)
    })
    public Response<TbStock> select(
            @RequestParam Long id
    ) {
        return Response.success(splitService.select(id));
    }

    @Weblog(description = "插入")
    @PostMapping("/insert")
    @ApiOperation(value = "插入")
    @ApiOperationSupport(author = "Geekerstar", order = 2)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tbStock", value = "插入", paramType = "body", dataType = "TbStock", required = true)
    })
    public Response<String> insert(
            @Validated @RequestBody TbStock tbStock
    ) {
        splitService.insert(tbStock);
        return Response.success();
    }
}
