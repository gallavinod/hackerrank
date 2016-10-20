package ctci.MergeSort;

public class MergeSort {
	
	public static void mergesort (int[] array) {
		mergesort(array, new int[array.length], 0, array.length-1);
	}
	
	public static void mergesort(int[] array, int[] temp, int leftStart, int rightEnd) {
		if (leftStart >= rightEnd) {
			return;
		}
		int mid = (leftStart+rightEnd)/2;
		mergesort(array, temp, leftStart, mid);
		mergesort(array, temp, mid+1, rightEnd);
		merge(array, temp, leftStart, rightEnd);
	}
	
	public static void merge(int[] array, int[] temp, int leftStart, int rightEnd) {
		int leftEnd = (leftStart + rightEnd)/2;
		int rightStart = leftEnd + 1;
		int size = rightEnd - leftStart + 1;
		
		int left = leftStart;
		int right = rightStart;
		int index = leftStart;
		
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
	}

	public static void main(String[] args) {
		int[] array = {2, 1, 3, 1, 2};
		mergesort(array);
		for (int i=0; i<array.length; i++) {
			System.out.print(array[i] + " ");
		}
	}

}
