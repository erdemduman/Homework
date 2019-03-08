/*******************************************************/
/*                                                     */
/*         Hakki_Erdem_Duman_151044005                 */
/*      Object_Oriented_Programming(CSE_241)           */
/*                    HW_04                            */
/*                                                     */
/*******************************************************/
/*******************************************************/
/*                                                     */
/* This program takes some commands from a text file   */
/* to give a meaning them and run.                     */
/*                                                     */
/*******************************************************/
/*                                                     */
/* Arguments:                                          */
/* - argv[0] = Name of program.                        */
/* - argv[1] = Name of the file that will be used.     */
/* - argv[2] = Option of the program.                  */
/*                                                     */
/* Outputs:                                            */
/* - Output of PRN function.                           */
/* - Output of the argv[2] option.                     */
/*                                                     */
/*******************************************************/

/*                      Includes                       */

#include <iostream>
#include <string>
#include <fstream>
#include <cstdlib> //for exit()

/*                      Headers                        */

#include "CPU.h"
#include "CPUProgram.h"
#include "Memory.h"
#include "Computer.h"

using namespace std;

int main(int argc, char* argv[]){

	string fileName = argv[1];
	int option = atoi(argv[2]);

	CPU myCPU(option);
	CPUProgram myCPUProgram(option);
	Memory myMemory(option);
	
	/*myMemory.setMem(0,100);

	myCPU.execute("MOV #0, R1",myMemory);
	myCPU.execute("MOV R1, #1",myMemory);
	
	myCPU.print();
	myMemory.printAll();*/
	
	/*myCPUProgram.readFile(fileName);
	cout << myCPUProgram.getLine(0) << endl;
	cout << myCPUProgram.getLine(myCPUProgram.size()-1) << endl;*/

	myCPUProgram.readFile(fileName);
	Computer myComputer1(myCPU,myCPUProgram,myMemory,option);
	Computer myComputer2(option);
	myComputer2.setCPU(myComputer1.getCPU());
	myComputer2.setCPUProgram(myComputer1.getCPUProgram());
	myComputer2.setMemory(myComputer1.getMemory());
	myComputer2.execute();



	return 0;
}
