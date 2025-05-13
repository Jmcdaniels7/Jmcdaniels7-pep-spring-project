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

    // trying to make the save method
    @Query("INSERT INTO account (username, password) values(:username, :password)")
    Account save(Account account);
    

}
