
package com.task_360t.cubes;

import java.io.File;
import java.util.List;

import com.task_360t.cubes.exceptions.InvalidPieceException;
import com.task_360t.cubes.exceptions.NoPossibleSolutionException;
import com.task_360t.cubes.models.Cube;
import com.task_360t.cubes.models.Piece;
import com.task_360t.cubes.utilities.CONSTANTS;
import com.task_360t.cubes.utilities.CubeLogger;
import com.task_360t.cubes.utilities.FileHandler;

/**
 * Program entry point
 * 
 * @author amahran
 */
public class Main {
	static CubeLogger logger = CubeLogger.getInstant();

	public static void main(String[] args) {
		if (args.length != CONSTANTS.PROGRAM_ARGUMENTS_COUNT) {
			System.out.println(CONSTANTS.PROGRAM_HELP);
			return;
		}
		logger.INFO("Starting...");
		String solvingStrategy = args[0];
		String inputFilePath = args[1];
		if (!fileExists(inputFilePath)) {
			logger.ERROR("Input file does not exist");
			return;
		}
		try {
			if (solvingStrategy.equals(CONSTANTS.FIND_ALL_SOLUTION_STRATEGY))
				findAllPossibleSolutions(InputsHandler.loadPieces(inputFilePath));
			else if (solvingStrategy.equals(CONSTANTS.FIND_ONE_SOLUTION_STRATEGY))
				createCube(InputsHandler.loadPieces(inputFilePath));
		} catch (InvalidPieceException e) {
			logger.ERROR(e);
		}
		logger.INFO("Done...");
	}

	/**
	 * @param inputFilePath
	 * @return
	 */
	private static boolean fileExists(String filePath) {
		File f = new File(filePath);
		return f.exists() && !f.isDirectory();
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
			FileHandler.dumpCubes(cubes);
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
