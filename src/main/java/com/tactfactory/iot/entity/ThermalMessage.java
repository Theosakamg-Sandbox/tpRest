package com.tactfactory.iot.entity;

import java.io.UnsupportedEncodingException;
import java.time.Instant;

import lombok.Data;

@Data
public class ThermalMessage {

    private Instant time;

    private String uuid;

    private Double temp;

    private Double hydro;

    public byte[] serialize() {

        final String internal = String.format("%s;%s;%s;%s",
                this.time.toString(),
                this.uuid,
                this.temp,
                this.hydro);

        return internal.getBytes();
    }

    public static ThermalMessage deserialize(byte[] raw) {
        ThermalMessage result = null;

        try {
            final String internal = new String(raw, "UTF-8");
            final String[] internalValue = internal.split(";");

            result = new ThermalMessage();
            result.setTime(Instant.parse(internalValue[0]));
            result.setUuid(internalValue[1]);
            result.setTemp(Double.valueOf(internalValue[2]));
            result.setHydro(Double.valueOf(internalValue[3]));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return  result;
    }
}
