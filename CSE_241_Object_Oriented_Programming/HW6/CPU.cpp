#include <iostream>
#include <string>
#include <fstream>
#include <cstdlib> //for exit()
#include "CPU.h"
#include "CPUProgram.h"
#include "Memory.h"

using namespace std;


/*this constructor takes an option as parameter to set the option.*/
/*sets the first values of PC and registerArr as well.*/

CPU::CPU(int option){
	
	for(int counter = 0; counter < ARR_SIZE; counter++){
		registerArr[counter] = 0;
	}	
	PC = 0;

	setOption(option);

}

CPU::CPU(){}

/*this function updates the registerArr according to their indices*/

void CPU::setRegister(int index, int num){registerArr[index] = num;}

/*this function returns the registerArr according to their indices*/

const int CPU::getRegister(int index)const{return registerArr[index];}

/*this function sets the option that comes from the constructor.*/

void CPU::setOption(int op){tempOp = op;}

/*this function returns the option that comes from the constructor.*/

int CPU::getOption(){return tempOp;}

/*the function that controls the option.*/
/*it prints the register according to option as well.*/

void CPU::optionController(int *registerArr, string inst, string reg1, 
string reg2, int option, Memory &myMemory){

	if(option == 1){
    	cout << inst << "   " << reg1 << "   " 
        << reg2 << " -     " << "R1:" << registerArr[0] 
        << "   " << "R2:" << registerArr[1] << "   R3:" <<
        registerArr[2] << "   R4:" << registerArr[3] << 
        "   R5:" << registerArr[4] << endl;
	}

	if(option == 2){
		cout << inst << "   " << reg1 << "   " 
        << reg2 << " -     " << "R1:" << registerArr[0] 
        << "   " << "R2:" << registerArr[1] << "   R3:" <<
        registerArr[2] << "   R4:" << registerArr[3] << 
        "   R5:" << registerArr[4] << endl;

		for(int a = 0; a<ADDRESS_SIZE; a++){
			cout << "[" << a << "] -> " << myMemory.getMem(a) << endl;
		} 
	}
			
            
     else if(option != 0 && option != 1 && option != 2)
     	cerr << "There is no option like this." << endl;
}

/*updates program counter*/

void CPU::setPC(int num){PC = num;}

/*returns program counter*/

const int CPU::getPC(){return PC;}

/*this function is the most important function because this one*/
/*leads all of the functions to get their job done.*/
/*takes a string as parameter(command) runs it with the help of*/
/*other functions*/ 

void CPU::execute(string str, Memory &myMemory){
	
	bool regLoop = false; //parse loop
	int proCount = getPC(); //variable to update PC
	int cursor = 0; // variable to move on command string
	string inst,reg1,reg2; //variables to store instruction and parameters
	str += ';'; /*I put ';' character at the end of all lines to 
                work easier.*/
	
	//this function parses the instruction from string.

    instParser(str,regLoop,inst,cursor);
            
    regLoop = false;

    /*since "halt" does not get any parameters, it is checked after */
    /*getting the instruction. if there is a halt instruction, it makes the*/
	/* test variable zero and calls the halted function to stop program.*/

    if((inst == "HLT")||(inst == "hlt")){
    	reg1 = "o";
		reg2 = "o";
		cout << "HLT" << endl;
		test = 0;
        halted();
    }

    

	else{
	
		//this function gets the first parameter of instruction.
		reg1Parser(str,regLoop,reg1,cursor);
		        

		/* since jump instruction can take only one parameter, it is*/
		/* checked after getting the first parameter.*/
		        
		if((inst == "JMP"||inst == "jmp")&&
		(reg1[0] >= '0' && reg1[0] <= '9'))
		            
			reg2 = "o";

		 /* the same stuff happens for the print instruction.*/
		        
		 else if(inst == "PRN"||inst == "prn")
		 	
			reg2 = "o";

		 else{
		            
		 	//column is checked here.
		            
			commaCheck(str,cursor);

			//second parameter is checked here.
		            
			reg2Parser(str,regLoop,reg2,cursor);
				
		}
            
        //some increment and assigning operations to read the next line.

		regLoop = false;
		cursor = 0;
		proCount++;
		//cout << inst << " " << reg1 << " " << reg2 << endl;

		

		/*we increased the proCount variable and set the PC variable with proCount*/

		setPC(proCount); 

		/*instruction functions are called here.*/

		instController(registerArr, inst, reg1, reg2, myMemory); 

		/*option is checked here.*/

		optionController(registerArr,inst, reg1, reg2, getOption(),myMemory);		
	
	}
	
	
}

/*this function separates the instruction from the command string*/

