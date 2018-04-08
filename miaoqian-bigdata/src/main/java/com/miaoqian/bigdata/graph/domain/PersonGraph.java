package com.miaoqian.bigdata.graph.domain;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * Created by lind
 * DATETIME 2016/11/10.9:50
 */
@NodeEntity(label = "PERSONS")
public class PersonGraph {

    @GraphId
    private Long id;
    @Property(name = "name")
    private String name;
    @Property(name = "mobile")
    private String mobile;

    @Relationship(type = "RECOMMEND", direction = Relationship.OUTGOING)
    private List<PersonGraph> friends;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
