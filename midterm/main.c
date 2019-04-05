//#include <stdio.h>
//#include <stdlib.h>
//
//int f(int x, int y){
//    int u;
//    u = x*y;
//    printf("in f\n");
//    return(u+x+y);
//}
//
//int g(int* p, int* q){
//    int v;
//    v = *p + *q;
//    printf("in g, before f\n");
//    *q = f(v, *p);
//    printf("in g, after f\n");
//    return(v-*q);
//}
//
//int main(void) {
//    int a =1, b=2, c=3;
//    printf("in main, before f and g\n");
//    a = f(a,b);
//    b = g(&b, &c);
//    printf("in main, after f and g\n");
//    printf("a = %d, b = %d, c = %d", a, b, c);
//    return EXIT_SUCCESS;
//}
//
//#include<stdio.h>
//#include<stdlib.h>
//int time;
//int a(int x){
//    int i;i = x*x;time++;return(i); } int b(int y){
//    int j;
//    j = y+a(y);
//    time++;
//    return(j);
//}
//int c(int z){
//    int k;
//    k = a(z)+b(z); // first call a() then b()
//    time++;
//    return(k);
//}
//int main(void){
//    int q, r;
//    time = 0;
//    q = b(5);
//    r = c(2);
//    printf("q=%d, r=%d, time=%d\n", q, r, time);
//    return(EXIT_SUCCESS);
//}

#include<stdio.h>
#include<stdlib.h>
int main(void){
    int i, j;
    double x = 4.2, y;
    double * A = calloc(4, sizeof(double));
    double B[] = {1.2, 5.3, 2.1, 3.4};
    double *p, *q;
    p = malloc(sizeof(double));
    y = x+2;
    q = &y;
    *p = *q + 2.5;
    for(i=0; i<4; i++){
        j = 3-i;
        *(A+i) = B[j] + i;
    }
    printf("%f, %f, %f, %f\n", *A, *B, *p, *q);
    A = B;
    printf("%f, %f, %f, %f\n", *A, *(A+1), *(A+2), *(A+3) );
    free(p); 
    return(EXIT_SUCCESS);
}