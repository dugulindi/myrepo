package com.miaoqian.bigdata.graph.service.impl;

import com.miaoqian.bigdata.graph.domain.Interface;
import com.miaoqian.bigdata.graph.repository.InterfaceRepository;
import com.miaoqian.bigdata.graph.service.InterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 2017/7/17.
 */

@Service
public class InterfaceServiceImpl implements InterfaceService {

    @Autowired
    InterfaceRepository repository;

    @Override
    public Interface getModuleByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Interface> findByModuleName(String moduleName) {
        return repository.findByModuleName(moduleName);
    }

    @Override
    public Interface findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public void save(String name) {
        repository.save(new Interface(name));
    }
}
