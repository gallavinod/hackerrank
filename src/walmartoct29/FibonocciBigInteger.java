package walmartoct29;

import java.math.BigInteger;

public class FibonocciBigInteger {

	public static final BigInteger bigZero = new BigInteger("0");
	public static final BigInteger bigOne = new BigInteger("1");
	
	
/*	public static BigInteger bigFib(long n, BigInteger[] powerTwoFib) {
		
	}
*/	
	public static BigInteger bigFib(long n) {
		BigInteger F[][] = new BigInteger[][]{{bigOne,bigOne},{bigOne,bigZero}};
		if (n == 0)
			return bigZero;
		power(F, n-1);

		return F[0][0];
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
		
		
		long timeStamp = System.currentTimeMillis();
		System.out.println(bigFib(2066).toString());
		System.out.println(System.currentTimeMillis() - timeStamp);
	}
}
