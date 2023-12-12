package bsm.choi.fancafe.domain.user.presentation;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public String handleChatMessage(String message) {
        return message;
    }
}
