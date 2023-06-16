package com.geekerstar.function.mybatisplus;

import com.geekerstar.function.module.mybatisplus.domain.entity.User;
import com.geekerstar.function.module.mybatisplus.mapper.UserMapper;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author geekerstar
 * @date 2023/6/11 15:27
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SqlInjectorTest  {

    @Autowired
    private UserMapper mapper;

    @Test
    public void alwaysUpdateSomeColumnById() {
        User user = new User();
        user.setUsername("小小");
        user.setPhone(null);
        user.setSex("女");
        user.setId(1);
        mapper.alwaysUpdateSomeColumnById(user);
    }

    @Test
    public void insertBatchSomeColumn() {
        User user = new User();
        user.setUsername("zhangsan");
        user.setPhone("13811111111");
        user.setSex("女");

        User user1 = new User();
        user1.setUsername("lisi");
        user1.setPhone("13822222222");
        user1.setSex("男");

        ArrayList<User> userDOS = Lists.newArrayList(user, user1);
        mapper.insertBatchSomeColumn(userDOS);
    }

    @Test
    public void findAll() {
        List<User> userDOS = mapper.findAll();
    }
}
