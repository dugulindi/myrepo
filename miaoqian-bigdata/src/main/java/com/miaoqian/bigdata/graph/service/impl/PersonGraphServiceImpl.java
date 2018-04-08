package com.miaoqian.bigdata.graph.service.impl;

import com.miaoqian.bigdata.graph.domain.PersonGraph;
import com.miaoqian.bigdata.graph.repository.PersonGraphRepository;
import com.miaoqian.bigdata.graph.service.PersonGraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lind
 * DATETIME 2016/11/15.17:04
 */

@Service
public class PersonGraphServiceImpl implements PersonGraphService {

    @Autowired
    PersonGraphRepository personGraphRepository;


    @Override
    public PersonGraph insert(PersonGraph personGraph) {
        return personGraphRepository.save(personGraph);
    }

    @Override
    public void update(PersonGraph personGraph) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public PersonGraph findById(String id) {
        return null;
    }

    @Override
    public List<PersonGraph> findList() {
        return null;
    }

    @Override
    public List<PersonGraph> findByName(String name) {
        return personGraphRepository.findByName(name);
    }
}
