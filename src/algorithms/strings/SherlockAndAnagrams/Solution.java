package algorithms.strings.SherlockAndAnagrams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Pair {
	private int start;
	private int end;
	
	Pair(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	protected int getStart() {
		return start;
	}

	protected int getEnd() {
		return end;
	}	
}

public class Solution {

	/*
	 * https://www.hackerrank.com/challenges/sherlock-and-anagrams
	 */
	
	public static boolean areAnagrams(Pair p1, Pair p2, String s) {
		Map <Character, Integer> charCountMap1 = new HashMap <Character, Integer> ();
		Map <Character, Integer> charCountMap2 = new HashMap <Character, Integer> ();
		for(int i=p1.getStart(), j=p2.getStart(); i<=p1.getEnd() && j<=p2.getEnd(); i++, j++) {
			if (charCountMap1.containsKey(s.charAt(i))) {
				charCountMap1.put(s.charAt(i), charCountMap1.get(s.charAt(i))+1);
			} else {
				charCountMap1.put(s.charAt(i), 1);
			}
			if (charCountMap2.containsKey(s.charAt(j))) {
				charCountMap2.put(s.charAt(j), charCountMap2.get(s.charAt(j))+1);
			} else {
				charCountMap2.put(s.charAt(j), 1);
			}
		}
		if (charCountMap1.size() != charCountMap2.size()) {
			return false;
		}
		for (Character c: charCountMap1.keySet()) {
			if (charCountMap2.containsKey(c)) {
				if (charCountMap1.get(c) != charCountMap2.get(c)) {
					return false;
				}
			} else {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String args[]) {

		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
	//	int primes[] = new int[26];
		
		if (T>0) {
			
			// prepare prime numbers;
/***		int index = 0;
			int num = 2;
			primes[index++] = num++;
			boolean isPrime = true;

			while (index <= 26) {
				for (int j=2; j<=Math.sqrt(num); j++) {
					if (num%j == 0) {
						isPrime = false;
						break;
					}
				}
				if (isPrime) {
					primes[index++] = num++;
				}
				isPrime = true;
			}
		***/	
			while (T-- > 0) {
				String s = in.next();
				int n = s.length();
				Map <Integer, List<Pair>> lengthSubStringsMap = new HashMap <Integer, List <Pair>> ();
				for (int i=0; i<n; i++) {
					for (int j=i; j<n; j++) {
						Pair pair = new Pair(i,j);
						List <Pair> pairsList = null;
						if (lengthSubStringsMap.containsKey(j-i)) {
							pairsList = lengthSubStringsMap.get(j-i);
						} else {
							pairsList = new ArrayList <Pair> ();
						}
						if (pairsList != null) {
							pairsList.add(pair);
							lengthSubStringsMap.put(j-i, pairsList);
						}
					}
				}
				int count = 0;
				for (Integer length: lengthSubStringsMap.keySet()) {
					for (int i=0; i<lengthSubStringsMap.get(length).size(); i++) {
						for (int j=i+1; j<lengthSubStringsMap.get(length).size(); j++) {
							if (areAnagrams(lengthSubStringsMap.get(length).get(i), lengthSubStringsMap.get(length).get(j),s)) {
								count++;
							}
						}
					}
				}
				System.out.println(count);
			}
		}
		in.close();
	}
}
