
/* [/]
 >> Project:    PoggersLoggers
 >> File:       resources.cpp
 >> Author:     Candy
[/] */

#include <iomanip>
#include <iostream>
#include <fstream>
using namespace std;
#include "resources.h"
#define XD -1

// Modulo de Traspaso de Letra a Minuscula
void pasarMayuscula(char &caracter) {
    if(caracter <= 'a' or caracter >= 'z') return;
    caracter -= 'a' - 'A';
}
// Modulo de Traspaso de Letra a Minuscula
void pasarMinuscula(char &caracter) {
    if(caracter <= 'A' or caracter >= 'Z') return;
    caracter += 'a' - 'A';
}
// Modulo de Apertura de Archivos IFSTREAM
void abrirArchivo_IFS(ifstream &arch, const char *nombArch) {
    arch.open(nombArch,ios::in);
    if(arch.is_open()) return;
    cout<<"ERROR DE APERTURA: Archivo '"<<nombArch<<"'."<<endl;
    exit(1);
}
// Modulo de Apertura de Archivos OFSTREAM
void abrirArchivo_OFS(ofstream &arch, const char *nombArch) {
    arch.open(nombArch,ios::out);
    if(arch.is_open()) return;
    cout<<"ERROR DE APERTURA: Archivo '"<<nombArch<<"'."<<endl;
    exit(1);
}
// Modulo de Impresion de Simbolos en Linea
void imprimirLinea(ofstream &archSalida, char simbolo, int medida, bool enter) {
    for(int i = 0;i < medida;i++) archSalida.put(simbolo);
    if(enter) archSalida<<endl;
}
// Modulo de Lectura de Valor Temporal [Ideado para: 'F' = Fecha {dd/mm/aaaa} || 'H' = Hora {hh:mm:ss}]
int leerValorTemporal(ifstream &archEntrada,char tipo) {
    int dd_hor,mm_min,aa_seg;
    char c;
    archEntrada>>dd_hor>>c>>mm_min>>c>>aa_seg;
    return compactarValorTemporal(dd_hor,mm_min,aa_seg,tipo);
}
// Modulo de Compactacion de Valor Temporal [Ideado para: 'F' = Fecha {dd/mm/aaaa} || 'H' = Hora {hh:mm:ss}]
int compactarValorTemporal(int dd_hor,int mm_min,int aa_seg,char tipo) {
    if(tipo == 'F') return aa_seg*10000 + mm_min * 100 + dd_hor;
    else return dd_hor * 3600 + mm_min * 60 + aa_seg;
}
// Modulo de Impresion de Valor Temporal [Ideado para: 'F' = Fecha {dd/mm/aaaa} || 'H' = Hora {hh:mm:ss}]
void imprimirValorTemporal(ofstream &archSalida,int valor,char tipo) {
    int dd_hor,mm_min,aa_seg,n = ((tipo == 'F')? 4:2);
    char simbolo = ((tipo == 'F')? '/' : ':');
    descompactarValorTemporal(valor,dd_hor,mm_min,aa_seg,tipo);
    archSalida.fill('0');
    archSalida<<setw(2)<<dd_hor<<simbolo<<setw(2)<<mm_min<<simbolo<<setw(n)<<aa_seg;
    archSalida.fill(' ');
}
// Modulo de Descompactacion de Valor Temporal [Ideado para: 'F' = Fecha {dd/mm/aaaa} || 'H' = Hora {hh:mm:ss}]
void descompactarValorTemporal(int valor,int &dd_hor,int &mm_min,int &aa_seg,char tipo) {
    if(tipo == 'F') {
        aa_seg = valor/10000;
        mm_min = (valor - aa_seg*10000)/100;
        dd_hor = valor - aa_seg*10000 - mm_min*100;
    } else {
        dd_hor = valor/3600;
        mm_min = (valor - dd_hor*3600)/60;
        aa_seg = valor - dd_hor*3600 - mm_min*60;
    }
}
// Modulo de Intercambio de Valores en Arreglo [INT] por Posicion
void intercambiarValor(int i,int j,int *arreglo) {
    int auxiliar = arreglo[i];
    arreglo[i] = arreglo[j];
    arreglo[j] = auxiliar;
}
// Modulo de Intercambio de Valores en Arreglo [CHAR] por Posicion
void intercambiarValor(int i,int j,char *arreglo) {
    char auxiliar = arreglo[i];
    arreglo[i] = arreglo[j];
    arreglo[j] = auxiliar;
}
// Modulo de Busqueda Binaria de Posicion en Arreglo [INT] ORDENADO ASCENDENTEMENTE
int obtenerPosicion_Bin_Asc(int valor, int *arreglo, int medida) {
    int posIni = 0,posFin = medida - 1,posMid;
    while(1) {
        if(posIni > posFin) return XD;
        posMid = (posIni + posFin)/2;
        if(arreglo[posMid] == valor) return posMid;
        else if(valor > arreglo[posMid]) posIni = posMid + 1;
        else posFin = posMid - 1;
    }
}
