package com.geekerstar.tdengine.runner;

import com.geekerstar.common.util.NetUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;


/**
 * @author geekerstar
 * @date 2023/2/2 16:34
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StartedUpRunner implements ApplicationRunner {
    @Value("${server.port:8888}")
    private String port;
    private final ConfigurableApplicationContext context;

    @Override
    public void run(ApplicationArguments args) {
        try {
            // TODO 做系统必要检查，通过则启动成功
        } catch (Exception e) {
            log.info("++++++++++++++++++++++++++++++++++++++++++++++++++");
            log.error("启动异常：提示信息");
            log.info("++++++++++++++++++++++++++++++++++++++++++++++++++");
            context.close();
        }
        if (context.isActive()) {
            log.info("++++++++++++++++++++++++++++++++++++++++++++++++++");
            String url = String.format("http://%s:%s%s", NetUtil.getInet4Address(), port, "/doc.html");
            log.info("【boot】启动成功!");
            log.info("【Swagger】{}", url);
            log.info("++++++++++++++++++++++++++++++++++++++++++++++++++");
        }
    }
}
