package rtcf.service;

import java.util.List;

public interface SubscribeService {
	public void subscribeUserInterests(String userSessionId, List<String> interests);
}
