package com.task_360t.cubes.models;

import java.util.BitSet;
import java.util.List;

import com.task_360t.cubes.utilities.CubeLogger;

public class Cube {
	CubeLogger logger = CubeLogger.getInstant();
	public Piece[] faces;
	private int currentFaceIdx;

	public Cube() {
		faces = new Piece[6];
		for(int i=0; i<6; ++i)
			faces[i] = new Piece();
		currentFaceIdx = 0;
	}

	

	public int createNextFace(List<Piece> pieces) {
		
		for (int i=0; i<pieces.size(); i++) {
			if (matchCurrentFace(pieces.get(i))) {
				faces[currentFaceIdx] = pieces.get(i);
				++currentFaceIdx;
				return i;
			}
		}
		return -1;
	}

	private boolean matchCurrentFace(Piece piece) {
		if (currentFaceIdx == 0)
			return true;
		
		return tryToMatch(piece);
	}
	
	private boolean tryToMatch(Piece piece) {
		BitSet currentEdge = getCurrentEdge();
		logger.INFO(Long.toString(currentEdge.toLongArray()[0], 2));
		for(int i=0; i<4; i++)
		{
			BitSet interstingEdge = getInterstingEdge(piece);
			logger.INFO(Long.toString(interstingEdge.toLongArray()[0], 2));
			if(matches(currentEdge, interstingEdge))
				return true;
			piece.rotateClockWise();
		}
		return false;
	}

	private boolean matches(BitSet currentEdge, BitSet interstingEdge) {
		// TODO Auto-generated method stub
		BitSet set = (BitSet) currentEdge.clone();
		set.or(interstingEdge);
		int idx = set.nextClearBit(0);
		//logger.INFO(Long.toString(set.toLongArray()[0], 2));
		return idx == 5;
	}

	private BitSet getInterstingEdge(Piece piece) {
		switch (currentFaceIdx) {
		case 1:
			return piece.getLeftEdge();
		
		case 2:
			return faces[1].getLeftEdge();
			
		case 3:
			return faces[1].getTopEdge();

		case 4:
			faces[3].getTopEdge();
			break;
		case 5:
			faces[4].getTopEdge();
			break;

		default:
			break;
		}
		return null;
	}

	private BitSet getCurrentEdge() {
		switch (currentFaceIdx) {
		case 1:
			return faces[0].getRightEdge();
		
		case 2:
			return faces[1].getRightEdge();
			
		case 3:
			return faces[1].getBottomEdge();

		case 4:
			faces[3].getBottomEdge();
			break;
		case 5:
			faces[4].getBottomEdge();
			break;

		default:
			break;
		}
		return null;
	}

	public boolean isCubeFormed()
	{
		return currentFaceIdx == 6;
	}
	
	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		for(int i=0; i<5; i++)
		{
			buff.append(faces[0].getRowStr(i));
			buff.append(faces[1].getRowStr(i));
			buff.append(faces[2].getRowStr(i));
			buff.append(System.lineSeparator());
		}
		buff.append(faces[3].toString("            "));
		buff.append(faces[4].toString("            "));
		buff.append(faces[5].toString("            "));
		return buff.toString();
	}
}
