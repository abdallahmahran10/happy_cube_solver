package com.task_360t.cubes.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.task_360t.cubes.exceptions.InvalidPieceException;
import com.task_360t.cubes.models.Piece;

public class PieceTest {

	@Test
	public void testPieceCtor_withValidArray() throws InvalidPieceException {
		// Arrange
		Piece piece;
		boolean[][] arr = new boolean[5][5];
		// Act
		piece = new Piece(arr);
	}

	@Test(expected = InvalidPieceException.class)
	public void testPieceCtor_withInvalidArray() throws InvalidPieceException {
		// Arrange
		Piece piece;
		boolean[][] arr = new boolean[4][5];
		// Act
		piece = new Piece(arr);
	}

}
