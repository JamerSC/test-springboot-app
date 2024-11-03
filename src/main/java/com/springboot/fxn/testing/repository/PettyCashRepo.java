package com.springboot.fxn.testing.repository;

import com.springboot.fxn.testing.model.PettyCash;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PettyCashRepo extends JpaRepository <PettyCash, Long> {
    //
}
