package com.my.quartz;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class DataQuartzScheduling {
	public static void main(String[] args) throws SchedulerException {
		JobDetail jobDetail = JobBuilder.newJob(HelloJob3.class).withIdentity("helloJob", "group1").build();
		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("myTrigger", "group1")
				//传入数据
				.usingJobData("name", "孙悟空")
				.startNow()
				.withSchedule(
						SimpleScheduleBuilder.simpleSchedule()
						//每5秒钟重复一次
						.withIntervalInSeconds(5)
						//重复3次
						.withRepeatCount(3))
				.build();
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();
		//添加job，以及其关联的trigger
		scheduler.scheduleJob(jobDetail, trigger);
		//启动job
		scheduler.start();
	}
}
