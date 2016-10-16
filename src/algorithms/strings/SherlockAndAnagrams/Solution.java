package algorithms.strings.SherlockAndAnagrams;

import java.util.Scanner;

public class Solution {

	/*
	 * https://www.hackerrank.com/challenges/sherlock-and-anagrams
	 */

	public static void main(String args[]) {

		Scanner in = new Scanner(System.in);
		int T = in.nextInt();

		int primes[] = new int[26];


		if (T>0) {
			// prepare prime numbers;
			primes[0] = 2;
			boolean isPrime = true;
			int num = 3;

			for (int i=1; i<26; ) {
				for (int j=2; j<=Math.sqrt(num); j++) {
					if (num%j == 0) {
						isPrime = false;
						break;
					}
				}
				if (isPrime) {
					primes[i++] = num;
				}
				isPrime = true;
				num++;
			}
			
			while (T-- > 0) {
				String s = in.next();
				int n = s.length();
				int primeProduct[][] = new int[n][n];
				for (int i=0; i<n; i++) {
					primeProduct[0][i] = primes[i];
					primeProduct[i][0] = primes[i];
				}
				for (int i=1; i<n; i++) {
					for (int j=1; j<n; j++) {
						primeProduct[i][j] = primeProduct[i][j-1] * primes[j];
					}
				}
			}
		}
}
