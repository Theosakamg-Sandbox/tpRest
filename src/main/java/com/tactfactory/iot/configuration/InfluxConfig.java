package com.tactfactory.iot.configuration;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfluxConfig {

    public static final String DB_NAME = "tp_rest";

    @Bean
    public InfluxDB makeInflux() {
        final InfluxDB influx = InfluxDBFactory.connect("http://localhost:8086");
        influx.setDatabase(DB_NAME);

        return influx;
    }

    @Bean
    public InfluxDBResultMapper makeMapper() {
        return new InfluxDBResultMapper();
    }
}
