package com.springboot.fxn.testing.config;

import com.springboot.fxn.testing.mapper.AccountMapper;
import com.springboot.fxn.testing.mapper.AccountMapperImpl;
import com.springboot.fxn.testing.mapper.PettyCashMapper;
import com.springboot.fxn.testing.mapper.PettyCashMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public PettyCashMapper pettyCashMapper() {
        return new PettyCashMapperImpl();
    }

    @Bean
    public AccountMapper accountMapper() {
        return new AccountMapperImpl();
    }
}
