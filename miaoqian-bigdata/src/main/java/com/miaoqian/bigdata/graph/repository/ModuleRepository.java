package com.miaoqian.bigdata.graph.repository;

import com.miaoqian.bigdata.graph.domain.Module;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by lind
 * DATETIME 2016/11/16.14:28
 */
public interface ModuleRepository extends GraphRepository<Module> {
    @Query("MATCH (m:Module {name:{name}}) RETURN m")
    Module getModuleByName(@Param("name") String name);

    Module findByName(String name);
}

