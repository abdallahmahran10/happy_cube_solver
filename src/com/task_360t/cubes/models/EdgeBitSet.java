package com.task_360t.cubes.models;

import java.util.BitSet;

import com.task_360t.cubes.utilities.CONSTANTS;

public class EdgeBitSet extends BitSet {

	private static final long serialVersionUID = -8613596608641317196L;

	public EdgeBitSet() {
		super();
	}
	
	public EdgeBitSet(int nbits) {
		super(nbits);
	}
	
	public boolean matches(BitSet secondSet)
	{
		BitSet set = (BitSet) secondSet.clone();
		set.or(this);
		return set.get(1) && set.get(2) && set.get(3);	
	}

	public EdgeBitSet edgeReverse() {
		if(size()<5)
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

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < this.length(); i++)
			if (this.get(i))
				buff.append(CONSTANTS.FILLED_CELL);
			else
				buff.append(CONSTANTS.EMPTY_CELL);
		return buff.toString();
	}
}
