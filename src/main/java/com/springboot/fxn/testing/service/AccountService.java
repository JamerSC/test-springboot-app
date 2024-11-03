package com.springboot.fxn.testing.service;

import com.springboot.fxn.testing.model.Account;

import java.util.List;

public interface AccountService {

    List<Account> getAllAccounts();
    Account getAccountId(Long id);
    void save(Account account);
    void deleteById(Long id);
}
