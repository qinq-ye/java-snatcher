package com.qinq.tools.snatcher.exception;

public class HttpTimeoutConnection extends SnatcherException {

	public HttpTimeoutConnection() {
		super();
	}

	public HttpTimeoutConnection(String message) {
		super(message);
	}

	public HttpTimeoutConnection(Throwable cause) {
		super(cause);
	}

	public HttpTimeoutConnection(String message, Throwable cause) {
		super(message, cause);
	}

}
