package algorithms.strings.RichieRich;

import java.util.Scanner;

public class Solution {
	
	/*
	 * https://www.hackerrank.com/challenges/richie-rich
	 */

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
		char[] number = in.next().toCharArray();
		in.close();
		int i=0, j=n-1, mismatches=0;
		while (i<j) {
			if (number[i] != number[j]) {
				if (++mismatches > k) {
					System.out.println(-1);
					return;
				}
			}
			i++;
			j--;
		}
		int k1 = k - mismatches;
		i=0;j=n-1;
		while (i < j) {
			if (number[i] == number[j]) {
				if (number[i] != '9' && k1 > 1) {
					number[i] = '9';
					number[j] = '9';
					k1 -= 2;
				}
			} else {
				if (number[i] == '9' || number[j] == '9') {
					if (number[i] == '9') {
						number[j] = '9';
					} else if (number[j] == '9') {
						number[i] = '9';
					}
				} else {
					if (k1 > 0) {
						number[i] = '9';
						number[j] = '9';
						k1--;
					} else {
						if (number[i] > number[j]) {
							number[j] = number[i];
						} else {
							number[i] = number[j];
						}
					}
				}
			}
			i++;
			j--;
		}
		if (k1 > 0 && (n%2 != 0) && number[n/2] != '9') {
			number[n/2] = '9';
		}
		System.out.println(number);
	}
}