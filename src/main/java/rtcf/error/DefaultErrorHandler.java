package rtcf.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ErrorHandler;

import rtcf.model.ApplicationEventRequest;

@Component
public class DefaultErrorHandler implements ErrorHandler {

	private static Logger log = LoggerFactory.getLogger(DefaultErrorHandler.class);

	@Autowired
	JmsTemplate jmsTemplate;

	private static String revivePolicy = "revive";

	private static String sidelinePolicy = "sideline";

	@Value("${rtcf.error-policy}")
	private String errorPolicy;

	@Value("${rtcf.error-queue}")
	private String errorQueueDestination;

	public void handleError(ApplicationEventRequest eventRequest, Exception ex) {
		if (errorPolicy.equalsIgnoreCase(revivePolicy)) {
			reviveReceivedMsg(eventRequest, ex);
		} else if (errorPolicy.equalsIgnoreCase(sidelinePolicy)) {
			sidelineReceivedMsg(eventRequest, ex);
		}
	}

	private void sidelineReceivedMsg(ApplicationEventRequest eventRequest, Exception ex) {
		log.error("About to sideline application event request due to exception", ex);
		// do nothing just log in
	}

	private void reviveReceivedMsg(ApplicationEventRequest eventRequest, Exception ex) {
		log.error("About to revive application event request due to exception", ex);
		// send it to a queue dedicated for error requests
		jmsTemplate.setPubSubDomain(false);
		jmsTemplate.convertAndSend(errorQueueDestination, eventRequest);
	}

	@Override
	public void handleError(Throwable t) {
		log.warn("Error occurred in JMS!");
        log.error(t.getCause().getMessage());
	}

}
