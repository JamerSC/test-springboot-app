package com.springboot.fxn.testing.mapper;

import com.springboot.fxn.testing.dto.PettyCashDto;
import com.springboot.fxn.testing.model.ClientAccount;
import com.springboot.fxn.testing.model.PettyCash;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(uses = {AccountMapper.class})
public interface PettyCashMapper {

    PettyCashMapper INSTANCE = Mappers.getMapper(PettyCashMapper.class);

    @Mapping(target = "accountIds", expression = "java(mapAccountsToIds(pettyCash.getAccounts()))")
    @Mapping(target = "accountDetails", expression = "java(mapAccountsToDetails(pettyCash.getAccounts()))")
    PettyCashDto toPettyCashDto(PettyCash pettyCash);

    @Mapping(target = "accounts", source = "accountIds", qualifiedByName = "mapIdsToAccounts")
    PettyCash toPettyCashEntity(PettyCashDto dto);

    List<PettyCashDto> toPettyCashListDto(List<PettyCash> pettyCashList);

    // Custom mapping to map accounts to account IDs
    default Set<Long> mapAccountsToIds(Set<ClientAccount> clientAccounts) {
        if (clientAccounts == null) {
            return null;
        }
        return clientAccounts.stream()
                .map(ClientAccount::getId)
                .collect(Collectors.toSet());
    }

    // Custom mapping to map accounts to account details (e.g., names or emails)
    default Set<String> mapAccountsToDetails(Set<ClientAccount> clientAccounts) {
        if (clientAccounts == null) {
            return null;
        }
        return clientAccounts.stream()
                .map(account -> account.getName() + " (" + account.getEmail() + ")")
                .collect(Collectors.toSet());
    }

    // Custom mapping to map account IDs to Account entities
    @Named("mapIdsToAccounts")
    default Set<ClientAccount> mapIdsToAccounts(Set<Long> accountIds) {
        if (accountIds == null) {
            return null;
        }

        return accountIds.stream()
                .map(id -> {
                    ClientAccount clientAccount = new ClientAccount();
                    clientAccount.setId(id); // Assume IDs are sufficient for entity creation
                    return clientAccount;
                })
                .collect(Collectors.toSet());
    }
}
