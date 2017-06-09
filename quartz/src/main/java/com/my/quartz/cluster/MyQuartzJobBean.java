package com.my.quartz.cluster;

import org.apache.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution// 不允许并发执行
public class MyQuartzJobBean extends QuartzJobBean {

    private static final Logger logger = Logger.getLogger(MyQuartzJobBean.class);

    @Override
    protected void executeInternal(JobExecutionContext jobexecutioncontext) throws JobExecutionException {

        SimpleService simpleService = getApplicationContext(jobexecutioncontext).getBean("simpleService",
                SimpleService.class);
        simpleService.testMethod1();

    }

    private ApplicationContext getApplicationContext(final JobExecutionContext jobexecutioncontext) {
        try {
            return (ApplicationContext) jobexecutioncontext.getScheduler().getContext().get("applicationContextKey");
        } catch (SchedulerException e) {
            logger.error("jobexecutioncontext.getScheduler().getContext() error!", e);
            throw new RuntimeException(e);
        }
    }

}
