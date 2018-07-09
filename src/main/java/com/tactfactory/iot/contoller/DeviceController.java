package com.tactfactory.iot.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tactfactory.iot.entity.Device;
import com.tactfactory.iot.service.DeviceService;

@RequestMapping(path = "/api")
@RestController
public class DeviceController {

    @Autowired
    private DeviceService service;

    @GetMapping(path ="/test")
    public String hello() {
        Device dev = new Device("totot")
            .setHwVer(2)
            .setSwVer(1);

        this.service.save(dev);

        return "Hello W!";
    }
}






