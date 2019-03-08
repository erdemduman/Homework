#include <iostream>
#include <string>
#include <fstream>
#include <cstdlib> //for exit()
#include "CPU.h"
#include "CPUProgram.h"


using namespace std;

/*this constructor takes an integer (option) as parameter to*/
/*send it to another function.*/
/*sets the first value of the counter as well.*/

CPUProgram::CPUProgram(int option){
    counter = 0; //counter for size function           
	
}

CPUProgram::CPUProgram(){}

/*this function takes the name of file, that we mentioned above,*/
/*as parameter and reads the instructions that is found in file*/
/*to store them in a vector.*/

void CPUProgram::ReadFile(string fileName){
	
	fstream fayl;
	fayl.open(fileName);
	
	while(!fayl.eof()){
		string temp;		
		getline(fayl,temp);
		database.push_back(temp);					
		++counter;
	}

	counter--; //since the counter counted one more, we decreased it.

	database.pop_back(); //we popped the eof

	fayl.close();
	
}

/*this function takes an index as parameter and returns one of the*/
/*lines of the file to use in execute function.*/ 

string CPUProgram::getLine(int index) const{

	string tmp;
	tmp = database[index];

	return tmp;

}

/* this function provides the counter variable for size function */

int CPUProgram::getCount() const {return counter;}

/*this function returns the line number of file.*/

int CPUProgram::size()const {return getCount();}

/*setter of counter*/

void CPUProgram::setSize(int t){counter = t;}

/*this overload of index operator takes an integer and returns the index*/
/* of that integer*/

string CPUProgram::operator[](int index){

	if(size() < index||index<0){
		cerr << "Index is not available." << endl;
		string empty = "";
		return empty;
	}

	string tmpString = getLine(index);

	return tmpString;

}

/*this overload of + operator creates an object, makes it the same*/
/*with "this" object and adds the parameter string to the end of it.*/

CPUProgram CPUProgram::operator+(const string addition){
	
	CPUProgram tempObj;	
	
	tempObj = *this;

	/*error check*/	

	if(addition == ""){
		cerr << "You better send a filled string." << endl;
		return tempObj;
	}
	
	tempObj += addition;

	return tempObj;

}

/* this operation does the same thing like above, but only difference is*/
/* this function returns the same object. not a temporary one.*/

CPUProgram& CPUProgram::operator+=(const string addition){
	
	/*error check*/

	if(addition == ""){
		cerr << "You better send a filled string." << endl;	
		return *this;
	}
	database.push_back(addition);
	++counter;

	return *this;
}

/*this overload prints every line of object that is given with*/
/*parameter*/

ostream& operator<<(ostream &output, const CPUProgram &other){

	for(int a = 0; a < other.size(); a++){
		output << other.getLine(a) << endl;
	}


	return output;
}

/*this overload of + operator takes another object and*/
/*combine the obj that we are working on and parameter obj.*/

CPUProgram CPUProgram::operator+(const CPUProgram &other){

	CPUProgram tempObj;

	tempObj.setSize(0);

	int tempCount = 0;	

	while(tempCount < this -> size()){
		
		tempObj += this->getLine(tempCount);

		tempCount++;
	
	}
	
	tempCount = 0;

	while(tempCount < other.size()){
	
		tempObj += other.getLine(tempCount);

		tempCount++;
	
	}

	return tempObj;
}

/*boolean operations. they compare the objects' sizes.*/

bool CPUProgram::operator ==(const CPUProgram &other){return (this->size() == other.size());}
bool CPUProgram::operator !=(const CPUProgram &other){return !(this->size() == other.size());}
bool CPUProgram::operator <=(const CPUProgram &other){return (this->size() <= other.size());}
bool CPUProgram::operator >(const CPUProgram &other){return !(this->size() <= other.size());}
bool CPUProgram::operator >=(const CPUProgram &other){return (this->size() >= other.size());}
bool CPUProgram::operator <(const CPUProgram &other){return !(this->size() >= other.size());}

/* -- operator deletes the last line of obj*/

CPUProgram& CPUProgram::operator--(){
	
	database.pop_back();
	--counter;

	return *this;

}

/*same as above.*/

CPUProgram CPUProgram::operator--(int a){
	
	CPUProgram tmp;

	tmp = *this;	
		
	database.pop_back();
	--counter;

	return tmp;

}
	
/*this overload of () operator takes 2 integers as parameters and*/
/*creates new object. It cuts the lines between "start" and "end"*/
/*to stores them in the new object.*/

CPUProgram CPUProgram::operator()(int start, int end){

	CPUProgram tmp;

	/*error check*/	

	if(start > end || start <= 0 || end <= 0){
		cerr << "Unavailable indices" << endl;
		return 0;
	}
	
	tmp.setSize(0);
	
	start--; //index and line are not the same thing.
	end--;

	while(start <= end){
	
		tmp += this->getLine(start);
		start++;
	
	}

	return tmp;

}
	

	




		


