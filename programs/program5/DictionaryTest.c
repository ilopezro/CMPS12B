//
// Created by Isai Lopez on 3/6/18.
//

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

int main(){
    Dictionary D = newDictionary();
    char* word1[] = {"one","two","three","four","five","six","seven"};
    char* word2[] = {"1","2","3","4","5","6","7"};

//    //Test #1 for isEmpty() + size()
//    printf("%s\n", (isEmpty(D)?"true":"false"));
//    printf("Total size of Dictionary = %d\n", size(D));

//    //Test #2 for lookup()
//    printf("%s", lookup(D, word1[2]));

    //Test #3 for insert(), isEmpty, size(), and makeEmpty()
//    for(int i = 0; i < 7; i++){
//        insert(D, word1[i], word2[i]);
//    }
//    printDictionary(stdout, D);
//    printf("Total size of Dictionary = %d\n", size(D));
//    printf("isEmpty(): %s\n", (isEmpty(D)?"true":"false"));
//    makeEmpty(D);
//    printDictionary(stdout, D);
//    printf("Total size of Dictionary = %d\n", size(D));
//    printf("isEmpty(): %s\n", (isEmpty(D)?"true":"false"));

    //Test #4 for insert(), isEmpty(), size(), and delete(), and printDictionary()
    for(int i = 0; i < 7; i++){
        insert(D, word1[i], word2[i]);
    }
    printDictionary(stdout, D);
    printf("Total size of Dictionary = %d\n", size(D));
    printf("isEmpty(): %s\n", (isEmpty(D)?"true":"false"));
    delete(D,"one");
    delete(D, "three");
//    delete(D, "zero");
    printDictionary(stdout, D);
    printf("Total size of Dictionary = %d\n", size(D));
    printf("isEmpty(): %s\n", (isEmpty(D)?"true":"false"));

    return(EXIT_SUCCESS);
}
