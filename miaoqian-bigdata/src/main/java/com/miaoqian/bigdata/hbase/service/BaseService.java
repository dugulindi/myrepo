package com.miaoqian.bigdata.hbase.service;

import java.util.List;

public interface BaseService<T> {
    public T insert(T t);

    public void update(T t);

    public void delete(String id);

    public T findById(String id);

    public List<T> findList();
}
