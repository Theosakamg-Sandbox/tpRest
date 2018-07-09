package com.tactfactory.iot.contoller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tactfactory.iot.dto.DtoDevice;
import com.tactfactory.iot.entity.Device;
import com.tactfactory.iot.entity.ThermalValue;
import com.tactfactory.iot.service.DeviceService;

import io.swagger.annotations.ApiOperation;

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

    @GetMapping(path = "/{uuid}/data")
    public ResponseEntity<List<ThermalValue>> getData(@PathVariable String uuid) {
        final List<ThermalValue> entities = this.service.getData(uuid);
        return new ResponseEntity<List<ThermalValue>>(entities, HttpStatus.OK);
    }

    //@RequestMapping(path = "/", method = RequestMethod.POST)
    @PostMapping(path = "/")
    @ApiOperation(value = "Registration fo new device in cloud system.")
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

    @DeleteMapping(path = "/{id:^\\d+$}")
    public ResponseEntity<Device> unregistration(@PathVariable long id, @RequestBody DtoDevice dtoDev) {
        return null;
    }
}
