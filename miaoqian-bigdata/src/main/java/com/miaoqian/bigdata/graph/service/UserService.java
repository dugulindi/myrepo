package com.miaoqian.bigdata.graph.service;


import com.miaoqian.bigdata.graph.domain.User;

/**
 * Created by lind
 * DATETIME 2016/11/15.17:03
 */
public interface UserService extends BaseService<User> {
    public void initData();

    public User getUserByName(String name);

    public User update(String name);

    public User updateAreas(String name);
}
