package com.task_360t.cubes.exceptions;

/**
 * exception thrown in case of failing to create a cube with a certain pieces
 * array
 * 
 * @author amahran
 */
public class NoPossibleSolutionException extends Exception {
	private static final long serialVersionUID = 1541669279382238627L;

	public NoPossibleSolutionException(String message) {
		super(message);
	}
}
