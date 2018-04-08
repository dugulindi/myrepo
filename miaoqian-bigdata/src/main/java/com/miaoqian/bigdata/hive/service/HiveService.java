package com.miaoqian.bigdata.hive.service;

import com.miaoqian.bigdata.hbase.domain.ActionLog;

import java.util.List;

public interface HiveService {

    public List<String> test();

    public List<ActionLog> getTraceLink(String requestId);
}
