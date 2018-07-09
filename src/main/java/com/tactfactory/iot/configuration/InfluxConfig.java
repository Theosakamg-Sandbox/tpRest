package com.tactfactory.iot.configuration;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfluxConfig {

    @Bean
    public InfluxDB makeInflux() {
        return InfluxDBFactory.connect("http://localhost:8086");
    }

    @Bean
    public InfluxDBResultMapper makeMapper() {
        return new InfluxDBResultMapper();
    }
}
