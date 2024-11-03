package com.springboot.fxn.testing.mapper;

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
        Set<Long> accountIds = pettyCash.getAccounts().stream()
                .map(Account::getId)
                .collect(Collectors.toSet());
        dto.setAccountIds(accountIds);

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
        Set<Account> accounts = dto.getAccountIds().stream()
                .map(id -> {
                    Account account = new Account();
                    account.setId(id);
                    return account;
                })
                .collect(Collectors.toSet());
        pettyCash.setAccounts(accounts);

        return pettyCash;
    }
}
