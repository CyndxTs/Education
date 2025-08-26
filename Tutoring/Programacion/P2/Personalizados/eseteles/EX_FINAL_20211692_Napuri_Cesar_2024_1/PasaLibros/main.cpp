
/* 
 * File:   main.cpp
 * Author: Cesar Augusto Napuri de la Cruz
 * Codigo: 20211692
 */

#include <iostream>
#include <iomanip>
#include <fstream>
#include <cstring>
using namespace std;
#include "Procesa.h"

/*
 * 
 */
int main(int argc, char** argv) {

    Procesa pro;
    
    pro.carga();
    pro.pasa();
    pro.muestra();
    
    return 0;
}

