package com.springboot.fxn.testing.service;

import com.springboot.fxn.testing.dto.PettyCashDto;
import com.springboot.fxn.testing.model.PettyCash;

import java.util.List;

public interface PettyCashService {

    List<PettyCash> getAllPettyCash();
    PettyCashDto getPettyCashById(Long id);
    void save(PettyCashDto dto);
    void deletePettyCashById(Long id);
}
