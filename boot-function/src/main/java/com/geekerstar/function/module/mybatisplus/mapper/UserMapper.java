package com.geekerstar.function.module.mybatisplus.mapper;

import com.geekerstar.function.config.mybatis_plus.ExpandBaseMapper;
import com.geekerstar.function.module.mybatisplus.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author geekerstar
 * @date 2023/6/11 15:26
 */
@Mapper
public interface UserMapper extends ExpandBaseMapper<User> {

}
