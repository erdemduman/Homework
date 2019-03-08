all: floristApp

floristApp: 151044005_main.o
	gcc -o floristApp 151044005_main.o -lm -pthread

151044005_main.o: 151044005_main.c
	gcc -c 151044005_main.c

clean:
	rm -f floristApp
	rm -f *.o
