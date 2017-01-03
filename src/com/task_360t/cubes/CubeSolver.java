package com.task_360t.cubes;

import java.util.List;

import com.task_360t.cubes.exceptions.InvalidPieceException;
import com.task_360t.cubes.exceptions.NoPossibleSolutionException;
import com.task_360t.cubes.models.Cube;
import com.task_360t.cubes.models.Piece;

public class CubeSolver {

	/**
	 * Arrange pieces to form a cube
	 * 
	 * @param piecesInput
	 * @return
	 * @throws NoPossibleSolutionException
	 * @throws InvalidPieceException
	 */
	public Cube createCube(List<Piece> piecesInput) throws NoPossibleSolutionException, InvalidPieceException {
		Cube cube = new Cube();
		if (piecesInput.size() != 6)
			throw new InvalidPieceException("Invalid input pieces");
		//
		while(piecesInput.size()>0) {
			int idx = cube.createNextFace(piecesInput);
			if(idx != -1)
				piecesInput.remove(idx);
		}
		//
		//if (!cube.isCubeFormed())
		//	throw new NoPossibleSolutionException("Could not form a cube using the input pieces");
		return cube;
	}
}
