package cn.nmmpa.task;

import cn.nmmpa.pojo.DdnsProperties;
import cn.nmmpa.util.UpdateDomainRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: tan shuai
 * @Date: 2019/9/9 9:33
 * @Version 1.0
 */
@Component
@EnableScheduling
@Configuration
public class DdnsTaskConfig implements SchedulingConfigurer {

    @Autowired
    private DdnsProperties ddnsProperties;

    @Autowired
    private DdnsTaskService ddnsTaskService;


    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(() -> {
            try {
                ddnsTaskService.task();  //异步定时操作
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, triggerContext -> {
            //定时任务触发,可修改定时任务的执行周期
            CronTrigger trigger=new CronTrigger(ddnsProperties.getScannTime());
            Date nextExecDate= trigger.nextExecutionTime(triggerContext);
            return nextExecDate;
        });
    }
}
