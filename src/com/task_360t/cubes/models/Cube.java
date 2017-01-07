package com.task_360t.cubes.models;

import com.task_360t.cubes.utilities.CONSTANTS;
import com.task_360t.cubes.utilities.CubeLogger;

public class Cube {
	CubeLogger logger = CubeLogger.getInstant();
	public Piece[] faces;
	private int currentFaceIdx;

	public Cube() {
		faces = new Piece[6];
		for (int i = 0; i < 6; ++i)
			faces[i] = new Piece();
		currentFaceIdx = 0;
	}

	public Cube(Cube cube) {
		this.faces = new Piece[6];
		System.arraycopy(cube.faces, 0, this.faces, 0, cube.faces.length);
		this.currentFaceIdx = cube.currentFaceIdx;
	}

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

//	private boolean matches(BitSet currentEdge, BitSet interstingEdge) {
//		// TODO Auto-generated method stub
//		BitSet set = (BitSet) currentEdge.clone();
//		set.or(interstingEdge);
//		return set.get(1) && set.get(2) && set.get(3);
//	}

	private boolean matches(boolean a, boolean b, boolean c) {
		return (a && !b && !c) || (!a && b && !c) || (!a && !b && c);
	}

	public void setNextFace(Piece piece) {
		faces[currentFaceIdx] = piece;
		++currentFaceIdx;
	}

	public boolean isCubeFormed() {
		return currentFaceIdx == 6;
	}

	@Override
	public String toString() { 
		if(currentFaceIdx==0)
			return "";
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < 5; i++) {
			buff.append(faces[0].getRowStr(i) + CONSTANTS.EMPTY_CELL);
			if(currentFaceIdx>1)
				buff.append(faces[1].getRowStr(i)+ CONSTANTS.EMPTY_CELL);
			if(currentFaceIdx>2)
				buff.append(faces[2].getRowStr(i));
			buff.append(System.lineSeparator());
		}
		if(currentFaceIdx>3)
			buff.append(faces[3].toString(CONSTANTS.INDENT));
		if(currentFaceIdx>4)
			buff.append(faces[4].toString(CONSTANTS.INDENT));
		if(currentFaceIdx>5)
			buff.append(faces[5].toString(CONSTANTS.INDENT));
		return buff.toString();
	}

}
