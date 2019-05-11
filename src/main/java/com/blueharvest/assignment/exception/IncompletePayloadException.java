package com.blueharvest.assignment.exception;

/**
 * Exception to denote invalid or non-existent fields in payload
 * 
 * @author nbhutada
 *
 */
public class IncompletePayloadException extends RuntimeException {

	private static final long serialVersionUID = 5816289926089218573L;

	public IncompletePayloadException(String msg) {
		super(msg);
	}

}
