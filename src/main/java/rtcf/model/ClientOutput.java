package rtcf.model;

import java.util.List;

public class ClientOutput<T> {
	List<String> interests;
	T content;

	public List<String> getInterests() {
		return interests;
	}

	public void setInterests(List<String> interests) {
		this.interests = interests;
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}
}
