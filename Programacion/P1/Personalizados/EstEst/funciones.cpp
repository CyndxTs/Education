
/* [/]
 >> Project:    EstEst
 >> File:       funciones.cpp
 >> Author:     Candy
[/] */

#include <iomanip>
#include <iostream>
#include <fstream>
#include <cstring>
using namespace std;
#include "funciones.h"
#define MAXTEXTO 40

void cargarMedicos(const char *nombArchMed,struct Medico *arrMed,int &cantMed) {
    double tarifa;
    char *cadAux,estAux[100];
    ifstream archMed(nombArchMed,ios::in);
    while(1) {
        cadAux = leerCadenaExacta(archMed,MAXTEXTO,' ');
        if(cadAux == nullptr) break;
        arrMed[cantMed].id = cadAux;
        cadAux = leerCadenaExacta(archMed,MAXTEXTO,' ');
        arrMed[cantMed].nombre = cadAux;
        // cadAux = leerCadenaExacta(archMed,MAXTEXTO,' ');  // tbn funciona
        // strcpy(arrMed[cantMed].especialidad,cadAux);
        archMed>>estAux;
        strcpy(arrMed[cantMed].especialidad,estAux);
        archMed>>tarifa;
        arrMed[cantMed].tarifa = tarifa;
        arrMed[cantMed].sumaTiempo = 0;
        arrMed[cantMed].cantAtenciones = 0;
        cantMed++;
    }
}

void imprimirMedicos(const char *nombArchRep,struct Medico *arrMed,int cantMed) {
    ofstream archRep(nombArchRep,ios::out);
    for(int i = 0;i < cantMed;i++) {
        archRep<<'['<<arrMed[i].id<<"] ";
        archRep<<left<<setw(MAXTEXTO)<<arrMed[i].nombre<<right<<setw(8)<<" ";
        archRep<<arrMed[i].especialidad<<endl;
        archRep<<setw(12)<<arrMed[i].tarifa;
        archRep<<setw(12)<<arrMed[i].sumaTiempo;
        archRep<<setw(12)<<arrMed[i].cantAtenciones<<endl;
    }
}

// FIJA
char *leerCadenaExacta(ifstream &archEntrada,int medida,char delimitador) {
    char buff[medida];
    if(delimitador == ' ') archEntrada>>buff;
    else archEntrada.getline(buff,medida,delimitador);
    if(archEntrada.eof()) return nullptr;
    return obtenerDinamicoExacto(buff);
}
// ANTIFIJA
char *leerCadenaExacta(ifstream &archEntrada,char delimitador) {
    int i = 0;
    char buff[100];
    while(1) {
        buff[i] = archEntrada.get();
        if(archEntrada.eof()) return nullptr;
        if(buff[i] == delimitador or buff[i] == '\n') break;
        i++;
    }
    buff[i] = 0;
    return obtenerDinamicoExacto(buff);
}
// SUPERFIJA
char *obtenerDinamicoExacto(char *buff) {
    char *dinamico = new char[strlen(buff) + 1];
    strcpy(dinamico,buff);
    return dinamico;
}