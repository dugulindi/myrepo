package com.miaoqian.bigdata.graph.service.impl;

import com.google.common.collect.Lists;
import com.miaoqian.bigdata.graph.domain.Area;
import com.miaoqian.bigdata.graph.domain.User;
import com.miaoqian.bigdata.graph.repository.AreaRepository;
import com.miaoqian.bigdata.graph.repository.MovieRepository;
import com.miaoqian.bigdata.graph.repository.SeenRepository;
import com.miaoqian.bigdata.graph.repository.UserRepository;
import com.miaoqian.bigdata.graph.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lind
 * DATETIME 2016/11/16.14:47
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private SeenRepository seenRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Override
    public void initData() {
        /**
         * 初始化用户
         */
        User user1 = new User("李连杰");
        User user2 = new User("周星驰");
        User user3 = new User("林青霞");
/**
 * 为用户John添加朋友关系
 */
        user1.setFriends(Lists.newArrayList(user2, user3));

/**
 * 初始化电影
 */
//        Movie movie1 = new Movie("黄飞鸿");
//        Movie movie2 = new Movie("月光宝盒");
//        Movie movie3 = new Movie("东方不败");

/**
 * 初始化HAS_SEEN关系
 */
//        Seen hasSeen1 = new Seen(5, user1, movie1);
//        Seen hasSeen2 = new Seen(3, user2, movie3);
//        Seen hasSeen3 = new Seen(6, user2, movie2);
//        Seen hasSeen4 = new Seen(4, user3, movie1);
//        Seen hasSeen5 = new Seen(5, user3, movie2);

/**
 * 如果不加@Transactional，下面每个save都会单独开启事物
 */
        userRepository.save(Lists.newArrayList(user1, user2, user3));
//        movieRepository.save(Lists.newArrayList(movie1, movie2, movie3));
//        seenRepository.save(Lists.newArrayList(hasSeen1, hasSeen2, hasSeen3, hasSeen4, hasSeen5));
    }

    @Override
    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User update(String name) {
        User user = userRepository.findByName(name);
        List<User> userFriends = user.getFriends();
        if (null == userFriends) {
            userFriends = new ArrayList<User>();
        }
        User friend = userRepository.findByName("刘德华");
        if (null == friend) {
            friend = new User("刘德华");
        }
        userFriends.add(friend);
        user.setFriends(userFriends);
        userRepository.save(user);
        return user;
    }

    @Override
    public User updateAreas(String name) {
        User user = userRepository.findByName(name);
        List<Area> areas = user.getAreas();
        if (null == areas) {
            areas = new ArrayList<Area>();
        }
        Area area = areaRepository.findByCity("北京");
        if (null == area) {
            area = new Area("北京", "中国", "北京", "北京");
        }
        areas.add(area);
        user.setAreas(areas);
        userRepository.save(user);
        return user;
    }

    @Override
    public User insert(User user) {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public User findById(String id) {
        return null;
    }

    @Override
    public List<User> findList() {
        return null;
    }
}
