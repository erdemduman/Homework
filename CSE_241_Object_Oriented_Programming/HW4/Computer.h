#include <iostream>
#include <string>
#include <fstream>
#include <cstdlib> //for exit()
#ifndef COMP_H
#define COMP_H

#include "CPU.h"
#include "CPUProgram.h"
#include "Memory.h"

/*Comments are at the cpp files.*/

using namespace std;

class Computer{
	public:
		Computer(CPU &myCPU, CPUProgram &myCPUProgram, Memory &myMemory, int option);
		Computer(int option);
		void setCPU(CPU CPUvar);
		void setMemory(Memory Memvar);
		void setCPUProgram(CPUProgram CPUProgvar);
		CPU getCPU();
		Memory getMemory();
		CPUProgram getCPUProgram();
		void setOption(int option);
		int getOption();
		void execute();
	private:
		CPU cpuVARinComp;
		Memory memoryVARinComp;
		CPUProgram cpuProgramVARinComp;
		int opt;
		
};

#endif
		
		
