package com.tactfactory.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tactfactory.iot.entity.Device;

/**
 * JPA Repository.
 */
public interface DeviceRepository extends JpaRepository<Device, Long> {

    /**
    * Find Device with UUID.
    * @param uuid Id of Device.
    * @return Device instance.
    */
    Device findOneByUuid(String uuid);

}
