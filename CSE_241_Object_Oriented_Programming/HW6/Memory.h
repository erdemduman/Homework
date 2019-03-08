#ifndef MEM_H
#define MEM_H

#include <iostream>
#include <string>
#include <fstream>
#include <cstdlib> //for exit()


/*Comments are at the cpp files.*/

using namespace std;

const int ADDRESS_SIZE = 50;

class Memory{
	
	public:
		Memory(int option);
		Memory();
		int getMem(int index)const;
		void setMem(int index, int value);
		void printAll();
		void setTheOption(int opt);
		int getTheOption()const;
	private:
		unsigned int addressArr[ADDRESS_SIZE];
		int tempOpt;

};	

#endif
