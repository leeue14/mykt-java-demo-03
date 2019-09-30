package com.leeue.xxl.demo;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.springframework.stereotype.Component;

/**
 * @author liyue
 * @date 2019/9/20 11:10
 */
@JobHandler(value = "demoTestHandler")
@Component
public class DemoHandler extends IJobHandler {
    @Override
    public ReturnT<String> execute(String param) throws Exception {
        System.out.println("我是自己整合的xxl-job案例执行任务，3秒执行一次");
        return ReturnT.SUCCESS;
    }
}
