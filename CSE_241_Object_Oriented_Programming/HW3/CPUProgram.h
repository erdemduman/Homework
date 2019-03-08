#include <iostream>
#include <string>
#include <fstream>
#include <cstdlib> //for exit()
#ifndef CPUP_H
#define CPUP_H

/*Commands are at the cpp files.*/

using namespace std;

const int LINE_SIZE = 200;

class CPUProgram{

	public:
		CPUProgram(string fileName);
		void readFile(string fileName);
		string getLine(int index);
		const int size();
		const int getCount();

	private:
		int counter;
		string database[LINE_SIZE]; //string array to store all of
};									//the lines of file

#endif
