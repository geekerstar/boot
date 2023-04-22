package com.geekerstar.function.module.sensitive;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author geekerstar
 * @date 2023/4/22 16:16
 * https://mp.weixin.qq.com/s/hMoyMeweMQfKLeKo-yCPFA
 */
@RestController
public class SensitiveController {

    @GetMapping("/test")
    public Person test() {
        Person user = new Person();
        user.setRealName("Geek");
        user.setPhoneNumber("19723328206");
        user.setAddress("浙江省杭州市....");
        user.setIdCard("4333333333334334333");
        return user;
    }
}
