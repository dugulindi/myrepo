package com.miaoqian.bigdata.hbase.service;


import com.miaoqian.bigdata.hbase.domain.ActionLog;

import java.util.List;

/**
 * Created by lind
 * DATETIME 2016/11/10.14:54
 */
public interface HbaseService extends BaseService<ActionLog> {
    List<ActionLog> findByName(String name);

    public ActionLog save(String log);

    public boolean save(List<String> logs);
}
