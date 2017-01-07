package com.task_360t.cubes;

import java.util.ArrayList;
import java.util.List;
import com.task_360t.cubes.exceptions.InvalidPieceException;
import com.task_360t.cubes.exceptions.NoPossibleSolutionException;
import com.task_360t.cubes.models.Cube;
import com.task_360t.cubes.models.Piece;
import com.task_360t.cubes.utilities.CubeLogger;

public class CubeSolver {
	static CubeLogger logger = CubeLogger.getInstant();
	/**
	 * Arrange pieces to form a cube
	 * 
	 * @param piecesInput
	 * @return
	 * @throws NoPossibleSolutionException
	 * @throws InvalidPieceException
	 */
	public Cube createCube(List<Piece> piecesInput) throws NoPossibleSolutionException, InvalidPieceException {
		if (piecesInput.size() != 6)
			throw new InvalidPieceException("Invalid input pieces");
		piecesInput.get(1).rotateClockWise();
		Cube solution = new Cube();
		solution = fillCubeWithPieces(solution, piecesInput);
		if (solution == null)
			throw new NoPossibleSolutionException("Could not form a cube using the input pieces");
		return solution;
	}
	//
	private Cube fillCubeWithPieces(Cube cube, List<Piece> pieces) {
		if(cube.isCubeFormed())
			return cube;
		//
		if(pieces.size()<1)
			return null;
		//
		for(Piece piece:pieces)
		{
			// try to fill the current cube the pieces list
			Cube sol = fillNextCubeFace(cube, piece, pieces);
			if(sol != null)
				return sol;
		}
		
		return null;
	}
	//
	private Cube fillNextCubeFace(Cube cube, Piece piece, List<Piece> pieces) {
		for(int i=0; i<8; ++i)
		{
			if(i ==4)
				piece.flipPiece();
			//logger.INFO(System.lineSeparator() + cube.toString());
			if(cube.isPieceMatch(piece))
			{
				Cube tmpCube = new Cube(cube);
				tmpCube.setNextFace(piece);
				// Complete the rest of the cube
				List<Piece> tmpList = new ArrayList<Piece>(pieces);
				tmpList.remove(piece);
				Cube sol = fillCubeWithPieces(tmpCube, tmpList);
				if(sol!=null)
					return sol;
			}
			piece.rotateClockWise();
		}
		piece.flipPiece();
		return null;
	}
}
