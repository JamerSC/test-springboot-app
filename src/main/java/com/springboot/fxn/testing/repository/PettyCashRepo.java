package com.springboot.fxn.testing.repository;

import com.springboot.fxn.testing.model.PettyCash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PettyCashRepo extends JpaRepository <PettyCash, Long> {

    @Query("SELECT p FROM PettyCash p LEFT JOIN FETCH p.accounts")
    List<PettyCash> findAllWithAccounts();
}
