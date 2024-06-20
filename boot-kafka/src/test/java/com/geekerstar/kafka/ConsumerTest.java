/*
 * Copyright (c) emfuture, 2024, Emfuture Technology Co.,Ltd. All rights reserved.
 */
package com.geekerstar.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.*;

/**
 * @author geekerstar
 * @date 2024/6/20 15:10
 */
public class ConsumerTest {

    @Test
    public void kafkaConsumerTest() {
        // 配置属性集合
        Map<String, Object> configMap = new HashMap<String, Object>();
        // 配置属性：Kafka集群地址
        configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        // 配置属性: Kafka传输的数据为KV对，所以需要对获取的数据分别进行反序列化
        configMap.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        configMap.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        // 配置属性: 读取数据的位置 ，取值为earliest（最早），latest（最晚）
        configMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        // 配置属性: 消费者组
        configMap.put("group.id", "geekerstar");
        // 配置属性: 自动提交偏移量
        configMap.put("enable.auto.commit", "true");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(configMap);
        // 消费者订阅指定主题的数据
        consumer.subscribe(Collections.singletonList("test"));
        while (true) {
            // 每隔100毫秒，抓取一次数据
            ConsumerRecords<String, String> records =
                    consumer.poll(Duration.ofMillis(100));
            // 打印抓取的数据
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("K = " + record.key() + ", V = " + record.value());
            }
        }
    }

    @Test
    public void kafkaConsumerOffsetTest() {
        // 配置属性集合
        Map<String, Object> configMap = new HashMap<String, Object>();
        // 配置属性：Kafka集群地址
        configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        // 配置属性: Kafka传输的数据为KV对，所以需要对获取的数据分别进行反序列化
        configMap.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        configMap.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        // 配置属性: 读取数据的位置 ，取值为earliest（最早），latest（最晚）
        configMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        configMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        // 配置属性: 消费者组
        configMap.put("group.id", "geekerstar");
        // 配置属性: 自动提交偏移量
        configMap.put("enable.auto.commit", "true");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(configMap);
        // 消费者订阅指定主题的数据
        consumer.subscribe(Collections.singletonList("test"));

        boolean flg = true;
        while (flg) {
            consumer.poll(Duration.ofMillis(100));
            final Set<TopicPartition> assignment = consumer.assignment();
            if (assignment != null && !assignment.isEmpty()) {
                for (TopicPartition topicPartition : assignment) {
                    if ("test".equals(topicPartition.topic())) {
                        consumer.seek(topicPartition, 2);
                        flg = false;
                    }
                }
            }
        }

        while (true) {
            // 每隔100毫秒，抓取一次数据
            ConsumerRecords<String, String> records =
                    consumer.poll(Duration.ofMillis(100));
            // 打印抓取的数据
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("K = " + record.key() + ", V = " + record.value());
            }
            // 同步提交
            consumer.commitSync();
            // 异步提交
            consumer.commitAsync();
        }
    }

    @Test
    public void kafkaConsumerGroup1Test() {
        // 创建配置对象
        Map<String, Object> consumerConfig = new HashMap<>();
        consumerConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        consumerConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        consumerConfig.put(ConsumerConfig.GROUP_ID_CONFIG, "geekerstar");
//        consumerConfig.put(ConsumerConfig.GROUP_INSTANCE_ID_CONFIG, "aaa");
//        consumerConfig.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, StickyAssignor.class.getName());
        // 创建消费者对象
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(consumerConfig);

        // 订阅主题
        consumer.subscribe(Arrays.asList("test"));

        // 从kafka的主题中获取数据
        while (true) {
            final ConsumerRecords<String, String> datas = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> data : datas) {
                System.out.println(data.partition());
            }
        }
        // LEO : Log End Offset = size + 1 = 0,1,2,3, [4]
        // LEO = 3
        // 关闭消费者对象
        //consumer.close();
    }

    @Test
    public void kafkaConsumerGroup2Test() {
        // 创建配置对象
        Map<String, Object> consumerConfig = new HashMap<>();
        consumerConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        consumerConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        consumerConfig.put(ConsumerConfig.GROUP_ID_CONFIG, "geekerstar");
        // 创建消费者对象
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(consumerConfig);

        // 订阅主题
        consumer.subscribe(Arrays.asList("test"));

        // 从kafka的主题中获取数据
        while (true) {
            final ConsumerRecords<String, String> datas = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> data : datas) {
                System.out.println(data.partition());
            }
        }

        // LEO : Log End Offset = size + 1 = 0,1,2,3, [4]
        // LEO = 3

        // TODO 关闭消费者对象
        //consumer.close();
    }

    @Test
    public void kafkaConsumerGroup3Test() {
        // 创建配置对象
        Map<String, Object> consumerConfig = new HashMap<>();
        consumerConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        consumerConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        consumerConfig.put(ConsumerConfig.GROUP_ID_CONFIG, "geekerstar");
        // 创建消费者对象
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(consumerConfig);

        // 订阅主题
        consumer.subscribe(Collections.singletonList("test"));

        // 从kafka的主题中获取数据
        while (true) {
            final ConsumerRecords<String, String> datas = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> data : datas) {
                System.out.println(data.partition());
            }
        }

        // LEO : Log End Offset = size + 1 = 0,1,2,3, [4]
        // LEO = 3

        // 关闭消费者对象
        //consumer.close();
    }

    @Test
    public void kafkaConsumerCommitTest() {
        // 配置属性集合
        Map<String, Object> configMap = new HashMap<String, Object>();
        // 配置属性：Kafka集群地址
        configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        // 配置属性: Kafka传输的数据为KV对，所以需要对获取的数据分别进行反序列化
        configMap.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        configMap.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        // 配置属性: 读取数据的位置 ，取值为earliest（最早），latest（最晚）
        configMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        configMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        // 配置属性: 消费者组
        configMap.put("group.id", "geekerstar");
        // 配置属性: 自动提交偏移量
        configMap.put("enable.auto.commit", "true");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(configMap);
        // 消费者订阅指定主题的数据
        consumer.subscribe(Collections.singletonList("test"));
        while (true) {
            // 每隔100毫秒，抓取一次数据
            ConsumerRecords<String, String> records =
                    consumer.poll(Duration.ofMillis(100));
            // 打印抓取的数据
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("K = " + record.key() + ", V = " + record.value());
            }
            // 同步提交
            consumer.commitSync();
            // 异步提交
            consumer.commitAsync();
        }
    }
}
