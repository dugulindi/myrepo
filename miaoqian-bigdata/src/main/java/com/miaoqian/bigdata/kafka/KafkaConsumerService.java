package com.miaoqian.bigdata.kafka;

import com.miaoqian.bigdata.common.utils.DateUtils;
import com.miaoqian.bigdata.graph.service.ModuleService;
import com.miaoqian.bigdata.search.es.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lind
 * DATETIME 2016/12/1.16:14
 */
@Component("kafkaConsumerService")
public class KafkaConsumerService {

    @Autowired
    private SysLogService sysLogService;

    @Autowired
    private ModuleService moduleService;

    public void processMessage(Map<String, Map<Integer, String>> msgs) {
        for (Map.Entry<String, Map<Integer, String>> entry : msgs.entrySet()) {
            LinkedHashMap<Integer, String> messages = (LinkedHashMap<Integer, String>) entry.getValue();
            Collection<String> values = messages.values();
            for (Iterator<String> iterator = values.iterator(); iterator.hasNext(); ) {
                final String log = iterator.next();
                Executors.newSingleThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
//                        String date = DateUtils.getYyyymmddFormat().format(new Date());
//                        String reg = ","+ date;
//                        final String[] logs = log.split(reg);
//                        for (int i=0; i<logs.length;i++){
//                            if (i>0) {
//                                sysLogService.save(date+logs[i]);
//                                //moduleService.save(logs[i].split("\\|")[1].trim(), logs[i].split("\\|")[2].trim(), logs[i].split("\\|")[5].trim());
//                            }else{
//                                sysLogService.save(logs[i]);
//                                //moduleService.save(logs[i].split("\\|")[1].trim(), logs[i].split("\\|")[2].trim(), logs[i].split("\\|")[5].trim());
//                            }
//                        }
                        String regex = "(\\d{1,4}\\-\\d{1,4}\\-\\d{1,4}\\-\\d{1,4}-\\d{1,4}\\-\\d{1,4}\\-\\d{1,4})";
                        Pattern pattern = Pattern.compile(regex);
                        Matcher matcher = pattern.matcher(log);
                        int start = 0;
                        int end = 0;
                        int index = 1;
                        while (matcher.find()) {
                            if (index > 1) {
                                end = matcher.start();
                                sysLogService.save(log.substring(start,end));
                                start = end;
                            }
                            index ++;
                        }
                        if (start ==0){
                            sysLogService.save(log);
                        }
                    }
                });
            }
        }
    }
}
