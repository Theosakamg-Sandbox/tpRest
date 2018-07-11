package com.tactfactory.iot.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration of MVC stack.
 */
@Configuration
public class MvcConfig {

    /** @return Instance of Model Mapper. */
    @Bean
    public ModelMapper makeModelMapper() {
        return new ModelMapper();
    }
}
