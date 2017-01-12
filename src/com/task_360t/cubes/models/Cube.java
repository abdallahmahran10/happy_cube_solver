package com.task_360t.cubes.models;

import com.task_360t.cubes.utilities.CONSTANTS;
import com.task_360t.cubes.utilities.CubeLogger;

/**
 * type represents a cube
 * 
 * @author amahran
 */
public class Cube {
	CubeLogger logger = CubeLogger.getInstant();
	private Piece[] faces;
	private int currentFaceIdx;

	/**
	 * Default ctor, initialize the faces array
	 */
	public Cube() {
		faces = new Piece[CONSTANTS.MAX_FACES];
		for (int i = 0; i < CONSTANTS.MAX_FACES; ++i)
			faces[i] = new Piece();
		currentFaceIdx = 0;
	}

	/**
	 * Copy constructor
	 * 
	 * @param cube
	 *            object to be copied to this instant
	 */
	public Cube(Cube cube) {
		this.faces = new Piece[CONSTANTS.MAX_FACES];
		System.arraycopy(cube.faces, 0, this.faces, 0, cube.faces.length);
		this.currentFaceIdx = cube.currentFaceIdx;
	}

	/**
	 * Check if the input piece can be set to the current empty face by checking
	 * the compatibility of the piece with the previous faces
	 * 
	 * @param piece
	 *            input piece
	 * @return true if the piece can be set to the current face, false otherwise
	 */
	public boolean isPieceMatch(Piece piece) {
		switch (currentFaceIdx) {
		case 0:
			return true;
		case 1:
			return faces[0].getRightEdge().matches(piece.getLeftEdge());

		case 2:
			return faces[1].getRightEdge().matches(piece.getLeftEdge());

		case 3:
			return faces[0].getBottomEdge().matches(piece.getLeftEdge())
					&& faces[1].getBottomEdge().matches(piece.getTopEdge())
					&& faces[2].getBottomEdge().matches(piece.getRightEdge())
					&& matches(faces[0].getCornerBit(CornersBit.BR), faces[1].getCornerBit(CornersBit.BL),
							piece.getCornerBit(CornersBit.TL))
					&& matches(faces[2].getCornerBit(CornersBit.BL), faces[1].getCornerBit(CornersBit.BR),
							piece.getCornerBit(CornersBit.TR));

		case 4:
			return faces[0].getLeftEdge().matches(piece.getLeftEdge())
					&& faces[2].getRightEdge().matches(piece.getRightEdge())
					&& faces[3].getBottomEdge().matches(piece.getTopEdge())
					&& matches(faces[0].getCornerBit(CornersBit.BL), faces[3].getCornerBit(CornersBit.BL),
							piece.getCornerBit(CornersBit.TL))
					&& matches(faces[2].getCornerBit(CornersBit.BR), faces[3].getCornerBit(CornersBit.BR),
							piece.getCornerBit(CornersBit.TR));

		case 5:
			return faces[0].getTopEdge().matches(piece.getLeftEdge())
					&& faces[2].getTopEdge().matches(piece.getRightEdge())
					&& faces[1].getTopEdge().matches(piece.getBottomEdge())
					&& matches(faces[0].getCornerBit(CornersBit.TL), faces[4].getCornerBit(CornersBit.BL),
							piece.getCornerBit(CornersBit.TL))
					&& matches(faces[4].getCornerBit(CornersBit.BR), faces[2].getCornerBit(CornersBit.TR),
							piece.getCornerBit(CornersBit.TR))
					&& matches(faces[0].getCornerBit(CornersBit.TR), faces[1].getCornerBit(CornersBit.TL),
							piece.getCornerBit(CornersBit.BL))
					&& matches(faces[1].getCornerBit(CornersBit.TR), faces[2].getCornerBit(CornersBit.TL),
							piece.getCornerBit(CornersBit.BR));
		default:
			break;
		}
		return false;
	}

	/**
	 * check if corners matches, only one of the pieces corners should be set
	 * 
	 * @param cornerCell1
	 *            corner cell of piece 1
	 * @param cornerCell3
	 *            corner cell of piece 2
	 * @param cornerCell3
	 *            corner cell of piece 3
	 * @return true if they matched, false otherwise
	 */
	private boolean matches(boolean cornerCell1, boolean cornerCell2, boolean cornerCell3) {
		return (cornerCell1 && !cornerCell3 && !cornerCell3) || (!cornerCell1 && cornerCell3 && !cornerCell3)
				|| (!cornerCell1 && !cornerCell3 && cornerCell3);
	}

	/**
	 * Set the current empty face with the input piece and advances the
	 * currentFaceIdx pointer
	 * 
	 * NOTE: this Method should not be called unless the piece matches
	 * 
	 * @param piece
	 */
	public void setNextFace(Piece piece) {
		faces[currentFaceIdx] = piece;
		++currentFaceIdx;
	}

	/**
	 * check if the cube filled
	 * 
	 * @return true if cube filled, false otherwise
	 */
	public boolean isCubeFormed() {
		return currentFaceIdx == CONSTANTS.MAX_FACES;
	}

	/**
	 * override to Object toStrnig function to return the string representation
	 * of a cube
	 */
	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < currentFaceIdx; i++)
			buff.append(String.valueOf(faces[i].getPieceId())+System.lineSeparator() +  faces[i].toString() );
		return buff.toString();
	}
	
	public String printUnfolded() {
		if(!this.isCubeFormed())
			return "";
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < CONSTANTS.MAX_CELLS; i++)
			buff.append(faces[0].getRowStr(i) + faces[1].getRowStr(i) + faces[2].getRowStr(i) + System.lineSeparator());
		for(int i=3; i<CONSTANTS.MAX_FACES; i++)
		{
			buff.append(faces[i].toString(CONSTANTS.ENDENT) );
		}
		return buff.toString();
	}

	/**
	 * 
	 */
	public void removeLastEntry() {
		if(currentFaceIdx<1)
			return;
		currentFaceIdx--;
		faces[currentFaceIdx] = null;
	}

	/**
	 * Compare two cubes and return true if similar
	 * @param cube
	 * @return
	 */
	public boolean matches(Cube cube) {
		if( this.currentFaceIdx != cube.currentFaceIdx)
			return false;
		
		for(int i=0; i<currentFaceIdx; i++)
		{
			if(this.faces[i].getPieceId() != cube.faces[i].getPieceId())
				return false;
			i++;
		}
		return true;
	}

	/**
	 * Get number of faces filled in the cube
	 * @return number of faces filled
	 */
	public int getNumberOfFilledFaces() {
		return currentFaceIdx;
	}

}
