package rtcf.model;

import java.util.List;

public class ApplicationEventResponse<E> {
	List<String> interests;
	E content;

	public List<String> getInterests() {
		return interests;
	}

	public void setInterests(List<String> interests) {
		this.interests = interests;
	}

	public E getContent() {
		return content;
	}

	public void setContent(E content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "ApplicationEventResponse [interests=" + interests + ", content=" + content + "]";
	}

}
