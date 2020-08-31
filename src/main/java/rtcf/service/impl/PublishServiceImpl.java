package rtcf.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import rtcf.model.ClientOutput;
import rtcf.service.PublishService;

@Service
public class PublishServiceImpl implements PublishService {

	private static final Logger logger = LoggerFactory.getLogger(PublishServiceImpl.class);

	private static final String WS_MESSAGE_TRANSFER_DESTINATION = "/topic/interests";
	
	@Autowired
	private SimpMessagingTemplate template;

	@Autowired
	Map<String, HashSet<String>> interestsToUsers;

	@Override
	public void sendMsgToUsers(ClientOutput output) {
		System.out.println("Output msg in sendMsgToUsers is : " + output.toString());
		List<String> interests = output.getInterests();
		String clientMessage = output.getMessage();
		if (interests != null && !interests.isEmpty()) {
			for (String interest : interests) {
				System.out.println("Visiting interest : " + interest);
				if (interestsToUsers.containsKey(interest)) {
					System.out.println("Interest found in map : " + interest + " with interested users being ");
					HashSet<String> interestedUsers = interestsToUsers.get(interest);
					interestedUsers.forEach(System.out::println);
					interestedUsers.parallelStream().forEach(user -> {
						try {
							System.out.println("Visiting user : " + user);
							template.convertAndSendToUser(user, WS_MESSAGE_TRANSFER_DESTINATION,
									clientMessage);
						} catch (Exception ex) {
							logger.error("Unable to send Message : {} to User : {} with Interest : {}", clientMessage,
									user, interest);
						}
					});
				} else {
					logger.debug("Interest : {} not found on this server instance!", interest);
				}
			}
		} else {
			logger.info(
					"Message received but no topic of interests received from the application. Discarding the message!");
		}
	}

}
