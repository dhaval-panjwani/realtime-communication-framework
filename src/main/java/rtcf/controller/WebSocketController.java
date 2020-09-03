package rtcf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import rtcf.model.Message;
import rtcf.service.SubscribeService;

@Controller
public final class WebSocketController {

	@Autowired
	SubscribeService subService;

	@MessageMapping("/interests")
	public void subscribeInterests(Message message, SimpMessageHeaderAccessor headerAccessor) throws Exception {
		String sessionId = headerAccessor.getUser().getName();
//		String sessionId = headerAccessor.getSessionAttributes().get("sessionId").toString();
		headerAccessor.setSessionId(sessionId);
		subService.subscribeUserInterests(sessionId, message.getInterests());
	}
}
