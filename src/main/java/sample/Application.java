package sample;

import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;

import sample.messaging.Publisher;

@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling

public class Application {

	@Autowired
	private Publisher publisher;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
//
//	@Override
//	public void run(ApplicationArguments applicationArguments) throws Exception {
//
//		for (int i = 0; i < 5; i++) {
//			OutputMessage order = new OutputMessage();
//			order.setFrom("Dhaval");
//			order.setMessage(String.valueOf(i));
//			order.setTopic("Yaozy");
//			publisher.sendTopic(order);
//		}
//
//		TimeUnit.SECONDS.sleep(10);
//
//		for (int i = 0; i < 5; i++) {
//			OutputMessage order = new OutputMessage();
//			order.setFrom("Dhaval");
//			order.setMessage(String.valueOf(i));
//			order.setTopic("Yaozy");
//			publisher.sendTopic(order);
//		}
//
//		TimeUnit.SECONDS.sleep(600);
//		System.exit(-1);
//	}

	@Bean
	public JmsListenerContainerFactory<?> topicListenerFactory(ConnectionFactory connectionFactory,
			DefaultJmsListenerContainerFactoryConfigurer configurer) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		configurer.configure(factory, connectionFactory);
		factory.setPubSubDomain(true);
		return factory;
	}
}
