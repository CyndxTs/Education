
/* [/]
 >> Project:    HyperPedidos
 >> File:       resources.cpp
 >> Author:     Candy
[/] */

#include <iomanip>
#include <iostream>
#include <fstream>
using namespace std;
#include "resources.h"
#define XD -1

// Modulo de Traspaso de Letra a Mayuscula
void pasarMayuscula(char &car) {
    if(car < 'a' or car > 'z') return;
    car -= 'a' - 'A';
}
// Modulo de Traspaso de Letra a Minuscula
void pasarMinuscula(char &car) {
    if(car < 'A' or car > 'Z') return;
    car += 'a' - 'A';
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
// Modulo de Busqueda Binaria de Posicion en Arreglo [INT] ORDENADO ASCENDENTEMENTE
int obtenerPosicion_Bin_Asc(int valor, int *arreglo, int medida) {
    int posIni = 0,posFin = medida - 1,posMid;
    while(1) {
        if(posIni > posFin) return XD;
        posMid = (posIni + posFin)/2;
        if(arreglo[posMid] == valor) return posMid;
        else if(valor > arreglo[posMid]) posIni = posMid + 1;   //
        else posFin = posMid - 1;
    }
}
// Modulo de Insercion de Elemento en Arreglo [INT]
void insertarElemento(int valor,int pos,int *arreglo,int &medida) {
    for(int i = medida;i > pos;i--) arreglo[i] = arreglo[i-1];
    arreglo[pos] = valor;
    medida++;
}
// Modulo de Insercion ORDENADA ASCENDENTEMENTE de Elemento en Arreglo [INT] {VERSION REDUCIDA}
void insertarOrdenado_Asc_V2(int valor,int *arreglo,int &medida){
    for(int i = 0;i <= medida;i++) {
        if(valor < arreglo[i] or i == medida) {
            insertarElemento(valor,i,arreglo,medida);
            break;
        }
    }
}
// Modulo de Medicion de Longitud de Cadena
int medir(const char *cad) {
    int i = 0;
    while(cad[i]) i++;
    return i;
}
// Modulo de Copia de Cadena Origen en Cadena Destino
void copiar(char *cadDest,const char *cadOrig) {
    int i = 0;
    while(cadOrig[i]) {
        cadDest[i] = cadOrig[i];
        i++;
    }
    cadDest[i] = 0;
}
// Version Reducida del Modulo de Concatenacion
void concatenar_V2(char *cad1,const char *cad2) {
    int i = medir(cad1);
    copiar(&cad1[i],cad2);
}
// Modulo de Normalizacion de Cadena [abCde EdcBA -> Abcde Edcba]
void normalizar(char *cad) {
    bool ini = true;
    for(int i = 0;cad[i];i++) {
        if(ini) {
            pasarMayuscula(cad[i]);
            ini = false;
        } else if(cad[i] == ' ') ini = true;
        else pasarMinuscula(cad[i]);
    }
}
// Modulo de Busqueda SECUENCIAL de Posicion de Palaba en Cadena
int obtenerPosisionDePalabraEnCadena(const char *palabra,const char *cadena) {
    int posBuff = 0,posPalabra = 0,posResultado = XD;
    while(cadena[posBuff]) {
        if(cadena[posBuff] == palabra[posPalabra]) {
            if(posPalabra == 0) posResultado = posBuff;
            posPalabra++;
            if(palabra[posPalabra] == 0) break;
        } else if(posPalabra > 0) {
            posBuff--;
            posPalabra = 0;
            posResultado = XD;
        }
        posBuff++;
    }
    return posResultado;
}
