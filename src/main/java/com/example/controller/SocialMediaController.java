package com.example.controller;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;
import java.util.List;

import org.springframework.web.bind.annotation.*;


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

    public SocialMediaController(AccountService accountService, MessageService messageService)
    {
        this.messageService = messageService;
        this.accountService = accountService;
    }

    //new user registration
    @PostMapping(value = "/register")
    public @ResponseBody Account postNewUser(@RequestBody String username, String Password)
    {
        // Account account 
        return accountService.persistAccount("", account);
        
    }

    //user login
    @PostMapping(value = "/login")
    public @ResponseBody Account userLogin(@RequestBody String username, String password)
    {
        return accountService.loginAccount(username, password);
    }

    //create new message
    @PostMapping(value = "/messages")
    public @ResponseBody Message postNewMessage(@RequestBody Message message)
    {
        return messageService.persistMessage(message);
    }

    // get all messages
    @GetMapping("/messages")
    public @ResponseBody List<Message> getMessages()
    {
        return messageService.getAllMessages();

    }

    //get message by message id
    @GetMapping("/messages/{messageId}")
    public @ResponseBody Message getMessageByID(@PathVariable Integer messageId)
    {
        return messageService.getMessageById(messageId);
    }

    //delete message by message id
    @DeleteMapping("/messages/{messageId}")
    public @ResponseBody void deleteMessageById(@PathVariable Integer messageId)
    {
        messageService.deleteMessage(messageId);
    }

    //update message by id
    @PatchMapping("/messages/{messageId}")
    public @ResponseBody void updateMessageById(@PathVariable Integer messageId, String newMessage)
    {
        messageService.updateMessage(messageId, newMessage);
    }

    //get all messages by a particular user from accounts entity(AKA posted_by in Message entity)
    @GetMapping("/accounts/{accountId}/messages")
    public @ResponseBody List<Message> getAllUserMessages(@PathVariable Integer accountId)
    {
        //Account account = accountService.getUserById(accountId);

        return messageService.getUserMessages(accountId);
    }





}
