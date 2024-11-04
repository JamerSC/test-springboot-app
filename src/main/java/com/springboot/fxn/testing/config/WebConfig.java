package com.springboot.fxn.testing.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final StringToSetLongConvert stringToSetLongConvert;

    private final StringToAccountDtoSetConverter stringToAccountDtoSetConverter;

    public WebConfig(StringToSetLongConvert stringToSetLongConvert,
                     StringToAccountDtoSetConverter stringToAccountDtoSetConverter) {
        this.stringToSetLongConvert = stringToSetLongConvert;
        this.stringToAccountDtoSetConverter = stringToAccountDtoSetConverter;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToSetLongConvert);
        registry.addConverter(stringToAccountDtoSetConverter);
    }
}
