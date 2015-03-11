package com.qinq.tools.snatcher.exception;

/**
 * HTTP 请求异常
 * 
 * @author qinq
 * 
 */
public class HttpConnectionException extends SnatcherException {

	public HttpConnectionException() {
		super();
	}

	public HttpConnectionException(String message) {
		super(message);
	}

	public HttpConnectionException(Throwable cause) {
		super(cause);
	}

	public HttpConnectionException(String message, Throwable cause) {
		super(message, cause);
	}

}
