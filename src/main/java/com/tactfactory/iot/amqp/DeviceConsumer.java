package com.tactfactory.iot.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tactfactory.iot.configuration.AmqpConfig;
import com.tactfactory.iot.entity.ThermalMessage;
import com.tactfactory.iot.service.DeviceService;

@Component
public class DeviceConsumer {

    @Autowired
    private DeviceService service;

    @RabbitListener(queues = AmqpConfig.QUEUE_SENSOR)
    public void receiveMessage(byte[] messageRaw) {
        ThermalMessage msg = ThermalMessage.deserialize(messageRaw);
        this.service.insert(msg);
    }
}
