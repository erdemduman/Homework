/*******************************************************/
/*                                                     */
/*         Hakki_Erdem_Duman_151044005                 */
/*      Object_Oriented_Programming(CSE_241)           */
/*                    HW_02                            */
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
#include <cstdlib> //compiler of netbeans accept "exit"
		   //function without this library.

using namespace std;

/*                 Function Prototypes                 */

void instParser(string tmp, bool &regLoop, 
string *inst, const int proCount, int &cursor);
void reg1Parser(string tmp, bool &regLoop, 
string *reg1, const int proCount, int &cursor);
void commaCheck(string tmp, int &cursor);
void reg2Parser(string tmp, bool &regLoop, 
string *reg2, const int proCount, int &cursor);
void fileOperation(char *fileName, char* option);
void instController(int *registerArr, string* inst, string *reg1, 
string *reg2, const int proCount, int &tempCounter,unsigned int* adressArr);
void jumpController(int *registerArr, string* inst, string *reg1, 
string *reg2, const int proCount, int &tempCounter,unsigned int* addressArr);
void optionController(int *registerArr, string* inst, string *reg1, 
string *reg2, const int proCount, char* option,unsigned int* addressArr);
void movf(int *registerArr, string reg1, string reg2,unsigned int* addressArr);
void addf(int *registerArr, string reg1, string reg2,unsigned int* addressArr);
void subf(int *registerArr, string reg1, string reg2,unsigned int* addressArr);
void jmpf(int *registerArr, string* inst, string *reg1, 
string *reg2, const int proCount, int* tempCounter);
void jpnf(int *registerArr, string* inst, string *reg1, 
string *reg2, const int proCount, int* tempCounter);
void prnf(int *registerArr, string reg1,unsigned int* addressArr);
int convertYourStr(string str);
int powerf(int number, int power);
int addressController(string str);

int main(int argc, char* argv[]){

    fileOperation(argv[1],argv[2]);

    return 0;
}

/* This function takes the file name as parameter, reads the text file and */
/* gives them a meaning to call functions of them.                         */

void fileOperation(char *fileName, char* option){
                

    const int ARR_SIZE = 5;
    const int MAX_LINE = 200;
	const int ADDRESS_SIZE = 50;

    fstream fayl; // file variable.

    /*string arrays to store instruction, first value(or register)*/
    /*and second value(or register).                              */
    
    string inst[MAX_LINE],reg1[MAX_LINE],reg2[MAX_LINE];
    
    string tmp; // variable that stores the next line of file.

	unsigned int addressArr[ADDRESS_SIZE]; //adresses.
    int registerArr[ARR_SIZE]; //registers.
    int proCount = 0; //variable to count line.
    int cursor = 0; //varible to read following letter.
    int tempCounter = 0; //variable to do jump instruction.
    bool regLoop = false; //parsing loop variable.
    

    fayl.open(fileName);
    
    if(fayl.is_open()){

        //array filled with 0 here.
        for(int a = 0; a < ARR_SIZE; a++){
            registerArr[a] = 0;
        }
		
		//addresses filled with 0 here.
		for(int b = 0; b < ADDRESS_SIZE; b++){
			addressArr[b] = 0;
		}

        //reading loop.
        while(!fayl.eof()){

       
			//this function controls whether there is a jump instruction.
			jumpController(registerArr,inst,reg1,reg2,proCount,tempCounter,addressArr);

            /*this if statement runs if tempCounter is increased by jump */
            /*function. */


            getline(fayl,tmp);
            tmp += ';'; //I put ';' character at the end of all lines to 
                        //work easier.

            //this function parses the instruction from string.

    		instParser(tmp,regLoop,inst,proCount,cursor);
            
            regLoop = false;

            /*since "halt" does not get any parameters, it is checked after */
            /*getting the instruction. */

            if((inst[proCount] == "HLT")||(inst[proCount] == "hlt")){
                cout << "HLT" << endl;
                cout << "Succeed!" << endl;
                exit(0);
            }

            //this function gets the first parameter of instruction.

			reg1Parser(tmp,regLoop,reg1,proCount,cursor);
            

            /* since jump instruction can take only one parameter, it is*/
            /* checked after getting the first parameter.*/
            
            if((inst[proCount] == "JMP"||inst[proCount] == "jmp")&&
               (reg1[proCount][0] >= '0' && reg1[proCount][0] <= '9'))
                
                reg2[proCount] = "o";

            /* the same stuff happens for the print instruction.*/
            
            else if(inst[proCount] == "PRN"||inst[proCount] == "prn")
                reg2[proCount] = "o";

            else{
                
                //column is checked here.
                
				commaCheck(tmp,cursor);

				//second parameter is checked here.
                
				reg2Parser(tmp,regLoop,reg2,proCount,cursor);
				
            }

            // funtions are called according to their instructions.
			
			if(tempCounter == proCount){
				instController(registerArr,inst,reg1, 
				reg2,proCount,tempCounter,addressArr);
			}
		
			else
				tempCounter--;
			
            //option controller function.

			optionController(registerArr,inst,reg1,reg2,proCount,option,addressArr);
            
            //some increment and assigning operations to read the next line.
            
            proCount++;
            regLoop = false;
            cursor = 0;
			tempCounter++;

        }
        fayl.close();
    }
    else
        cerr << "There is no file like this." << endl;
}

