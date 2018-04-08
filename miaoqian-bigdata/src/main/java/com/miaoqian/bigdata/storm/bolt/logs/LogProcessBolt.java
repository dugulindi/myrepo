package com.miaoqian.bigdata.storm.bolt.logs;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import com.miaoqian.bigdata.common.utils.DateUtils;
import com.miaoqian.bigdata.hbase.service.HbaseService;
import com.miaoqian.bigdata.kafka.KafkaService;
import com.miaoqian.bigdata.kafka.config.KafkaConfig;
import com.miaoqian.bigdata.search.es.service.SysLogService;
import com.miaoqian.bigdata.web.utils.ApplicationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by lind
 * DATETIME 2016/3/29.15:25
 */
@Component
public class LogProcessBolt extends BaseBasicBolt {
    /**
     *
     */
    private static final Logger logger = LoggerFactory.getLogger(LogProcessBolt.class);

    @Autowired
    private HbaseService hbaseService;

    @Autowired
    private SysLogService sysLogService;

    @Autowired
    private KafkaService kafkaService;

    private static final long serialVersionUID = 1838050750860039005L;

    private List<String> logList = new ArrayList<String>();

    private Date begin;
    private Date end;
    //    ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(100);
    private static boolean initScheduled = true;
    private boolean isBatchSync = true;
    private int batchSize = 1000;
    private int interval = 10;

    @Override
    public void execute(Tuple tuple, BasicOutputCollector collector) {
        try {
            final String log = (String) tuple.getValue(0);
            final String[] logs = log.split("\\|");
            if (logs.length < 18) {
                return;
            }
            if (isBatchSync) {
                syncBatchData(log);
            } else {
                syncSingleData(log);
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        collector.emit(new Values("日志操作完成"));
    }

    public void syncSingleData(final String log) {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                syncData(log);
            }
        });
    }

    public void syncBatchData(final String log) {
        if (null != log)
            logList.add(log);
        if (initScheduled) {
            initScheduled = false;
            begin = new Date();
            Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(new Runnable() {
                @Override
                public void run() {
                    syncBatchData(null);
                }
            }, interval, interval, TimeUnit.SECONDS);
        }
        end = new Date();
        int secondBetweenDate = DateUtils.getSecondBetweenDate(begin, end);
        if (logList.size() >= batchSize || secondBetweenDate >= interval) {
            begin = end;
            final List<String> tempList = new ArrayList<String>();
            for (String temp : logList) tempList.add(temp);
            logList.clear();
            Executors.newSingleThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    syncData(tempList);
                }
            });
        }
    }

    public void syncData(final List<String> logs) {
        if (logs.size() < 1) return;
        try {
            Executors.newCachedThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    if (null == hbaseService) {
                        hbaseService = (HbaseService) ApplicationUtil.getBean("hbaseService");
                    }
                    hbaseService.save(logs);
                }
            });
            Executors.newCachedThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    if (null == sysLogService) {
                        sysLogService = (SysLogService) ApplicationUtil.getBean("sysLogService");
                    }
                    sysLogService.save(logs);
                }
            });
            //出错处理
            for (String tempLog : logs) {
                if (tempLog.split("\\|")[2].trim().equalsIgnoreCase("error")) {
                    //new KafkaProducer().sentMessage(log);
                    if (null == kafkaService) {
                        kafkaService = (KafkaService) ApplicationUtil.getBean("kafkaService");
                    }
                    kafkaService.sendMessage(KafkaConfig.getErrorTopic(), tempLog);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void syncData(String log) {
        try {
            if (null == hbaseService) {
                hbaseService = (HbaseService) ApplicationUtil.getBean("hbaseService");
            }
            hbaseService.save(log);
            if (null == sysLogService) {
                sysLogService = (SysLogService) ApplicationUtil.getBean("sysLogService");
            }
            sysLogService.save(log);

            //出错处理
            if (log.split("\\|")[2].trim().equalsIgnoreCase("error")) {
                //new KafkaProducer().sentMessage(log);
                if (null == kafkaService) {
                    kafkaService = (KafkaService) ApplicationUtil.getBean("kafkaService");
                }
                kafkaService.sendMessage(KafkaConfig.getErrorTopic(), log);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通知
     *
     * @param
     * @param
     */
//    public void notice(LogInfo logInfo, String log) throws Exception {
//        if (null == userService) {
//            userService = (UserService) ApplicationUtil.getBean("userService");
//        }
//        List<User> users = userService.getNoticeUser(logInfo.getModuleName());
//        List<String> smsReceivers = new ArrayList<String>();
//        List<String> mailReceivers = new ArrayList<String>();
//        for (User user : users) {
//            if (StringUtils.isNotEmpty(user.getMobile().trim())) {
//                smsReceivers.add(user.getMobile().trim());
//            }
//            if (StringUtils.isNotEmpty(user.getEmail().trim())) {
//                mailReceivers.add(user.getEmail().trim());
//            }
//        }
//        if (null == noticeService) {
//            noticeService = (NoticeService) ApplicationUtil.getBean("noticeService");
//        }
//        noticeService.smsNotice(smsReceivers, null);
//        noticeService.mailNotice(mailReceivers, log);
//
    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("message"));
    }

//    public IRedSelector getRedSelector() {
//        return redSelector;
//    }
//
//    public void setRedSelector(IRedSelector redSelector) {
//        this.redSelector = redSelector;
//    }
}
