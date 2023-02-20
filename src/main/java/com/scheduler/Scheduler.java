package com.scheduler;


import com.scheduler.task.ClearLog;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

//@Scheduled注解各参数详解
//https://www.jianshu.com/p/1defb0f22ed1
//      cron表达式语法
//      [秒] [分] [小时] [日] [月] [周] [年]
//      每隔5秒执行一次：*/5 * * * * ?
//		每隔1分钟执行一次：0 */1 * * * ?
//		每天23点执行一次：0 0 23 * * ?
//		每天凌晨1点执行一次：0 0 1 * * ?
//		每月1号凌晨1点执行一次：0 0 1 1 * ?
//		每月最后一天23点执行一次：0 0 23 L * ?
//		每周星期天凌晨1点实行一次：0 0 1 ? * L
//		在26分、29分、33分执行一次：0 26,29,33 * * * ?
//		每天的0点、13点、18点、21点都执行一次：0 0 0,13,18,21 * * ?


@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class Scheduler {

    //@Scheduled(cron = "0 */1 * * * ?")
    //直接指定时间间隔，例如：5秒 = 5000
    @Scheduled(fixedRate = 600000)
    private void runClearLog() throws Exception {
        ClearLog c = new ClearLog();
        c.clearLog();
    }


}
