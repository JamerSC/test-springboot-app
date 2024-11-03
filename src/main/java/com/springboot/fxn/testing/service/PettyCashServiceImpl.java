package com.springboot.fxn.testing.service;

import com.springboot.fxn.testing.dto.AccountDto;
import com.springboot.fxn.testing.dto.PettyCashDto;
import com.springboot.fxn.testing.model.Account;
import com.springboot.fxn.testing.model.PettyCash;
import com.springboot.fxn.testing.repository.AccountRepo;
import com.springboot.fxn.testing.repository.PettyCashRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
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

    @Override
    public List<PettyCash> getAllPettyCash() {
        return pettyCashRepo.findAll();
    }

    @Override
    public PettyCashDto getPettyCashById(Long id) {
        PettyCash pettyCashId = pettyCashRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Petty Cash ID not found!"));
        if (pettyCashId != null) {
            PettyCashDto dto = new PettyCashDto();
            BeanUtils.copyProperties(pettyCashId, dto);
            return dto;
        }
        return null;
    }

    @Override
    public void save(PettyCashDto dto) {
        PettyCash pettyCash = new PettyCash();

        Set<Account> accounts = dto.getAccountIds().stream()
                                    .map(accountRepo::findById)
                                    .filter(Optional::isPresent)
                                    .map(Optional::get)
                                    .collect(Collectors.toSet());
        pettyCash.setAccounts(accounts);

        BeanUtils.copyProperties(dto, pettyCash);
        pettyCashRepo.save(pettyCash);
    }

    @Override
    public void deletePettyCashById(Long id) {
        pettyCashRepo.deleteById(id);
    }
}
