package com.geekerstar.tdengine;

import com.geekerstar.tdengine.module.tdengine.service.TdengineService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author geekerstar
 * @date 2023/5/28 09:35
 */
public class SelectTest extends TdengineApplicationTests{

    @Autowired
    private TdengineService tdengineService;

    @Test
    public void test1(){
        System.out.println(tdengineService.selectPower());
    }
}
