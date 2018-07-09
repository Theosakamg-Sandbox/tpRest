package com.tactfactory.iot.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tactfactory.iot.entity.Device;
import com.tactfactory.iot.repository.DeviceRepository;

@RequestMapping(path = "/api")
@RestController
public class DeviceController {

    @Autowired
    private DeviceRepository repo;

    @GetMapping(path ="/test")
    public String hello() {
        Device dev = new Device();
        dev.setUuid("totot")
            .setHwVer(2)
            .setSwVer(1);

        repo.saveAndFlush(dev);

        return "Hello W!";
    }
}






