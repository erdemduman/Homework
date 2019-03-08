all: exe

exe: 151044005_main.o
	gcc -o exe 151044005_main.o -pthread

151044005_main.o: 151044005_main.c
	gcc -c 151044005_main.c

clean:
	rm -f exe
	rm -f *.o
