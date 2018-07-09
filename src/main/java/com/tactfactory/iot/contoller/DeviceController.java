package com.tactfactory.iot.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/api")
@RestController
public class DeviceController {

    @GetMapping(path ="/test")
    public String hello() {
        return "Hello W!";
    }
}
