package com.task_360t.cubes.models;

import java.util.BitSet;

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
			return matches(faces[0].getRightEdge(), piece.getLeftEdge());

		case 2:
			return matches(faces[1].getRightEdge(), piece.getLeftEdge());

		case 3:
			return matches(faces[0].getBottomEdge(), piece.getLeftEdge())
					&& matches(faces[1].getBottomEdge(), piece.getTopEdge())
					&& matches(faces[2].getBottomEdge(), piece.getRightEdge())
					&& matches(faces[0].getCornerBit(CornersBit.BR), faces[1].getCornerBit(CornersBit.BL),
							piece.getCornerBit(CornersBit.TL))
					&& matches(faces[2].getCornerBit(CornersBit.BL), faces[1].getCornerBit(CornersBit.BR),
							piece.getCornerBit(CornersBit.TR));

		case 4:
			return matches(faces[0].getLeftEdge(), piece.getLeftEdge())
					&& matches(faces[2].getRightEdge(), piece.getRightEdge())
					&& matches(faces[3].getBottomEdge(), piece.getTopEdge())
					&& matches(faces[0].getCornerBit(CornersBit.BL), faces[3].getCornerBit(CornersBit.BL),
							piece.getCornerBit(CornersBit.TL))
					&& matches(faces[2].getCornerBit(CornersBit.BR), faces[3].getCornerBit(CornersBit.BR),
							piece.getCornerBit(CornersBit.TR));

		case 5:
			return matches(faces[0].getTopEdge(), piece.getLeftEdge())
					&& matches(faces[2].getTopEdge(), piece.getRightEdge())
					&& matches(faces[1].getTopEdge(), piece.getBottomEdge())
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

	private boolean matches(BitSet currentEdge, BitSet interstingEdge) {
		// TODO Auto-generated method stub
		BitSet set = (BitSet) currentEdge.clone();
		set.or(interstingEdge);
		return set.get(1) && set.get(2) && set.get(3);
	}

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
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < 5; i++) {
			buff.append(faces[0].getRowStr(i));
			buff.append(faces[1].getRowStr(i));
			buff.append(faces[2].getRowStr(i));
			buff.append(System.lineSeparator());
		}
		buff.append(faces[3].toString(CONSTANTS.INDENT));
		buff.append(faces[4].toString(CONSTANTS.INDENT));
		buff.append(faces[5].toString(CONSTANTS.INDENT));
		return buff.toString();
	}

}
