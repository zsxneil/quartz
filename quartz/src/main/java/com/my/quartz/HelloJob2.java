package com.my.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class HelloJob2 implements Job {

	public void execute(JobExecutionContext context) throws JobExecutionException {
		//取数据
		JobDataMap dataMap = context.getMergedJobDataMap();
		String name = dataMap.getString("name");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("hello " + name + ", "+ sdf.format(new Date()));
	}

}
