#------------------------------------------------------------------------------
# Makefile for hello.c with Macros
#------------------------------------------------------------------------------
FLAGS = -std=c99 -Wall
SOURCES = main.c
OBJECTS = main.o
EXEBIN = main

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES)
	gcc -c $(FLAGS) $(SOURCES)

clean :
	rm $(EXEBIN) $(OBJECTS)

check : 
	gcc $(SOURCES)
	valgrind --leak-check=full $(EXEBIN)


