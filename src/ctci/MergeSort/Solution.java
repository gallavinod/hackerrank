package ctci.MergeSort;
import java.util.Scanner;

public class Solution {

	public static long countInversions(int[] array){
		if (array.length == 0) {
			return 0;
		}
		return mergesortInversions(array);		
	}

	public static long mergesortInversions (int[] array) {
		return mergesortInversions(array, new int[array.length], 0, array.length-1);
	}

	public static long mergesortInversions(int[] array, int[] temp, int leftStart, int rightEnd) {
		if (leftStart >= rightEnd) {
			return 0;
		}
		int mid = (leftStart+rightEnd)/2;
		return mergesortInversions(array, temp, leftStart, mid) + mergesortInversions(array, temp, mid+1, rightEnd) + merge(array, temp, leftStart, rightEnd);
	}

	public static long merge(int[] array, int[] temp, int leftStart, int rightEnd) {
		int leftEnd = (leftStart + rightEnd)/2;
		int rightStart = leftEnd + 1;
		int size = rightEnd - leftStart + 1;

		int left = leftStart;
		int right = rightStart;
		int index = leftStart;
		
		long inversions = 0;
		for (int i=right; i<= rightEnd; i++) {
			inversions += findInversions(array, leftStart, leftEnd, i);
		}
		while (left <= leftEnd && right <= rightEnd) {
			if (array[left] <= array[right]) {
				temp[index++] = array[left++];
			} else {
				temp[index++] = array[right++];
			}
		}
		System.arraycopy(array, left, temp, index, leftEnd - left + 1);
		System.arraycopy(array, right, temp, index, rightEnd - right + 1);
		System.arraycopy(temp, leftStart, array, leftStart, size);
		return inversions;
	}
	
	public static long findInversions(int[] array, int leftStart, int leftEnd, int reference) {
		int low = leftStart;
		int high = leftEnd;
		while (low <= high) {
			int mid = (low + high)/2;
			if (array[reference] >= array[mid]) {
				low = mid+1;
			} else {
				high = mid-1;
			}
		}
		return (long) (leftEnd - low + 1);
	}

	public static void main(String[] args) {
		/*int[] array = {1,3,5,2,4};
		for (int i=3; i<=4; i++) {
			System.out.println(array[i] + " " + findInversions(array, 0, 2, i));
		}*/
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for(int a0 = 0; a0 < t; a0++){
			int n = in.nextInt();
			int arr[] = new int[n];
			for(int arr_i=0; arr_i < n; arr_i++){
				arr[arr_i] = in.nextInt();
			}
			System.out.println(countInversions(arr));
		}
		in.close(); 
	}
}
