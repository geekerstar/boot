package com.geekerstar.function.jasypt;

import com.geekerstar.function.FunctionApplicationTests;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author geekerstar
 * @date 2023/2/3 17:23
 */
public class JasyptTest extends FunctionApplicationTests {

    @Autowired
    private StringEncryptor encryptor;

    /**
     * 1、部署时传入盐值 java -jar xxx.jar  -Djasypt.encryptor.password=Y6M9fAJQdU7jNp5MW
     * 2、环境变量设置盐值
     * 打开/etc/profile文件
     * vim /etc/profile
     * 在profile文件末尾插入salt(盐)变量
     * export JASYPT_PASSWORD = Y6M9fAJQdU7jNp5MW
     * 编译，使配置文件生效
     * source /etc/profile
     * 运行
     * java -jar -Djasypt.encryptor.password=${JASYPT_PASSWORD} xxx.jar
     */
    @Test
    public void getPass() {
        String url = encryptor.encrypt("139.155.88.184");
        String name = encryptor.encrypt("16379");
        String password = encryptor.encrypt("geekerstar");
        System.out.println("database url: ENC(" + url + ")");
        System.out.println("database name: ENC(" + name + ")");
        System.out.println("database password: ENC(" + password + ")");
        Assert.assertTrue(url.length() > 0);
        Assert.assertTrue(name.length() > 0);
        Assert.assertTrue(password.length() > 0);
    }
}
