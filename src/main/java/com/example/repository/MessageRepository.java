package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Message;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{

    //jpaRepository magic sql queries

    //find message by messageId
    Message findMessageByMessageId(int messageId);

    //delete message by message id
    //something wrong here due to 500 error
    //Message deleteByMessageId(Integer messageId);

    //getMessages by posted_by/accountId
    List<Message> findMessagesByPostedBy(Integer postedBy);

    
}
