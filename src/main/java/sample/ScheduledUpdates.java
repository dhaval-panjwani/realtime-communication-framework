package sample;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledUpdates {

	@Autowired
	private SimpMessagingTemplate template;

	@Scheduled(fixedDelay = 5000)
	public void publishUpdates() {
		template.convertAndSend("/topic/scheduledpush", "Current time is : " + new Date());
	}

}