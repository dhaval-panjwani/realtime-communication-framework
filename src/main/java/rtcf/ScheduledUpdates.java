package rtcf;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import rtcf.model.ApplicationEventRequest;

@EnableScheduling
@Component
public class ScheduledUpdates {

	@Autowired
	Map<String, HashSet<String>> interestsToUsers;
	
	@Autowired
	JmsTemplate jmsTemplate;

	@Scheduled(fixedDelay = 5000)
	public void publishUpdates() {
		interestsToUsers.entrySet().forEach(entry -> {
			System.out.println("Interest is : " + entry.getKey() + ". Users are : " + entry.getValue());
		});
//		Map<String,String> headers = new HashMap<>();
//		
//		headers.put("id", "24");
//		String str = "{\r\n" + 
//				"        \"message\" : \"Hello world\",\r\n" + 
//				"        \"interest\" : \"World\"\r\n" + 
//				"                    }";
//		jmsTemplate.setPubSubDomain(true);
//	    jmsTemplate.convertAndSend("applicationTopic", new ApplicationEventRequest(headers,str));
	}
	
	@Scheduled(fixedDelay = 10000)
	public void publishUpdatesDemo() {
		Map<String,String> headers = new HashMap<>();
		
		headers.put("type", "Pizza");
		String str = "{\r\n" + 
				"        \"message\" : \"Hello world\",\r\n" + 
				"        \"interest\" : \"Pizza\"\r\n" + 
				"                    }";
		jmsTemplate.setPubSubDomain(true);
	    jmsTemplate.convertAndSend("applicationTopic", new ApplicationEventRequest(headers,str));
	}
	

}