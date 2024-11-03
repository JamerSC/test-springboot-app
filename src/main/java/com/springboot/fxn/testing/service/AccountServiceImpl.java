package com.springboot.fxn.testing.service;

import com.springboot.fxn.testing.model.Account;
import com.springboot.fxn.testing.repository.AccountRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Override
    public List<Account> getAllAccounts() {
        return accountRepo.findAll();
    }

    @Override
    public Account getAccountId(Long id) {
        return accountRepo.findById(id).orElse(null);
    }

    @Override
    public void save(Account account) {
        accountRepo.save(account);
    }

    @Override
    public void deleteById(Long id) {
        accountRepo.deleteById(id);
    }
}
