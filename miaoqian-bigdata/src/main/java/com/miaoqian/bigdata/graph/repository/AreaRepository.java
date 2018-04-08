package com.miaoqian.bigdata.graph.repository;

import com.miaoqian.bigdata.graph.domain.Area;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by lind
 * DATETIME 2016/11/16.14:28
 */
public interface AreaRepository extends GraphRepository<Area> {
    Area findByCity(String city);
}

