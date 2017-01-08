/**
 * 
 */
package com.task_360t.cubes.tests;

import com.task_360t.cubes.exceptions.InvalidPieceException;
import com.task_360t.cubes.models.EdgeBitSet;
import com.task_360t.cubes.models.Piece;

/**
 * Helper functions
 * @author amahran
 *
 */
public class TestUtilities {
	public static boolean equals(Piece p1, Piece p2) {
		return equals( p1.getTopEdge() , p2.getTopEdge()) &&
				equals( p1.getBottomEdge() , p2.getBottomEdge()) &&
				equals( p1.getRightEdge() , p2.getRightEdge()) &&
				equals( p1.getLeftEdge() , p2.getLeftEdge());
	}
	public static boolean equals(EdgeBitSet e1, EdgeBitSet e2) {
		EdgeBitSet tmp = e1.clone();
		tmp.xor(e2);
		return tmp.equalsLong(0);
	}
	

	/**
	 * @return test piece
	 * @throws InvalidPieceException 
	 */
	public static Piece getTestPiece() 
	{
		try {
			return new Piece(
					new boolean[][] { {true,  false, false, false, false }, 
									  {false, true,  true,  true,  true },
									  {true,  true,  true,  true,  true }, 
									  {false, true,  true,  true,  false }, 
									  {true,  true,  true,  false, false }} , 0);
		} catch (InvalidPieceException e) {
			return null;
		}
	}
	/**
	 * @return test piece
	 * @throws InvalidPieceException 
	 */
	public static Piece getTestPieceFliped() 
	{
		try {
			return new Piece(
					new boolean[][] { {false, false, false, false, true }, 
									  {true,  true,  true,  true,  false },
									  {true,  true,  true,  true,  true }, 
									  {false, true,  true,  true,  false }, 
									  {false, false, true,  true,  true }} , 0);
		} catch (InvalidPieceException e) {
			return null;
		}
	}
	/**
	 * @return
	 */
	public static Piece getTestPieceRotated() {
		try {
			return new Piece(
					new boolean[][] { {true,  false, true, false, true }, 
									  {true,  true,  true, true,  false },
									  {true,  true,  true, true,  false }, 
									  {false, true,  true, true,  false }, 
									  {false, false, true, true,  false }} , 0);
		} catch (InvalidPieceException e) {
			return null;
		}
	}
}
