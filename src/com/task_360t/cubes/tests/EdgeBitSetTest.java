package com.task_360t.cubes.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import com.task_360t.cubes.models.EdgeBitSet;

/**
 * Test cases for {@link EdgeBitSet} class
 * 
 * @author amahran
 *
 */
public class EdgeBitSetTest {

	@Test
	public void matchesTest_compareTwoSimilarEdgeBitSet() {
		// Arrange
		EdgeBitSet bs1 = new EdgeBitSet();
		bs1.clear(0);bs1.set(1);  bs1.clear(2);bs1.set(3);  bs1.clear(4);
		EdgeBitSet bs2 = new EdgeBitSet();
		bs2.clear(0);bs2.clear(1);bs2.set(2);  bs2.clear(3); bs2.clear(4);
		// Act
		boolean isMatched = bs1.matches(bs2);
		// Assert
		assertTrue(isMatched);
	}
	
	@Test
	public void matchesTest_compareDifferentTwoEdgeBitSet() {
		// Arrange
		EdgeBitSet bs1 = new EdgeBitSet();
		bs1.clear(0);bs1.set(1);  bs1.clear(2);bs1.set(3);  bs1.clear(4);
		EdgeBitSet bs2 = new EdgeBitSet();
		bs2.clear(0);bs2.clear(1);bs2.set(2);  bs2.set(3); bs2.clear(4);
		// Act
		boolean isMatched = bs1.matches(bs2);
		// Assert
		assertFalse(isMatched);
	}
	
	@Test
	public void reverseTest_reverseEdgeBitSet() {
		// Arrange
		EdgeBitSet bs1 = new EdgeBitSet();
		bs1.clear(0);bs1.clear(1);  bs1.clear(2);bs1.set(3);  bs1.set(4);
		EdgeBitSet expected = new EdgeBitSet();
		expected.set(0);expected.set(1);expected.clear(2);  expected.clear(3); expected.clear(4);
		// Act
		bs1.reverse();
		// Assert
		assertFalse(bs1.matches(expected));
	}

}
