package com.springboot.fxn.testing.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
public class StringToSetLongConvert implements Converter<String, Set<Long>> {

    private static final Logger logger = LoggerFactory.getLogger(StringToSetLongConvert.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Set<Long> convert(@NonNull String source) {
        try {
            return objectMapper.readValue(source, new TypeReference<Set<Long>>() {});
        } catch (IOException e) {
            logger.error("Failed to convert String to Set<Long>: {}", source, e);
            return new HashSet<>();
        }
    }
}
