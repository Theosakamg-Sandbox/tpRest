package com.tactfactory.iot.service;

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
}
