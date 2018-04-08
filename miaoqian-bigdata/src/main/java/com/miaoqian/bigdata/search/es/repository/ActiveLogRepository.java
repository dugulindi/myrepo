package com.miaoqian.bigdata.search.es.repository;

import com.miaoqian.bigdata.search.es.entity.ActiveLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by lind
 * DATETIME 2016/11/4.14:04
 */
public interface ActiveLogRepository extends ElasticsearchRepository<ActiveLog, String> {

    Page<ActiveLog> findBylogLevelAndLogTimeBetween(String logLevel, Date beginTime, Date endTime, Pageable page);

    Page<ActiveLog> findByModuleNameAndLogTimeBetween(String moduleName, Date beginTime, Date endTime, Pageable page);

    Page<ActiveLog> findByContentContainingAndLogTimeBetween(String content, Date beginTime, Date endTime, Pageable page);

    Page<ActiveLog> findByContentInAndLogTimeBetween(Collection<String> content, Date beginTime, Date endTime, Pageable page);

    Page<ActiveLog> findByContentInAndLogLevelAndLogTimeBetween(Collection<String> content, String logLevel, Date beginTime, Date endTime, Pageable page);

    @Query("{\"query\": {\"filtered\": {\"query\": {\"match\": {\"content\": {\"query\": \"?0\",\"operator\": \"and\"}}},\"filter\": {\"range\": {\"logTime\": {\"gte\": \"?1\",\"lt\": \"?2\"}}}}}}")
    Page<ActiveLog> findByContentAndLogTimeBetween(String content, Date beginTime, Date endTime, Pageable page);

    Page<ActiveLog> findByLogTimeBetween(Date beginTime, Date endTime, Pageable page);

    Page<ActiveLog> findByModuleNameAndLogLevelAndLogTimeBetween(String moduleName, String logLevel, Date beginTime, Date endTime,  Pageable page);

    Page<ActiveLog> findByContentContainingAndLogLevelAndLogTimeBetween(String content, String logLevel, Date beginTime, Date endTime,  Pageable page);

    Page<ActiveLog> findByModuleNameAndContentContainingAndLogTimeBetween(String moduleName, String content, Date beginTime, Date endTime,  Pageable page);

    Page<ActiveLog> findByModuleNameAndContentContainingAndLogLevelAndLogTimeBetween(String moduleName, String logLevel, String content, Date beginTime, Date endTime,  Pageable page);

}
