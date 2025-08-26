
/* [/]
 >> Project:    TPX-6
 >> File:       tpx.cpp
 >> Author:     Candy
[/] */

#include <iomanip>
#include <iostream>
#include <fstream>
#include <cstring>
using namespace std;
#include "tpx.h"
#define MAXTEXTO 200

//
char *obtenerDinamicoExacto(const char *buffer) {
    char *dinamico = new char[strlen(buffer) + 1];
    strcpy(dinamico,buffer);
    return dinamico;
}
// Modulo de Lectura de Cadena Exacta
char *leerCadenaExacta(ifstream &archEntrada,char delimitador) {
    char buffer[MAXTEXTO];
    if(delimitador == ' ') archEntrada>>buffer;
    else archEntrada.getline(buffer,MAXTEXTO,delimitador);
    return obtenerDinamicoExacto(buffer);
}
// Modulo de Lectura de Cadena Exacta
char *leerCadenaExacta(ifstream &archEntrada,int maxMed,char delimitador) {
    char buffer[maxMed];
    if(delimitador == ' ') archEntrada>>buffer;
    else archEntrada.getline(buffer,maxMed,delimitador);
    return obtenerDinamicoExacto(buffer);
}
