package com.task_360t.cubes;

import java.util.ArrayList;
import java.util.List;

import com.task_360t.cubes.exceptions.InvalidPieceException;
import com.task_360t.cubes.models.Piece;
import com.task_360t.cubes.utilities.CONSTANTS;
import com.task_360t.cubes.utilities.CubeLogger;
import com.task_360t.cubes.utilities.FileHandler;

/**
 * Load input pieces in a list
 * 
 * @author amahran
 */
public class InputsHandler {
	static CubeLogger logger = CubeLogger.getInstant();

	/**
	 * Load pieces from input file
	 * 
	 * @param inputFilePath
	 * @return
	 * @throws InvalidPieceException
	 */
	public static List<Piece> loadPieces(String inputFilePath) throws InvalidPieceException {
		List<Piece> pieces = new ArrayList<Piece>();
		String input = FileHandler.readFile(inputFilePath);
		int piecesCount = 0;
		if (input != null && !input.isEmpty()) {
			String[] lines = input.split(System.lineSeparator());
			int i = 0;
			while (i < lines.length && piecesCount < 6) {
				boolean[][] arr = new boolean[CONSTANTS.MAX_CELLS][CONSTANTS.MAX_CELLS];
				do {
					for (int j = 0; j < CONSTANTS.MAX_CELLS && j * 2 < lines[i].length(); j++)
						arr[i % CONSTANTS.MAX_CELLS][j] = lines[i].charAt(j * 2) == '[';
					++i;
				} while (i % CONSTANTS.MAX_CELLS != 0);
				pieces.add(new Piece(arr, piecesCount));
				piecesCount++;
			}
			if (pieces.size() != CONSTANTS.MAX_FACES)
				throw new InvalidPieceException("Invalid number of input pieces");
		}
		return pieces;
	}
}
