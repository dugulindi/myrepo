package com.miaoqian.bigdata.search.es.repository;

import com.miaoqian.bigdata.search.es.entity.ActionLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by lind
 * DATETIME 2016/11/4.14:04
 */
public interface ActionLogRepository extends ElasticsearchRepository<ActionLog, String> {
    Page<ActionLog> findBySourcePlatformIn(Collection<String> sourcePlatfor, Pageable page);
    List<ActionLog> findBySourcePlatformAndRequestDateBetween(String sourcePlatform, Date begin, Date end);
}
