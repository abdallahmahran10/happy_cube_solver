package com.task_360t.cubes.models;

import java.util.BitSet;

import com.task_360t.cubes.utilities.CONSTANTS;

/**
 * Extension of the BitSet class to handle pieces edge
 * @author amahran
 *
 */
public class EdgeBitSet extends BitSet {

	private static final long serialVersionUID = -8613596608641317196L;

	public EdgeBitSet() {
		super();
	}

	public EdgeBitSet(int nbits) {
		super(nbits);
	}

	public boolean matches(BitSet secondSet) {
		BitSet set = (BitSet) secondSet.clone();
		set.or(this);
		return set.get(1) && set.get(2) && set.get(3);
	}

	public EdgeBitSet edgeReverse() {
		if (size() < CONSTANTS.MAX_CELLS)
			return this;
		//
		boolean tmp = this.get(0);
		this.set(0, this.get(4));
		this.set(4, tmp);
		tmp = this.get(3);
		this.set(3, this.get(1));
		this.set(1, tmp);
		return (EdgeBitSet) this;
	}
	
	public long toLong() {
	    long value = 0L;
	    for (int i = 0; i < this.length(); ++i) {
	      value += this.get(i) ? (1L << i) : 0L;
	    }
	    return value;
	  }
	/**
	 * check if BitSet is equals the'int
	 * @param i check if BitSet is equals this int
	 * @return true if equals
	 */
	public boolean equalsLong(long i) {
		return toLong() == i;
	}


	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < CONSTANTS.MAX_CELLS; i++)
			if (this.get(i))
				buff.append(CONSTANTS.FILLED_CELL);
			else
				buff.append(CONSTANTS.EMPTY_CELL);
		return buff.toString();
	}
	@Override
	public EdgeBitSet clone() {
		return (EdgeBitSet) super.clone();
	}
	

}
