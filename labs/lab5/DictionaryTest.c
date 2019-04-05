// Isai Lopez
// ilopezro
// Dustin Adams 
// CMPS12B - Lab 5

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

int main(int argc, char* argv[])
{
	Dictionary D = newDictionary(); 

	//Test #1
	bool isEmpty = D.isEmpty(); 
	fprintf(out, "dictionary should be empty" + isEmpty);
}