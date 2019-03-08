#include <iostream>
#include <string>
#include <fstream>
#include <cstdlib> //for exit()
#ifndef CPU_H
#define CPU_H

/*Commands are at the cpp files.*/

using namespace std;

const int ARR_SIZE = 5;

class CPU{

	public:
		CPU(char* option); 
		void setPC(int num); 
		const int getPC(); 
		void setRegister(int index, int value);
		const int getRegister(int index)const;
		void print();
		bool halted();
		void execute(string str);

	private:
		int PC; //program counter
		int registerArr[ARR_SIZE]; //register array
		char tempOp; //temporary variable of option
		void optionController(int *registerArr, string inst, string reg1, 
		string reg2, char option);
		int test = -1; //test variable to break program
		void setOption(char op);
		char getOption();
		void instParser(string tmp, bool &regLoop, string &inst, int &cursor);
		void reg1Parser(string tmp, bool &regLoop, string &reg1, int &cursor);
		void commaCheck(string tmp, int &cursor);
		void reg2Parser(string tmp, bool &regLoop, string &reg2, int &cursor);
		void instController(int *registerArr, string inst, string reg1, string reg2);
		void movf(int *registerArr, string reg1, string reg2);
		void addf(int *registerArr, string reg1, string reg2);
		void subf(int *registerArr, string reg1, string reg2);
		int jmpf(int *registerArr, string reg1, string reg2);
		int jpnf(int *registerArr, string reg1, string reg2);
		void prnf(int *registerArr, string reg1);
		int convertYourStr(string str);
		int powerf(int number, int power);

};

#endif
