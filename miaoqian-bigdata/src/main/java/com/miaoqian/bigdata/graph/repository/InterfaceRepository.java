package com.miaoqian.bigdata.graph.repository;

import com.miaoqian.bigdata.graph.domain.Interface;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by lind
 * DATETIME 2016/11/16.14:28
 */
public interface InterfaceRepository extends GraphRepository<Interface> {
    @Query("MATCH (i:Interface {name:{name}}) RETURN i")
    Interface InterfaceByName(@Param("name") String name);

    @Query("MATCH p=(m:Module)-[r:`接口`]->(i:Interface) where m.name={0} RETURN i")
    List<Interface> findByModuleName(String moduleName);

    Interface findByName(String name);
}

