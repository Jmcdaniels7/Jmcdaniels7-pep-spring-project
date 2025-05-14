package com.example.service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;
import com.example.entity.Account;
import com.example.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    MessageRepository messageRepository;
    AccountRepository accountRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository)
    {
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    //new message added
    public Message persistMessage(Message message)
    {
        if(message.getMessageText().trim().length() > 255 || message.getMessageText().isEmpty())
        {
            return null;

        }
        //this line is causing a runtime exception, I know it is because message.getPostedBy() is null because if a user doesn't exist then there are no messages for that user. But if all we get is a message from the resquest body then how do we figure if a user exists?
        else if(accountRepository.findAccountByAccountId(message.getPostedBy()) == null)
        {
            return null;
        }
        else
        {
            //.save() in jpaRepository library to save/add a record in the DB)
            messageRepository.save(message);
            return message;

        }
        
    }

    public List<Message> getAllMessages()
    {
        // findAll() in the jpaReposiotry library to find all records 
       return messageRepository.findAll();
    }

    public Message getMessageById(int id)
    {
        if(messageRepository.findMessageByMessageId(id) == null)
        {
            return null;
        }
        else
        {
            return messageRepository.findMessageByMessageId(id);
        }
    }

    public Message deleteMessage(Integer id)
    {
        Message message = messageRepository.findMessageByMessageId(id);
        if(message == null)
        {
            return null;

        }
        else
        {
            
           messageRepository.delete(message);
           return message;

        }
        
    }

    public int updateMessage(Integer id, String newMessage)
    {
        //ensuring a message is found by a messageId
        Message message = messageRepository.findMessageByMessageId(id);

        if(message == null || newMessage.isEmpty() || newMessage.trim().length() > 255)
        {
            return 0;
        }
        else
        {
            message.setMessageText(newMessage);
            messageRepository.save(message);
            return 1;
        }
        

    }

    public List<Message> getUserMessages(Integer userId)
    {
        
        return messageRepository.findMessagesByPostedBy(userId);
        
    }
}
