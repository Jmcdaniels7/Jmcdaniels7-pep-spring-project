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

    public MessageService(MessageRepository messageRepository)
    {
        this.messageRepository = messageRepository;
    }

    public Message persistMessage(Message message)
    {
        //return messageRepository.save(message);
    }

    public List<Message> getAllMessages()
    {
       // return messageRepository.findAll();
    }

    public Message getMessageById(Integer id)
    {
        Optional<Message> optionalMessage = messageRepository.findById(id);

        if(optionalMessage.isPresent())
        {
            return optionalMessage.get();
        }
        else
        {
            return null;
        }
    }

    public void deleteMessage(Integer id)
    {
        messageRepository.deleteById(id);
    }

    public void updateMessage(Integer id, Message newMessage)
    {
        Optional<Message> optionalMessage = messageRepository.findById(id);

        if(optionalMessage.isPresent())
        {
            Message message = optionalMessage.get();
            message.setMessageText(newMessage.getMessageText());
            messageRepository.save(message);
        }
        else
        {
            return null;
        }

    }

    public List<Message> getUserMessages(Integer userId)
    {
        //we could use findALL with messages are from userId?
        Optional<Account> optionalAccount = accountRepository.findById(userId);

        if(optionalAccount.isPresent())
        {
            Account account = optionalAccount.get();
            // return all messages where posted_by = account.getAccountId()
        }



    }
}
