package com.tactfactory.iot.entity;

import java.time.Instant;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = false)
@Measurement(name = "thermal")
public class ThermalValue {

    @Column(name = "time")
    private Instant time;

    @Column(name = "uuid", tag = true)
    private String uuid;

    @Column(name = "value")
    private Double temp;

    @Column(name = "hydro")
    private Double hydro;
}
