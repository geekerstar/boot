package com.geekerstar.function.module.transaction.controller;

import com.geekerstar.function.config.log.Weblog;
import com.geekerstar.function.config.web.Response;
import com.geekerstar.function.module.transaction.service.invalid.InvalidOtherClassNoTransaction;
import com.geekerstar.function.module.transaction.service.invalid.InvalidSameClass;
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
 * @date 2023/2/5 10:41
 */
@Slf4j
@RestController
@RequestMapping("/transaction")
@Api(tags = "事务")
@RequiredArgsConstructor
@ApiSupport(author = "Geekerstar", order = 1)
public class TransactionController {

    private final InvalidSameClass invalidSameClass;
    private final InvalidOtherClassNoTransaction invalidOtherClassNoTransaction;

    @Weblog(description = "事务失效")
    @GetMapping("/invalid")
    @ApiOperation(value = "事务失效")
    @ApiOperationSupport(author = "Geekerstar", order = 1)
    public Response<String> invalid() {
        invalidSameClass.methodNoTransaction();
//        invalidOtherClassNoTransaction.methodNoTransaction();
//        invalidSameClass.self();
        return Response.success();
    }

}
