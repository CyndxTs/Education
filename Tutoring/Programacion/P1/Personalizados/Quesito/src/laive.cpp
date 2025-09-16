

#include <iostream>
#include <fstream>
using namespace std;
#include "laive.h"


void tipoQueso() {
    ifstream archMain("twitchdataTP.txt",ios::in);
    int num;
    archMain>>num;
    cout<<num<<endl;
}