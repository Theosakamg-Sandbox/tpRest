package com.tactfactory.iot.service;

import java.time.ZonedDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tactfactory.iot.entity.Device;
import com.tactfactory.iot.repository.DeviceRepository;

@Service
@Transactional
public class DeviceService {

    @Autowired
    private DeviceRepository repo;

    public DeviceService save(Device device) {
        this.repo.saveAndFlush(device);

        return this;
    }

    public boolean register(Device dev) {
        boolean result = false;
        // check if device already exist.
        if (this.repo.findByUuid(dev.getUuid()) == null) {
            dev.setInventoryAt(ZonedDateTime.now());

            // Insert into system
            this.repo.save(dev);

            result = true;
        }

        return result;
    }
}
