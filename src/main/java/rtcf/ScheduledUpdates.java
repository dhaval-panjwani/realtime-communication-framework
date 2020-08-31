package rtcf;

import java.util.HashSet;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class ScheduledUpdates {

	@Autowired
	Map<String, HashSet<String>> interestsToUsers;

	@Scheduled(fixedDelay = 5000)
	public void publishUpdates() {
		interestsToUsers.entrySet().forEach(entry -> {
			System.out.println("Interest is : " + entry.getKey() + ". Users are : " + entry.getValue());
		});
	}

}