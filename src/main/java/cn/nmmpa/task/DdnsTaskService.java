package cn.nmmpa.task;

import cn.nmmpa.util.UpdateDomainRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: tan shuai
 * @Date: 2019/9/9 9:57
 * @Version 1.0
 */
@Component
public class DdnsTaskService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DdnsTaskConfig.class);

    public void task(){
        LOGGER.info("IP扫描开始,时间[{}]..." , getTime());
        UpdateDomainRecord record = new UpdateDomainRecord();
        record.analysisDns();
        LOGGER.info("IP扫描完成...");
    }

    public static String getTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return format.format(date).toString();
    }
}
