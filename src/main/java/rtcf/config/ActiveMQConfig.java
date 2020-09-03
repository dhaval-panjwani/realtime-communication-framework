package rtcf.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import rtcf.error.DefaultErrorHandler;

@EnableJms
@Configuration
public class ActiveMQConfig {

	@Value("${rtcf.activemq.broker-url}")
	private String brokerUrl;

	@Bean
	public ActiveMQConnectionFactory receiverActiveMQConnectionFactory() {
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
		activeMQConnectionFactory.setBrokerURL(brokerUrl);
		return activeMQConnectionFactory;
	}

	@Bean
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(receiverActiveMQConnectionFactory());
		factory.setPubSubDomain(true);
		factory.setErrorHandler(new DefaultErrorHandler());
		factory.setMessageConverter(jacksonJmsMessageConverter());
		return factory;
	}
	
	 @Bean // Serialize message content to json using TextMessage
	  public MessageConverter jacksonJmsMessageConverter() {
	      MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
	      converter.setTargetType(MessageType.TEXT);
	      converter.setTypeIdPropertyName("_type");
	      return converter;
	  }
	 
}