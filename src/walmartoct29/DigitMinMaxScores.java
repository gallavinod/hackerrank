package walmartoct29;

public class DigitMinMaxScores {

	public static long minMax(String s) {
		long count = 0;
		for (int i=1; i<s.length()-1; i++) {
			if ((s.charAt(i) > s.charAt(i-1) && s.charAt(i) > s.charAt(i+1)) || 
					(s.charAt(i) < s.charAt(i-1) && s.charAt(i) < s.charAt(i+1))) {
				count++;
			}
		}
		return count;
	}

	public static void count(long[] f, int start) {
		for (int i=start; i<f.length; i++) {
			long small = (long) Math.pow(10, i);
			long big = (long) Math.pow(10, i+1);
			long count = 0;
			for (long num=small; num<big; num++) {
				String s = Long.toString(num);
				for (int k=1; k<s.length()-1; k++) {
					if ((s.charAt(k) > s.charAt(k-1) && s.charAt(k) > s.charAt(k+1)) || 
							(s.charAt(k) < s.charAt(k-1) && s.charAt(k) < s.charAt(k+1))) {
						count++;
					}
				}
			}
			f[i] = count;
		}
	}

	public static void main(String[] args) {
		/*	Scanner in = new Scanner(System.in);
		long A = in.nextLong();
		int dA = (int) (Math.log10(A) + 1);
		long B = in.nextLong();
		int dB = (int) (Math.log10(B) + 1);
		in.close(); */
		long[] counts = new long[10];
		count(counts, 9);
		int index=0;
		for (long count: counts) {
			System.out.println(index + " : " + count);
			index++;
		}
		//	long totalCount = 0;

		//	for (long i=A; i<=B; i++) {
		//		totalCount += minMax(Long.toString(i));
		//	}
		//	System.out.println(totalCount);
	}

}
