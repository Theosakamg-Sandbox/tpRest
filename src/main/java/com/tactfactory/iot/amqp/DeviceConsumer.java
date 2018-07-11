package com.tactfactory.iot.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tactfactory.iot.configuration.AmqpConfig;
import com.tactfactory.iot.entity.ThermalMessage;
import com.tactfactory.iot.service.DeviceService;

/**
 * Consumer (subscriber) of sensor message from device.
 */
@Component
public class DeviceConsumer {

    /** Service layer of device.*/
    @Autowired
    private DeviceService service;

    /**
     * Call when receive a new message from Publisher (with queue).
     * @param messageRaw Raw message of BUS.
     */
    @RabbitListener(queues = AmqpConfig.QUEUE_SENSOR)
    public void receiveMessage(byte[] messageRaw) {
        // Deserialize Message
        ThermalMessage msg = ThermalMessage.deserialize(messageRaw);
        // Save to log.
        this.service.insert(msg);
    }
}
