package com.trendyol.playlist.config;

import com.couchbase.client.java.Cluster;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CouchbaseIndexConfiguration {

    private final Cluster couchbaseCluster;

    public CouchbaseIndexConfiguration(Cluster couchbaseCluster) {
        this.couchbaseCluster = couchbaseCluster;
    }

    @Bean
    public void createIndexes() {
        couchbaseCluster.query("CREATE PRIMARY INDEX idx1 ON `playlist`");
        couchbaseCluster.query("CREATE INDEX userId ON `playlist`(userId)");
        couchbaseCluster.query("CREATE INDEX bootcampArray ON `playlist`(DISTINCT ARRAY `t`.`name` FOR t in `track` END)");
    }
}
