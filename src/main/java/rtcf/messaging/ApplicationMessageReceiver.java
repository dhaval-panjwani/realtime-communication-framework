package rtcf.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import rtcf.application.ApplicationEventHandler;
import rtcf.model.ClientOutput;
import rtcf.service.PublishService;

@Component
public class ApplicationMessageReceiver {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationMessageReceiver.class);

	@Autowired
	private ApplicationEventHandler applicationEventHandler;
	
	@Autowired
	private PublishService publishService;

	@JmsListener(destination = "${rtcf.messaging-queue.topic}")
	public void receiveApplicationMsgFromTopic(String message) {
		System.out.println("Received message='{}'" + message);
		logger.debug("Message received : {}", message);
		ClientOutput processedOutput = this.applicationEventHandler.processAndTransformEvent(message);
		logger.debug("Processed output : {}", processedOutput.toString());
		System.out.println("Output msg is : " + processedOutput.toString());
		this.publishService.sendMsgToUsers(processedOutput);
	}
}