package rtcf.exception;

@SuppressWarnings("serial")
public class RequestProcessingException extends RuntimeException {
	public RequestProcessingException(String message) {
        super(message);
    }
}
