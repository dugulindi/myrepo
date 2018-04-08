package com.miaoqian.bigdata.graph.domain;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;
import java.util.Set;

/**
 * Created by lind
 * DATETIME 2016/11/23.20:04
 */
public class Module {
    @GraphId
    private Long nodeId;

    @Property(name = "name")
    private String name;

    @Relationship(type = "调用", direction = Relationship.OUTGOING)
    private Set<Module> modules;

    @Relationship(type = "接口", direction = Relationship.OUTGOING)
    private Set<Interface> interfaces;

    public Module() {
    }

    public Module(String name) {
        this.name = name;
    }

    public Module(String name, Set<Module> moduless) {
        this.name = name;
        this.modules = moduless;
    }
    public Module(String name, Set<Module> modules, Set<Interface> interfaces) {
        this.name = name;
        this.modules = modules;
        this.interfaces = interfaces;
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

    public Set<Module> getModules() {
        return modules;
    }

    public void setModules(Set<Module> modules) {
        this.modules = modules;
    }

    public Set<Interface> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(Set<Interface> interfaces) {
        this.interfaces = interfaces;
    }
}
