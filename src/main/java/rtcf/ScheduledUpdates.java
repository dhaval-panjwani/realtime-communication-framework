package rtcf;

import java.util.HashSet;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledUpdates {

	@Autowired
	private SimpMessagingTemplate template;

	@Autowired
	Map<String, HashSet<String>> userTopicOfInterests;

	@Scheduled(fixedDelay = 5000)
	public void publishUpdates() {
		userTopicOfInterests.entrySet().stream().forEach(eachEntry -> {
			System.out.println("Session ID is : " + eachEntry.getKey());
			System.out.println("Interests are : ");
			eachEntry.getValue().stream().forEach(System.out::println);
		});
	}

}