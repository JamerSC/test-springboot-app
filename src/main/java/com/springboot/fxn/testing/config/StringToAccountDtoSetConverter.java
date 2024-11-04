package com.springboot.fxn.testing.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.fxn.testing.dto.AccountDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class StringToAccountDtoSetConverter implements Converter<String, Set<AccountDto>> {

    private  final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Set<AccountDto> convert(@NonNull String source) {
        try {
            // Convert JSON string directly into Set<AccountDto>
            List<Long> ids = objectMapper.readValue(source, new TypeReference<List<Long>>() {});
            Set<AccountDto> accountDtos = new HashSet<>();
            for (Long id : ids) {
                AccountDto accountDto = new AccountDto();
                accountDto.setId(id);
                accountDtos.add(accountDto);
            }
            return accountDtos;
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert JSON string to Set<AccountDto>", e);
        }
    }
}
