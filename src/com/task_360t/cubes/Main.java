
package com.task_360t.cubes;

import com.task_360t.cubes.utilities.CONSTANTS;
import com.task_360t.cubes.utilities.CubeLogger;
import com.task_360t.cubes.utilities.FileHandler;

import com.task_360t.cubes.exceptions.InvalidPieceException;
import com.task_360t.cubes.exceptions.NoPossibleSolutionException;
import com.task_360t.cubes.models.Cube;
import com.task_360t.cubes.models.Piece;

/**
 * @author amahran
 * Main function
 */
public class Main {
	static CubeLogger logger = CubeLogger.getInstant();
	public static void main(String[] args) throws InvalidPieceException {
		logger.INFO("Starting...");
		CubeSolver solver = new CubeSolver();
		try {
			Cube cube = solver.createCube(Input.PiecesInputs);
			FileHandler.dumpCube(cube);
		} catch (NoPossibleSolutionException | InvalidPieceException e) {
			logger.ERROR(e);
		}
		
		logger.INFO("Done...");
	}

}
