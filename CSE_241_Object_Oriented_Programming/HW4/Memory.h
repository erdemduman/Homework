#include <iostream>
#include <string>
#include <fstream>
#include <cstdlib> //for exit()
#ifndef MEM_H
#define MEM_H

/*Comments are at the cpp files.*/

using namespace std;

const int ADDRESS_SIZE = 50;

class Memory{
	
	public:
		Memory(int option);
		Memory();
		int getMem(int index);
		void setMem(int index, int value);
		void printAll();
		void setTheOption(int opt);
		int getTheOption();
	private:
		unsigned int addressArr[ADDRESS_SIZE];
		int tempOpt;

};	

#endif
