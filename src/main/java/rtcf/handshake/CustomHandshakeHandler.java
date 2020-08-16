package rtcf.handshake;

import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

/**
 * Set anonymous user (Principal) in WebSocket messages by using UUID This is
 * necessary to avoid broadcasting messages but sending them to specific user
 * sessions
 */
public class CustomHandshakeHandler extends DefaultHandshakeHandler {
//	@Override
//	protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler,
//			Map<String, Object> attributes) {
//		// generate user name by UUID
//		return new StompPrincipal(UUID.randomUUID().toString());
//	}
}