/*******************************************************/
/*                                                     */
/*         Hakki_Erdem_Duman_151044005                 */
/*      Object_Oriented_Programming(CSE_241)           */
/*                    HW_03                            */
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

using namespace std;

int main(int argc, char* argv[]){

	CPUProgram progVar(argv[1]); //This variable calls the constructor of CPUProgram class.
	CPU cpuVar(argv[2]); //This variable calls the constructor of CPU class.

	/*This loop gets a counter to return a string to run the command.*/ 

	while(!cpuVar.halted()){
		string ins = progVar.getLine(cpuVar.getPC());
		cpuVar.execute(ins);
			
	}

	cpuVar.print();
	cout << "Size: " << progVar.size() << endl;

	return 0;
}
