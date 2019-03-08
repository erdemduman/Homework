#include "BigramMap.h"

//constructor
template <class T>
BigramMap<T>::BigramMap(int dataType){
	numGramsVar = 0; //bigram counter
	datatype = dataType; 
} 

/*this function takes filename as parameter and calculates bigrams*/

template <class T>
void BigramMap<T>::readFile(string filename){
	
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
	

	fayl.close();

	fayl.open(filename);

	/*file closed and opened to find all bigrams*/ 
	/*pair is used for key of map*/

	T pairFirst,pairSecond;
	
	typename map<pair<T,T>,int>::iterator it; //iterator of our map

	fayl >> pairFirst; 

	while(!fayl.eof()){

		int flag = 0; //if the same bigram is found, this turns to 1

		fayl >> pairSecond;

		/*this for loop searchs weather there is the same bigram*/

		for(it = myMap.begin(); it != myMap.end(); it++){
	        if(it->first.first == pairFirst && it->first.second == pairSecond){
	            it->second++;
	            flag = 1;
	        }
	    }

	    /*if not, the bigram that we found is added to the map*/

		if (flag == 0){
		    myMap.insert(pair<pair<T,T>,int>(pair<T,T>(pairFirst,pairSecond),1));
		}


		pairFirst = pairSecond;
	}

	numGramsVar--;

    fayl.close();

}

/*this function returns the number of bigram*/

template <class T>
int BigramMap<T>::numGrams()const{return numGramsVar;}

/*this function takes 2 parameters and returns how many bigrams are matched with them*/

template <class T>
int BigramMap<T>::numOfGrams(const T firstVar, const T secondVar){

	typename map<pair<T,T>,int>::iterator it;

	for(it = myMap.begin(); it != myMap.end(); it++){
		if(it->first.first == firstVar && it->first.second == secondVar){
			return it->second;
		}
	}

	return 0;

}

/*this function returns the most used bigram with pair*/

template <class T>
pair<T,T> BigramMap<T>::maxGrams(){

	int max=0;
	T firstVar,secondVar;
	typename map<pair<T,T>,int>::iterator it;

	for(it = myMap.begin(); it != myMap.end(); it++){
		if(it->second >= max){
			max = it->second;
			firstVar = it->first.first;
			secondVar = it->first.second;
		}
	}

	return pair<T,T>(firstVar,secondVar);
}

/*this function prints the bigrams and their values*/


template <class T>
void BigramMap<T>::helperFunc(ostream& output){

	typename map<pair<T,T>,int>::iterator it;
	for(it = myMap.begin(); it != myMap.end(); it++){
		output << "First: " << it->first.first << " Second: " << it->first.second << " Number: " << it->second << endl;
	}
}
