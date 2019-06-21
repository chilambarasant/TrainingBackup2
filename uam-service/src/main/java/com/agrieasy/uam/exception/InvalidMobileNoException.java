package com.agrieasy.uam.exception;

public class InvalidMobileNoException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidMobileNoException() {
		super();
	}
	public InvalidMobileNoException(final String msg) {
		super(msg);
	}
}
