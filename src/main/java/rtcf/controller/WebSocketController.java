package rtcf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;

import rtcf.model.Message;
import rtcf.service.SubscribeService;

@Controller
public class WebSocketController {

	@Autowired
	SubscribeService subService;

	@Autowired
	private SimpUserRegistry simpUserRegistry;

	public int getNumberOfSessions() {
		return simpUserRegistry.getUserCount();
	}

	@MessageMapping("/interests/subscribe")
	public void send(Message message, SimpMessageHeaderAccessor headerAccessor) throws Exception {
		// chatService.subscibeOrsendMessagesByTopic(topic, message, sessionId);
		String sessionId = headerAccessor.getSessionAttributes().get("sessionId").toString();
		System.out.println(sessionId);
		headerAccessor.setSessionId(sessionId);
		subService.subscribeUserInterests(sessionId, message.getInterests());
	}

}
