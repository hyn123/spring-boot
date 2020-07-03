package com.nn.rediscluster.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * 用于读取配置文件信息
 */
@Component
@Validated
@Data
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfigProperties {

    private String host;

    private String port;

    private String password;

    private Cluster cluster;

    private int database;

    public static class Cluster {

        private List<String> nodes;

        public List<String> getNodes() {
            return nodes;
        }

        public void setNodes(List<String> nodes) {
            this.nodes = nodes;
        }
    }
}