void CPU::instParser(string tmp, bool &regLoop, string &inst, int &cursor){

	while(!regLoop){
    	if((tmp[cursor] >= 'A' && tmp[cursor] <= 'Z')||
       	(tmp[cursor] >= 'a' && tmp[cursor] <= 'z')){
                    
        	inst += tmp[cursor];
            cursor++;
                    
            if(tmp[cursor] == ' ' || tmp[cursor] == ';')
            	regLoop = true;
                    
     	}
        else{
        	if((inst[0] >= 'A' && inst[0] <= 'Z')||
            (inst[0] >= 'a' && inst[0] <= 'z')){
            	cerr << "Syntax Error!" << endl;
                return;
            }

             else
             	cursor++;
         }
	}
}

/*this function separates the first instruction from the command string*/

void CPU::reg1Parser(string tmp, bool &regLoop, string &reg1, int &cursor){
	
	while(!regLoop){
    	if((tmp[cursor] == 'R' || tmp[cursor] == 'r' || tmp[cursor] == '-' || tmp[cursor] == '#') || 
        (tmp[cursor] >= '0' && tmp[cursor] <= '9')){
                    
         	reg1 += tmp[cursor];
            cursor++;
                    
        	if(tmp[cursor] == ' '|| tmp[cursor] == ';'|| tmp[cursor] == ','){
         	
				regLoop = true;
         	
			}

         }
                
		else{
        	if((reg1[0] == 'R' || reg1[0] == 'r' || reg1[0] == '-' 
			|| reg1[0] == '#') || (reg1[0] >= '0' && reg1[0] <= '9')){
                      
            	cerr << "Syntax Error!" << endl;
                return;
             
			}

                    else
                        cursor++;
        }
	}
}

/* this function checks the comma whether there is a one.*/
/* if not, syntax error will come*/

void CPU::commaCheck(string tmp, int &cursor){
	
	while(tmp[cursor] != ','){
    	if(tmp[cursor] != ' '){
        	if(tmp[cursor] != ','){
                             
            	cerr << "Syntax Error!" << endl;
                return;
            }
        }
    	cursor++;
    }
}

/*this function separates the second instruction from the command string*/

void CPU::reg2Parser(string tmp, bool &regLoop, string &reg2, int &cursor){
	while(tmp[cursor] != ';'){
    	if((tmp[cursor] == 'R' || tmp[cursor] == 'r' || tmp[cursor] == '-'|| tmp[cursor] == '#')||
        (tmp[cursor] >= '0' && tmp[cursor] <= '9')){
                        
        	reg2 += tmp[cursor];
            cursor++;

        }
        else{
        	if(tmp[cursor] == ' ')
            	cursor++;

            else if((reg2[0] == 'R' || 
            reg2[0] == 'r' || reg2[0] == '-'||reg2[0] == '#')||
            (reg2[0] >= '0' && reg2[0] <= '9')){
                            
            	cerr << "Syntax Error!" << endl;
                return;
            }

            else 
            	cursor++;
        }

	}
	
}	

/*this function calls all of the instruction functions to get their job done*/

void CPU::instController(int *registerArr, string inst, string reg1, string reg2, Memory &myMemory){

	if(inst == "MOV" || inst == "mov")
    	movf(registerArr,reg1,reg2,myMemory);
    else if(inst == "ADD" || inst == "add")
    	addf(registerArr,reg1,reg2,myMemory);
    else if(inst == "SUB" || inst == "sub")
        subf(registerArr,reg1,reg2,myMemory);

	/*if jump function returns something except the negative numbers
	and zero, that means jump function will do its job. It must*/
	/* update the PC*/

    else if(inst == "JMP" || inst == "jmp"){
		int x = jmpf(registerArr,reg1,reg2);
		if(x > -1)
			setPC(x);
    }    
	else if(inst == "JPN" || inst == "jpn"){
		int x = jpnf(registerArr,reg1,reg2);
		if(x > -1)
			setPC(x);
	}
		
    else if(inst == "PRN" || inst == "prn")
        prnf(registerArr,reg1,myMemory);
    else{
        cerr << "Invalid instruction!" << endl;
        exit(1);
    }
}

/*this function takes the parameters of command and registerArr as 
parameters and runs the move instruction*/

