#include <iostream>
#include <string>
#include <fstream>
#include <cstdlib> //for exit()
#include "CPU.h"
#include "CPUProgram.h"

using namespace std;

/*this constructor takes a string(name of file) as parameter to*/
/*send it to another function.*/
/*sets the first value of the counter as well.*/

CPUProgram::CPUProgram(string fileName){
    counter = 0; //counter for size function
	readFile(fileName);
	           
	
}

/*this function takes the name of file, that we mentioned above,*/
/*as parameter and reads the instructions that is found in file*/
/*to store them in an array.*/

void CPUProgram::readFile(string fileName){
	
	fstream fayl;
	fayl.open(fileName);
	
	while(!fayl.eof()){
		getline(fayl,database[counter]);		
		++counter;
		
	}

	getCount();

	fayl.close();
	
}

/*this function takes an index as parameter and returns one of the*/
/*lines of the file to use in execute function.*/ 

string CPUProgram::getLine(int index){
	
	string tmp;
	--index; //line number and index are not the same things.
	tmp = database[index];

	return tmp;

}

/* this function provides the counter variable for size function */

const int CPUProgram::getCount(){return counter-1;}

/*this function returns the line number of file.*/

const int CPUProgram::size(){return getCount();}




	


		