//this function takes a string as a parameter to return an index
//number for an address. 

int addressController(string str){
	
	int result;

	result = convertYourStr(str);

	if(result < 0 && result > 49){
		cerr << "Invalid adress!" << endl;
		exit(1);
	}

	return result;
}

//controls the option. 

void optionController(int *registerArr, string* inst, string *reg1, 
string *reg2, const int proCount, char* option,unsigned int* addressArr){

	const int ADDRESS_SIZE = 50;

	if(option[0] == '1'){
    	cout << inst[proCount] << "   " << reg1[proCount] << "   " 
        << reg2[proCount] << " -     " << "R1:" << registerArr[0] 
        << "   " << "R2:" << registerArr[1] << "   R3:" <<
        registerArr[2] << "   R4:" << registerArr[3] << 
        "   R5:" << registerArr[4] << endl;
	}

	
	
	else if(option[0] == '2'){
		cout << inst[proCount] << "   " << reg1[proCount] << "   " 
        << reg2[proCount] << " -     " << "R1:" << registerArr[0] 
        << "   " << "R2:" << registerArr[1] << "   R3:" <<
        registerArr[2] << "   R4:" << registerArr[3] << 
        "   R5:" << registerArr[4] << endl;

		for(int a = 0; a < ADDRESS_SIZE ; a++){
			cout << "Memory " << a << ": " << addressArr[a] << endl;
                     
    	}
	}
            
     if(option[0] != '0' && option[0] != '1' && option[0] != '2')
     	cerr << "There is no option like this." << endl;
}

	
//this function controls the instruction and calls instruction
//functions according to it.

void instController(int *registerArr, string* inst, string *reg1, 
string *reg2, const int proCount, int &tempCounter,unsigned int* addressArr){

	if(inst[proCount] == "MOV" || inst[proCount] == "mov")
    	movf(registerArr,reg1[proCount],reg2[proCount],addressArr);
    else if(inst[proCount] == "ADD" || inst[proCount] == "add")
    	addf(registerArr,reg1[proCount],reg2[proCount],addressArr);
    else if(inst[proCount] == "SUB" || inst[proCount] == "sub")
        subf(registerArr,reg1[proCount],reg2[proCount],addressArr);
    else if(inst[proCount] == "JMP" || inst[proCount] == "jmp")
        jmpf(registerArr,inst,reg1,reg2,proCount,&tempCounter);
	else if(inst[proCount] == "JPN" || inst[proCount] == "jpn")
		jpnf(registerArr,inst,reg1,reg2,proCount,&tempCounter);
    else if(inst[proCount] == "PRN" || inst[proCount] == "prn")
        prnf(registerArr,reg1[proCount],addressArr);
    else{
        cerr << "Invalid instruction!" << endl;
        return;
    }
}

//if there is a jump instruction, this function helps the tempCounter
//variable to catch proCounter variable. Until catching, following
//functions are called. 