void CPU::movf(int *registerArr, string reg1, string reg2, Memory &myMemory){

	int temp;

    if((reg2[0] >= '0' && reg2[0] <= '9') || reg2[0] == '-'){
		temp = convertYourStr(reg2);
		
		if(reg1[0] == '#'){
			int temp2 = convertYourStr(reg1);
			myMemory.setMem(temp2,temp);
		}
		
		else if(reg1 == "R1" || reg1 == "r1")
			registerArr[0] = temp;
		else if(reg1 == "R2" || reg1 == "r2")
			registerArr[1] = temp;
		else if(reg1 == "R3" || reg1 == "r3")
			registerArr[2] = temp;
		else if(reg1 == "R4" || reg1 == "r4")
			registerArr[3] = temp;
		else if(reg1 == "R5" || reg1 == "r5")
			registerArr[4] = temp;
		else{
			cerr << "Unknown Register!" << endl;
			exit(1);
		}
	
	}
    

    else{

		if(reg1[0] == '#'){
			int temp2 = convertYourStr(reg1);
			temp = myMemory.getMem(temp2);
		}
	
        else if(reg1 == "R1" || reg1 == "r1")
            temp = registerArr[0];
        else if(reg1 == "R2" || reg1 == "r2")
            temp = registerArr[1];
        else if(reg1 == "R3" || reg1 == "r3")
            temp = registerArr[2];
        else if(reg1 == "R4" || reg1 == "r4")
            temp = registerArr[3];
        else if(reg1 == "R5" || reg1 == "r5")
            temp = registerArr[4];
        else{
            cerr << "Unknown Register!" << endl;
            exit(1);
        }

		if(reg2[0] == '#'){
			int temp2 = convertYourStr(reg2);
			myMemory.setMem(temp2,temp);
		}

        else if(reg2 == "R1" || reg2 == "r1")
            registerArr[0] = temp;
        else if(reg2 == "R2" || reg2 == "r2")
            registerArr[1] = temp;
        else if(reg2 == "R3" || reg2 == "r3")
            registerArr[2] = temp;
        else if(reg2 == "R4" || reg2 == "r4")
            registerArr[3] = temp;
        else if(reg2 == "R5" || reg2 == "r5")
            registerArr[4] = temp;
        else{
            cerr << "Unknown Register!" << endl;
            exit(1);
        }
    }
}

/*this function takes the parameters of command and registerArr as 
parameters and runs the add instruction*/

void CPU::addf(int *registerArr, string reg1, string reg2,Memory &myMemory){

	int temp;

    if((reg2[0] >= '0' && reg2[0] <= '9') || reg2[0] == '-'||reg2[0] == '#'){
		
		if(reg2[0] != '#')
        	temp = convertYourStr(reg2);
		
		else{
			int temp2 = convertYourStr(reg2);
			temp = myMemory.getMem(temp2);
    	}
		
    }

    else{
        if(reg2 == "R1" || reg2 == "r1")
            temp = registerArr[0];
        else if(reg2 == "R2" || reg2 == "r2")
            temp = registerArr[1];
        else if(reg2 == "R3" || reg2 == "r3")
            temp = registerArr[2];
        else if(reg2 == "R4" || reg2 == "r4")
            temp = registerArr[3];
        else if(reg2 == "R5" || reg2 == "r5")
            temp = registerArr[4];
        else{
            cerr << "Unknown Register!" << endl;
            exit(1);
        }
    }

    if(reg1 == "R1" || reg1 == "r1")
        registerArr[0] += temp;
    else if(reg1 == "R2" || reg1 == "r2")
        registerArr[1] += temp;
    else if(reg1 == "R3" || reg1 == "r3")
        registerArr[2] += temp;
    else if(reg1 == "R4" || reg1 == "r4")
        registerArr[3] += temp;
    else if(reg1 == "R5" || reg1 == "r5")
        registerArr[4] += temp;
    else{
        cerr << "Unknown Register!" << endl;
        exit(1);
    }
}

/*this function takes the parameters of command and registerArr as 
parameters and runs the sub instruction*/

void CPU::subf(int *registerArr, string reg1, string reg2,Memory &myMemory){

	int temp;	

	if((reg2[0] >= '0' && reg2[0] <= '9') || reg2[0] == '-'||reg2[0] == '#'){
		
		if(reg2[0] != '#')
        	temp = convertYourStr(reg2);
		
		else{
			int temp2 = convertYourStr(reg2);
			temp = myMemory.getMem(temp2);
    	}
		
    }

    else{
        if(reg2 == "R1" || reg2 == "r1")
            temp = registerArr[0];
        else if(reg2 == "R2" || reg2 == "r2")
            temp = registerArr[1];
        else if(reg2 == "R3" || reg2 == "r3")
            temp = registerArr[2];
        else if(reg2 == "R4" || reg2 == "r4")
            temp = registerArr[3];
        else if(reg2 == "R5" || reg2 == "r5")
            temp = registerArr[4];
        else{
            cerr << "Unknown Register!" << endl;
            exit(1);
        }
    }

    if(reg1 == "R1" || reg1 == "r1")
        registerArr[0] -= temp;
    else if(reg1 == "R2" || reg1 == "r2")
        registerArr[1] -= temp;
    else if(reg1 == "R3" || reg1 == "r3")
        registerArr[2] -= temp;
    else if(reg1 == "R4" || reg1 == "r4")
        registerArr[3] -= temp;
    else if(reg1 == "R5" || reg1 == "r5")
        registerArr[4] -= temp;
    else{
        cerr << "Unknown Register!" << endl;
        exit(1);
    }
}

