package sample.messaging;

import static config.ActiveMQConfig.TOPIC;

import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import sample.ChatService;
import sample.OutputMessage;

@Component
public class Subscriber {

	private static final String NO_GREETING = "no greeting";

	@Value("${clientId}")
	private String clientId;

	@Autowired
	ChatService chatService;

	@JmsListener(destination = TOPIC, containerFactory = "topicListenerFactory")
	public void receiveTopicMessage(@Payload String outputMessage, @Headers MessageHeaders headers, Message message,
			Session session) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			OutputMessage om = objectMapper.readValue(outputMessage, OutputMessage.class);
			System.out.println("received <" + om.toString() + ">");
			this.chatService.sendMessageToUsers(om.getTopic(), om);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}