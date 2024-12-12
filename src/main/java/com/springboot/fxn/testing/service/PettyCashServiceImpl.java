package com.springboot.fxn.testing.service;

import com.springboot.fxn.testing.dto.PettyCashDto;
import com.springboot.fxn.testing.mapper.PettyCashMapper;
import com.springboot.fxn.testing.model.ClientAccount;
import com.springboot.fxn.testing.model.PettyCash;
import com.springboot.fxn.testing.repository.AccountRepo;
import com.springboot.fxn.testing.repository.PettyCashRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Getter
public class PettyCashServiceImpl implements PettyCashService {

    @Autowired
    private PettyCashRepo pettyCashRepo;
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private PettyCashMapper pettyCashMapper;

    @Override
    public List<PettyCashDto> getAllPettyCash() {
        // before
        /*return pettyCashRepo.findAll().stream()
                .map(pettyCashMapper::toPettyCashDto)
                .collect(Collectors.toList());*/
        // after
        //List<PettyCash> pettyCashList = pettyCashRepo.findAllWithAccounts();
        //return pettyCashMapper.toPettyCashListDto(pettyCashList);
        List<PettyCash> pettyCashList = pettyCashRepo.findAll();

        return pettyCashList.stream()
                .map(pettyCash -> {
                    PettyCashDto pettyCashDto = pettyCashMapper.toPettyCashDto(pettyCash);

                    // Fetch associated ClientAccount entities based on accountIds
                    Set<Long> accountIds = pettyCashDto.getAccountIds();
                    List<ClientAccount> accounts = accountRepo.findAllById(accountIds);  // This assumes you have a method to fetch accounts by their IDs

                    // Optionally, if you want to map full account details, you can modify the DTO to store those details
                    Set<String> accountDetails = accounts.stream()
                            .map(account -> account.getName() + " (" + account.getEmail() + ")")
                            .collect(Collectors.toSet());

                    pettyCashDto.setAccountDetails(accountDetails);  // Set account details in DTO (you would add this field to PettyCashDto)

                    return pettyCashDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public PettyCashDto getPettyCashById(Long id) {
        /*PettyCash pettyCash = pettyCashRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Petty Cash ID not found!"));
        return pettyCashMapper.toPettyCashDto(pettyCash);*/

        return pettyCashRepo.findById(id)
                .map(pettyCashMapper::toPettyCashDto).
                orElseThrow(() -> new RuntimeException("Petty cash ID not found!"));
    }

    @Override
    public void save(PettyCashDto dto) {
        PettyCash pettyCash = pettyCashMapper.toPettyCashEntity(dto);

        // Map AccountDto to Account by fetching Account entities from the database using the IDs
        Set<ClientAccount> clientAccounts = dto.getAccountIds()
                .stream()
                .map(accountRepo::findById)
                .filter(Optional::isPresent)
                .map(Optional::get).collect(Collectors.toSet());
        pettyCash.setAccounts(clientAccounts);

        pettyCashRepo.save(pettyCash);
    }
    @Override
    public void deletePettyCashById(Long id) {
        pettyCashRepo.deleteById(id);
    }
}
