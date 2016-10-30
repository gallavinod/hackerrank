package walmartoct29;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InterestingFibonocciSum {

	public static long fib(int n)
	{
		long F[][] = new long[][]{{1,1},{1,0}};
		if (n == 0)
			return 0;
		power(F, n-1);

		return F[0][0];
	}

	public static void multiply(long F[][], long M[][])
	{
		long x =  F[0][0]*M[0][0] + F[0][1]*M[1][0];
		long y =  F[0][0]*M[0][1] + F[0][1]*M[1][1];
		long z =  F[1][0]*M[0][0] + F[1][1]*M[1][0];
		long w =  F[1][0]*M[0][1] + F[1][1]*M[1][1];

		F[0][0] = x;
		F[0][1] = y;
		F[1][0] = z;
		F[1][1] = w;
	}

	/* Optimized version of power() in method 4 */
	public static void power(long F[][], int n)
	{
		if( n == 0 || n == 1)
			return;
		long M[][] = new long[][]{{1,1},{1,0}};

		power(F, n/2);
		multiply(F, F);

		if (n%2 != 0)
			multiply(F, M);
	}
	
	public static final BigInteger bigZero = new BigInteger("0");
	public static final BigInteger bigOne = new BigInteger("1");
	public static Map <Long, BigInteger> map = new HashMap <Long, BigInteger> ();
	public static final BigInteger M = new BigInteger("1000000007");	
/*	public static BigInteger bigFib(long n, BigInteger[] powerTwoFib) {
		
	}
*/	
	public static BigInteger bigFib(long n) {
		if (map.containsKey(n)) {
			return map.get(n);
		}
		BigInteger F[][] = new BigInteger[][]{{bigOne,bigOne},{bigOne,bigZero}};
		if (n == 0)
			return bigZero;
		power(F, n-1);
		
		map.put(n, F[0][0].mod(M));
		return F[0][0].mod(M);
	}
	
	/* Optimized version of power() in method 4 */
	public static void power(BigInteger F[][], long n)
	{
		if( n == 0 || n == 1)
			return;
		BigInteger M[][] = new BigInteger[][]{{bigOne,bigOne},{bigOne,bigZero}};

		power(F, n/2);
		multiply(F, F);

		if (n%2 != 0)
			multiply(F, M);
	}
	
	public static void multiply(BigInteger F[][], BigInteger M[][])
	{
		BigInteger x =  (F[0][0].multiply(M[0][0])).add(F[0][1].multiply(M[1][0]));
		BigInteger y =  (F[0][0].multiply(M[0][1])).add(F[0][1].multiply(M[1][1]));
		BigInteger z =  (F[1][0].multiply(M[0][0])).add(F[1][1].multiply(M[1][0]));
		BigInteger w =  (F[1][0].multiply(M[0][1])).add(F[1][1].multiply(M[1][1]));

		F[0][0] = x;
		F[0][1] = y;
		F[1][0] = z;
		F[1][1] = w;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		short Q = in.nextShort();
		
		map.put((long) 0, bigZero);
		map.put((long) 1, bigOne);
		for (long l=2; l<=150000; l++) {
			map.put(l, map.get(l-1).add(map.get(l-2)).mod(M));
		}
		for (int q=0; q<Q; q++) {
			int n = in.nextInt();
			long[] a = new long[n];
			long[] sum = new long[n];
			long[][] rangeSum = new long[n][n];
			BigInteger totalSum = bigZero;
			if (n>0) {
				a[0] = in.nextLong();
				sum[0] = a[0];
				for (int i=1; i<n; i++) {
					a[i] = in.nextLong();
					sum[i] = sum[i-1] + a[i];
				}
			}
			for (int j=0; j<n; j++) {
				rangeSum[0][j] = sum[j];
	//			System.out.println(0 + " " + j + " " + rangeSum[0][j] + " : " + bigFib(rangeSum[0][j]));
				totalSum = totalSum.add(bigFib(rangeSum[0][j])).mod(M);
			}
			for (int i=1; i<n; i++) {
				for (int j=i; j<n; j++) {
					rangeSum[i][j] = sum[j] - sum[i-1];
		//			System.out.println(i + " " + j + " " + rangeSum[i][j] + " : " + bigFib(rangeSum[i][j]));
					totalSum = totalSum.add(bigFib(rangeSum[i][j])).mod(M); 
				}
			}
			System.out.println(totalSum.mod(M));
		}
		in.close();
	}
}
