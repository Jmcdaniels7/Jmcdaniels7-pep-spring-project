package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository)
    {
        this.accountRepository = accountRepository;
    }

    //readMe requirements here for username not blank etc.
    // new user registration
    public Account persistAccount(Account account)
    {
       return accountRepository.save(account);
    }

    // user login
    public Account loginAccount(String username, String password)
    {
        return null;
        
    }

    //get user by id
    public Account getUserById(Integer id)
    {
        return null;

    }
}
