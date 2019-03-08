#ifndef BIGRAM_H
#define BIGRAM_H

#include <iostream>
#include <string>
#include <map>
#include <utility>
#include <fstream>

using namespace std;

/*HAKKI ERDEM DUMAN 151044005 CPP HW07*/

template <class T> class Bigram{
	public:
		virtual void readFile(string) = 0;
		virtual int numGrams() const = 0;
		virtual int numOfGrams(const T,const T) = 0;
		virtual pair<T,T> maxGrams() = 0;
		friend ostream& operator <<(ostream& output, Bigram<T> &other){
			other.helperFunc(output);
			return output;
		}
		virtual void helperFunc(ostream& output) = 0;
};

#endif