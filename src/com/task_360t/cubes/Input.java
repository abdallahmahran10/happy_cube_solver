package com.task_360t.cubes;

import java.util.ArrayList;
import java.util.List;

import com.task_360t.cubes.exceptions.InvalidPieceException;
import com.task_360t.cubes.models.Piece;
import com.task_360t.cubes.utilities.CONSTANTS;
import com.task_360t.cubes.utilities.CubeLogger;
import com.task_360t.cubes.utilities.FileHandler;


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
			String input = FileHandler.readFile(CONSTANTS.INPUT_FILE_NAME);

			try {
				if(input!=null && !input.isEmpty())
				{
					String[] lines = input.split(System.lineSeparator());
					int i=0;
					int piecesCount = 0 ;
					while(i < lines.length && piecesCount< 6)
					{
						boolean[][] arr = new boolean[5][5];
						do
						{
							for(int j=0; j<5; j++)
								arr[i%5][j] = lines[i].charAt(j*2) == '[';
							++i;
						}while(i%5!=0);
						PiecesInputs.add(new Piece(arr));
						piecesCount++;
					}
				}
			/*
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
					*/
			} catch (InvalidPieceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
