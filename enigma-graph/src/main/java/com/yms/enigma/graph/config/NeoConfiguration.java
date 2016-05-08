package com.yms.enigma.graph.config;

import org.neo4j.ogm.config.*;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.server.Neo4jServer;
import org.springframework.data.neo4j.server.RemoteServer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.inject.Inject;

/**
 * Created by Razvan.Simion on 4/17/2016.
 */
/*@Configuration
@EnableNeo4jRepositories("com.yms.enigma.graph.repository")
@EnableTransactionManagement
@EnableConfigurationProperties({ NeoProperties.class})*/

@EnableTransactionManagement
//@EnableConfigurationProperties({ NeoProperties.class})
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.yms.enigma.graph"})
@Configuration
@EnableNeo4jRepositories(basePackages = "com.yms.enigma.graph.repository")
public class NeoConfiguration extends Neo4jConfiguration {

    public static final String URL = "http://localhost:7474";
    @Autowired
    Environment env;

    /*@Inject
    private NeoProperties neo4JProperties;*/


    /*@Override
    public SessionFactory getSessionFactory() {
        return new SessionFactory("com.yms.enigma.graph.domain");
    }

    @Bean
    public Neo4jServer neo4jServer() {
        HttpDriver driver = new HttpDriver();
        return new RemoteServer(
            neo4JProperties.getUrl(),neo4JProperties.getUsername(),neo4JProperties.getPassword());
    }

    @Override
    @Bean
    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Session getSession() throws Exception {
        return super.getSession();
    }*/

    /*@Override
    public Neo4jServer neo4jServer() {
        return new RemoteServer(URL,"neo4j","password");
        *//*return new RemoteServer(
            neo4JProperties.getUrl(),neo4JProperties.getUsername(),neo4JProperties.getPassword());*//*
    }*/

    // needed for session in view in web-applications

    @Bean
    public org.neo4j.ogm.config.Configuration getConfiguration() {
        org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration();
        config
            .driverConfiguration()
            .setDriverClassName("org.neo4j.ogm.drivers.http.driver.HttpDriver")
            .setURI("http://localhost:7474")
            .setCredentials("neo4j","password");
        return config;
    }


    @Bean
    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Session getSession() throws Exception {
        return super.getSession();
    }

    @Override
    public SessionFactory getSessionFactory() {
        return new SessionFactory(getConfiguration(),"com.yms.enigma.graph.domain");
    }
}
