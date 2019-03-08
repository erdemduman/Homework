#ifndef BIGRAMAP_H
#define BIGRAMAP_H

#include <iostream>
#include <string>
#include <map>
#include <utility>
#include <fstream>
#include "Bigram.h"
#include "myExcept.h"

using namespace std;

template <class T> class BigramMap: public Bigram<T>{
	public:
		BigramMap(int dataType);
		void readFile(string);
		int numGrams()const;
		int numOfGrams(const T,const T);
		pair<T,T> maxGrams();
		void helperFunc(ostream& output);
	private:
		map < pair<T,T>, int > myMap;
		int numGramsVar;
		int datatype;
};

#endif



