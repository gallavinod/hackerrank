package walmartoct29;

import java.util.Scanner;

public class HikingSelfies {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int x = in.nextInt();
		in.close();
		System.out.println((int) Math.abs(x - (Math.pow(2, n) - 1)));
	}

}
