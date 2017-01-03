package com.task_360t.cubes;

import java.util.ArrayList;
import java.util.List;

import com.task_360t.cubes.exceptions.InvalidPieceException;
import com.task_360t.cubes.models.Piece;
import com.task_360t.cubes.utilities.CONSTANTS;
import com.task_360t.cubes.utilities.CubeLogger;


/**
 * @author amahran
 *	Generate inputs 
 *
 */

public class Input {
	static CubeLogger logger = CubeLogger.getInstant();
	static List<Piece> PiecesInputs;
	static
	{
			PiecesInputs = new ArrayList<Piece>();
			boolean on = CONSTANTS.ON;
			boolean of = CONSTANTS.OFF;
			try {
				PiecesInputs.add(new Piece(new boolean[][]{
					{of, of, on, of, of}, 
					{of, on, on, on, of},
					{on, on, on, on, on},
					{of, on, on, on, of},
					{of, of, on, of, of},
					}));
				PiecesInputs.add(new Piece(new boolean[][]{
					{on, of, on, of, on}, 
					{on, on, on, on, on},
					{of, on, on, on, of},
					{on, on, on, on, on},
					{on, of, on, of, on},
					}));
				PiecesInputs.add(new Piece(new boolean[][]{
					{of, of, on, of, of },
					{of, on, on, on, on },
					{on, on, on, on, of },
					{of, on, on, on, on },
					{of, of, on, of, of },
					}));
				PiecesInputs.add( new Piece(new boolean[][]{
					{of, on, of, on, of },
					{on, on, on, on, of },
					{of, on, on, on, on },
					{on, on, on, on, of },
					{on, on, of, on, of },
					}));
				PiecesInputs.add( new Piece(new boolean[][]{
					{of, on, of, on, of },
					{on, on, on, on, on },
					{of, on, on, on, of },
					{on, on, on, on, on },
					{on, of, on, of, of },
					}));
				PiecesInputs.add(new Piece(new boolean[][]{
					{of, on, of, on, of },
					{of, on, on, on, on },
					{on, on, on, on, of },
					{of, on, on, on, on },
					{on, on, of, on, on },
					}));
			} catch (InvalidPieceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
