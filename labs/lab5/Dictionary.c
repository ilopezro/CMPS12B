//Isai Lopez
//ilopezro
//Dustin Adams
//CMPS12B - Lab 5
//implementing a Dictionary ADT in C

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include "Dictionary.h"

//private types--------------------------

// NodeObj
typedef struct NodeObj{
   char* mKey;
   char* mValue; 
   struct NodeObj* mNext;
} NodeObj;

// Node
typedef NodeObj* Node;

// newNode()
// constructor of the Node type
Node newNode(char* key, char* value) {
   Node N = malloc(sizeof(NodeObj));
   assert(N!=NULL);
   N->mKey = key;
   N->mValue = value;
   N->mNext = NULL; 
   return(N);
}


// freeNode()
// destructor for the Node type
void freeNode(Node* pN){
   if( pN!=NULL && *pN!=NULL ){
      free(*pN);
      *pN = NULL;
   }
}

// Dictionary Obj
typedef struct DictionaryObj{
	Node head;
   int numItems;
} DictionaryObj;

//find key 
Node findKey(Dictionary D, char* key){
	// Node temp = (*D)->head; 
	Node temp = D->head;  
	while(temp!=NULL)
	{
		if(strcmp(key, temp->mKey) ==0)
			return temp; 
		temp = temp->mNext; 
	}
	freeNode(&temp); 
	return NULL; 
} 



//public types ---------------------------------------------

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void){
	Dictionary D = malloc(sizeof(DictionaryObj)); 
	assert(D!=NULL); 
	D->head = NULL; 
	D->numItems = 0; 
	return D; 
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD){
	if( pD!= NULL && *pD!=NULL){
		if( !isEmpty(*pD) ) makeEmpty(*pD); 
			free(*pD); 
		*pD = NULL; 
	}
}

// isEmpty()
// returns 1(true) if D is empty, 0(false) otherwise 
// pre: none 
int isEmpty(Dictionary D){
	return(D->numItems == 0); 
}

// size() 
// returns the number of (key, value) pairs in D
// pre: none
int size(Dictionary D){
	return(D->numItems); 
}

// lookup()
// returns the value v such that (k,v) is in D, or returns NULL if no such value v exists 
// pre: none
char* lookup(Dictionary D, char*k){
	if(findKey(D, k) != NULL)
	{
		Node temp = D->head; 
		while(temp != findKey(D, k))
		{
			temp = temp->mNext; 
		}
		return temp->mValue; 
	}
	else
		return NULL; 
}

//insert()
//inserts new (key,value) pair into D
// pre: lookup(D,k) == null
void insert(Dictionary D, char* k, char*v)
{
	if(lookup(D, k) != NULL)
	{
		fprintf(stderr, "Error: Cannot insert duplicate keys\n"); 
		exit(EXIT_FAILURE); 
	}
	// Node toAdd = newNode(k,v); 
	if(D->numItems == 0)
	{
		D->head = newNode(k,v); 
	}
	else
	{
		Node temp = D->head; 
		while(temp->mNext!= NULL)
		{
			temp = temp->mNext; 
		}
		temp->mNext = newNode(k,v);
		// freeNode(&temp);  
	}
	D->numItems++; 
}

//delete()
//deletes pair with the key K 
// pre: lookup(D,k) != null
void delete(Dictionary D, char* k)
{
	if(lookup(D, k) == NULL)
	{
		fprintf(stderr, "Error: cannot delete non-existent key\n");
		exit(EXIT_FAILURE); 
	}
	else
	{
		if(D->head == findKey(D, k))
		{
			Node temp = D->head; 
			D->head = temp->mNext;
			freeNode(&temp); 
		}
		else
		{
			Node temp = D->head; 
			while(temp->mNext != findKey(D, k))
			{
				temp = temp->mNext; 
			}
			Node toDelete = temp->mNext; 
			temp->mNext = temp->mNext->mNext; 
			freeNode(&toDelete); 
		}
		D->numItems--; 	
	}
}


//makeEmpty()
// re-sets D to the empty state
// pre: none
void makeEmpty(Dictionary D)
{
	while(D->numItems>0)
	{
		Node temp = D->head; 
		D->head = temp->mNext; 
		freeNode(&temp); 
		D->numItems--; 
	} 
}

// printDictionary()
// pre: none
// prints a text representation of D to the file pointed out by out
void printDictionary(FILE* out, Dictionary D)
{
	Node N; 
	for(N=D->head; N!=NULL; N=N->mNext)
	{
		fprintf(out, "%s %s \n", N->mKey, N->mValue); 
	}
	freeNode(&N); 
}