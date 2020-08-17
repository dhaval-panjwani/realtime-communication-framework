package rtcf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import rtcf.application.ApplicationEventHandler;
import rtcf.model.ClientOutput;

@Component
public class ApplicationMessageReceiver {

	@Autowired
	private ApplicationEventHandler applicationEventHandler;

	@JmsListener(destination = "${rtcf.messaging-queue.topic}")
	public void receiveApplicationMsgFromTopic(String message) {
		System.out.println("Received message='{}'" + message);
		ClientOutput processedOutput = this.applicationEventHandler.processAndTransformEvent(message);
		System.out.println("Processed output is : " + processedOutput.toString());
	}
}