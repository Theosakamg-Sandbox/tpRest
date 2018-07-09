package com.tactfactory.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tactfactory.iot.entity.Device;

public interface DeviceRepository extends JpaRepository<Device, Long> {

}
