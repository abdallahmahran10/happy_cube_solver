package com.task_360t.cubes.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.task_360t.cubes.models.Cube;
import com.task_360t.cubes.models.Piece;

/**
 * Test cases of the {@link Cube} class
 * 
 * @author amahran
 *
 */
public class CubeTest {

	@Test
	public void getNumberOfFilledFacesTest_addOnePieceToCube_firstPieceShouldMatche() {
		// Arrange
		Cube cube = new Cube();
		Piece piece = TestUtilities.getTestPieces().get(0);

		// Act
		if (cube.isPieceMatch(piece))
			cube.setNextFace(piece);

		// Assert
		assertTrue(cube.getNumberOfFilledFaces() == 1);
	}

	@Test
	public void getNumberOfFilledFacesTest_addTwoPieceToCube_secondPieceIsNotValid() {
		// Arrange
		Cube cube = new Cube();
		List<Piece> pieces = TestUtilities.getTestPieces();

		// Act
		cube.setNextFace(pieces.get(0));
		if (cube.isPieceMatch(pieces.get(1)))
			cube.setNextFace(pieces.get(1));

		// Assert
		assertTrue(cube.getNumberOfFilledFaces() == 2);
	}

	@Test
	public void getNumberOfFilledFacesTest_addTwoPieceToCube_secondPieceIsValid() {
		// Arrange
		Cube cube = new Cube();
		List<Piece> pieces = TestUtilities.getTestPieces();

		// Act
		cube.setNextFace(pieces.get(0));
		if (cube.isPieceMatch(pieces.get(3)))
			cube.setNextFace(pieces.get(3));

		// Assert
		assertTrue(cube.getNumberOfFilledFaces() == 2);
	}

	@Test
	public void matchesTest_compareTwoSimularCubes() {
		// Arrange
		Cube cube1 = new Cube();
		Cube cube2 = new Cube();
		List<Piece> pieces = TestUtilities.getTestPieces();

		// Act
		cube1.setNextFace(pieces.get(0));
		cube2.setNextFace(pieces.get(0));

		// Assert
		assertTrue(cube1.matches(cube2));
	}

	@Test
	public void matchesTest_compareTwoDifferentCubes() {
		// Arrange
		Cube cube1 = new Cube();
		Cube cube2 = new Cube();
		List<Piece> pieces = TestUtilities.getTestPieces();

		// Act
		cube1.setNextFace(pieces.get(0));
		cube2.setNextFace(pieces.get(1));

		// Assert
		assertFalse(cube1.matches(cube2));
	}

}
