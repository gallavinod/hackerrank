package algorithms.strings.SherlockAndValidString;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {

	/*
	 * https://www.hackerrank.com/challenges/sherlock-and-valid-string
	 */

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		String s = in.next();
		in.close();

		Map <Character, Integer> charCountMap = new HashMap <Character, Integer> ();
		Map <Integer, Integer> countCharCountMap = new HashMap <Integer, Integer> ();

		for (int i=0; i<s.length(); i++) {
			char c =s.charAt(i);
			if (charCountMap.containsKey(c)) {
				charCountMap.put(c, charCountMap.get(c)+1);
			} else {
				charCountMap.put(c, 1);
			}
		}
		int count;
		for (Character c: charCountMap.keySet()) {
			count = charCountMap.get(c);
			if (countCharCountMap.containsKey(count)) {
				countCharCountMap.put(count, countCharCountMap.get(count)+1);
			} else {
				countCharCountMap.put(count, 1);
			}
		}
		
		boolean isValid = true;
		if (countCharCountMap.keySet().size() <= 2) {
			for (Integer count1: countCharCountMap.keySet()) {
				if (!(countCharCountMap.get(count1) == 1 || 
						countCharCountMap.get(count1) == charCountMap.keySet().size() - 1 || 
						countCharCountMap.get(count1) == charCountMap.keySet().size())) {
					isValid = false;
					break;
				}
			}
		} else {
			isValid = false;
		}
		if(isValid) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}
}
