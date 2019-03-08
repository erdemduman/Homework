#ifndef CPUP_H
#define CPUP_H

#include <iostream>
#include <string>
#include <fstream>
#include <cstdlib> //for exit()


/*Comments are at the cpp files.*/

using namespace std;

namespace spaceOfName{

	class CPUProgramDyn{
		public:
			CPUProgramDyn(int optimus);
			CPUProgramDyn();
			CPUProgramDyn(const CPUProgramDyn&);
			~CPUProgramDyn();
			void ReadFile(const char* fileName);
			string getLine(int index) const;
			int size() const;
			void setSize(int);
			int getCount() const;
			string operator[](int);
			CPUProgramDyn operator+(const string); 
			CPUProgramDyn& operator=(const CPUProgramDyn&);
			CPUProgramDyn& operator+=(const string);
			CPUProgramDyn operator+(const CPUProgramDyn&);
			friend ostream& operator<<(ostream &output, const CPUProgramDyn &other);
			bool operator ==(const CPUProgramDyn &other);
			bool operator !=(const CPUProgramDyn &other);
			bool operator <(const CPUProgramDyn &other);
			bool operator >(const CPUProgramDyn &other);
			bool operator <=(const CPUProgramDyn &other);
			bool operator >=(const CPUProgramDyn &other);
		
			CPUProgramDyn& operator--();
			CPUProgramDyn operator--(int);	

			CPUProgramDyn operator()(int, int);

		private:
			int counter;
			string* instDatabase; //this one will be dynamic array.
			int option;
	};		
};

#endif
