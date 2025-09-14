package kiii.messagesapiservice.web;

import kiii.messagesapiservice.model.Message;
import kiii.messagesapiservice.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MessagesApi {
    private final MessageService messageService;

    public MessagesApi(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public List<Message> findAll() {
        return this.messageService.getAllMessages();
    }

    @PostMapping("/add")
    public ResponseEntity<Message> saveMessage(@RequestParam String text, @RequestParam String receiver) {
        return this.messageService
                .add(text, receiver)
                .map(message -> ResponseEntity.ok().body(message))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
