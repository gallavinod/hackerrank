package algorithms.strings.CommonChild;

import java.util.Scanner;

public class Solution {

	public static String s1 = "";
	public static String s2 = "";

	public static int longestCommonSubSequence(int i, int j) {
		if (i<0 || j<0) {
			return 0;
		} else {
			if (s1.charAt(i) == s2.charAt(j)) {
				return 1 + longestCommonSubSequence(i-1, j-1);
			} else {
				return Math.max(longestCommonSubSequence(i-1, j), longestCommonSubSequence(i, j-1));
			}
		}
	}

	public static void main(String[] args) {
		
		Scanner in = new Scanner (System.in);
		
		s1 = in.next();
		s2 = in.next();
		in.close();
		
		int l1 = s1.length();
		int l2 = s2.length();
		
		if (l1>0 && l2>0) {
			System.out.println(longestCommonSubSequence(l1-1,l2-1));
		} else {
			System.out.println(0);
		}
	}

}
