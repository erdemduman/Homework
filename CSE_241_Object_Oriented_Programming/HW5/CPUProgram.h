#include <iostream>
#include <string>
#include <fstream>
#include <cstdlib> //for exit()
#include <vector>
#ifndef CPUP_H
#define CPUP_H

/*Comments are at the cpp files.*/

using namespace std;

class CPUProgram{
	public:
		CPUProgram(int option);
		CPUProgram();
		void ReadFile(string fileName);
		string getLine(int index) const;
		int size() const;
		void setSize(int);
		int getCount() const;
		string operator[](int);
		CPUProgram operator+(const string); 
		CPUProgram& operator+=(const string);
		CPUProgram operator+(const CPUProgram&);
		friend ostream& operator<<(ostream &output, const CPUProgram &other);
		bool operator ==(const CPUProgram &other);
		bool operator !=(const CPUProgram &other);
		bool operator <(const CPUProgram &other);
		bool operator >(const CPUProgram &other);
		bool operator <=(const CPUProgram &other);
		bool operator >=(const CPUProgram &other);
		
		CPUProgram& operator--();
		CPUProgram operator--(int);	

		CPUProgram operator()(int, int);

	private:
		int counter;
		vector <string> database; //string vector to store all of
};									//the lines of file

#endif
