package rtcf.config;

import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserSessionConfig {
	@Bean
	public Map<String, HashSet<String>> interestsToUsers() {
		Map<String, HashSet<String>> interestsToUsers = new ConcurrentHashMap<String, HashSet<String>>();
		return interestsToUsers;
	}
}
