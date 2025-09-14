package kiii.messagesapiservice.service;

import kiii.messagesapiservice.model.Message;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    List<Message> getAllMessages();
    Optional<Message> add(String message, String receiver);
}
