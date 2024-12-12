package com.springboot.fxn.testing.service;

import com.springboot.fxn.testing.dto.ClientAccountDto;

import java.util.List;

public interface AccountService {

    List<ClientAccountDto> getAllAccounts();
    ClientAccountDto getAccountId(Long id);
    void save(ClientAccountDto dto);
    void deleteById(Long id);
}
