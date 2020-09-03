package rtcf.messaging;

import javax.jms.Message;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import rtcf.application.ApplicationEventHandler;
import rtcf.application.ApplicationEventHandlerFactory;
import rtcf.error.DefaultErrorHandler;
import rtcf.model.ApplicationEventRequest;
import rtcf.model.ApplicationEventResponse;
import rtcf.service.PublishService;

@Component
public class ApplicationMessageReceiver {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationMessageReceiver.class);

	@Autowired
	private ApplicationEventHandlerFactory eventHandlerFactory;

	@Autowired
	private PublishService publishService;

	@Autowired
	private DefaultErrorHandler customErrorHandler;

	@SuppressWarnings("rawtypes")
	@JmsListener(destination = "${rtcf.messaging-queue.topic}")
	public void receiveApplicationMsgFromTopic(ApplicationEventRequest eventRequest) {
		System.out.println("Received event='{}'" + eventRequest);
		logger.debug("Message event : {}", eventRequest);
		try {
			ApplicationEventHandler applicationEventHandler = this.eventHandlerFactory.createEventHandler(eventRequest);
			ApplicationEventResponse processedOutput = applicationEventHandler.processAndTransformEvent(eventRequest);
			logger.debug("Processed output : {}", processedOutput.toString());
			System.out.println("Output msg is : " + processedOutput.toString());
			this.publishService.sendMsgToUsers(processedOutput);
		} catch (Exception ex) {
			this.customErrorHandler.handleError(eventRequest, ex);
		}
	}

}