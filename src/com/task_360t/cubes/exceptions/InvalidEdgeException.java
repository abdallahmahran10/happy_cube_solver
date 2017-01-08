package com.task_360t.cubes.exceptions;

/**
 * exception thrown in case of the client tried to access invalid edge in the
 * piece
 * 
 * @author amahran
 */
public class InvalidEdgeException extends Exception {

	private static final long serialVersionUID = 5850771933339970887L;

	public InvalidEdgeException(String message) {
		super(message);
	}
}
