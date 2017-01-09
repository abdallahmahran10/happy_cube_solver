package com.task_360t.cubes;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.task_360t.cubes.exceptions.InvalidPieceException;
import com.task_360t.cubes.exceptions.NoPossibleSolutionException;
import com.task_360t.cubes.models.Cube;
import com.task_360t.cubes.models.Piece;
import com.task_360t.cubes.models.SolutionPath;
import com.task_360t.cubes.utilities.CONSTANTS;
import com.task_360t.cubes.utilities.CubeLogger;

/**
 * This class responsible of arranging the pieces in a way that can form a cube
 * 
 * @author amahran
 */
public class CubeSolver {
	static CubeLogger logger = CubeLogger.getInstant();
	private Queue<SolutionPath> SolutionPaths = new ConcurrentLinkedQueue<SolutionPath>();
	Queue<Cube> cubesList = new ConcurrentLinkedQueue<Cube>();
	private boolean continueSearch;

	/**
	 * @param pieces
	 * @return
	 */
	public List<Cube> findAllPossibleSolutions(List<Piece> pieces)
			throws NoPossibleSolutionException, InvalidPieceException {
		if (pieces.size() != CONSTANTS.MAX_FACES)
			throw new InvalidPieceException("Invalid input pieces");
		logger.INFO("Create a cube and try to fill with the input pieces");
		// prepare members before start searching for solutions
		SolutionPaths.clear();
		SolutionPath path = new SolutionPath();
		SolutionPaths.add(path);
		continueSearch = true;
		//
		Cube solution = new Cube();
		fillCubeWithPieces(solution, pieces);
		if (cubesList.isEmpty())
			throw new NoPossibleSolutionException("No possible solution found");
		return new ArrayList(cubesList);
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
		continueSearch = false;
		Cube solution = new Cube();
		solution = fillCubeWithPieces(solution, piecesInput);
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
	private Cube fillCubeWithPieces(Cube cube, List<Piece> pieces) {
		if (cube.isCubeFormed())
			return cube;
		//
		if (pieces.size() < 1)
			return null;
		//
		for (int i = 0; i < pieces.size(); i++) {
			Piece piece = pieces.get(i);
			// try to fill the current cube the pieces list
			Cube sol = fillNextCubeFace(cube, piece, pieces);
			if (sol != null) {
				if(!continueSearch)
					return sol;					
				if (sol.isCubeFormed()) {
					if (cubesList.peek() == null || !addedBefore(sol)) {
						cubesList.add(new Cube(sol));
					}
				} 
				//return sol;
			}
		}
		return null;
	}

	/**
	 * @param sol
	 * @return
	 */
	private boolean addedBefore(Cube c) {
		for(Cube cube : cubesList)
			if(cube.matches(c))
				return true;
		return false;
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
	private Cube fillNextCubeFace(Cube cube, Piece piece, List<Piece> pieces) {
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
				Cube sol = fillCubeWithPieces(tmpCube, tmpList);
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
	private List<Piece> cloneArray(List<Piece> pieces) {
		List<Piece> clone = new ArrayList<Piece>(pieces.size());
		for (Piece item : pieces)
			clone.add((Piece) item.clone());
		return clone;
	}
}
