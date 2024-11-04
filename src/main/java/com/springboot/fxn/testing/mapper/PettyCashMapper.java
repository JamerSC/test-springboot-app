package com.springboot.fxn.testing.mapper;

import com.springboot.fxn.testing.dto.AccountDto;
import com.springboot.fxn.testing.dto.PettyCashDto;
import com.springboot.fxn.testing.model.Account;
import com.springboot.fxn.testing.model.PettyCash;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PettyCashMapper {

    public static PettyCashDto pettyCashToDto(PettyCash pettyCash) {
        if (pettyCash == null) {
            return null;
        }

        PettyCashDto dto = new PettyCashDto();
        dto.setId(pettyCash.getId());
        dto.setDate(pettyCash.getDate());
        dto.setActivityDescription(pettyCash.getActivityDescription());
        dto.setActivityCategory(pettyCash.getActivityCategory());
        dto.setSoaCategory(pettyCash.getSoaCategory());
        dto.setAmount(pettyCash.getAmount());

        // Map User entities to user IDs
        /*
        //private Set<Long> accountIds;
        Set<Long> accountIds = pettyCash.getAccounts().stream()
                .map(Account::getId)
                .collect(Collectors.toSet());
        dto.setAccountIds(accountIds);*/

        // Map Account entities to AccountDto objects
        Set<AccountDto> accounts = pettyCash.getAccounts().stream()
                .map(account -> new AccountDto(account.getId(), account.getName(), account.getEmail()))
                .collect(Collectors.toSet());

        dto.setAccounts(accounts);

        return dto;
    }

    public static PettyCash pettyCashToEntity(PettyCashDto dto) {
        if (dto == null) {
            return null;
        }

        PettyCash pettyCash = new PettyCash();
        pettyCash.setId(dto.getId());
        pettyCash.setDate(dto.getDate());
        pettyCash.setActivityDescription(dto.getActivityDescription());
        pettyCash.setActivityCategory(dto.getActivityCategory());
        pettyCash.setSoaCategory(dto.getSoaCategory());
        pettyCash.setAmount(dto.getAmount());

        // Set users to null or use a service to find User entities by IDs if necessary
        /* //private Set<Long> accountIds;
        Set<Account> accounts = dto.getAccountIds().stream()
                .map(id -> {
                    Account account = new Account();
                    account.setId(id);
                    return account;
                })
                .collect(Collectors.toSet());*/

        // Map AccountDto objects to Account entities
        Set<Account> accounts = dto.getAccounts().stream()
                .map(accountDto -> {
                    Account account = new Account();
                    account.setId(accountDto.getId());
                    account.setName(accountDto.getName());
                    account.setEmail(accountDto.getEmail());
                    return account;
                })
                .collect(Collectors.toSet());


        pettyCash.setAccounts(accounts);

        return pettyCash;
    }
}
