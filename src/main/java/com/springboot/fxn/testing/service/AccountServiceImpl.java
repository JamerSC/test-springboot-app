package com.springboot.fxn.testing.service;

import com.springboot.fxn.testing.dto.AccountDto;
import com.springboot.fxn.testing.mapper.AccountMapper;
import com.springboot.fxn.testing.model.Account;
import com.springboot.fxn.testing.repository.AccountRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Override
    public List<AccountDto> getAllAccounts() {
        return accountRepo.findAll().stream()
                .map(AccountMapper::accountToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto getAccountId(Long id) {
        Account account = accountRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Account ID Not found!"));
        return AccountMapper.accountToDto(account);
    }

    @Override
    public void save(AccountDto dto) {
        Account account = AccountMapper.accountToEntity(dto);
        accountRepo.save(account);
    }

    @Override
    public void deleteById(Long id) {
        accountRepo.deleteById(id);
    }
}
