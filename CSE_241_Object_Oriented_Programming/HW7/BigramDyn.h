#ifndef BIGRAMDYN_H
#define BIGRAMDYN_H

#include <iostream>
#include <string>
#include <map>
#include <utility>
#include <fstream>
#include "Bigram.h"
#include "myExcept.h"

using namespace std;

template <class T> class BigramDyn: public Bigram<T>{
	public:
		BigramDyn(int dataType);
		BigramDyn(const BigramDyn&);
		void readFile(string);
		BigramDyn& operator =(const BigramDyn& other);
		int numGrams()const;
		int numOfGrams(const T, const T);
		pair<T,T> maxGrams();
		void helperFunc(ostream& output);
		virtual ~BigramDyn();
	private:
		T** database;
		int* howMany;
		int sizeOfDyn;
		int numGramsVar;
		int datatype;
};

#endif