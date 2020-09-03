package rtcf.application;

import rtcf.model.ApplicationEventRequest;
import rtcf.model.ApplicationEventResponse;

public interface ApplicationEventHandler {

	@SuppressWarnings("rawtypes")
	public ApplicationEventResponse processAndTransformEvent(ApplicationEventRequest eventRequest) throws Exception;

}
