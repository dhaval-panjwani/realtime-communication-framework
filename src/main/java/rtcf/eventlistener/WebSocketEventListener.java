package rtcf.eventlistener;

import java.util.HashSet;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

	private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

	@Autowired
	private SimpMessageSendingOperations messagingTemplate;

	@Autowired
	Map<String, HashSet<String>> userTopicOfInterests;

	@SuppressWarnings("unchecked")
	@EventListener
	public void handleWebSocketConnectListener(SessionConnectedEvent event) {
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
		headerAccessor.getSessionId();
		GenericMessage<?> simpConnectMessageHeader = (GenericMessage<?>) headerAccessor.getHeader("simpConnectMessage");
		if (simpConnectMessageHeader == null
				|| !simpConnectMessageHeader.getHeaders().containsKey("simpSessionAttributes")) {
			return;
		}
		Map<String, String> simpSessionAttributes = (Map<String, String>) simpConnectMessageHeader.getHeaders()
				.get("simpSessionAttributes");
		String sessionId = simpSessionAttributes.get("sessionId");
		if (!userTopicOfInterests.containsKey(sessionId))
			userTopicOfInterests.put(sessionId, new HashSet<>());
		logger.info("Session ID Connected : " + sessionId);
	}

	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
		String sessionId = (String) headerAccessor.getSessionAttributes().get("sessionId");
		if (userTopicOfInterests.containsKey(sessionId))
			userTopicOfInterests.remove(sessionId);
		logger.info("Session ID Disconnected : " + sessionId);
	}
}