package com.springboot.fxn.testing.mapper;

import com.springboot.fxn.testing.dto.ClientAccountDto;
import com.springboot.fxn.testing.model.ClientAccount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper()
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    ClientAccountDto toAccountDto(ClientAccount clientAccount);

    ClientAccount toAccountEntity(ClientAccountDto dto);

    List<ClientAccountDto> toListClientAccountDto(List<ClientAccount> clientAccounts);
}
