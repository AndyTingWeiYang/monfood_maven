package com.model.pairlist.quartz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;


@WebServlet(urlPatterns="/Schedule",loadOnStartup=1)
public class Schedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 public void init() throws ServletException {

		    try { 

		  //執行任務調度

		  Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

		  JobDetail job = JobBuilder.newJob(matchJob.class).withIdentity("午夜排程任務觸發","配對").build();


		  CronTrigger trigger = TriggerBuilder.newTrigger() .withIdentity("午夜排程任務觸發", "配對") .withSchedule(CronScheduleBuilder.cronSchedule("1 0 0 * * ?")).build();//每天的00:00:01秒執行         

		 

		scheduler.scheduleJob(job, trigger);

		  scheduler.start();

		} catch (SchedulerException e) {

		e.printStackTrace();

		}   

		    

		}

}
