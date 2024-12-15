package com.springboot.fxn.testing.service;

import com.springboot.fxn.testing.dto.ClientAccountDto;
import com.springboot.fxn.testing.mapper.AccountMapper;
import com.springboot.fxn.testing.model.ClientAccount;
import com.springboot.fxn.testing.repository.AccountRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public List<ClientAccountDto> getAllAccounts() {
        // Before
        /*return accountRepo.findAll().stream()
                .map(accountMapper::toAccountDto)
                .collect(Collectors.toList());*/
        // After
        List<ClientAccount> accounts = accountRepo.findAll();
        return accountMapper.INSTANCE.toListClientAccountDto(accounts);
    }

    @Override
    public ClientAccountDto getAccountId(Long id) {

        // Before
        /*ClientAccount clientAccount = accountRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Account ID Not found!"));
        return accountMapper.toAccountDto(clientAccount);*/

        // After
        return accountRepo.findById(id)
                .map(accountMapper.INSTANCE::toAccountDto)
                .orElseThrow(() -> new RuntimeException("Client Account ID Not Found!"));
    }

    @Override
    public void save(ClientAccountDto dto) {
        //Before
        /*ClientAccount clientAccount = accountMapper.toAccountEntity(dto);
        accountRepo.save(clientAccount);*/

        // After
        accountRepo.save(accountMapper.INSTANCE.toAccountEntity(dto));
    }

    @Override
    public void deleteById(Long id) {
        accountRepo.deleteById(id);
    }
}
