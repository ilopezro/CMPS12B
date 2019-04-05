#include<stdio.h>
#include<stdlib.h>
#include<ctype.h>
#include<assert.h>
#include <string.h>

#define MAX_STRING_LENGTH 100

// function prototype 
void extract_alpha_num(char* s, char* a, char* d, char* p, char* w);

// function main which takes command line arguments 
int main(int argc, char* argv[]){
   FILE* in;        // handle for input file                  
   FILE* out;       // handle for output file                 
   char* line;      // string holding input line              
   char* alpha; // string holding all alpha chars 
   char* digits; // string holding all numbers
   char* punct; // string holding all punctuations
   char* space; //string holding all white space
   int lineCount = 1; 

   // check command line for correct number of arguments 
   if( argc != 3 ){
      printf("Usage: %s input-file output-file\n", argv[0]);
      exit(EXIT_FAILURE);
   }

   // open input file for reading 
   if( (in=fopen(argv[1], "r"))==NULL ){
      printf("Unable to read from file %s\n", argv[1]);
      exit(EXIT_FAILURE);
   }

   // open output file for writing 
   if( (out=fopen(argv[2], "w"))==NULL ){
      printf("Unable to write to file %s\n", argv[2]);
      exit(EXIT_FAILURE);
   }

   // allocate strings line, alpha, digits, punct, and space on the heap 
   line = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   alpha = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   digits = calloc(MAX_STRING_LENGTH+1, sizeof(char)); 
   punct = calloc(MAX_STRING_LENGTH+1, sizeof(char)); 
   space = calloc(MAX_STRING_LENGTH+1, sizeof(char)); 
   assert( line!=NULL && alpha!=NULL && digits!=NULL && punct!=NULL && space!=NULL);

   // read each line in input file, extract all characters 
   while( fgets(line, MAX_STRING_LENGTH, in) != NULL ){
      extract_alpha_num(line, alpha, digits, punct, space);
      fprintf(out, "line %d contains:\n", lineCount); 
      if(strlen(alpha) == 1)
      {
         fprintf(out, "1 alphabetic character: %s\n", alpha);
      }
      else
      {
         fprintf(out, "%d alphabetic characters: %s\n", (int)strlen(alpha), alpha);
      }

      if(strlen(digits) == 1)
      {
         fprintf(out, "1 numeric character: %s\n", digits);
      }
      else
      {
         fprintf(out, "%d numeric characters: %s\n", (int)strlen(digits), digits);
      }
      if(strlen(punct) == 1)
      {
         fprintf(out, "1 punctuation character: %s\n", punct);
      }
      else
      {
         fprintf(out, "%d punctuation characters: %s\n", (int)strlen(punct), punct);
      }
      if(strlen(space) == 1)
      {
         fprintf(out, "1 whitespace character: %s\n", space);
      }
      else
      {
         fprintf(out, "%d whitespace characters%s:\n", (int)strlen(space), space);
      }
      lineCount++; 
   }

   // free heap memory 
   free(line);
   free(alpha);
   free(digits); 
   free(punct); 
   free(space);  

   // close input and output files 
   fclose(in);
   fclose(out);

   return EXIT_SUCCESS;
}

// function definition 
void extract_alpha_num(char* s, char* a, char* d, char* p, char* w)
{
   int i =0, j=0, k=0, l=0, m=0;
   while(s[i]!='\0' && i<MAX_STRING_LENGTH)
   {
      if( isalpha( (int) s[i]) )
      { 
         a[j++] = s[i];
         i++; 
      }
      if( isdigit((int) s[i]))
      {
         d[k++] = s[i];
         i++; 
      }
      if( ispunct((int) s[i]))
      { 
         p[l++] = s[i]; 
         i++; 
      }
      if ( isspace((int) s[i]))
      {
         w[m++] = s[i]; 
         i++; 
      }
   }
   a[j] = '\0';
   d[k] = '\0';
   p[l] = '\0';
   w[m] = '\0';
}