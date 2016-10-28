package algorithms.sorting.FradulentActivityNotifications;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Solution {
	
	public static int median(Map <Integer, Integer> map, int d) {
		int median = 0;
		boolean dIsOdd = (d&1) == 1;
		
		if (dIsOdd) {
			int center = (d/2) + 1;
			for (Integer key: map.keySet()) {
				if ((center -= map.get(key)) <= 0) {
					return 2*key;
				}
			}
		} else {
			int left = d/2;
			int right = (d/2) + 1;
			boolean leftIsSet = false;
			for (Integer key: map.keySet()) {
				System.out.println("key: " + key);
				if (leftIsSet) {
					right = key;
					return (left+right);
				}
				left -= map.get(key);
				if (left < 0) {
					return 2*key;
				} else if (left == 0) {
					left = key;
					leftIsSet = true;
				}
			}
		}
		return median;
	}
	
	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int d = in.nextInt();
        int[] a = new int[n];
        Map <Integer, Integer> treeMap = new TreeMap <Integer, Integer> ();
        for (int i=0; i<n; i++) {
        	a[i] = in.nextInt();
        }
        for (int i=0; i<d; i++) {
        	if (treeMap.containsKey(a[i])) {
        		treeMap.put(a[i], treeMap.get(a[i]) + 1);
        	} else {
        		treeMap.put(a[i], 1);
        	}
        }
        int count = 0;
        for (int i=d; i<n; i++) {
        	if (a[i] >= median(treeMap, d)) {
        		count++;
        	}
        	if (a[i] != a[i-d]) {
        		if (treeMap.containsKey(a[i])) {
        			treeMap.put(a[i], treeMap.get(a[i]) + 1);
        		} else {
        			treeMap.put(a[i], 1);
        		}
        		if (treeMap.get(a[i-d]) == 1) {
        			treeMap.remove(a[i-d]);
        		} else {
        			treeMap.put(a[i-d], treeMap.get(a[i-d]) - 1);
        		}
        	}
        } 
        System.out.println(count);
        
        in.close();
    }

}
