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
	static List<Piece> PiecesInputs;
	static {
		PiecesInputs = new ArrayList<Piece>();
		String input = FileHandler.readFile(CONSTANTS.INPUT_FILES[2]);

		try {
			if (input != null && !input.isEmpty()) {
				String[] lines = input.split(System.lineSeparator());
				int i = 0;
				int piecesCount = 0;
				while (i < lines.length && piecesCount < 6) {
					boolean[][] arr = new boolean[CONSTANTS.MAX_CELLS][CONSTANTS.MAX_CELLS];
					do {
						for (int j = 0; j < CONSTANTS.MAX_CELLS && j * 2 < lines[i].length(); j++)
							arr[i % CONSTANTS.MAX_CELLS][j] = lines[i].charAt(j * 2) == '[';
						++i;
					} while (i % CONSTANTS.MAX_CELLS != 0);
					PiecesInputs.add(new Piece(arr, piecesCount));
					piecesCount++;
				}
			}
		} catch (InvalidPieceException e) {
			logger.ERROR(e);
		}
	}
}
