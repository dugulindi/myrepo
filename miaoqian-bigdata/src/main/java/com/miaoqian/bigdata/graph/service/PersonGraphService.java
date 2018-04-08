package com.miaoqian.bigdata.graph.service;


import com.miaoqian.bigdata.graph.domain.PersonGraph;

import java.util.List;

/**
 * Created by lind
 * DATETIME 2016/11/15.17:03
 */
public interface PersonGraphService extends BaseService<PersonGraph> {
    List<PersonGraph> findByName(String name);
}
