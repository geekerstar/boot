/*
 * Copyright (c) emfuture, 2024, Emfuture Technology Co.,Ltd. All rights reserved.
 */
package com.geekerstar.kafka.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author geekerstar
 * @date 2024/6/20 15:29
 */
@Slf4j
@RestController
@RequestMapping("/kafka")
@Api(tags = "Kafka")
@RequiredArgsConstructor
@ApiSupport(author = "Geekerstar", order = 1)
public class KafkaController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/sendMsg")
    @ApiOperation(value = "发送消息")
    @ApiOperationSupport(author = "Geekerstar", order = 1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "message", value = "消息内容", paramType = "query",required = true)
    })
    public String sendMsg(
            @RequestParam String message
    ) {
        kafkaTemplate.send("test", message);
        return "ok";
    }
}
