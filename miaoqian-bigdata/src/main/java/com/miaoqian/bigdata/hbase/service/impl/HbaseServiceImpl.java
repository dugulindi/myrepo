package com.miaoqian.bigdata.hbase.service.impl;

import com.miaoqian.bigdata.hbase.dao.HbaseRepository;
import com.miaoqian.bigdata.hbase.domain.ActionLog;
import com.miaoqian.bigdata.hbase.service.HbaseService;
import com.miaoqian.bigdata.kafka.KafkaService;
import com.miaoqian.bigdata.kafka.config.KafkaConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lind
 * DATETIME 2016/11/10.14:54
 */
@Service("hbaseService")
public class HbaseServiceImpl implements HbaseService {
    private final static Logger logger = LoggerFactory.getLogger(HbaseServiceImpl.class);

    @Autowired
    HbaseRepository hbaseRepository;

    @Autowired
    private KafkaService kafkaService;

    @Override
    public ActionLog insert(ActionLog actionLog) {
        return hbaseRepository.save(actionLog);
    }

    @Override
    public void update(ActionLog actionLog) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public ActionLog findById(String id) {
        return hbaseRepository.getByRowKey(id);
    }

    @Override
    public List<ActionLog> findList() {
        return hbaseRepository.findAll();
    }

    @Override
    public List<ActionLog> findByName(String name) {
        return hbaseRepository.findByName(name);
    }

    @Override
    public ActionLog save(String log) {
        ActionLog actionLog;
        try {
            actionLog = new ActionLog(log);
            hbaseRepository.save(actionLog);
        } catch (Exception e) {
            logger.info(e.getMessage());
            kafkaService.sendMessage(KafkaConfig.getTopic(), log);
        }
        return null;
    }

    @Override
    public boolean save(List<String> logs) {
        List<ActionLog> actionLogs = new ArrayList<ActionLog>();
        try {
            for (String log : logs) {
                actionLogs.add(new ActionLog(log));
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        try {
            hbaseRepository.batchSave(actionLogs);
        }catch (Exception e) {
            logger.info(e.getMessage());
            for (String log:logs){
                kafkaService.sendMessage(KafkaConfig.getTopic(),log);
            }
        }
        return true;
    }
}
