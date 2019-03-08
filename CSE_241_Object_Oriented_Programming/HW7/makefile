all: exe

exe: _mainTester.o BigramMap.o 
	g++ -std=c++11 _mainTester.o BigramMap.o -o exe

_mainTester.o: _mainTester.cpp
	g++ -std=c++11 -c _mainTester.cpp

BigramMap.o: BigramMap.cpp
	g++ -std=c++11 -c BigramMap.cpp

clean:
	rm *.o exe
