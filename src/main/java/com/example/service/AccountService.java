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
        if(account.getPassword().trim().length() < 4 || account.getUsername().isEmpty())
        {
            return null;

        }
        else
        {
            accountRepository.save(account);
            return account;
        }
       
    }

    // user login
    public Account loginAccount(String username, String password)
    {
        Account account = accountRepository.findAccountByUsernameAndPassword(username, password);
        if(account != null)
        {
            return account;

        }
        else
        {
            return null;

        }
        
        
    }

    //get user by id
    public Account getUserById(Integer id)
    {
        if(accountRepository.findAccountByAccountId(id) == null)
        {
            return null;

        }
        else
        {
            return accountRepository.findAccountByAccountId(id);

        }
        

    }

    //find account by username boolean
    public Account getUserByUsername(String username)
    {
        return accountRepository.findAccountByUsername(username);
    }
}
