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

/**
 * Device MVC controller.
 */
@RequestMapping(path = "/api/device")
@RestController
public class DeviceController {

    /** Service to use. */
    @Autowired
    private DeviceService service;

    /** Model Mapper to use. */
    @Autowired
    private ModelMapper modelMapper;

    /**
     * End-point for Test !
     * @return Simple message String.
     */
    @GetMapping(path ="/test")
    @ApiOperation(value = "Not use ! Just for test...")
    public String hello() {
        Device dev = new Device()
            .setUuid("A8")
            .setHwVer(2)
            .setSwVer(1);

        this.service.save(dev);

        return "Hello W!";
    }

    /**
     * Get Data from Log.
     * @param uuid Id of device to query.
     * @return List of Measurement to Thermal.
     */
    @GetMapping(path = "/{uuid}/data")
    @ApiOperation(value = "Get history data of specific device.")
    public ResponseEntity<List<ThermalValue>> getData(@PathVariable String uuid) {
        final List<ThermalValue> entities = this.service.getData(uuid);
        return new ResponseEntity<List<ThermalValue>>(entities, HttpStatus.OK);
    }

    /**
     * Registration of new Device.
     * @param dtoDev Device to register.
     * @return POJO of Device with full parameters.
     */
    //@RequestMapping(path = "/", method = RequestMethod.POST)
    @PostMapping(path = "/")
    @ApiOperation(value = "Registration fo new device in cloud system.")
    public ResponseEntity<Device> registration(@RequestBody DtoDevice dtoDev) {
        ResponseEntity<Device> result = null;

//        // Case 1
//        Device dev1 = new Device();
//        this.modelMapper.map(dtoDev, dev1);

        // Case 2
        Device dev = this.modelMapper.map(dtoDev, Device.class);
        if (this.service.register(dev)) {
            result = new ResponseEntity<Device>(dev, HttpStatus.CREATED);
        } else {
            result = new ResponseEntity<Device>(dev, HttpStatus.OK);
        }

        // Return valide registration
        return result;
    }

    /**
     * Un-register the device.
     * @param uuid Id of Device
     * @param dtoDev Update parameter of device (optional)
     * @return POJO of Device with full parameters.
     */
    @DeleteMapping(path = "/{uuid}")
    public ResponseEntity<Device> unregistration(@PathVariable String uuid, @RequestBody DtoDevice dtoDev) {
        return null;
    }
}
