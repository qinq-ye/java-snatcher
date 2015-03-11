package com.qinq.tools.snatcher.exception;

/**
 * 抓取异常
 * 
 * @author qinq
 * 
 */
public class SnatcherException extends RuntimeException {

	public SnatcherException() {
		super();
	}

	public SnatcherException(String message) {
		super(message);
	}

	public SnatcherException(Throwable cause) {
		super(cause);
	}

	public SnatcherException(String message, Throwable cause) {
		super(message, cause);
	}

}
