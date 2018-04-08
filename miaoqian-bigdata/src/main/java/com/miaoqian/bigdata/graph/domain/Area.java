package com.miaoqian.bigdata.graph.domain;

import lombok.Data;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * Created by lind
 * DATETIME 2016/11/18.9:52
 */
@Data
@NodeEntity(label = "AREA")
public class Area {
    @GraphId
    private Long nodeId;

    @Property(name = "city")
    private String city;

    @Property(name = "country")
    private String country;

    @Property(name = "province")
    private String province;

    @Property(name = "county")
    private String county;

    public Area() {
    }

    public Area(String city, String country, String province, String county) {
        this.city = city;
        this.country = country;
        this.province = province;
        this.county = county;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(String Long) {
        this.nodeId = nodeId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }
}
