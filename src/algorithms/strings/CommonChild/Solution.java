package algorithms.strings.CommonChild;

import java.util.Scanner;

public class Solution {
	
	/*
	 * https://www.hackerrank.com/challenges/common-child
	 */

	public static void main(String[] args) {
		
		Scanner in = new Scanner (System.in);
		
		String s1 = in.next();
		String s2 = in.next();
		in.close();
		
		long startTime = System.nanoTime();
		
		int l1 = s1.length();
		int l2 = s2.length();
		
		int lengthArray[][] = new int[l1+1][l2+1];
		
		for (int i=1; i<=l1; i++) {
			for (int j=1; j<=l2; j++) {
				if (s1.charAt(i-1) == s2.charAt(j-1)) {
					lengthArray[i][j] = 1 + lengthArray[i-1][j-1];
				} else {
					lengthArray[i][j] = Math.max(Math.max(lengthArray[i-1][j], lengthArray[i][j-1]), lengthArray[i-1][j-1]);
				}
			}
		}
		
		if (l1>0 && l2>0) {
			System.out.println(lengthArray[l1][l2]);
		} else {
			System.out.println(0);
		}
		
		System.out.println(System.nanoTime() - startTime);
	}

}
