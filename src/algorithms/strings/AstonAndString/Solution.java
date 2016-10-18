package algorithms.strings.AstonAndString;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        while (T-- > 0) {
            String s = in.next();
            int K = in.nextInt();
            int len = s.length();
            Set <String> treeSet = new TreeSet <String> ();
            for (int i=0; i<len; i++) {
                for (int j=i+1; j<=len; j++) {
                    treeSet.add(s.substring(i,j));
                }
            }
            int count=0;
            for (String str: treeSet) {
            //    System.out.println(str);
                int strLen = str.length();
                count += strLen;
                if (count >= K) {
                    int originalCount = count - strLen;
                    System.out.println(str.charAt(K - originalCount - 1));
                    break;
                }
            }
        }
        in.close();
    }
}