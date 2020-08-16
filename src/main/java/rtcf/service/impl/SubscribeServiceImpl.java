package rtcf.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rtcf.service.SubscribeService;

@Service
public class SubscribeServiceImpl implements SubscribeService {

	@Autowired
	Map<String, HashSet<String>> userTopicOfInterests;

	@Override
	public void subscribeUserInterests(String userSessionId, List<String> interests) {
		if (userTopicOfInterests.containsKey(userSessionId)) {
			userTopicOfInterests.put(userSessionId, new HashSet<>());
		}
		userTopicOfInterests.put(userSessionId, new HashSet<>(interests));
	}
}
