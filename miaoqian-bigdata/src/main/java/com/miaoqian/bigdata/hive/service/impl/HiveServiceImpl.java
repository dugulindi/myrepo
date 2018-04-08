package com.miaoqian.bigdata.hive.service.impl;

import com.miaoqian.bigdata.hbase.domain.ActionLog;
import com.miaoqian.bigdata.hive.dao.ActionLogDao;
import com.miaoqian.bigdata.hive.service.HiveService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class HiveServiceImpl implements HiveService {

    @Override
    public List<String> test() {
        return null;
    }

    @Override
    public List<ActionLog> getTraceLink(String requestId) {
        return new ActionLogDao().findTraceLink(requestId);
    }

    public List<ActionLog> getChildLog(ActionLog actionLog, List<ActionLog> logList) {
        List<ActionLog> childLogs = new ArrayList<ActionLog>();
        for (ActionLog log : logList) {
            if (log.getCurrentStep() == (actionLog.getCurrentStep() - 1) && log.getPreviousStepId() == actionLog.getCurrentStepId()) {
                childLogs.add(log);
            }
        }
        return childLogs;
    }

}
