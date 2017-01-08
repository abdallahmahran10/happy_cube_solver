package com.task_360t.cubes.exceptions;

/**
 * exception thrown in case of trying to initialize a piece with invalid array
 * 
 * @author amahran
 */
public class InvalidPieceException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidPieceException(String message) {
		super(message);
	}
}
