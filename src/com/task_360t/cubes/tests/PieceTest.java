package com.task_360t.cubes.tests;

import static org.junit.Assert.assertTrue;

import java.util.BitSet;

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
		piece = new Piece(arr, 0);
	}

	@Test(expected = InvalidPieceException.class)
	public void testPieceCtor_withInvalidArray() throws InvalidPieceException {
		// Arrange
		Piece piece;
		boolean[][] arr = new boolean[4][5];
		// Act
		piece = new Piece(arr, 0);
	}

	
	@Test
	public void testTopEdgeCreation() {
		// Arrange
		Piece piece = TestUtilities.getTestPiece();
		// Act
		BitSet bs = piece.getTopEdge();
		// Assert
		assertTrue( bs.get(0) && !bs.get(1) && !bs.get(2) && !bs.get(3) && !bs.get(4));
	}
	
	@Test
	public void testBottomEdgeCreation() {
		// Arrange
		Piece piece = TestUtilities.getTestPiece();
		// Act
		BitSet bs = piece.getBottomEdge();
		// Assert
		assertTrue(bs.get(0) && bs.get(1) && bs.get(2) && !bs.get(3) && !bs.get(4));
	}

	@Test
	public void testRightEdgeCreation() {
		// Arrange
		Piece piece = TestUtilities.getTestPiece();
		// Act
		BitSet bs = piece.getRightEdge();
		// Assert
		assertTrue(! bs.get(0) && bs.get(1) && bs.get(2) && !bs.get(3) && !bs.get(4));
	}

	@Test
	public void testLeftEdgeCreation() {
		// Arrange
		Piece piece = TestUtilities.getTestPiece();
		// Act
		BitSet bs = piece.getLeftEdge();
		
		// Assert
		assertTrue(bs.get(0) && !bs.get(1) && bs.get(2) && !bs.get(3) && bs.get(4));
	}

	@Test
	public void testFlipPieceMethod_DoOneFlip() {
		// Arrange
		Piece piece = TestUtilities.getTestPiece();
		Piece pieceFliped = TestUtilities.getTestPieceFliped();
		// Act
		piece.flipPiece();
		// Assert
		assertTrue(TestUtilities.equals(piece, pieceFliped));
	}
	
	@Test
	public void testFlipPieceMethod_DoTwoFlip() {
		// Arrange
		Piece piece = TestUtilities.getTestPiece();
		Piece expected = TestUtilities.getTestPiece();
		// Act
		piece.flipPiece();
		piece.flipPiece();
		// Assert
		assertTrue(TestUtilities.equals(piece, expected));
	}

	@Test
	public void testRotation_DoOneRotation() {
		// Arrange
		Piece piece = TestUtilities.getTestPiece();
		Piece expected = TestUtilities.getTestPieceRotated();
		// Act
		piece.rotateClockWise();
		// Assert
		assertTrue(TestUtilities.equals(piece, expected));
	}
	@Test
	public void testRotation_DoFourRotation() {
		// Arrange
		Piece piece = TestUtilities.getTestPiece();
		Piece expected = TestUtilities.getTestPiece();
		// Act
		piece.rotateClockWise();
		piece.rotateClockWise();
		piece.rotateClockWise();
		piece.rotateClockWise();
		// Assert
		assertTrue(TestUtilities.equals(piece, expected));
	}


}
