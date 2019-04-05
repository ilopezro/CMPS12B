/**
 * Isai Lopez
 * ilopezro
 * Program 2
 * Jan 26, 2018
 * Uses Binary Search, MergeSort, and Merge in order to find the target words using commmand line
 * arguments from Unix
 */

import java.io.*;
import java.util.Scanner; 

public class Search 
{
	public static void main(String [] args) throws IOException
	{
		//initialize the variable we will use: file scanner, array of String 
		Scanner in = null; 
		Scanner in2 = null; 
		String[] stringArray; 
		int[] lineNum; 

		//checks if command line has at least two arguments, a file and a string 
		if(args.length < 2)
		{
			System.out.println("Usage: Search file target1 [target2...]");
			System.exit(1);
		}

		//open file 
		in = new Scanner(new File(args[0]));
		in2 = new Scanner(new File(args[0]));

		//counts the lines in the file 
		int lineCount = lineCount(in); 

		//initiate String array and lineNum array and fill up arrays
		stringArray = new String[lineCount]; 
		lineNum = new int[lineCount];  
		int n = 0; 

		//copies file into array 
		while(in2.hasNextLine())
		{
			stringArray[n] = in2.nextLine();
			lineNum[n] = (n+1); 
			n++; 
		}

		//sorts the array in alphabetical order
		mergeSort(stringArray, lineNum, 0, stringArray.length-1); 

		// for loops to check if the target words are found within the array
		int finding; 
		for(int i = 1; i < args.length; i++)
		{
			finding = binarySearch(stringArray, 0, stringArray.length-1, args[i]);
			if(finding != -1)
			{
				
				System.out.println(args[i] + " found on line " + lineNum[finding]);
			}
			else 
				System.out.println(args[i] + " not found");
		}
		
		//closes file
		in2.close();
	}

	static int lineCount(Scanner secondIn)
	{
		// count the number of lines in file
		int lineCount = 0;
		while( secondIn.hasNextLine() )
		{
			secondIn.nextLine();
			lineCount++;
		}
		secondIn.close();
		return lineCount; 
	}

	public static void mergeSort(String[] word, int[] lineNumber, int p, int r)
	{
		int q;
		if(p < r) 
		{
			q = (p+r)/2;
			mergeSort(word, lineNumber, p, q);
			mergeSort(word, lineNumber, q+1, r);
			merge(word, lineNumber, p, q, r);
		}

	}

	public static void merge(String[] word, int[] lineNumber, int p, int q, int r)
	{
		int n1 = q-p+1;
		int n2 = r-q;
		String[] L = new String[n1];
		String[] R = new String[n2];
		int[] littleL = new int[n1];
		int[] littleR = new int[n2];
		int i, j, k;

		for(i=0; i<n1; i++)
		{
			L[i] = word[p+i];
			littleL[i] = lineNumber[p+i]; 
		}
		for(j=0; j<n2; j++)
		{ 
			R[j] = word[q+j+1];
			littleR[j] = lineNumber[q+j+1]; 
		}

		i = 0; j = 0;
		for(k=p; k<=r; k++)
		{
			if( i<n1 && j<n2 )
			{
				if( L[i].compareTo(R[j]) < 0 )
				{
					word[k] = L[i];
					lineNumber[k] = littleL[i]; 
					i++;
				}
				else
				{
					word[k] = R[j];
					lineNumber[k] = littleR[j]; 
					j++;
				}
			}
			else if( i<n1 )
			{
				word[k] = L[i];
				lineNumber[k] = littleL[i]; 
				i++;
			}
			else // j<n2
			{ 
				word[k] = R[j];
				lineNumber[k] = littleR[j]; 
				j++;
			}
		}
	}

	static int binarySearch(String[] A, int p, int r, String target)
	{
		int q;
		if(p > r) 
			return -1;
		else
		{
			q = (p+r)/2;
			if(target.compareTo(A[q]) == 0)
				return q;
			else if(target.compareTo(A[q]) < 0)
				return binarySearch(A, p, q-1, target);
			else
				// target > A[q]
				return binarySearch(A, q+1, r, target);
		}
	}
}