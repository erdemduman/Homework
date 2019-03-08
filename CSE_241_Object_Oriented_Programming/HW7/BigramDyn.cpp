#include "BigramDyn.h"

template <class T>
BigramDyn<T>::BigramDyn(int dataType){
	numGramsVar = 0;
	sizeOfDyn = 0; //dynamic array will be limited with this size variable
	datatype = dataType;

	/* I opened some space for dynamic memories to avoid deleting nothing*/

	howMany = new int[2];
	database = new T*[2];
	for(int a = 0; a < 2; a++){
		database[a] = new T[2];
	}
}

//copy constructor

template <class T>
BigramDyn<T>::BigramDyn(const BigramDyn& other){

	sizeOfDyn = other.sizeOfDyn;
	numGramsVar = other.numGramsVar;
	datatype = other.datatype;

	howMany = new int[numGramsVar];

	for(int a = 0; a < sizeOfDyn; a++){
		howMany[a] = other.howMany[a];
	}

	database = new T*[numGramsVar];
	for(int a = 0; a < numGramsVar; a++){
		database[a] = new T[2];
	}

	for(int a = 0; a < sizeOfDyn; a++){
		database[a][0] = other.database[a][0];
		database[a][1] = other.database[a][1];

	}
}

//assignment operator

template <class T>
BigramDyn<T>& BigramDyn<T>::operator =(const BigramDyn& other){

	if(&other == this)
		return *this;
	else{

		delete [] howMany;
		for(int a = 0; a < 2; a++){
			delete [] database[a];
		}
		delete [] database;

		sizeOfDyn = other.sizeOfDyn;
		numGramsVar = other.numGramsVar;
		datatype = other.datatype;

		howMany = new int[numGramsVar];

		for(int a = 0; a < sizeOfDyn; a++){
			howMany[a] = other.howMany[a];
		}

		database = new T*[numGramsVar];
		for(int a = 0; a < numGramsVar; a++){
			database[a] = new T[2];
		}

		for(int a = 0; a < sizeOfDyn; a++){
			database[a][0] = other.database[a][0];
			database[a][1] = other.database[a][1];

		}
	}
}

/*this function takes filename as parameter and calculates bigrams*/
/*BUT USES DYNAMIC MEMORY*/

template <class T>
void BigramDyn<T>::readFile(string filename){

	/*we delete the space that we opened in constructor*/

	delete [] howMany;
	for(int a = 0; a < 2; a++){
		delete [] database[a];
	}
	delete [] database;

	fstream fayl;
    fayl.open(filename);
    T reader;


    /*error check of "is there any file named like this?"*/
    
    if(!fayl){
    	myExcept obj;
    	throw obj;
    }

    else{

    	/*error check of empty file*/
    	
    	string isFileEmpty;
    	fayl >> isFileEmpty;

    	if(isFileEmpty == ""){
    		myExcept obj;
    		throw obj;
    	}

    	fayl.close();

    	/*file closed and opened to find bigram number*/

    	fayl.open(filename);

    	while(!fayl.eof()){

	        fayl >> reader;
	        
	        /*error check of bad data*/

	        if(fayl.fail()){
	        	myExcept obj;
	            throw obj;
	        }

			numGramsVar++;
		}
	}

	//if there is just one element in file, that is an error for me
	if(numGramsVar == 1){
		myExcept obj;
	    throw obj;
	}

	numGramsVar--;

	fayl.close();

	fayl.open(filename);

	T firstVar, secondVar;

	/*we opened space for dynamic memories*/

	database = new T*[numGramsVar];
	for(int a = 0; a < numGramsVar; a++){
		database[a] = new T[2];
	}

	/*database pointer keeps the biagrams*/
	/*howMany pointer keeps how many match is there*/ 

	howMany = new int[numGramsVar];

	for(int a = 0; a < numGramsVar; a++){
		howMany[a] = 1;
	}


	fayl >> firstVar;

	while(!fayl.eof()){

		int flag = 0;

		fayl >> secondVar;

		for(int a=0; a < sizeOfDyn; a++){
			if(database[a][0] == firstVar && database[a][1] == secondVar){
				flag = 1;
				howMany[a]++;
			}
		}

		if(flag == 0){
			database[sizeOfDyn][0] = firstVar;
			database[sizeOfDyn][1] = secondVar;
			sizeOfDyn++;
		}

		firstVar = secondVar;

	}
}

/*this function returns the number of bigram*/

template <class T>
int BigramDyn<T>::numGrams()const{return numGramsVar;}

/*this function takes 2 parameters and returns how many bigrams are matched with them*/

template <class T>
int BigramDyn<T>::numOfGrams(const T firstVar, const T secondVar){

	int returnValue = 0;

	for(int a = 0; a < sizeOfDyn; a++){
		if(database[a][0] == firstVar && database[a][1] == secondVar){
			returnValue = howMany[a];
		}
	}

	return returnValue;

}

/*this function returns the most used bigram with pair*/

template <class T>
pair<T,T> BigramDyn<T>::maxGrams(){

	int max = 0;
	int indexHolder=0;

	for(int a = 0; a < sizeOfDyn; a++){
		if(howMany[a]>=max){
			max = howMany[a];
			indexHolder = a;
		}	
	}

	pair<T,T>newPair;
	newPair.first = database[indexHolder][0];
	newPair.second = database[indexHolder][1];
	
	return newPair;
}

/*this function prints the bigrams and their values*/

template <class T>
void BigramDyn<T>::helperFunc(ostream& output){
	
	for(int a = 0; a < sizeOfDyn; a++){
		output << "First: " << database[a][0] << " Second: " << database[a][1] << " Number: " << howMany[a] << endl;
	}
}

//destructor

template <class T>
BigramDyn<T>::~BigramDyn(){

	for(int a = 0; a < numGramsVar; a++){
		delete [] database[a];
	}
	delete [] database;
	delete [] howMany;
}


