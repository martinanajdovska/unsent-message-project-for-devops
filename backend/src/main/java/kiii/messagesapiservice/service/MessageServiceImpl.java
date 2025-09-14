package kiii.messagesapiservice.service;

import kiii.messagesapiservice.model.Message;
import kiii.messagesapiservice.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public Optional<Message> add(String message, String sender) {
        Message msg = new Message();
        msg.setSender(sender);
        msg.setMessage(message);
        msg.setDate(LocalDate.now());
        messageRepository.save(msg);
        return Optional.of(msg);
    }
}
