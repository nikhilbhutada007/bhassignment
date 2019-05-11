package com.blueharvest.assignment.exception;

/**
 * Exception to denote that particular resource is not present in the database.
 * 
 * @author nbhutada
 *
 */
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3539866696123235770L;

	public ResourceNotFoundException(String msg) {
		super(msg);
	}

}
