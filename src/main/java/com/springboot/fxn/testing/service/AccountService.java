package com.springboot.fxn.testing.service;

import com.springboot.fxn.testing.dto.AccountDto;
import com.springboot.fxn.testing.model.Account;

import java.util.List;

public interface AccountService {

    List<AccountDto> getAllAccounts();
    AccountDto getAccountId(Long id);
    void save(AccountDto dto);
    void deleteById(Long id);
}
