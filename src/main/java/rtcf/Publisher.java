package rtcf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class Publisher {

	@Value("${clientId}")
	private String clientId;

	@Autowired
	private JmsTemplate jmsTemplate;

	public void sendTopic(OutputMessage message) {
		System.out.println("sending with convertAndSend() with messages <" + message.toString() + ">");
		ObjectMapper objectMapper = new ObjectMapper();
		String messageSent;
		try {
			messageSent = objectMapper.writeValueAsString(message);
			jmsTemplate.convertAndSend("XD", messageSent);
		} catch (JsonProcessingException e) {
			System.out.println("Message sending failed. Due to exception " + e.getMessage());
			e.printStackTrace();
		}
	}
}