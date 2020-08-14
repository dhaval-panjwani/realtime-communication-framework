package sample;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

	@Autowired
	ChatService chatService;

	@MessageMapping("/chat/{topic}")
	public void send(@DestinationVariable("topic") String topic, Message message, Principal principal)
			throws Exception {
		String sessionId = principal.getName();
		System.out.println("Session ID is " + sessionId);
		chatService.subscibeOrsendMessagesByTopic(topic, message, sessionId);
	}
}
