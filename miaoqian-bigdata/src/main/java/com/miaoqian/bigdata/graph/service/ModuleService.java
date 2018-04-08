package com.miaoqian.bigdata.graph.service;


import com.miaoqian.bigdata.graph.domain.Module;

import java.util.Set;

/**
 * Created by lind
 * DATETIME 2016/11/15.17:03
 */
public interface ModuleService extends BaseService<Module> {

    public Module getModuleByName(String name);

    public Set<String> findDistinctModule();

    public void save(String module1);

    public void save(String module1, String module2);

    public void save(String module1, String module2, String interfaces);

    public void initData();

}
