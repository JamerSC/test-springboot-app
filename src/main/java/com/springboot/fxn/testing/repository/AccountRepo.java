package com.springboot.fxn.testing.repository;

import com.springboot.fxn.testing.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account, Long> {
    //
}
