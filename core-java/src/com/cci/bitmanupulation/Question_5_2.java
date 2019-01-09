package com.cci.bitmanupulation;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import com.sun.org.apache.xalan.internal.xsltc.dom.BitArray;

public class Question_5_2 {

	@Test
	public void test1() {
		int a = 0b10110;
		String result = Integer.toBinaryString(clearBit(a, 2));
		assertTrue(result.equalsIgnoreCase("10010"));
		a = 0b11000111;
		result = Integer.toBinaryString(clearBit(a, 7));
		assertTrue(result.equalsIgnoreCase("1000111"));
		
	}
	
	public static void main(String[] args) {
		new Question_5_2().insertBits(0b1001101100, 0b10010, 2, 6);
	}
	
	public void insertBits(int n, int m , int i ,int j) {
		int mask1 = Integer.toBinaryString(1<<2);
		String result1 = Integer.toBinaryString(n&mask1<<i);
		System.out.println(result1);
		
	}
	
	public int clearBit(int num, int pos) {
		return ~(1<<pos)&num;
	}
}


