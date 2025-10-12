#include<iomanip>
#include <iostream>
#include <fstream>
using namespace  std;
#include "funciones.h"
#define MAXGRANDE 50
void cargarSumas(const char *nombArchNum,const char *nombArchRep,int *numeroGrande1,int *numeroGrande2,int *totalGrande) {
    int posSolucion = 1;
    ifstream archNum;
    abrirAchivo_IFS(archNum,nombArchNum);
    ofstream archRep;
    abrirAchivo_OFS(archRep,nombArchRep);
    leerNumero(archNum,numeroGrande1);
    while (1) {
        leerNumero(archNum,numeroGrande2);
        if (archNum.eof()) break;
        sumarNumerosGrandes(numeroGrande1,numeroGrande2,totalGrande);
        imprimirResultado(archRep,totalGrande,posSolucion);
        copiarNumeroGrande(totalGrande,numeroGrande1);
        posSolucion++;
    }
}

void sumarNumerosGrandes(int *numeroGrande1,int *numeroGrande2,int *totalGrande) {
    bool acarreo = false,tomarG1=true,tomarG2=true;
    int suma,pos1 = obtenerMedida(numeroGrande1) - 1,pos2 = obtenerMedida(numeroGrande2) - 1,pos3 = 0;
    while(tomarG1 || tomarG2) {
        if (pos1 < 0) tomarG1 = false;
        if (pos2 < 0) tomarG2 = false;
        suma = tomarG1*numeroGrande1[pos1] + tomarG2*numeroGrande2[pos2] + acarreo*1;
        acarreo = false;
        if (suma >= 10) {
            suma -= 10;
            acarreo = true;
        }
        totalGrande[pos3] = suma;
        pos1--;
        pos2--;
        pos3++;
    }
    if (acarreo) {
        totalGrande[pos3] = 1;
        totalGrande[pos3+1] = -1;
    } else totalGrande[pos3 - 1] = -1; // aqui se tenia que poner "pos3-1" , pues antes se habia heho pos++
}

void leerNumero(ifstream &archNum,int *numero) {
    int digito,pos = 0;
    while (1) {
        archNum>>digito;
        if (archNum.eof()) return;
        numero[pos] = digito;
        if (archNum.get() == '\n') {
            numero[pos + 1] = -1;
            break;
        }
        pos++;
    }
}

void abrirAchivo_IFS(ifstream &arch,const char *nombArch) {
    arch.open(nombArch,ios::in);
    if (arch.is_open()) return;
    cout<<"ERROR: No se encontro el archivo '"<<nombArch<<"'."<<endl;
    exit(1);
}

void abrirAchivo_OFS(ofstream &arch,const char *nombArch) {
    arch.open(nombArch,ios::out);
    if (arch.is_open()) return;
    cout<<"ERROR: No se encontro el archivo '"<<nombArch<<"'."<<endl;
    exit(1);
}

void imprimirResultado(ofstream &archReporte,int *numeroGrande, int posSol) {
    int medida = obtenerMedida(numeroGrande);
    archReporte<<"RESULTADO #"<<posSol<<": ";
    archReporte<<setw(MAXGRANDE - medida)<<" ";
    for (int i = medida - 1; i>= 0;i--) {
        archReporte<<numeroGrande[i];
    }
    archReporte<<endl;
}

int obtenerMedida(int *numeroGrande) {
    int medida = 0;
    while (1) {
        if (numeroGrande[medida] == -1) break;
        medida++;
    }
    return medida;
}

void copiarNumeroGrande(int *numOrig,int *numDest) {
    int posOrig = obtenerMedida(numOrig) - 1;
    int posDest = 0;
    while (posOrig >= 0) {
        numDest[posDest] = numOrig[posOrig];
        posDest++;
        posOrig--;
    }
    numDest[posDest] = -1;
}
