all: exe

exe: _mainTester.o CPU.o CPUProgram.o Memory.o Computer.o CPUProgramDyn.o
	g++ -std=c++11 _mainTester.o CPU.o CPUProgram.o Memory.o Computer.o CPUProgramDyn.o -o exe

_mainTester.o: _mainTester.cpp
	g++ -std=c++11 -c _mainTester.cpp

CPU.o: CPU.cpp
	g++ -std=c++11 -c CPU.cpp

CPUProgram.o: CPUProgram.cpp
	g++ -std=c++11 -c CPUProgram.cpp

Memory.o: Memory.cpp
	g++ -std=c++11 -c Memory.cpp

Computer.o: Computer.cpp
	g++ -std=c++11 -c Computer.cpp

CPUProgramDyn.o: CPUProgramDyn.cpp
	g++ -std=c++11 -c CPUProgramDyn.cpp

clean:
	rm *.o exe
