package com.miaoqian.bigdata.graph.config;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * neo4j图配置表
 *
 * @author lind
 */
@Configuration
//启动类的@SpringBootApplication会自动扫描同级包以及子包，所以下面的@ComponentScan不加应该没关系
@EnableNeo4jRepositories("com.miaoqian.bigdata.graph.repository")
@EnableTransactionManagement
public class GraphConfiguration extends Neo4jConfiguration {

    @Bean
    public org.neo4j.ogm.config.Configuration getConfiguration()

    {
        org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration();
        config.driverConfiguration()
                .setDriverClassName("org.neo4j.ogm.drivers.http.driver.HttpDriver")
                .setURI("http://neo4j:neo4j123@172.16.10.92:7474");
        return config;
    }

    @Override
    public SessionFactory getSessionFactory() {
/**
 * 如果不指定节点映射的java bean路径，保存时会报如下警告，导致无法将节点插入Neo4j中
 * ... is not an instance of a persistable class
 */
        return new SessionFactory(getConfiguration(), "com.miaoqian.bigdata.graph.domain");
    }
}
