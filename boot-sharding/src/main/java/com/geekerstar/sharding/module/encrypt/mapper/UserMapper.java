package com.geekerstar.sharding.module.encrypt.mapper;

import com.geekerstar.sharding.module.encrypt.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author geekerstar
 * @date 2023/2/8 10:59
 */
@Repository
public interface UserMapper {

    @Insert("insert into t_user(user_id, fullname,password,id_card,mobile) value(#{userId},#{fullName},#{cipherPwd},#{idCard},#{mobile})")
    int insertUser(User user);

    @Select("SELECT\n" +
            "\tuser_id,\n" +
            "\t`password` AS cipherPwd,\n" +
            "\tfullname,\n" +
            "\tuser_type,\n" +
            "\tid_card,\n" +
            "\tmobile\n" +
            "FROM\n" +
            "\tt_user where user_id=#{userId}")
    User selectByUserId(Long userId);

    @Select("SELECT\n" +
            "\t`password` AS cipherPwd,\n" +
            "\tfullname,\n" +
            "\tuser_type,\n" +
            "\tid_card\n" +
            "FROM\n" +
            "\tt_user where user_id=#{userId} and password=#{password} ")
    User selectByUserIdAndPassword(@Param("userId") Long userId, @Param("password") String password);

    @Select("SELECT\n" +
            "\t`password` AS cipherPwd,\n" +
            "\tfullname,\n" +
            "\tuser_type,\n" +
            "\tid_card\n" +
            "FROM\n" +
            "\tt_user where id_card=#{idCard}")
    List<User> selectByIdCard(String idCard);

    @Select("SELECT\n" +
            "\t`password` AS cipherPwd,\n" +
            "\tfullname,\n" +
            "\tuser_type,\n" +
            "\tmobile,\n" +
            "\tid_card\n" +
            "FROM\n" +
            "\tt_user where mobile=#{mobile}")
    List<User> selectByMobile(String mobile);

    @Select("SELECT\n" +
            "\t`password` AS cipherPwd,\n" +
            "\tfullname,\n" +
            "\tuser_type,\n" +
            "\tmobile,\n" +
            "\tid_card\n" +
            "FROM\n" +
            "\tt_user")
    List<User> listAll();
}
