package com.geekerstar.function.module.xxljob.service.impl;

import cn.hutool.json.JSONUtil;
import com.geekerstar.function.exception.BusinessException;
import com.geekerstar.function.module.xxljob.entity.TaskAddDTO;
import com.geekerstar.function.module.xxljob.entity.XxlJobInfo;
import com.geekerstar.function.module.xxljob.service.XxlJobService;
import com.geekerstar.function.module.xxljob.support.XxlJobSupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author geekerstar
 * @date 2023/4/6 20:19
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class XxlJobServiceImpl implements XxlJobService {

    private final XxlJobSupport xxlJobSupport;

    @Override
    public void taskCreate(TaskAddDTO taskAddDTO) {
        XxlJobInfo jobInfo = new XxlJobInfo();
        jobInfo.setJobGroup(1);
        jobInfo.setJobDesc(taskAddDTO.getTaskName());
        jobInfo.setAddTime(new Date());
        jobInfo.setUpdateTime(new Date());
        jobInfo.setAuthor("魏星");
        jobInfo.setAlarmEmail("");
        jobInfo.setScheduleType("FIX_RATE");
        jobInfo.setScheduleConf(taskAddDTO.getTaskPeriod());
        jobInfo.setMisfireStrategy("DO_NOTHING");
        jobInfo.setExecutorRouteStrategy("FIRST");
        jobInfo.setExecutorHandler(taskAddDTO.getTaskHandler());
        jobInfo.setExecutorParam(taskAddDTO.getTaskParam());
        jobInfo.setExecutorBlockStrategy("SERIAL_EXECUTION");
        jobInfo.setExecutorTimeout(10);
        jobInfo.setExecutorFailRetryCount(1);
        jobInfo.setGlueType("BEAN");
//        jobInfo.setGlueSource();
//        jobInfo.setGlueRemark();
//        jobInfo.setGlueUpdatetime();
//        jobInfo.setChildJobId();
        jobInfo.setTriggerStatus(1);
//        jobInfo.setTriggerLastTime();
//        jobInfo.setTriggerNextTime();
        String result = xxlJobSupport.add(jobInfo);
        log.info("result:{}", result);
        int code = (int) JSONUtil.parseObj(result).get("code");
        if (200 != code) {
            throw new BusinessException("A3001", JSONUtil.parseObj(result).get("msg").toString());
        }
    }
}
