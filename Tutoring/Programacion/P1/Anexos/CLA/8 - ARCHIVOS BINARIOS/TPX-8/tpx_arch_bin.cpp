
/* [/]
 >> Project:    TPX_ArchivosBinarios
 >> File:       tpx_arch_bin.cpp
 >> Author:     Candy
[/] */

#include <iomanip>
#include <iostream>
#include <fstream>
#include <cstring>
using namespace std;
#include "tpx.h"


void abrirArchivo_BIN_Escribir(ofstream &arch,const char *nombArch) {
    arch.open(nombArch,ios::out|ios::binary);
    if(arch.is_open()) return;
    cout<<"ERROR DE APERTURA: Archivo '"<<nombArch<<"'."<<endl;
    exit(1);
}

void abrirArchivo_BIN_Leer(ifstream &arch,const char *nombArch) {
    arch.open(nombArch,ios::in|ios::binary);
    if(arch.is_open()) return;
    cout<<"ERROR DE APERTURA: Archivo '"<<nombArch<<"'."<<endl;
    exit(1);
}

void abrirArchivo_BIN_Actualizar(fstream &arch,const char *nombArch) {
    arch.open(nombArch,ios::in|ios::out|ios::binary);
    if(arch.is_open()) return;
    cout<<"ERROR DE APERTURA: Archivo '"<<nombArch<<"'."<<endl;
    exit(1);
}
