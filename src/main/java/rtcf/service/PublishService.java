package rtcf.service;

import rtcf.model.ClientOutput;

public interface PublishService {
	public void sendMsgToUsers(ClientOutput output);
}
