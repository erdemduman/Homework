#include <iostream>
#include <string>
#include <fstream>
#include <cstdlib> //for exit()
#include "CPUProgramDyn.h"

namespace spaceOfName{

	/*this constructor takes an integer (option) as parameter to*/
	/*send it to another function.*/
	/*sets the first value of the counter as well.*/

	CPUProgramDyn::CPUProgramDyn(int optimus){
		counter = 0; //counter for size function           
		instDatabase = new string[23]; //empty dynamic array to delete smth in destructor.
		option = optimus;
	}

	CPUProgramDyn::CPUProgramDyn(){

		instDatabase = new string[23]; //empty dynamic array to delete smth in destructor.
		counter = 0; //counter
	}

	/* copy constructor copies all of the details of the object to another one*/

	CPUProgramDyn::CPUProgramDyn(const CPUProgramDyn& other){

		counter = other.counter;
		option = other.option;

		instDatabase = new string[counter];

		for(int a = 0; a < counter; a++){
			instDatabase[a] = other.instDatabase[a];
		}

	}
	

	/*this function takes the name of file, that we mentioned above,*/
	/*as parameter and reads the instructions that is found in file*/
	/*to store them in a vector.*/

	void CPUProgramDyn::ReadFile(const char* fileName){
	
		fstream fayl;
		fayl.open(fileName);
		string temp;
	
		while(!fayl.eof()){	
			getline(fayl, temp);				
			++counter;
		}

		counter--; //since the counter counted one more, we decreased it.

		fayl.close(); // we closed the file because we need to start from
						//the beginning of the file.	

		delete [] instDatabase; //deleted junk dynamic array.

		instDatabase = new string[counter];
	
		fayl.open(fileName);

		for(int a = 0; a < counter; a++){
			getline(fayl,temp);	
			instDatabase[a] = temp;
		}

		fayl.close();
	
	}

	/*this function takes an index as parameter and returns one of the*/
	/*lines of the file to use in execute function.*/ 

	string CPUProgramDyn::getLine(int index) const{

		string tmp;
		tmp = instDatabase[index];

		return tmp;

	}

	/* this function provides the counter variable for size function */

	int CPUProgramDyn::getCount() const {return counter;}

	/*this function returns the line number of file.*/

	int CPUProgramDyn::size()const {return getCount();}

	/*setter of counter*/

	void CPUProgramDyn::setSize(int t){counter = t;}

	/*this overload of index operator takes an integer and returns the index*/
	/* of that integer*/

	string CPUProgramDyn::operator[](int index){

		if(size() <= index||index<0){
			cerr << "Index is not available." << endl;
			string empty = "";
			return empty;
		}

		string tmpString = getLine(index);

		return tmpString;

	}

	/*this overload of + operator creates an object, makes it the same*/
	/*with "this" object and adds the parameter string to the end of it.*/

	CPUProgramDyn CPUProgramDyn::operator+(const string addition){
	
		CPUProgramDyn tempObj;

		delete [] tempObj.instDatabase;	

		tempObj.instDatabase = new string[counter+1];

		tempObj.setSize(counter+1);

		for(int a = 0; a < counter; a++){
			tempObj.instDatabase[a] = instDatabase[a];
		}
	
		/*error check*/	

		if(addition == ""){
			cerr << "You better send a filled string." << endl;
			return tempObj;
		}
	
		//tempObj += addition;

		tempObj.instDatabase[tempObj.counter-1] = addition;

		return tempObj;

	}

	CPUProgramDyn& CPUProgramDyn::operator=(const CPUProgramDyn& other){

		if(&other == this)
			return *this;

		else{
			option = other.option;
			counter = other.counter;
			delete [] instDatabase;
		
			instDatabase = new string[counter];
		
			for(int a = 0; a < counter; a++){
				instDatabase[a] = other.instDatabase[a];
			}

		}
	}

	/* this operation does the same thing like above, but only difference is*/
	/* this function returns the same object. not a temporary one.*/

