package com.miaoqian.bigdata.graph.service;

import com.miaoqian.bigdata.graph.domain.Interface;

import java.util.List;

/**
 * Created by admin on 2017/7/17.
 */
public interface InterfaceService {
    public Interface getModuleByName(String name);

    List<Interface> findByModuleName(String moduleName);

    Interface findByName(String name);

    public void save(String name);
}
