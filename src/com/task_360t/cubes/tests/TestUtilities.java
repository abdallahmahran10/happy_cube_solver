/**
 * 
 */
package com.task_360t.cubes.tests;

import java.util.ArrayList;
import java.util.List;

import com.task_360t.cubes.InputsHandler;
import com.task_360t.cubes.exceptions.InvalidPieceException;
import com.task_360t.cubes.models.EdgeBitSet;
import com.task_360t.cubes.models.Piece;
import com.task_360t.cubes.utilities.CONSTANTS;

/**
 * Helper functions
 * @author amahran
 *
 */
public class TestUtilities {
	static List<Piece> testPieces;
	static
	{
		try {
			testPieces = InputsHandler.loadPieces(CONSTANTS.TEST_INPUT_FILES[0]);
		} catch (InvalidPieceException e) {
			e.printStackTrace();
		}
	}
	public static boolean equals(Piece p1, Piece p2) {
		return equals( p1.getTopEdge() , p2.getTopEdge()) &&
				equals( p1.getBottomEdge() , p2.getBottomEdge()) &&
				equals( p1.getRightEdge() , p2.getRightEdge()) &&
				equals( p1.getLeftEdge() , p2.getLeftEdge());
	}
	public static boolean equals(EdgeBitSet e1, EdgeBitSet e2) {
		EdgeBitSet tmp = e1.clone();
		tmp.xor(e2);
		return toLong(tmp) ==  0;
	}
	
	public static long toLong(EdgeBitSet es) {
	    long value = 0L;
	    for (int i = 0; i < es.length(); ++i) {
	      value += es.get(i) ? (1L << i) : 0L;
	    }
	    return value;
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
	 * @return test piece rotated once
	 */
	public static Piece getTestPieceRotated() {
		try {
			return new Piece(
					new boolean[][] { 	{true,  false, true, false, true }, 
						  				{true, true,  true,  true,  false },
						  				{true,  true,  true,  true,  false }, 
						  				{false, true,  true,  true,  false }, 
						  				{false,  false,  true,  true, false }} , 0);
		} catch (InvalidPieceException e) {
			return null;
		}
	}
	/**
	 * @return test piece flip once
	 */
	public static Piece getTestPieceFlip() 
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
	 * load test pieces from file
	 * @throws InvalidPieceException 
	 * 
	 */
	public static List<Piece> getTestPieces()  {
		
		return new ArrayList<>(testPieces);
	}
}
