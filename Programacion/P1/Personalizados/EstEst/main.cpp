
/* [/]
 >> Project:    EstEst
 >> File:       main.cpp
 >> Author:     Candy
[/] */

#include <iomanip>
#include <iostream>
#include <fstream>
#include <cstring>
using namespace std;
#include "funciones.h"
#define MAXMED 100

int main(int argc, char** argv) {

    struct Medico arrMed[MAXMED];
    int cantMed = 0;
    
    cargarMedicos("Medicos.txt",arrMed,cantMed);
    imprimirMedicos("ReporteDeMedicos.txt",arrMed,cantMed);
    
    return 0;
}
