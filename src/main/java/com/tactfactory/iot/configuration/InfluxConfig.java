package com.tactfactory.iot.configuration;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.impl.InfluxDBResultMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * InfluxDb configuration.
 */
@Configuration
public class InfluxConfig {

    /** Default name of DataBase. */
    public static final String DB_NAME = "tp_rest";

    /** @return Instance of InfluxDB connected. */
    @Bean
    public InfluxDB makeInflux() {
        final InfluxDB influx = InfluxDBFactory.connect("http://localhost:8086");
        influx.setDatabase(DB_NAME);

        return influx;
    }

    /** @return Instance of Mapper to POJO for InfluxDB. */
    @Bean
    public InfluxDBResultMapper makeMapper() {
        return new InfluxDBResultMapper();
    }
}
