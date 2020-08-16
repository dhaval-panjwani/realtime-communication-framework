package rtcf.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import rtcf.handshake.HttpHandshakeInterceptor;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	@Value("${rtcf.application.prefix}")
	private String appDestinationPrefix;

	@Value("${rtcf.websocket.endpoint}")
	private String webSocketConnectionEndpoint;

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic");
		config.setApplicationDestinationPrefixes(appDestinationPrefix);
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint(webSocketConnectionEndpoint).setAllowedOrigins("*")
				.addInterceptors(new HttpHandshakeInterceptor()).setHandshakeHandler(new DefaultHandshakeHandler())
				.withSockJS().setClientLibraryUrl("/webjars/sockjs-client/1.0.2/sockjs.min.js");
	}
}
