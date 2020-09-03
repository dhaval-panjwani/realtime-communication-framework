package rtcf.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


import rtcf.handshake.CustomHandshakeHandler;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer  {

	@Value("${rtcf.application.prefix}")
	private String appDestinationPrefix;

	@Value("${rtcf.websocket.endpoint}")
	private String webSocketConnectionEndpoint;
	
	CustomHandshakeHandler cust;

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic");
		config.setApplicationDestinationPrefixes(appDestinationPrefix);
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint(webSocketConnectionEndpoint).setAllowedOrigins("*")
				.setHandshakeHandler(new CustomHandshakeHandler())
				.withSockJS().setClientLibraryUrl("/webjars/sockjs-client/1.0.2/sockjs.min.js");
	}
}
