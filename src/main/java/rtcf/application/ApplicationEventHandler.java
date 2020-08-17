package rtcf.application;

import rtcf.model.ClientOutput;

public interface ApplicationEventHandler {

	public ClientOutput processAndTransformEvent(String message);

}
