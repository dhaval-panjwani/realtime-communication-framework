package rtcf;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationMessageReceiver {

	@JmsListener(destination = "${rtcf.messaging-queue.topic}")
	public void receiveApplicationMsgFromTopic(String message) {
		System.out.println("Received message='{}'" + message);
	}
}