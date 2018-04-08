package com.miaoqian.bigdata.hive.domain;

import com.miaoqian.bigdata.hbase.domain.ActionLog;

import java.util.List;

/**
 * Created by lind
 * DATETIME 2016/11/23.14:52
 */
public class TraceLink {
    ActionLog actionLog;
    List<TraceLink> childLogs;

    public TraceLink() {
    }

    public TraceLink(ActionLog actionLog, List<TraceLink> childLogs) {
        this.actionLog = actionLog;
        this.childLogs = childLogs;
    }

    public ActionLog getActionLog() {
        return actionLog;
    }

    public void setActionLog(ActionLog actionLog) {
        this.actionLog = actionLog;
    }

    public List<TraceLink> getChildLogs() {
        return childLogs;
    }

    public void setChildLogs(List<TraceLink> childLogs) {
        this.childLogs = childLogs;
    }
}
