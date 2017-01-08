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
public class Input {
	static CubeLogger logger = CubeLogger.getInstant();
	static List<Piece> PiecesInputs;
	static {
		PiecesInputs = new ArrayList<Piece>();
		String input = FileHandler.readFile(CONSTANTS.INPUT_FILE_NAME2);

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
			/*
			 * boolean on = true; boolean of = false; PiecesInputs.add(new
			 * Piece(new boolean[][]{ {of, of, on, of, of}, {of, on, on, on,
			 * of}, {on, on, on, on, on}, {of, on, on, on, of}, {of, of, on, of,
			 * of}, })); PiecesInputs.add(new Piece(new boolean[][]{ {on, of,
			 * on, of, on}, {on, on, on, on, on}, {of, on, on, on, of}, {on, on,
			 * on, on, on}, {on, of, on, of, on}, })); PiecesInputs.add(new
			 * Piece(new boolean[][]{ {of, of, on, of, of }, {of, on, on, on, on
			 * }, {on, on, on, on, of }, {of, on, on, on, on }, {of, of, on, of,
			 * of }, })); PiecesInputs.add( new Piece(new boolean[][]{ {of, on,
			 * of, on, of }, {on, on, on, on, of }, {of, on, on, on, on }, {on,
			 * on, on, on, of }, {on, on, of, on, of }, })); PiecesInputs.add(
			 * new Piece(new boolean[][]{ {of, on, of, on, of }, {on, on, on,
			 * on, on }, {of, on, on, on, of }, {on, on, on, on, on }, {on, of,
			 * on, of, of }, })); PiecesInputs.add(new Piece(new boolean[][]{
			 * {of, on, of, on, of }, {of, on, on, on, on }, {on, on, on, on, of
			 * }, {of, on, on, on, on }, {on, on, of, on, on }, }));
			 */
		} catch (InvalidPieceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
