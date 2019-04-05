/**
 * Isai Lopez
 * ilopezro
 * Program 1 - Recursion
 * Jan 18, 2018
 * Recursively reverse arrays and finding mins and maxs 
 */

public class Recursion 
{
	public static void main(String[] args) 
	{
		int[] A = { -1, 2, 6, 12, 9, 2, -5, -2, 8, 5, 7 };
		int[] B = new int[A.length];
		int[] C = new int[A.length];
		int minIndex = minArrayIndex(A, 0, A.length - 1);
		int maxIndex = maxArrayIndex(A, 0, A.length - 1);
		for (int x : A)
			System.out.print(x + " ");
		System.out.println();
		System.out.println("minIndex = " + minIndex);
		System.out.println("maxIndex = " + maxIndex);
		reverseArray1(A, A.length, B);
		for (int x : B)
			System.out.print(x + " ");
		System.out.println();
		reverseArray2(A, A.length, C);
		for (int x : C)
			System.out.print(x + " ");
		System.out.println();
		reverseArray3(A, 0, A.length - 1);
		for (int x : A)
			System.out.print(x + " ");
		System.out.println();
	}

	//  copy the leftmost n elements in X[] into the rightmost n positions in in reverse order. 
	//  It will do this by first copying the nth element from the left in X[] into the nth position from the right in Y[],
	//  then calling itself recursively to place the leftmost 𝑛 − 1 elements in X[] into the rightmost 𝑛 − 1 positions in Y[] 
	//  in reverse order. The recursion 'bottoms out' when the length of the subarray being reversed is zero, in which case 
	//  the function returns without doing anything. To reverse the entire array, one calls reverseArray1() with n equal to the 
	//  length of the input array X[]. It is assumed that the calling function has allocated the output array Y[] before the function
	//  is called

	static void reverseArray1(int[] X, int n, int[] Y) 
	{
		if (n>0)
		{
			Y[n-1] = X[X.length-n]; 
			reverseArray1(X, n-1, Y); 
		}
	} 

	//  copy the rightmost n elements in X[] into the leftmost n positions in in reverse order. 
	//  It will do this by first copying the nth element from the right in X[] into the nth position from the left in Y[],
	//  then calling itself recursively to place the rightmost 𝑛 − 1 elements in X[] into the leftmost 𝑛 − 1 positions in Y[] 
	//  in reverse order. The recursion 'bottoms out' when the length of the subarray being reversed is zero, in which case 
	//  the function returns without doing anything. To reverse the entire array, one calls reverseArray2() with n equal to the 
	//  length of the input array X[]. It is assumed that the calling function has allocated the output array Y[] before the function
	//  is called

	static void reverseArray2(int[] X, int n, int[] Y) 
	{
		if(n>0)
		{
			Y[X.length-n] = X[n-1]; 
			reverseArray2(X, n-1, Y); 
		}

	} 

	//  different from the first two functions in that it actually alters the order of the elements in X[], 
	//  rather than just copying them in reverse order into another array. This function reverses the subarray X[i,..,j]
	//  consisting of those elements from index i to index j, inclusive. It does this by first swapping the elements at 
	//  positions i and j, then calling itself recursively on the subarray X[i+1,...,j-1] from index 𝑖 + 1 to index 𝑗 − 1.
	//  To reverse the entire array, one calls reverseArray3() with i equal to 0 and j equal to X.length-1

	static void reverseArray3(int[] X, int i, int j)
	{
		if (X.length/2 - j < 0)
		{
			int temp = X[i]; 
			X[i] = X[j]; 
			X[j] = temp; 
			reverseArray3(X, i+1, j-1);
		}

	} 

	//  If subarray X[p...r] contains more than one element, the middle index is computed as q=(p+r)/2, 
	//  the indices of the maximum elements in subarrays A[p...q] and A[q+1...r] are computed recursively,
	//  then the index of the larger of the two maxima is returned

	static int maxArrayIndex(int[] X, int p, int r)
	{
		int q; 
		if(p == r)
			return r;
		else if ( p < r)
		{
			q = (p+r)/2;
			int firstCompare = maxArrayIndex(X, p, q); 
			int secondCompare = maxArrayIndex(X, q+1, r); 
			if(X[firstCompare] < X[secondCompare])
				return secondCompare; 
			else 
				return firstCompare; 
		}
		else
		{
			return -1; 
		}
	} 

	//  If subarray X[p...r] contains more than one element, the middle index is computed as q=(p+r)/2, 
	//  the indices of the minimum elements in subarrays A[p...q] and A[q+1...r] are computed recursively,
	//  then the index of the smaller of the two maxima is returned

	static int minArrayIndex(int[] X, int p, int r)
	{
		int q; 
		if(p == r)
			return r;
		else if ( p < r)
		{
			q = (p+r)/2;
			int firstCompare = minArrayIndex(X, p, q); 
			int secondCompare = minArrayIndex(X, q+1, r); 
			if(X[firstCompare] < X[secondCompare])
				return firstCompare; 
			else 
				return secondCompare; 
		}
		else
		{
			return -1; 
		}
	} 
}