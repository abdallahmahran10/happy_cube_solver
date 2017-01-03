package com.task_360t.cubes.models;

import java.util.BitSet;

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
	private BitSet rightEdge;
	private BitSet lefttEdge;
	private BitSet topEdge;
	private BitSet bottomEdge;

	
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
	public BitSet getRightEdge() {
		if(rightEdge == null)
		{
			rightEdge = new BitSet(00000);
			for(int i=0; i<5; i++)
				rightEdge.set(i, pieceArray[i][4]);			
		}
		return rightEdge;
	}

	/**
	 * @return the lefttEdge
	 */
	public BitSet getLeftEdge() {
		if(lefttEdge == null)
		{
			lefttEdge = new BitSet(00000);
			for(int i=0; i<5; i++)
				lefttEdge.set(i, pieceArray[i][0]);			
		}
		return lefttEdge;
	}

	/**
	 * @return the topEdge
	 */
	public BitSet getTopEdge() {
		if(topEdge == null)
		{
			topEdge = new BitSet(00000);
			for(int i=0; i<5; i++)
				topEdge.set(i, pieceArray[0][i]);			
		}
		return topEdge;
	}

	/**
	 * @return the bottomEdge
	 */
	public BitSet getBottomEdge() {
		if(topEdge == null)
		{
			bottomEdge = new BitSet(00000);
			for(int i=0; i<5; i++)
				bottomEdge.set(i, pieceArray[4][i]);			
		}
		return bottomEdge;
	}

	BitSet getEdgeBitRepresentation(int edge) throws InvalidEdgeException
	{
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

	public String getRowStr(int rowIdx) {
		StringBuffer buff= new StringBuffer();
		for(int i=0; i<5; i++)
			if(pieceArray[rowIdx][i])
				buff.append("[]");
			else
				buff.append("  ");
		
		return buff.toString();
	}

	public String toString(String indent) {
		StringBuffer buff= new StringBuffer();
		for(int i=0; i<5; ++i)
		{
			buff.append(indent + getRowStr(i));
			buff.append(System.lineSeparator());
		}
		return buff.toString();
	}

	public void rotateClockWise() {
		BitSet tmp1 = topEdge, tmp2 = rightEdge;
		topEdge = lefttEdge;
		rightEdge = tmp1;
		tmp1 = bottomEdge;
		bottomEdge = tmp2;
		lefttEdge = tmp1;
	}

}
