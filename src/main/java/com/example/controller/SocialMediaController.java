package com.example.controller;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

 @RestController
public class SocialMediaController {

    private AccountService accountService;
    private MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService)
    {
        this.messageService = messageService;
        this.accountService = accountService;
    }

    //new user registration
    @PostMapping(value = "/register")
    public @ResponseBody ResponseEntity<Account> postNewUser(@RequestBody Account account)
    {
        //checking to see if username already exists
        if(accountService.getUserByUsername(account.getUsername()) != null)
        {
            return ResponseEntity.status(409).body(null);

        }
        
        //persisting a new account 
        Account newAccount = accountService.persistAccount(account);

        if (newAccount == null)
        {
            return ResponseEntity.status(400).body(null);

        }

        //if we get here then we have successfully created a new user
        return ResponseEntity.status(200).body(newAccount);

    }

    //user login
    @PostMapping(value = "/login")
    public @ResponseBody ResponseEntity<Account> userLogin(@RequestBody Account account)
    {
        //verifying login
        Account login = accountService.loginAccount(account.getUsername(), account.getPassword());

        if(login == null)
        {
            return ResponseEntity.status(401).body(null);

        }
        
        //if we get here then the login was successful
        return ResponseEntity.status(200).body(login);

        
    }

    //create new message
    @PostMapping(value = "/messages")
    public @ResponseBody ResponseEntity<Message> postNewMessage(@RequestBody Message message)
    {
        if(messageService.persistMessage(message) == null)
        {
            return ResponseEntity.status(400).body(message);
        }
        
        //if we get here then the new message was persisted
        return ResponseEntity.status(200).body(messageService.persistMessage(message));

        
        
    }

    // get all messages
    @GetMapping("/messages")
    public @ResponseBody ResponseEntity<List<Message>> getMessages()
    {
        if(messageService.getAllMessages().isEmpty())
        {
            return ResponseEntity.status(200).body(messageService.getAllMessages());

        }
        
        //if we get here the there are messages records
        return ResponseEntity.status(200).body(messageService.getAllMessages());
        

    }

    //get message by message id
    @GetMapping("/messages/{messageId}")
    public @ResponseBody ResponseEntity<Message> getMessageByID(@PathVariable int messageId)
    {
        return ResponseEntity.status(200).body(messageService.getMessageById(messageId));
        
    }

    //delete message by message id
    @DeleteMapping("/messages/{messageId}")
    public @ResponseBody ResponseEntity<String> deleteMessageById(@PathVariable Integer messageId)
    {
        if(messageService.deleteMessage(messageId) == null)
        {
            return ResponseEntity.status(200).body("");

        }
        
        //if we get here then we have succesfully deleted a row
        return ResponseEntity.status(200).body("1");

        
    }

    //update message by id
    @PatchMapping("/messages/{messageId}")
    public @ResponseBody ResponseEntity<Integer> updateMessageById(@PathVariable Integer messageId, @RequestBody Message message)
    {
        if(messageService.updateMessage(messageId, message.getMessageText()) == 0)
        {
            return ResponseEntity.status(400).body(0);

        }
        
        //if we ger here then we have succefully updated a message record
        return ResponseEntity.status(200).body(1);
        
    }

    //get all messages by a particular user from accounts entity(AKA posted_by in Message entity)
    @GetMapping("/accounts/{accountId}/messages")
    public @ResponseBody ResponseEntity<List<Message>> getAllUserMessages(@PathVariable Integer accountId)
    {
        return ResponseEntity.status(200).body(messageService.getUserMessages(accountId));
        
    }





}
