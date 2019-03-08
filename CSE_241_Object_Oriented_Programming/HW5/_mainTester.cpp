/*******************************************************/
/*                                                     */
/*         Hakki_Erdem_Duman_151044005                 */
/*      Object_Oriented_Programming(CSE_241)           */
/*                    HW_05                            */
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
/*													   */
/* Overloads:										   */ 
/*  + operation(with an obj and string)				   */
/*  post -- and pre -- operations				       */
/*  () operation									   */
/*  [] operation									   */
/*  += operation									   */
/*  boolean operations (==, >=, <=, !=, <, >)		   */
/*  << operation									   */
/*													   */
/*													   */ 
/*                                                     */
/*******************************************************/

/*                      Includes                       */

#include "requiredIncs.h"

int main(int argc, char* argv[]){

	string filename = argv[1];
	int option = atoi(argv[2]);
	
	CPUProgram myCPUProgram(option);

	myCPUProgram.ReadFile(filename);

	cout << myCPUProgram[0] << endl;
	cout << myCPUProgram[myCPUProgram.size()-1] << endl;

	cout << ((myCPUProgram + "MOV R1, #45")[myCPUProgram.size()]) << endl;

	myCPUProgram += "MOV R1, #45";
	cout << myCPUProgram[myCPUProgram.size()-1] << endl;

	CPUProgram myOtherCPUProgram(option); 
	myOtherCPUProgram.ReadFile(filename);

	CPUProgram temp(option);

	temp =  myCPUProgram + myOtherCPUProgram;

	cout << (myCPUProgram + myOtherCPUProgram) << endl;

	cout << (myCPUProgram == myOtherCPUProgram ? "DONE" : "FAIL") << endl; 
	cout << (myCPUProgram <= myOtherCPUProgram ? "DONE" : "FAIL") << endl; 
	cout << (myCPUProgram > myOtherCPUProgram ? "FAIL" : "DONE") << endl; 
	
	--myOtherCPUProgram; 
	
	cout << (myCPUProgram != myOtherCPUProgram ? "DONE" : "FAIL") << endl; 
	cout << (myCPUProgram >= myOtherCPUProgram ? "DONE" : "FAIL") << endl; 
	cout << (myCPUProgram < myOtherCPUProgram ? "FAIL" : "DONE") << endl; 

	cout << myCPUProgram(5, 10) << endl;
	

	return 0;
}
