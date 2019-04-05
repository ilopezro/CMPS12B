/**
 * Isai Lopez
 * ilopezro
 * Lab 2
 * Jan 22, 2018
 * (Doc Description)
 */

import java.io.*;
import java.util.Scanner;

public class FileReverse
{
	public static void main(String [] args) throws IOException
	{
		Scanner in = null;
		PrintWriter out = null;
		String line = null;
		String[] token = null; 

		// check number of command line arguments is at least 2 
		if(args.length < 2)
		{
			System.out.println("Usage: FileCopy <input file> <output file>");
			System.exit(1);
		}

		// open files
		in = new Scanner(new File(args[0]));
		out = new PrintWriter(new FileWriter(args[1]));

		// read lines from in, write lines to out 
		while( in.hasNextLine() )
		{
			line = in.nextLine().trim() + " ";
			token = line.split("\\s+");
			for(int i = 0; i < token.length; i++)
			{
				out.println(stringReverse(token[i], token[i].length()));
			}
		}

		// close files 
		in.close();
		out.close();
	}

	//return a String that is the reversal of the first n characters of s
	public static String stringReverse(String s, int n)
	{
		String newString = s; 
		if (n>0)
		{
			newString = stringReverse(s.substring(1), n-1) + s.charAt(0); 
			return newString; 
		}
		else 
			return s; 
	}
}