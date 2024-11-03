package com.springboot.fxn.testing.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final StringToSetLongConvert stringToSetLongConvert;

    public WebConfig(StringToSetLongConvert stringToSetLongConvert) {
        this.stringToSetLongConvert = stringToSetLongConvert;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToSetLongConvert);
    }
}
