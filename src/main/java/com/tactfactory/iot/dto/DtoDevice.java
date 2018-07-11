package com.tactfactory.iot.dto;

import lombok.Data;

/**
 * Device DTO, link to Device class.
 */
@Data
public class DtoDevice {

    private String uuid;

    private int hwVer = 1;

    private int swVer = 1;

    private int upTime = 0;
}
