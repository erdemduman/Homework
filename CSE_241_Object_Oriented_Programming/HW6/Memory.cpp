#include <iostream>
#include <string>
#include <fstream>
#include <cstdlib> //for exit()
#include "CPU.h"
#include "CPUProgram.h"
#include "Memory.h"

using namespace std;

/*This constructor takes option as parameter and, fills the memory with zero*/
/*and sets the option.*/
Memory::Memory(int option){
	for(int a = 0; a < ADDRESS_SIZE; a++){
		addressArr[a] = 0;
	}

	setTheOption(option);
}
// no param constructor
Memory::Memory(){
}

void Memory::setMem(int index, int value){addressArr[index] = value;}// setter of memory
int Memory::getMem(int index)const{return addressArr[index];} //getter of memory
void Memory::setTheOption(int opt){tempOpt = opt;}//setter of option
int Memory::getTheOption()const{return tempOpt;}//getter of option

/*this function prints all the memory*/
void Memory::printAll(){
	for(int a = 0; a < ADDRESS_SIZE; a++){
		cout << "[" << a << "]" << " -> " << addressArr[a] << endl;
	}
}
	



