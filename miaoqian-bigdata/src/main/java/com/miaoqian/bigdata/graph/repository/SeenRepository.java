package com.miaoqian.bigdata.graph.repository;

import com.miaoqian.bigdata.graph.domain.Seen;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by lind
 * DATETIME 2016/11/16.14:30
 */
public interface SeenRepository extends GraphRepository<Seen> {
}
