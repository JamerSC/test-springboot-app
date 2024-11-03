package com.springboot.fxn.testing.mapper;

import com.springboot.fxn.testing.dto.AccountDto;
import com.springboot.fxn.testing.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public static AccountDto accountToDto(Account account) {
        if (account == null) {
            return null;
        }

        AccountDto dto = new AccountDto();
        dto.setId(account.getId());
        dto.setName(account.getName());
        dto.setEmail(account.getEmail());

        return dto;
    }

    public static Account accountToEntity(AccountDto dto) {
        if (dto == null) {
            return null;
        }

        Account account = new Account();
        account.setId(dto.getId());
        account.setName(dto.getName());
        account.setEmail(dto.getEmail());

        return account;
    }
}