	CPUProgramDyn& CPUProgramDyn::operator+=(const string addition){
	
		/*error check*/

		if(addition == ""){
			cerr << "You better send a filled string." << endl;	
			return *this;
		}
	
		string temp[counter];

		for(int a = 0; a < counter; a++){
			temp[a] = instDatabase[a];
		}

		delete [] instDatabase;

		counter++;

		instDatabase = new string[counter];

		for(int a = 0; a < counter-1; a++){
			instDatabase[a] = temp[a];
		}

		instDatabase[counter-1] = addition;
	 	
		return *this;
	}

	/*this overload prints every line of object that is given with*/
	/*parameter*/

	ostream& operator<<(ostream &output, const CPUProgramDyn &other){

		for(int a = 0; a < other.size(); a++){
			output << other.getLine(a) << endl;
		}


		return output;
	}

	/*this overload of + operator takes another object and*/
	/*combine the obj that we are working on and parameter obj.*/

	CPUProgramDyn CPUProgramDyn::operator+(const CPUProgramDyn &other){

		int a = 0;
		int b = 0;

		CPUProgramDyn tempObj;

		delete [] tempObj.instDatabase;

		tempObj.instDatabase = new string[counter+other.counter];

		tempObj.setSize(counter + other.counter);

		tempObj.instDatabase = new string[tempObj.counter];

		while(a < counter){
			tempObj.instDatabase[a] = instDatabase[a];
			a++;
		}

		while (a < tempObj.counter){
			tempObj.instDatabase[a] = other.instDatabase[b];
			b++;
			a++;
		}

		return tempObj;
	}

	/*boolean operations. they compare the objects' sizes.*/

	bool CPUProgramDyn::operator ==(const CPUProgramDyn &other){return (this->size() == other.size());}
	bool CPUProgramDyn::operator !=(const CPUProgramDyn &other){return !(this->size() == other.size());}
	bool CPUProgramDyn::operator <=(const CPUProgramDyn &other){return (this->size() <= other.size());}
	bool CPUProgramDyn::operator >(const CPUProgramDyn &other){return !(this->size() <= other.size());}
	bool CPUProgramDyn::operator >=(const CPUProgramDyn &other){return (this->size() >= other.size());}
	bool CPUProgramDyn::operator <(const CPUProgramDyn &other){return !(this->size() >= other.size());}

	/* -- operator deletes the last line of obj*/

	CPUProgramDyn& CPUProgramDyn::operator--(){
	
		--counter;

		string temp[counter];	

		for(int a = 0; a < counter; a++){
			temp[a] = instDatabase[a];
		}

		delete [] instDatabase;
	
		instDatabase = new string[counter];

		for(int a = 0; a < counter; a++){
			instDatabase[a] = temp[a];
		}


		return *this;

	}

	/*same as above.*/

	CPUProgramDyn CPUProgramDyn::operator--(int a){
	
		CPUProgramDyn tmp;

		tmp = *this;	
		
		--counter;

		string temp[counter];	

		for(int a = 0; a < counter; a++){
			temp[a] = instDatabase[a];
		}

		delete [] instDatabase;
	
		instDatabase = new string[counter];

		for(int a = 0; a < counter; a++){
			instDatabase[a] = temp[a];
		}

		return tmp;

	}
	
	/*this overload of () operator takes 2 integers as parameters and*/
	/*creates new object. It cuts the lines between "start" and "end"*/
	/*to stores them in the new object.*/

	CPUProgramDyn CPUProgramDyn::operator()(int start, int end){

		CPUProgramDyn tmp;

		int b = 0;

		/*error check*/	

		if(start > end || start <= 0 || end <= 0){
			cerr << "Unavailable indices" << endl;
			return 0;
		}

		int indexSize = end - start + 1;
	
		tmp.setSize(indexSize);

		delete [] tmp.instDatabase;

		tmp.instDatabase = new string[tmp.size()];

		for(int a = start-1; a < end; a++){
			tmp.instDatabase[b] = instDatabase[a];
			b++;
		}
	

		return tmp;

	}

	CPUProgramDyn::~CPUProgramDyn(){
		delete [] instDatabase;
	}
}
	

	




		


