package rtcf.eventlistener;

import java.util.HashSet;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public final class WebSocketEventListener {

	private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

	@Autowired
	Map<String, HashSet<String>> interestsToUsers;

//	@SuppressWarnings("unchecked")
//	@EventListener
//	public void handleWebSocketConnectListener(SessionConnectedEvent event) {
//		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
//		headerAccessor.getSessionId();
//		GenericMessage<?> simpConnectMessageHeader = (GenericMessage<?>) headerAccessor.getHeader("simpConnectMessage");
//		if (simpConnectMessageHeader == null
//				|| !simpConnectMessageHeader.getHeaders().containsKey("simpSessionAttributes")) {
//			return;
//		}
//		Map<String, String> simpSessionAttributes = (Map<String, String>) simpConnectMessageHeader.getHeaders()
//				.get("simpSessionAttributes");
//		String sessionId = simpSessionAttributes.get("sessionId");
//		logger.debug("Session ID Connected : " + sessionId);
//	}
	
	@EventListener
	void handleSessionConnectedEvent(SessionConnectedEvent event) {
	    // Get Accessor
	    StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
	}

	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
		//String sessionId = (String) headerAccessor.getSessionAttributes().get("sessionId");
		String sessionId = headerAccessor.getUser().getName();
		removeUserInterests(sessionId);
		logger.info("Session ID Disconnected : " + sessionId);
	}
	
	private void removeUserInterests(String sessionId) {
		interestsToUsers.entrySet().forEach( entry -> {
			entry.getValue().remove(sessionId);
			if(entry.getValue().isEmpty()) {
				interestsToUsers.remove(entry.getKey());
			}
		});
	}
	
	
}