void jumpController(int *registerArr, string* inst, string *reg1, 
string *reg2, const int proCount, int &tempCounter,unsigned int* addressArr){

	if(tempCounter < proCount){
    	while(tempCounter < proCount){

        	if(inst[tempCounter] == "MOV" || 
            inst[tempCounter] == "mov")
                        
            	movf(registerArr,reg1[tempCounter],reg2[tempCounter],addressArr);
                    
            else if(inst[tempCounter] == "ADD" || 
           	inst[tempCounter] == "add")
                        
            	addf(registerArr,reg1[tempCounter],reg2[tempCounter],addressArr);
                    
            else if(inst[tempCounter] == "SUB" || 
            inst[tempCounter] == "sub")
                        
                subf(registerArr,reg1[tempCounter],reg2[tempCounter],addressArr);
                    
            else if(inst[tempCounter] == "JMP" || 
            inst[tempCounter] == "jmp")
                        
            	jmpf(registerArr,inst,reg1,reg2,proCount,&tempCounter);

			else if(inst[tempCounter] == "JPN" || 
            inst[tempCounter] == "jpn")
                        
            	jpnf(registerArr,inst,reg1,reg2,proCount,&tempCounter);
                    
            else if(inst[tempCounter] == "PRN" || 
            inst[tempCounter] == "prn")
                        
            	prnf(registerArr,reg1[tempCounter],addressArr);
                    
            else{
                        
            	cerr << "Invalid instruction!" << endl;
                return;
            }
                    
                tempCounter++;
                    
		}
                tempCounter = proCount;
	}
}

	
//this function parses the instruction word and writes that into the "inst"
//string array.	

void instParser(string tmp, bool &regLoop, string *inst, const int proCount, int &cursor){

	while(!regLoop){
    	if((tmp[cursor] >= 'A' && tmp[cursor] <= 'Z')||
       	(tmp[cursor] >= 'a' && tmp[cursor] <= 'z')){
                    
        	inst[proCount] += tmp[cursor];
            cursor++;
                    
            if(tmp[cursor] == ' ' || tmp[cursor] == ';')
            	regLoop = true;
                    
     	}
        else{
        	if((inst[proCount][0] >= 'A' && inst[proCount][0] <= 'Z')||
            (inst[proCount][0] >= 'a' && inst[proCount][0] <= 'z')){
            	cerr << "Syntax Error!" << endl;
                return;
            }

             else
             	cursor++;
         }
	}
}

//this function parses the first parameter and writes that into the "reg1"
//string array.	

void reg1Parser(string tmp, bool &regLoop, string *reg1, const int proCount, int &cursor){
	
	while(!regLoop){
    	if((tmp[cursor] == 'R' || tmp[cursor] == 'r' || tmp[cursor] == '-' || tmp[cursor] == '#') || 
        (tmp[cursor] >= '0' && tmp[cursor] <= '9')){
                    
         	reg1[proCount] += tmp[cursor];
            cursor++;
                    
        	if(tmp[cursor] == ' '|| tmp[cursor] == ';'|| tmp[cursor] == ','){
         	
				regLoop = true;
         	
			}

         }
                
		else{
        	if((reg1[proCount][0] == 'R' || reg1[proCount][0] == 'r' || reg1[proCount][0] == '-' 
			|| tmp[cursor] == '#') || (reg1[proCount][0] >= '0' && reg1[proCount][0] <= '9')){
                      
            	cerr << "Syntax Error!" << endl;
                return;
             
			}

                    else
                        cursor++;
        }
	}
}

//comma is checked here. If there is no comma, syntax error will
// come.

