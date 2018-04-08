package com.miaoqian.bigdata.search.es.repository;

import com.miaoqian.bigdata.search.es.entity.SysLog;
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
public interface SysLogRepository extends ElasticsearchRepository<SysLog, String> {
//    Page<SysLog> findBylogLevel(String logLevel, Pageable page);

    Page<SysLog> findByLogTimeBetween(Date start, Date end, Pageable page);

    @Query("{\"query\": {\"filtered\": {\"query\": {\"match\": {\"content\": {\"query\": \"?0\",\"operator\": \"and\"}}},\"filter\": {\"range\": {\"logTime\": {\"gte\": \"?1\",\"lt\": \"?2\"}}}}}}")
    Page<SysLog> findByContentAndLogTimeBetween(String content, Date beginTime, Date endTime, Pageable page);

    Page<SysLog> findByContentInAndLogTimeBetween(Collection<String> content, Date beginTime, Date endTime, Pageable page);

    List<SysLog> findByContentContaining(String content);

    List<SysLog> findByModuleNameAndLogLevelAndLogTimeBetween(String moduleName, String logLevel, Date beginTime, Date endTime);

//    Page<SysLog> findByLogLevelInAndModuleNameInAndLogTimeBetween(Collection<String> logLevel, Collection<String> moduleName, Date beginTime, Date endTime, Pageable page);
//
//    Page<SysLog> findByModuleNameIn(Collection<String> moduleName, Pageable page);
//
//    Page<SysLog> findByLogLevelInAndModuleNameInAndRequestIdAndParamsContainingAndResultContainingAndLogTimeBetween(Collection<String> logLevel, Collection<String> moduleName,
//                                                                                                                    String requestId, String params, String result, Date beginTime, Date endTime, Pageable page);
//
//    Page<SysLog> findByLogLevelInAndModuleNameInAndRequestIdAndParamsContainingAndLogTimeBetween(Collection<String> logLevel, Collection<String> moduleName,
//                                                                                                 String requestId, String params, Date beginTime, Date endTime, Pageable page);
//
//    Page<SysLog> findByLogLevelInAndModuleNameInAndRequestIdAndResultContainingAndLogTimeBetween(Collection<String> logLevel, Collection<String> moduleName,
//                                                                                                 String requestId, String result, Date beginTime, Date endTime, Pageable page);
//
//    Page<SysLog> findByLogLevelInAndModuleNameInAndParamsContainingAndResultContainingAndLogTimeBetween(Collection<String> logLevel, Collection<String> moduleName,
//                                                                                                        String params, String result, Date beginTime, Date endTime, Pageable page);
//
//    Page<SysLog> findByLogLevelInAndModuleNameInAndRequestIdAndLogTimeBetween(Collection<String> logLevel, Collection<String> moduleName,
//                                                                              String requestId, Date beginTime, Date endTime, Pageable page);
//
//    Page<SysLog> findByLogLevelInAndModuleNameInAndParamsContainingAndLogTimeBetween(Collection<String> logLevel, Collection<String> moduleName,
//                                                                                     String params, Date beginTime, Date endTime, Pageable page);
//
//    Page<SysLog> findByLogLevelInAndModuleNameInAndResultContainingAndLogTimeBetween(Collection<String> logLevel, Collection<String> moduleName,
//                                                                                     String result, Date beginTime, Date endTime, Pageable page);

}
