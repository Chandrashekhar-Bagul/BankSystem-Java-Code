package custom_exception;

@SuppressWarnings("serial")
public class IllegalTransferException extends RuntimeException {

	public IllegalTransferException(String message) {
		super(message);
	}

}
