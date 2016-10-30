package walmartoct29;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ConcurrentHashMap;

class ThreadHelper extends Thread {
	
	private int threadID = 0;
	private static int threadCount = 0;
	private static int key = 0;
	
	public int getThreadID() {
		return threadID;
	}

	public static ConcurrentHashMap<Integer, BigInteger> getMap() {
		return map;
	}

	private static final BigInteger bigZero = new BigInteger("0");
	private static final BigInteger bigOne = new BigInteger("1");
	
	private static ConcurrentHashMap <Integer, BigInteger> map;
	
	ThreadHelper(ConcurrentHashMap <Integer, BigInteger> map) {
		this.threadID = threadCount;
		if (ThreadHelper.map == null) {
			ThreadHelper.map = map;
		}
		threadCount++;
	}
	
	BigInteger bigFib(long n) {
		int key = (int) ((Math.log(n))/(Math.log(2)));
		if (map.containsKey(key)) {
			return map.get(key);
		}
		
		BigInteger F[][] = new BigInteger[][]{{bigOne,bigOne},{bigOne,bigZero}};
		if (n == 0)
			return bigZero;
		power(F, n-1);

		return F[0][0];
	}
	
	/* Optimized version of power() in method 4 */
	void power(BigInteger F[][], long n) {
		if( n == 0 || n == 1)
			return;
		BigInteger M[][] = new BigInteger[][]{{bigOne,bigOne},{bigOne,bigZero}};

		power(F, n/2);
		multiply(F, F);

		if (n%2 != 0)
			multiply(F, M);
	}
	
	void multiply(BigInteger F[][], BigInteger M[][]) {
		BigInteger x =  (F[0][0].multiply(M[0][0])).add(F[0][1].multiply(M[1][0]));
		BigInteger y =  (F[0][0].multiply(M[0][1])).add(F[0][1].multiply(M[1][1]));
		BigInteger z =  (F[1][0].multiply(M[0][0])).add(F[1][1].multiply(M[1][0]));
		BigInteger w =  (F[1][0].multiply(M[0][1])).add(F[1][1].multiply(M[1][1]));

		F[0][0] = x;
		F[0][1] = y;
		F[1][0] = z;
		F[1][1] = w;
	}
	
	public void run() {
		while (key <= 4) {
			synchronized((Integer) key) {
				if (!map.containsKey(key)) {
					map.putIfAbsent(key, bigFib((long) Math.pow(2, key)));
					System.out.println(key + " : updated by " + threadID);
					System.out.println(key + " : " + map.get(key).toString());
				}
			}
			key++;
		}
	}
}

public class ConcurrentHashMapUpdates {
	
	public static void main(String[] args) {
		
		ConcurrentHashMap <Integer, BigInteger> map = new ConcurrentHashMap <Integer, BigInteger> ();
		ThreadHelper[] helperThreads = new ThreadHelper[1];
		for (int i=0; i<1; i++) {
			helperThreads[i] = new ThreadHelper(map);
			helperThreads[i].start();
		}
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("size : " + map.size());
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(
					new File("/Users/admin/eclipse/workspace/hackerrank/src/walmartoct29/fibonacci")));
			for (int key: map.keySet()) {
				bw.write(key + " : " + map.get(key).toString() + "\n");
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
