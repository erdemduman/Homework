all: tiffProcessor

tiffProcessor: 151044005_main.o
	gcc -o tiffProcessor 151044005_main.o

151044005_main.o: 151044005_main.c
	gcc -ansi -pedantic-errors -Wall -std=c11 -c 151044005_main.c

clean:
	rm -f *.o
	rm -f tiffProcessor
