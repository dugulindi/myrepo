package com.miaoqian.bigdata.graph.domain;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Property;

/**
 * Created by admin on 2017/7/17.
 */
public class Interface {
    @GraphId
    private Long nodeId;

    @Property(name = "name")
    private String name;

    public Interface() {
    }

    public Interface(String name) {
        this.name = name;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
