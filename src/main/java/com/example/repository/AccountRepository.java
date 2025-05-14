package com.example.repository;

import com.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    //we need to do named queries here
    // return type, method, parameters, semicolon

    //finds account by id
    Account findAccountByAccountId(Integer accountId);

    //verify user
    Account findAccountByUsernameAndPassword(String username, String password);

    //finds account by username
    Account findAccountByUsername(String username);

}
