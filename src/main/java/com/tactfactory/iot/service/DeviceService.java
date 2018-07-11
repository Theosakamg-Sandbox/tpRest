package com.tactfactory.iot.service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tactfactory.iot.configuration.InfluxConfig;
import com.tactfactory.iot.entity.Device;
import com.tactfactory.iot.entity.ThermalMessage;
import com.tactfactory.iot.entity.ThermalValue;
import com.tactfactory.iot.repository.DeviceRepository;

@Service
@Transactional
public class DeviceService {

    @Autowired
    private DeviceRepository repo;

    @Autowired
    private InfluxDB influx;

    @Autowired
    private InfluxDBResultMapper resultMapper;

    public DeviceService save(Device device) {
        this.repo.saveAndFlush(device);

        return this;
    }

    /**
     * Register new device.
     * @param dev
     * @return
     */
    public boolean register(Device dev) {
        boolean result = false;
        // check if device already exist.
        if (this.repo.findOneByUuid(dev.getUuid()) == null) {
            dev.setInventoryAt(ZonedDateTime.now());

            // Insert into system
            this.repo.save(dev);

            result = true;
        }

        return result;
    }

    public List<ThermalValue> getData(String uuid) {
        final QueryResult queryResult = this.influx.query(
                new Query("SELECT * FROM thermal WHERE salle='" + uuid + "'", InfluxConfig.DB_NAME));

        return this.resultMapper.toPOJO(queryResult, ThermalValue.class);
    }

    public void insert(ThermalMessage value) {
        Point point = Point.measurement("thermal")
                .time(System.nanoTime(), TimeUnit.NANOSECONDS)
                .tag("salle", value.getUuid())
                .addField("value", value.getTemp())
                .addField("hydro", value.getHydro())
                .build();

        this.influx.write(point);
    }
}
