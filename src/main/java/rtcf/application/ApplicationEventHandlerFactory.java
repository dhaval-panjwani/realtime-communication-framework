package rtcf.application;

import rtcf.model.ApplicationEventRequest;

public abstract class ApplicationEventHandlerFactory {
	public abstract ApplicationEventHandler createEventHandler(ApplicationEventRequest eventRequest);
}
