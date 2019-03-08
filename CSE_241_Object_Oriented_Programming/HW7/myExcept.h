#ifndef MYEXCEPT_H
#define MYEXCEPT_H

#include <iostream>
#include <exception>

using namespace std;

class myExcept: public exception{

    virtual const char* what()const throw(){
        return "ERROR.";
    }
};

#endif