/*this function takes the parameters of command and registerArr as 
parameters and runs the jump instruction*/

int CPU::jmpf(int *registerArr, string reg1, string reg2){

	int temporary;	
	
	if(reg2 == "o"){
        temporary = convertYourStr(reg1);
		return temporary-1;
	}
    
   else{
        int temp;

        if(reg1 == "R1" || reg1 == "r1")
            temp = registerArr[0];
        else if(reg1 == "R2" || reg1 == "r2")
            temp = registerArr[1];
        else if(reg1 == "R3" || reg1 == "r3")
            temp = registerArr[2];
        else if(reg1 == "R4" || reg1 == "r4")
            temp = registerArr[3];
        else if(reg1 == "R5" || reg1 == "r5")
            temp = registerArr[4];
        else{
            cerr << "Unknown Register!" << endl;
            exit(1);
        }

        if(temp == 0){
            temporary = convertYourStr(reg2);
			return temporary-1;
        }
	}

	return -1;

}

/*this function takes the parameters of command and registerArr as 
parameters and runs the jpn instruction*/

int CPU::jpnf(int *registerArr, string reg1, string reg2){

	int temporary;	
	
	if(reg2 == "o"){
        temporary = convertYourStr(reg1);
		return temporary-1;
	}
    
    else{
        int temp;

        if(reg1 == "R1" || reg1 == "r1")
            temp = registerArr[0];
        else if(reg1 == "R2" || reg1 == "r2")
            temp = registerArr[1];
        else if(reg1 == "R3" || reg1 == "r3")
            temp = registerArr[2];
        else if(reg1 == "R4" || reg1 == "r4")
            temp = registerArr[3];
        else if(reg1 == "R5" || reg1 == "r5")
            temp = registerArr[4];
        else{
            cerr << "Unknown Register!" << endl;
            exit(1);
        }

        if(temp <= 0){
			
            temporary = convertYourStr(reg2);
			return temporary-1;
        }
	}

	return -1;

}

/*this function takes the parameters of command and registerArr as 
parameters and runs the print instruction*/

void CPU::prnf(int* registerArr, string reg1,Memory &myMemory){

    int temp;

	if((reg1[0] >= '0' && reg1[0] <= '9') || reg1[0] == '-'){
        temp = convertYourStr(reg1);
        cout << "I don't know why but you printed: " << temp << endl;
    }

	else if(reg1[0] == '#'){
		temp = convertYourStr(reg1);
		cout << "Memory " << temp << " : " << myMemory.getMem(temp) << endl;
	}
	

    else{

        if(reg1 == "R1" || reg1 == "r1")
            temp = registerArr[0];
        else if(reg1 == "R2" || reg1 == "r2")
            temp = registerArr[1];
        else if(reg1 == "R3" || reg1 == "r3")
            temp = registerArr[2];
        else if(reg1 == "R4" || reg1 == "r4")
            temp = registerArr[3];
        else if(reg1 == "R5" || reg1 == "r5")
            temp = registerArr[4];
        else{
            cerr << "Unknown Register!" << endl;
            exit(1);
        }

        cout << reg1 << ": " << temp << endl;

    }
}

/*this function takes a string as parameter and converts it to an integer*/

int CPU::convertYourStr(string str){

    unsigned int counter;
    int length = str.length();
    int result = 0;
    
    if(str[0] == '-'||str[0] == '#'){
        
        length = str.length() - 1;
        
        for(counter = 1; counter < str.length(); counter++){
            result += (str[counter] - '0')*powerf(10,length-1);
            length--;
        }
        
		if(str[0] == '-')
        	result = -result;
    }
    
    else{
    

        for(counter = 0; counter < str.length(); counter++){
            result += (str[counter] - '0')*powerf(10,length-1);
            length--;
            
        }
        
    }

    return result;

}

/*this function runs a power process*/

int CPU::powerf(int number, int power){
    int counter;
    int result = 1;
    for(counter = 0; counter < power; counter++){
        result *= number;
    }
    
    return result;
}

/* this function returns a boolean operation to start the while loop */
/* that is based on main function and to finish the program*/

bool CPU::halted(){

	if(test == 0)
		return true;
	

	return false;

}

/*this funtion prints the last situation of registers and PC*/

void CPU::print(){
	for(int a = 0; a < ARR_SIZE; a++){
		cout << "R" << a+1 << ": " << registerArr[a] << endl;
	}
	cout << "Program Counter: " << PC << endl;
}

	




	
	

	



