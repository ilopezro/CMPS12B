#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"
#define tableSize 101


typedef struct NodeObj{
    char* key;
    char* value;
    struct NodeObj* next;
} NodeObj;


typedef NodeObj* Node;

Node newNode(char* code, char* thing) {
    Node N = malloc(sizeof(NodeObj));
    assert(N!=NULL);
    N->key = code;
    N->value = thing;
    N->next = NULL;
    return(N);
}

void freeNode(Node* pN){
    if( pN!=NULL && *pN!=NULL ){
        free(*pN);
        *pN = NULL;
    }
}

// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
    int sizeInBits = 8*sizeof(unsigned int);
    shift = shift & (sizeInBits - 1);
    if ( shift == 0 )
        return value;
    return (value << shift) | (value >> (sizeInBits - shift));
}
// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input) {
    unsigned int result = 0xBAE86554;
    while (*input) {
        result ^= *input++;
        result = rotate_left(result, 5);
    }
    return result;
}
// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(char* key){
    return pre_hash(key)%tableSize;
}

typedef struct DictionaryObj {
    int numItems;
    //changed here 
    Node *hashtable;
} DictionaryObj;

Dictionary newDictionary(void){
    Dictionary a = malloc(sizeof(DictionaryObj)); //changed here
    assert(a!=NULL);
    //changed herer
    a->hashtable = calloc(tableSize, sizeof(Node)); 
    a->numItems = 0;
    return a;
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD) {
    if( pD!=NULL && *pD!=NULL ){
        if(!isEmpty(*pD)) makeEmpty(*pD);
        //changed here
        free((*pD)->hashtable);
        free(*pD);
        *pD = NULL;
    }
}

// isEmpty()
// returns 1 (true) if S is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D) {
    return (D->numItems == 0);
}

// size()
// returns the number of (key, value) pairs in D
// pre: none
int size(Dictionary D) {
    return D->numItems;
}

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k) {
    Node a = D->hashtable[hash(k)];
    while (a != NULL) {
        if (a->key == k)
            return a->value;
        a = a->next;
    }
    return NULL;
}

// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v) {
    if (lookup(D, k) == NULL) {
        if (D->hashtable[hash(k)] == NULL) {
            D->hashtable[hash(k)] = newNode(k, v);
        }
        else {
            Node a = D->hashtable[hash(k)];
            while (a->next != NULL) {
                a = a->next;
            }
            a->next = newNode(k, v);
        }
            D->numItems++;
    }
    else
        printf("Same key error\n");

}

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k) {
    if (lookup(D,k) != NULL) {
        Node a = D->hashtable[hash(k)];
        if (a->key == k) {
            D->hashtable[hash(k)] = a->next;
            freeNode(&a);

        }
        else {
            while (a->key != k) {
                a = a->next;
            }
            Node b = a->next;
            freeNode(&a);
            a = b;
        }
        D->numItems--;
    }
    else
        printf("Key doesn't exist\n");
}

// makeEmpty()
// re-sets D to the empty state.
// pre: none
void makeEmpty(Dictionary D) {
    D->numItems = 0;
    for (int i = 0; i < tableSize; i++) {
        if (D->hashtable[i] != NULL) {
            freeNode(&(D->hashtable[i]));
            D->hashtable[i] = NULL; 
        }
    }
}

// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D) {

    for(int i = 0; i < tableSize; i++){
        if(D->hashtable[i] != NULL){
            printf("%s %s\n", D->hashtable[i]->key, D->hashtable[i]->value);
        }
    }

}

