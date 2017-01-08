package com.task_360t.cubes;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import com.task_360t.cubes.exceptions.InvalidPieceException;
import com.task_360t.cubes.exceptions.NoPossibleSolutionException;
import com.task_360t.cubes.models.Cube;
import com.task_360t.cubes.models.Piece;
import com.task_360t.cubes.utilities.CONSTANTS;
import com.task_360t.cubes.utilities.CubeLogger;

/**
 * This class responsible of arranging the pieces in a way that can form a cube
 * 
 * @author amahran
 */
public class CubeSolver {
	static CubeLogger logger = CubeLogger.getInstant();

	/**
	 * @param pieces
	 * @return
	 */
	public List<Cube> findAllPossibleSolutions(List<Piece> pieces) throws NoPossibleSolutionException, InvalidPieceException {
		if (pieces.size() != CONSTANTS.MAX_FACES)
			throw new InvalidPieceException("Invalid input pieces");
		logger.INFO("Create a cube and try to fill with the input pieces");
		Cube solution = new Cube();
		Queue<Cube> cubes = new PriorityQueue<Cube>();
		do {
			solution = fillCubeWithPieces(solution, pieces, cubes);	
		} while (solution!=null);
		
		if (cubes.isEmpty())
			throw new NoPossibleSolutionException("Could not form a cube using the input pieces");
		logger.INFO("Cube Successfully created!");
		return null;
	}
	/**
	 * Arrange pieces to form a cube
	 * 
	 * @param piecesInput
	 * @return
	 * @throws NoPossibleSolutionException
	 * @throws InvalidPieceException
	 */
	public Cube createCube(List<Piece> piecesInput) throws NoPossibleSolutionException, InvalidPieceException {
		if (piecesInput.size() != CONSTANTS.MAX_FACES)
			throw new InvalidPieceException("Invalid input pieces");
		logger.INFO("Create a cube and try to fill with the input pieces");
		Cube solution = new Cube();
		solution = fillCubeWithPieces(solution, piecesInput, null);
		if (solution == null)
			throw new NoPossibleSolutionException("Could not form a cube using the input pieces");
		logger.INFO("Cube Successfully created!");
		return solution;
	}

	/**
	 * try to fill the rest of the missing faces in the input "cube" using the
	 * input list of pieces
	 * 
	 * @param cube
	 *            input cube with missing faces
	 * @param pieces
	 *            list to be used to fill the cube
	 * @return if success return the filled cube
	 */
	private Cube fillCubeWithPieces(Cube cube, List<Piece> pieces,Queue<Cube> cubes) {
		if (cube.isCubeFormed())
		{
			if(cubes!=null && !cubes.contains(cube))
				cubes.add(cube);
			return cube;
		}
		//
		if (pieces.size() < 1)
			return null;
		//
		for (Piece piece : pieces) {
			// try to fill the current cube the pieces list
			Cube sol = fillNextCubeFace(cube, piece, pieces,cubes);
			if (sol != null)
				return sol;
		}

		return null;
	}

	/**
	 * try to fill the next empty face in the cube with the input piece, if
	 * success remove this piece from the list and fill the cube with the rest
	 * of the list by recursively call fillCubeWithPieces and so on
	 * 
	 * @param cube
	 *            the current working cube
	 * @param piece
	 *            piece to fill the next empty face
	 * @param pieces
	 *            the rest of the pieces that did not assigned to one of the
	 *            faces in the cube
	 * @param cubes 
	 * @return if success return the filled cube
	 */
	private Cube fillNextCubeFace(Cube cube, Piece piece, List<Piece> pieces, Queue<Cube> cubes) {
		for (int i = 0; i < 8; ++i) {
			if (i == 4)
				piece.flipPiece();
			// logger.INFO(System.lineSeparator() + cube.toString());
			if (cube.isPieceMatch(piece)) {
				Cube tmpCube = new Cube(cube);
				tmpCube.setNextFace(piece);
				// Complete the rest of the cube
				List<Piece> tmpList = new ArrayList<Piece>(pieces);
				tmpList.remove(piece);
//				if(cubes!=null && cube.isCubeFormed())
//					cubes.add(cube);
				Cube sol = fillCubeWithPieces(tmpCube, tmpList, cubes);
				if (sol != null)
					return sol;
			}
			piece.rotateClockWise();
		}
		piece.flipPiece();
		return null;
	}

	/**
	 * @param pieces
	 * @return
	 * @throws CloneNotSupportedException 
	 */
	private List<Piece> cloneArray(List<Piece> pieces)  {
	    List<Piece> clone = new ArrayList<Piece>(pieces.size());
	    for (Piece item : pieces) 
	    	clone.add((Piece) item.clone());
	    return clone;
	}
}
