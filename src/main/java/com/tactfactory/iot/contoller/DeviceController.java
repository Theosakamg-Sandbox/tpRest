package com.tactfactory.iot.contoller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tactfactory.iot.dto.DtoDevice;
import com.tactfactory.iot.entity.Device;
import com.tactfactory.iot.service.DeviceService;

@RequestMapping(path = "/api/device")
@RestController
public class DeviceController {

    @Autowired
    private DeviceService service;

    @GetMapping(path ="/test")
    public String hello() {
        Device dev = new Device()
            .setUuid("totot")
            .setHwVer(2)
            .setSwVer(1);

        this.service.save(dev);

        return "Hello W!";
    }

    //@RequestMapping(path = "/", method = RequestMethod.POST)
    @PostMapping(path = "/")
    public ResponseEntity<Device> registration(@RequestBody DtoDevice dtoDev) {
        ResponseEntity<Device> result = null;
        ModelMapper modelMapper = new ModelMapper();

//        // Case 1
//        Device dev1 = new Device();
//        modelMapper.map(dtoDev, dev1);

        // Case 2
        Device dev = modelMapper.map(dtoDev, Device.class);
        if (this.service.register(dev)) {
            result = new ResponseEntity<Device>(dev, HttpStatus.CREATED);
        } else {
            result = new ResponseEntity<Device>(dev, HttpStatus.OK);
        }

        // Return valide registration
        return result;
    }

}
