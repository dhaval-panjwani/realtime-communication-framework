package rtcf.service;

import rtcf.model.ApplicationEventResponse;

public interface PublishService {
	public void sendMsgToUsers(ApplicationEventResponse eventResponse);
}
