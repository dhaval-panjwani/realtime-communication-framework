//package rtcf;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.jms.annotation.EnableJms;
//import org.springframework.scheduling.annotation.EnableScheduling;
//
//@SpringBootApplication
//@EnableAutoConfiguration
//@EnableScheduling
//@EnableJms
//
//public class Application {
//
//	public static void main(String[] args) {
//		SpringApplication.run(Application.class, args);
//	}
////
////	@Override
////	public void run(ApplicationArguments applicationArguments) throws Exception {
////
////		for (int i = 0; i < 5; i++) {
////			OutputMessage order = new OutputMessage();
////			order.setFrom("Dhaval");
////			order.setMessage(String.valueOf(i));
////			order.setTopic("Yaozy");
////			publisher.sendTopic(order);
////		}
////
////		TimeUnit.SECONDS.sleep(10);
////
////		for (int i = 0; i < 5; i++) {
////			OutputMessage order = new OutputMessage();
////			order.setFrom("Dhaval");
////			order.setMessage(String.valueOf(i));
////			order.setTopic("Yaozy");
////			publisher.sendTopic(order);
////		}
////
////		TimeUnit.SECONDS.sleep(600);
////		System.exit(-1);
////	}
//
////	@Bean
////	public JmsListenerContainerFactory<?> topicListenerFactory(ConnectionFactory connectionFactory,
////			DefaultJmsListenerContainerFactoryConfigurer configurer) {
////		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
////		configurer.configure(factory, connectionFactory);
////		factory.setPubSubDomain(true);
////		SimpleMessageConverter s = new SimpleMessageConverter();
////		MappingJackson2MessageConverter s2 = new MappingJackson2MessageConverter();
////		s2.setTargetType(MessageType.BYTES);
////		s2.setTypeIdPropertyName("DocumentType");
////		s2.setTypeIdPropertyName("_type");
////		factory.setMessageConverter(s2); // or "s"
////		return factory;
////	}
////
////	@Bean
////	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory connectionFactory) {
////		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
////		factory.setConnectionFactory(connectionFactory);
////		factory.setConcurrency("3-10");
////		SimpleMessageConverter s = new SimpleMessageConverter();
////		MappingJackson2MessageConverter s2 = new MappingJackson2MessageConverter();
////		s2.setTargetType(MessageType.BYTES);
////		s2.setTypeIdPropertyName("DocumentType");
////		factory.setMessageConverter(s2); // or "s"
////		return factory;
////	}
//
//}
