package com.miaoqian.bigdata.search.es.service;

import com.miaoqian.bigdata.search.es.entity.ActiveLog;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by lind
 * DATETIME 2016/11/4.14:55
 */
public interface ActiveLogService extends BaseService<ActiveLog> {
    String save(String log);

    String save(List<String> logs);

    Page<ActiveLog> findAll(Integer pageIndex, Integer pageSize);
}
