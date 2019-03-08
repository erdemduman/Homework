#ifndef CPU_H
#define CPU_H

#include <iostream>
#include <string>
#include <fstream>
#include <cstdlib> //for exit()

#include "Memory.h"




/*Comments are at the cpp files.*/

using namespace std;

const int ARR_SIZE = 5;

class CPU{

	public:
		CPU(int option);
		CPU();
		void setPC(int num); 
		const int getPC(); 
		void setRegister(int index, int value);
		const int getRegister(int index)const;
		void print();
		bool halted();
		void execute(string str,Memory &myMemory);

	private:
		int PC; //program counter
		int registerArr[ARR_SIZE]; //register array
		char tempOp; //temporary variable of option
		void optionController(int *registerArr, string inst, string reg1, 
		string reg2, int option, Memory &myMemory);
		int test = -1; //test variable to break program
		void setOption(int op);
		int getOption();
		void instParser(string tmp, bool &regLoop, string &inst, int &cursor);
		void reg1Parser(string tmp, bool &regLoop, string &reg1, int &cursor);
		void commaCheck(string tmp, int &cursor);
		void reg2Parser(string tmp, bool &regLoop, string &reg2, int &cursor);
		void instController(int *registerArr, string inst, string reg1, string reg2,Memory &myMemory);
		void movf(int *registerArr, string reg1, string reg2,Memory &myMemory);
		void addf(int *registerArr, string reg1, string reg2,Memory &myMemory);
		void subf(int *registerArr, string reg1, string reg2,Memory &myMemory);
		int jmpf(int *registerArr, string reg1, string reg2);
		int jpnf(int *registerArr, string reg1, string reg2);
		void prnf(int *registerArr, string reg1,Memory &myMemory);
		int convertYourStr(string str);
		int powerf(int number, int power);

};

#endif
