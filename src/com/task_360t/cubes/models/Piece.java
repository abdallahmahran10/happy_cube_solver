package com.task_360t.cubes.models;

import com.task_360t.cubes.exceptions.InvalidEdgeException;
import com.task_360t.cubes.exceptions.InvalidPieceException;
import com.task_360t.cubes.utilities.CONSTANTS;

/**
 * Representation of one piece
 * 
 * @author amahran
 * 
 */
public class Piece {
	boolean[][] pieceArray;
	private EdgeBitSet rightEdge;
	private EdgeBitSet lefttEdge;
	private EdgeBitSet topEdge;
	private EdgeBitSet bottomEdge;

	/**
	 * Initialize the piece array with the arr param
	 * 
	 * @param arr
	 *            piece array
	 * @throws InvalidPieceException
	 */
	public Piece(boolean[][] arr) throws InvalidPieceException {
		if (arr.length != 5 || arr[0].length != 5)
			throw new InvalidPieceException("Invalid piece array");
		pieceArray = arr;
		createEdges();
	}

	private void createEdges() {
		getTopEdge();
		getRightEdge();
		getBottomEdge();
		getLeftEdge();
	}

	public Piece() {
		pieceArray = new boolean[5][5];
	}

	public Piece(Piece piece) {
		pieceArray = new boolean[5][5];
		for (int i = 0; i < 5; i++) {
			System.arraycopy(piece.pieceArray[i], 0, this.pieceArray[i], 0, piece.pieceArray[i].length);
		}
	}

	/**
	 * @return the rightEdge
	 */
	public EdgeBitSet getRightEdge() {
		if (rightEdge == null) {
			rightEdge = new EdgeBitSet();
			for (int i = 0; i < 5; i++)
				rightEdge.set(i, pieceArray[i][4]);
		}
		return rightEdge;
	}

	/**
	 * @return the lefttEdge
	 */
	public EdgeBitSet getLeftEdge() {
		if (lefttEdge == null) {
			lefttEdge = new EdgeBitSet();
			for (int i = 0; i < 5; i++)
				lefttEdge.set(i, pieceArray[i][0]);
		}
		return lefttEdge;
	}

	/**
	 * @return the topEdge
	 */
	public EdgeBitSet getTopEdge() {
		if (topEdge == null) {
			topEdge = new EdgeBitSet();
			for (int i = 0; i < 5; i++)
				topEdge.set(i, pieceArray[0][i]);
		}
		return topEdge;
	}

	/**
	 * @return the bottomEdge
	 */
	public EdgeBitSet getBottomEdge() {
		if (bottomEdge == null) {
			bottomEdge = new EdgeBitSet(00000);
			for (int i = 0; i < 5; i++)
				bottomEdge.set(i, pieceArray[4][i]);
		}
		return bottomEdge;
	}

	EdgeBitSet getEdgeBitRepresentation(int edge) throws InvalidEdgeException {
		switch (edge) {
		case CONSTANTS.TOP_EDGE:
			return getTopEdge();
		case CONSTANTS.RIGHT_EDGE:
			return getTopEdge();
		case CONSTANTS.BOTTOM_EDGE:
			return getBottomEdge();
		case CONSTANTS.LEFT_EDGE:
			return getLeftEdge();
		default:
			throw new InvalidEdgeException("Wrond edge id");
		}
	}

	public void flipPiece() {
		EdgeBitSet tmp = topEdge;
		topEdge = bottomEdge;
		bottomEdge = tmp;
		tmp = rightEdge;
		rightEdge = lefttEdge;
		lefttEdge = tmp;
	}

	public void rotateClockWise() {
		EdgeBitSet tmpTop = (EdgeBitSet) topEdge;
		// set TOP edge
		topEdge = lefttEdge.edgeReverse();
		EdgeBitSet tmpRight = (EdgeBitSet) rightEdge.edgeReverse();
		EdgeBitSet tmpBottom = (EdgeBitSet) bottomEdge;
		lefttEdge = tmpBottom;
		bottomEdge = tmpRight;
		rightEdge = tmpTop;
		/*
		EdgeBitSet tmpTop = (EdgeBitSet) topEdge.clone();
		// set TOP edge
		topEdge = lefttEdge.edgeReverse();
		EdgeBitSet tmpRight = (EdgeBitSet) rightEdge.edgeReverse().clone();
		EdgeBitSet tmpBottom = (EdgeBitSet) bottomEdge.clone();
		lefttEdge = tmpBottom;
		bottomEdge = tmpRight;
		rightEdge = tmpTop;
		*/
	}

	public boolean getCornerBit(CornersBit cb) {
		switch (cb) {
		case TR:
			return getTopEdge().get(4) || getRightEdge().get(0);
		case BR:
			return getBottomEdge().get(4) || getRightEdge().get(4);

		case BL:
			return getBottomEdge().get(0) || getLeftEdge().get(4);

		case TL:
			return getTopEdge().get(0) || getLeftEdge().get(0);
		default:
			break;
		}
		return false;
	}

	public String getRowStr(int rowIdx) {
		if(rowIdx == 0)
			return getTopEdge().toString();
		if(rowIdx == 4)
			return getBottomEdge().toString();		
		EdgeBitSet rowBitSet = new EdgeBitSet();
		rowBitSet.set(4, getRightEdge().get(rowIdx));
		rowBitSet.set(0, getLeftEdge().get(rowIdx));
		rowBitSet.set(1, 4);
		return rowBitSet.toString();
	}

	
	public String toString(String indent) {
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < 5; ++i) {
			buff.append(indent + getRowStr(i));
			buff.append(System.lineSeparator());
		}
		return buff.toString();
	}
	
	@Override
	public String toString() {
		return toString("");
	}

}
