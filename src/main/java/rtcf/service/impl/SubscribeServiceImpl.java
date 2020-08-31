package rtcf.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rtcf.service.SubscribeService;

@Service
public final class SubscribeServiceImpl implements SubscribeService {

	@Autowired
	Map<String, HashSet<String>> interestsToUsers;

	@Override
	public void subscribeUserInterests(String userSessionId, List<String> interests) {
		
		for(String interest : interests) {
			if(interestsToUsers.containsKey(interest)) {
				interestsToUsers.get(interest).add(userSessionId);
			} else {
				HashSet<String> newInterestSet = new HashSet<>();
				newInterestSet.add(userSessionId);
				interestsToUsers.put(interest, newInterestSet);
			}
		}
	}
}
