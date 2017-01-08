
package com.task_360t.cubes;

import java.util.List;

import com.task_360t.cubes.exceptions.InvalidPieceException;
import com.task_360t.cubes.exceptions.NoPossibleSolutionException;
import com.task_360t.cubes.models.Cube;
import com.task_360t.cubes.models.Piece;
import com.task_360t.cubes.utilities.CubeLogger;
import com.task_360t.cubes.utilities.FileHandler;

/**
 * Program entry point
 * 
 * @author amahran
 */
public class Main {
	static CubeLogger logger = CubeLogger.getInstant();

	public static void main(String[] args) throws InvalidPieceException {
		logger.INFO("Starting...");
		//createCube(InputsHandler.PiecesInputs);
		findAllPossibleSolutions(InputsHandler.PiecesInputs);
		logger.INFO("Done...");
	}

	/**
	 * find all possible solutions to form a cube using the input pieces
	 * 
	 * @param piecesInputs
	 *            input pieces
	 */
	private static void findAllPossibleSolutions(List<Piece> pieces) {
		CubeSolver solver = new CubeSolver();
		try {
			List<Cube> cubes = solver.findAllPossibleSolutions(pieces);
//			if (cube.isCubeFormed())
//				FileHandler.dumpCube(cube);
		} catch (NoPossibleSolutionException e) {
			logger.ERROR(e);
			logger.INFO(
					"One or more of the input pieces are not correct, make sure you input the right pieces to construct a cube");
		} catch (InvalidPieceException e) {
			logger.ERROR(e);
			logger.INFO("Kindly insert the correct number of pieces");
		}
	}

	/**
	 * try to find one solution to form a cube using the input pieces
	 * 
	 * @param pieces
	 *            input pieces
	 */
	private static void createCube(List<Piece> pieces) {

		CubeSolver solver = new CubeSolver();
		try {
			Cube cube = solver.createCube(pieces);
			if (cube.isCubeFormed())
				FileHandler.dumpCube(cube);
		} catch (NoPossibleSolutionException e) {
			logger.ERROR(e);
			logger.INFO(
					"One or more of the input pieces are not correct, make sure you input the right pieces to construct a cube");
		} catch (InvalidPieceException e) {
			logger.ERROR(e);
			logger.INFO("Kindly insert correct number of pieces");
		}
	}

}
