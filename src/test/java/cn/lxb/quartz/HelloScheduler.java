package cn.lxb.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 54LXB on 2017-07-15.
 */
public class HelloScheduler {

    public static void main(String[] args) throws SchedulerException {
        // 创建一个简单的Job detail实例。将该实例与HelloJob Class 绑定
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                .withIdentity("myJob", "myGroup").build();
        // 创建一个Trigger实例,定义该job立即执行，并且每隔两秒钟重复执行一次，直到永远
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myJob", "myGroup")
                .startNow().withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(10)
                                .repeatForever())
                .build();
        // 创建scheduler实例
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        // 打印当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前执行时间是："+sdf.format(new Date()));
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
