package com.geekerstar.function.module.distributed_lock.controller;

import com.geekerstar.function.config.log.Weblog;
import com.geekerstar.function.module.distributed_lock.service.StockService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author geekerstar
 * @date 2023/2/2 17:41
 */
@Slf4j
@RestController
@RequestMapping("/stock")
@Api(tags = "库存操作")
@RequiredArgsConstructor
@ApiSupport(author = "Geekerstar", order = 1)
public class StockController {

    private final StockService stockService;

    @Weblog(description = "库存扣减")
    @GetMapping("/deduct")
    @ApiOperation(value = "库存扣减")
    @ApiOperationSupport(author = "Geekerstar", order = 1)
    public String deduct() {
        stockService.deduct();
        return "success";
    }
}
