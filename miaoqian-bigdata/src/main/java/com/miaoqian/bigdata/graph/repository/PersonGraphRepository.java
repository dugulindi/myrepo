package com.miaoqian.bigdata.graph.repository;

import com.miaoqian.bigdata.graph.domain.PersonGraph;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

/**
 * Created by lind
 * DATETIME 2016/11/15.16:57
 */
public interface PersonGraphRepository extends GraphRepository<PersonGraph> {
    List<PersonGraph> findByName(String name);
}

