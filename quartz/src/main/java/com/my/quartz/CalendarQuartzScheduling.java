package com.my.quartz;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.AnnualCalendar;

public class CalendarQuartzScheduling {
	public static void main(String[] args) throws SchedulerException {
		JobDetail jobDetail = JobBuilder.newJob(HelloJob3.class).withIdentity("helloJob", "group1").build();
		
		AnnualCalendar holidays = new AnnualCalendar();
		
		//五一劳动节
		Calendar laborDay = new GregorianCalendar(2015, 4, 1);
		holidays.setDayExcluded(laborDay, true);//排除的日期，如果设置为false则为包含
		
		//万圣节（31号）  
        Calendar halloween = new GregorianCalendar(2005, 9, 31);  
        holidays.setDayExcluded(halloween, true);//排除该日期  
        
        //今天
        Calendar today = new GregorianCalendar(2017, 5, 6);
        holidays.setDayExcluded(today, true);
        
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
				.modifiedByCalendar("holidays")
				.build();
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();
		//scheduler.addCalendar("holidays", holidays, false, false);
		//添加job，以及其关联的trigger
		scheduler.scheduleJob(jobDetail, trigger);
		//启动job
		scheduler.start();
	}
}
