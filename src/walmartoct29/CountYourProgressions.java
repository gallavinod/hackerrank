package walmartoct29;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Node {
	int d;
	int lastIndex;
	
	Node(int d, int index) {
		this.d = d;
		this.lastIndex = index;
	}
}

public class CountYourProgressions {

	public static final BigInteger M = new BigInteger("1000000009");
	public static final BigInteger bigOne = new BigInteger("1");
	public static final BigInteger bigZero = new BigInteger("0");
	
	public static long countProgressions(int[] a) {
		int n = a.length;
		long totalCount = 1 + n;	// empty set and all sets with one elements
		if (n >= 2) {
			List <Node> list = new ArrayList <Node> ();
			long count = 0;
			for (int i=0; i<n-1; i++) {
				for (int j=i+1; j<n; j++) {
					list.add(new Node(a[j] - a[i], j));
					count++;
				}
			}
			totalCount += count;
			while(count>0) {
				count = 0;
				List <Node> tempList = new ArrayList <Node> ();
				for (Node node: list) {
					int d = node.d;
					int lastIndex = node.lastIndex;
					for (int i=lastIndex+1; i<n; i++) {
						if ((a[i] - a[lastIndex]) == d) {
							tempList.add(new Node(d, i));
							count++;
						}
					}
				}
				totalCount = (totalCount+count)%1000000009;
				list = tempList;
			}
		}
		return totalCount;
	}
	
	public static BigInteger countBigProgressions(int[] a) {
		int n = a.length;
		BigInteger bigTotal = (new BigInteger(Integer.toString(n))).add(bigOne);
		// empty set and all sets with one elements
		if (n >= 2) {
			List <Node> list = new ArrayList <Node> ();
			BigInteger bigCount = bigZero;
			for (int i=0; i<n-1; i++) {
				for (int j=i+1; j<n; j++) {
					list.add(new Node(a[j] - a[i], j));
					bigCount = bigCount.add(bigOne).mod(M);
				}
			}
			bigTotal = bigTotal.add(bigCount);
			while(bigCount.compareTo(bigZero) > 0) {
				bigCount = bigZero;
				List <Node> tempList = new ArrayList <Node> ();
				for (Node node: list) {
					int d = node.d;
					int lastIndex = node.lastIndex;
					for (int i=lastIndex+1; i<n; i++) {
						if ((a[i] - a[lastIndex]) == d) {
							tempList.add(new Node(d, i));
							bigCount = bigCount.add(bigOne).mod(M);
						}
					}
				}
				bigTotal = bigTotal.add(bigCount).mod(M);
				list = tempList;
			}
		}
		return bigTotal.mod(M);
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] a = new int[n];
		for (int i=0; i<n; i++) {
			a[i] = in.nextInt();
		}
		in.close();
		System.out.println(countProgressions(a));
		System.out.println(countBigProgressions(a).toString());
	}

}
