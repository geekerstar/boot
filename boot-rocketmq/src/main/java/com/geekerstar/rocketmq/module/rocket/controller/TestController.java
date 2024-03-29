package com.geekerstar.rocketmq.module.rocket.controller;

import com.geekerstar.rocketmq.module.rocket.producer.*;
import com.geekerstar.rocketmq.module.rocket.producer.tx.TxProducer;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author geekerstar
 * @date 2023/4/23 20:16
 */
@Api(tags = "RocketMQ接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("rocket")
public class TestController {

    /**
     * 搭建测试流程生成者
     */
    private final TestProducer testProducer;

    /**
     * 基本信息案例
     */
    private final BaseProducer baseProducer;

    /**
     * 顺序消息发送样例
     */
    private final OrderProducer orderProducer;

    /**
     * 延时消息
     */
    private final ScheduledProducer scheduledProducer;

    /**
     * 指标标签
     */
    private final TagProducer tagProducer;

    /**
     * 过滤消息
     */
    private final SQLProducer SQLProducer;

    /**
     * 消息事务
     */
    private final TxProducer txProducer;

    /**
     * 消息额外属性测试
     */
    private final ExProducer exProducer;

    /**
     * 回馈消息样例
     */
    private final ReplyProducer replyProducer;

    /**
     * 批量消息发送
     */
    private final BatchProducer batchProducer;

    @ApiOperation(value = "测试消息生成及发送", notes = "测试消息生成及发送")
    @ApiOperationSupport(order = 5)
    @GetMapping
    public Object test() {
        testProducer.send();
        return "测试消息生成及发送";
    }

    @ApiOperation(value = "基本消息样例", notes = "基本消息样例")
    @ApiOperationSupport(order = 10)
    @GetMapping("/base")
    public Object base() {
        // 同步发送
        baseProducer.sync();
        // 异步发送
        baseProducer.async();
        // 单向发送
        baseProducer.oneWay();
        return "基本消息样例";
    }

    @ApiOperation(value = "发送顺序消息", notes = "发送顺序消息")
    @ApiOperationSupport(order = 15)
    @GetMapping("/order")
    public Object order() {
        orderProducer.order();
        return "发送顺序消息";
    }

    @ApiOperation(value = "发送延时消息", notes = "发送延时消息")
    @ApiOperationSupport(order = 20)
    @GetMapping("/scheduled")
    public Object scheduled() {
        scheduledProducer.scheduled();
        return "发送延时消息";
    }

    @ApiOperation(value = "标签过滤消息样例", notes = "标签过滤消息样例")
    @ApiOperationSupport(order = 25)
    @GetMapping("/tag")
    public Object tag() {
        // TAG过滤
        tagProducer.tag();
        return "指定标签消息";
    }

    @ApiOperation(value = "SQL92过滤消息样例", notes = "SQL92过滤消息样例")
    @ApiOperationSupport(order = 30)
    @GetMapping("/selector")
    public Object selector() {
        // SQL92过滤
        SQLProducer.selector();
        return "过滤消息样例";
    }

    @ApiOperation(value = "消息事务样例", notes = "消息事务样例")
    @ApiOperationSupport(order = 35)
    @GetMapping("/tx")
    public Object tx() {

        // 消息事务
        txProducer.tx();
        return "消息事务样例";
    }

    @ApiOperation(value = "消息额外属性", notes = "消息额外属性")
    @ApiOperationSupport(order = 40)
    @GetMapping("/ex")
    public Object ex() {
        // 消息事务
        exProducer.ex();
        return "消息额外属性";
    }

    @ApiOperation(value = "回馈消息样例", notes = "回馈消息样例")
    @ApiOperationSupport(order = 40)
    @GetMapping("/reply")
    public Object reply() {
        // 消息事务
        replyProducer.reply();
        return "回馈消息样例";
    }

    @ApiOperation(value = "批量消息样例", notes = "批量消息样例")
    @ApiOperationSupport(order = 45)
    @GetMapping("/batch")
    public Object batch() {
        // 批量消息样例
        batchProducer.batch();
        return "批量消息样例";
    }
}

