all: hw03

hw03: main.o CPU.o CPUProgram.o
	g++ -std=c++11 main.o CPU.o CPUProgram.o -o hw03

main.o: main.cpp
	g++ -std=c++11 -c main.cpp

CPU.o: CPU.cpp
	g++ -std=c++11 -c CPU.cpp

CPUProgram.o: CPUProgram.cpp
	g++ -std=c++11 -c CPUProgram.cpp

clean:
	rm *.o hw03
