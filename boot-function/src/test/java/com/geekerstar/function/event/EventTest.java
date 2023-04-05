package com.geekerstar.function.event;

import com.geekerstar.function.FunctionApplicationTests;
import com.geekerstar.function.module.event.publish.PublishService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author geekerstar
 * @date 2023/4/5 14:28
 */
public class EventTest extends FunctionApplicationTests {

    @Autowired
    private PublishService publishService;

    @Test
    public void test(){
        publishService.placeOrder();
    }
}
