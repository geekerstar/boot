package com.geekerstar.sharding.module.encrypt.entity;

import lombok.Data;

/**
 * @author geekerstar
 * @date 2023/2/8 10:59
 */
@Data
public class User {
    private Long userId;

    private String fullName;

    private String userType;

    private String cipherPwd;

    private String mobile;

    private String idCard;
}
