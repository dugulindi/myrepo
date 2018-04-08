package com.miaoqian.bigdata.graph.repository;

import com.miaoqian.bigdata.graph.domain.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by lind
 * DATETIME 2016/11/16.14:28
 */
public interface UserRepository extends GraphRepository<User> {
    @Query("MATCH (user:USERS {name:{name}}) RETURN user")
    User getUserByName(@Param("name") String name);

    User findByName(String name);
}

