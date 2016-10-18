package algorithms.strings.AstonAndString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        while (T-- > 0) {
            String s = in.next();
            int K = in.nextInt();
            int len = s.length();
            Set <String> hashSet = new HashSet <String> ();
            List <String> arrayList = new ArrayList <String> ();
      //      Map <String, Integer> treeMap = new TreeMap <String, Integer> ();
            for (int i=0; i<len; i++) {
                for (int j=i+1; j<=len; j++) {
                    String subStr = s.substring(i,j);
                    if (!hashSet.contains(subStr)) {
                        hashSet.add(subStr);
                        arrayList.add(subStr);
                    }
      //              treeMap.put(subStr, (j-i));
                }
            }
            Collections.sort(arrayList);
            for (String str: arrayList) {
                int strLen = str.length();
                K -= strLen;
                if (K <= 1) {
                    K += strLen;
                    System.out.println(str.charAt(K - 1));
                    break;
                }
            }
      /***      for (Map.Entry <String, Integer> entry: treeMap.entrySet()) {
                int strLen = entry.getValue();
                K -= strLen;
                if (K <= 1) {
                    K += strLen;
                    System.out.println(entry.getKey().charAt(K - 1));
                    break;
                }
            } ***/
        }
        in.close();
    }
}