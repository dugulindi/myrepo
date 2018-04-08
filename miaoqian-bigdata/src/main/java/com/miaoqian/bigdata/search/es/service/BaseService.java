package com.miaoqian.bigdata.search.es.service;

import com.miaoqian.bigdata.search.es.entity.ActionLog;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface BaseService<T> {
    public int insert(String logString);

    public int insert(T t);

    public T findById(String id);

    Page<T> findAll(Integer pageIndex, Integer pageSize);

    List<T> findByModuleAndTime(String moduleName, Date begin, Date end);

    List<T> findByModuleAndTime(String moduleName, String begin, String end);

    Page<T> count(String content, String beginTime, String endTime,
                          Integer pageIndex, Integer pageSize);

    Page<T> logSearch(String content, String beginTime, String endTime,
                              Integer pageIndex, Integer pageSize);

    Set<String> logSearchModuleInterface(String content, String beginTime, String endTime,
                          Integer pageIndex, Integer pageSize);
}
