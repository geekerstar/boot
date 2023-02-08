package com.geekerstar.sharding.test1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geekerstar.sharding.module.encrypt.entity.User;
import com.geekerstar.sharding.module.encrypt.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author geekerstar
 * @date 2023/2/8 11:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class EncryptTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsertUser() {
        for (int i = 110; i < 120; i++) {
            User user = new User();
            user.setFullName("Geekerstar");
            user.setCipherPwd("abc123");
            user.setIdCard("320829198708012232");
            user.setUserId((long)i);
            user.setMobile("13852331509");
            userMapper.insertUser(user);
        }
    }

    @Test
    public void testList() throws JsonProcessingException {
        List<User> users = userMapper.listAll();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(users);
        System.out.println(json);
    }

    @Test
    public void test2() {
        User user = userMapper.selectByUserId(1L);
        System.out.println(user);
    }

    @Test
    public void test3() {
        User user = userMapper.selectByUserIdAndPassword(1L,"abc123");
        System.out.println(user);
    }

    @Test
    public void test4() {
        List<User> user = userMapper.selectByIdCard("320829198708012232");
        System.out.println(user);
    }

    @Test
    public void test5(){
        List<User> users = userMapper.selectByMobile("13852331509");
        System.out.println(users);
    }
}
