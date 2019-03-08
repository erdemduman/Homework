all: multiprocess_DFT

multiprocess_DFT: main.o
	gcc -o multiprocess_DFT main.o -lm 

151044005_main.o: 151044005_main.c
	gcc -std=c11 -c main.c

clean:
	rm -f *.o
	rm -f multiprocess_DFT
