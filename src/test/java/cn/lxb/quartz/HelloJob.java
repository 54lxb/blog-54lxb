package cn.lxb.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 测试
 * Created by 54LXB on 2017-07-15.
 */
public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // 打印当前的执行时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前执行时间是："+sdf.format(new Date()));
        // 具体业务逻辑编写
        System.out.println("你好，我是大爱我小宝哥！");
    }
}
