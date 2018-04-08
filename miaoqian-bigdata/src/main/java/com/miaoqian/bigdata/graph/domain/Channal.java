package com.miaoqian.bigdata.graph.domain;

import lombok.Data;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * Created by lind
 * DATETIME 2016/11/18.9:51
 */
@Data
@NodeEntity(label = "DEVICE")
public class Channal {
    @GraphId
    private String nodeId;

    @Property(name = "name")
    private String name;
}
