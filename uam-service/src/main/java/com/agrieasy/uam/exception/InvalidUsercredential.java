package com.agrieasy.uam.exception;

public class InvalidUsercredential extends Exception{
	private static final long serialVersionUID = 1L;

	public InvalidUsercredential() {
		super();
	}
	public InvalidUsercredential(final String msg) {
		super(msg);
	}
}