void commaCheck(string tmp, int &cursor){
	
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

//this function parses the second parameter and writes that into the "reg2"
//string array.

void reg2Parser(string tmp, bool &regLoop, string *reg2, const int proCount, int &cursor){
	
	while(tmp[cursor] != ';'){
    	if((tmp[cursor] == 'R' || tmp[cursor] == 'r' || tmp[cursor] == '-' || tmp[cursor] == '#')||
        (tmp[cursor] >= '0' && tmp[cursor] <= '9')){
                        
        	reg2[proCount] += tmp[cursor];
            cursor++;

        }
        else{
        	if(tmp[cursor] == ' ')
            	cursor++;

            else if((reg2[proCount][0] == 'R' || 
            reg2[proCount][0] == 'r' || reg2[proCount][0] == '-' || tmp[cursor] == '#')||
            (reg2[proCount][0] >= '0' && reg2[proCount][0] <= '9')){
                            
            	cerr << "Syntax Error!" << endl;
                return;
            }

            else 
            	cursor++;
        }

	}
	
}	

// this function takes a string variable as parameter and converts it to 
// an integer to return.

int convertYourStr(string str){

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

// this function takes a number and its power as parameters
// and returns a power process.
	
int powerf(int number, int power){
    int counter;
    int result = 1;
    for(counter = 0; counter < power; counter++){
        result *= number;
    }
    
    return result;
}

// this function takes registerArr, reg1 and reg2 as parameters and 
// makes the move instruction.

void movf(int *registerArr, string reg1, string reg2,unsigned int* addressArr){

    int temp;

    if((reg2[0] >= '0' && reg2[0] <= '9') || reg2[0] == '-'){
		temp = convertYourStr(reg2);
		
		if(reg1[0] == '#'){
			int temp2 = addressController(reg1);
			addressArr[temp2] = temp;
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
			int temp2 = addressController(reg1);
			temp = addressArr[temp2];
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
			int temp2 = addressController(reg2);
			addressArr[temp2] = temp;
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

// this function takes registerArr, reg1 and reg2 as parameters and 
// makes the add instruction.

void addf(int *registerArr, string reg1, string reg2,unsigned int* addressArr){

    int temp;

    if((reg2[0] >= '0' && reg2[0] <= '9') || reg2[0] == '-' || reg2[0] == '#'){
		if(reg2[0] != '#')
        	temp = convertYourStr(reg2);
		
		else{
			int temp2 = addressController(reg2);
			temp = addressArr[temp2];
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

// this function takes registerArr, reg1 and reg2 as parameters and 
// makes the sub instruction.
// since there is an instruction like this, negative numbers won't be 
// counted.

void subf(int *registerArr, string reg1, string reg2,unsigned int* addressArr){

    int temp;

    if((reg2[0] >= '0' && reg2[0] <= '9') || reg2[0] == '-' || reg2[0] == '#'){
		if(reg2[0] != '#')
        	temp = convertYourStr(reg2);
		
		else{
			int temp2 = addressController(reg2);
			temp = addressArr[temp2];
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

// this function takes registerArr, instruction array and reg1,reg2 value arrays
// , proCount variable and tempCounter variable(pointer) to make the jump inst.
// this function creates a tempCounter variable to jump.

void jmpf(int *registerArr, string *inst, string *reg1, string *reg2, 
        const int proCount, int *tempCounter){



    if(reg2[*tempCounter] == "o"){
        *tempCounter = convertYourStr(reg1[*tempCounter]);
        *tempCounter = *tempCounter-2;

    }
    else{
        int temp;

        if(reg1[*tempCounter] == "R1" || reg1[*tempCounter] == "r1")
            temp = registerArr[0];
        else if(reg1[*tempCounter] == "R2" || reg1[*tempCounter] == "r2")
            temp = registerArr[1];
        else if(reg1[*tempCounter] == "R3" || reg1[*tempCounter] == "r3")
            temp = registerArr[2];
        else if(reg1[*tempCounter] == "R4" || reg1[*tempCounter] == "r4")
            temp = registerArr[3];
        else if(reg1[*tempCounter] == "R5" || reg1[*tempCounter] == "r5")
            temp = registerArr[4];
        else{
            cerr << "Unknown Register!" << endl;
            exit(1);
        }

        if(temp == 0){
            *tempCounter = convertYourStr(reg2[*tempCounter]);
            *tempCounter = *tempCounter-2;
            
        }

    }


}

void jpnf(int *registerArr, string *inst, string *reg1, string *reg2, 
        const int proCount, int *tempCounter){


	int temp;

    if(reg1[*tempCounter] == "R1" || reg1[*tempCounter] == "r1")
    	temp = registerArr[0];
    else if(reg1[*tempCounter] == "R2" || reg1[*tempCounter] == "r2")
    	temp = registerArr[1];
    else if(reg1[*tempCounter] == "R3" || reg1[*tempCounter] == "r3")
        temp = registerArr[2];
    else if(reg1[*tempCounter] == "R4" || reg1[*tempCounter] == "r4")
        temp = registerArr[3];
    else if(reg1[*tempCounter] == "R5" || reg1[*tempCounter] == "r5")
        temp = registerArr[4];
    else{
    	cerr << "Unknown Register!" << endl;
        exit(1);
    }

    if(temp <= 0){
    	*tempCounter = convertYourStr(reg2[*tempCounter]);
        *tempCounter = *tempCounter-2;
            
    }

}

// this function takes registerArr and reg1 as parameters and 
// makes the print instruction.

void prnf(int* registerArr, string reg1,unsigned int* addressArr){

    int temp;


    if((reg1[0] >= '0' && reg1[0] <= '9') || reg1[0] == '-'){
        temp = convertYourStr(reg1);
        cout << "I don't know why but you printed: " << temp << endl;
    }
	
	else if(reg1[0] == '#'){
		temp = addressController(reg1);
		cout << "Memory " << temp << " : " << addressArr[temp] << endl;
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

        cerr << reg1 << ": " << temp << endl;

    }
}
