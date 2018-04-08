package com.miaoqian.bigdata.search.es.service;

import com.miaoqian.bigdata.search.es.entity.SysLog;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by lind
 * DATETIME 2016/11/4.14:55
 */
public interface SysLogService extends BaseService<SysLog> {
    String save(String log);

    String save(List<String> logs);

    Page<SysLog> findAll(Integer pageIndex, Integer pageSize);

    List<SysLog> findByContent(String content);

}
