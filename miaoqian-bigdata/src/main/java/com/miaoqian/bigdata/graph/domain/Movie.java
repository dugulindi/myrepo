package com.miaoqian.bigdata.graph.domain;

import lombok.Data;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * Created by lind
 * DATETIME 2016/11/16.14:26
 */
@Data
@NodeEntity(label = "MOVIES")
public class Movie {
    public Movie() {
    }

    public Movie(String name) {
        this.name = name;
    }

    @GraphId
    private Long nodeId;

    @Property(name = "name")
    private String name;

}

