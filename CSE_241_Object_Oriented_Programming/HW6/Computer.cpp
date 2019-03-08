#include <iostream>
#include <string>
#include <fstream>
#include <cstdlib> //for exit()
#include "CPU.h"
#include "CPUProgram.h"
#include "Memory.h"
#include "Computer.h"

using namespace std;

/*this constructor takes the variables of CPU, CPUProgram, Memory and option as*/
/*parameters and copy the variables to the variables of itself.*/

Computer::Computer(CPU &myCPU, CPUProgram &myCPUProgram, Memory &myMemory, int option){
	
	setOption(option);
	setCPU(myCPU);
	setMemory(myMemory);
	setCPUProgram(myCPUProgram);

}

Computer::Computer(int option){setOption(option);}

/*these functions copy the parameters to the variables of Computer class.*/

void Computer::setCPU(CPU CPUvar){cpuVARinComp = CPUvar;}
void Computer::setMemory(Memory Memvar){memoryVARinComp = Memvar;}
void Computer::setCPUProgram(CPUProgram CPUProgvar){cpuProgramVARinComp = CPUProgvar;}

/*these functions return the variables that is filled by parameters*/

CPU Computer::getCPU()const{return cpuVARinComp;}
Memory Computer::getMemory()const{return memoryVARinComp;}
CPUProgram Computer::getCPUProgram()const{return cpuProgramVARinComp;}

void Computer::setOption(int option){opt = option;}//option setter
int Computer::getOption(){return opt;}//option getter

/*this function uses the variables that is filled by CPU, CPUProgram and Memory variables*/
/*and runs the program according to given file*/

void Computer::execute(){

	while(!cpuVARinComp.halted()){
		string instruction = cpuProgramVARinComp.getLine(cpuVARinComp.getPC());
		cpuVARinComp.execute(instruction,memoryVARinComp);
	}
	
	

}
	
