
/* [/]
 >> Project:    TPX-5
 >> File:       tpx.cpp
 >> Author:     Candy
[/] */

#include <iomanip>
#include <iostream>
#include <fstream>
#include <cstring>
using namespace std;
#include "tpx.h"

// Modulo de Impresion de Caracteres de Cadena
void imprimirCadena(char *cadena,int medida) {
    cout<<"[ ";
    for(int i = 0;i < medida;i++) cout<<"'"<<cadena[i]<<"' ";
    cout<<"]";
}
// Modulo de Cálculo de Longitud de Cadena
int longitud(const char *cadena) {
    int i = 0;
    while(cadena[i]) i++;
    return i;
}
// Modulo de Copia de Cadena Origen a Cadena Destino
void copiar(char *cadDest,const char *cadOrig) {
    int i = 0;
    while(cadOrig[i]) {
        cadDest[i] = cadOrig[i];
        i++;
    }
    cadDest[i] = 0;
}
// Modulo de Comparacion entre Cadenas
int comparar(const char *cad1,const char *cad2) {
    for(int i = 0;1;i++) {
        if(cad1[i] > cad2[i]) return 1;
        if(cad1[i] < cad2[i]) return -1;
        if(cad1[i] == 0 or cad2[i] == 0) return 0; 
    }
}
// Modulo de Concatenacion de Cadenas [cad1 + cad2 -> cad1cad2]
void concatenar(char *cad1,const char *cad2) {
    int i = longitud(cad1),j = 0;
    while(cad2[j]) {
        cad1[i] = cad2[j];
        i++;
        j++;
    }
    cad1[i] = 0;
}
// Modulo de Concatenacion de Cadenas [cad1 + cad2 -> cad1cad2] {VERSION OPTIMA}
void concatenar_V2(char *cad1,const char *cad2) {
    int i = longitud(cad1);
    copiar(&cad1[i],cad2);
}
// Modulo de Busqueda Binaria de Posicion de Caracter en Cadena ORDENADA ASCENDENTE
int obtenerPosicion_Bin_Asc(char valor,char *arreglo,int medida) {
    int posIni = 0,posFin = medida - 1,posMid;
    while(1) { 
        if(posIni > posFin) return -1;
        posMid = (posIni + posFin)/2;
        if(arreglo[posMid] == valor) return posMid;
        else if(arreglo[posMid] > valor) posFin = posMid - 1;
        else posIni = posMid + 1;
    }
}
// Modulo de Busqueda Binaria de Posicion de Caracter en Cadena ORDENADA DESCENDENTE
int obtenerPosicion_Bin_Desc(char valor,char *arreglo,int medida) {
    int posIni = 0,posFin = medida - 1,posMid;
    while(1) { 
        if(posIni > posFin) return -1;
        posMid = (posIni + posFin)/2;
        if(arreglo[posMid] == valor) return posMid;
        else if(arreglo[posMid] < valor) posFin = posMid - 1;
        else posIni = posMid + 1;
    }
}
// Modulo de Eliminacion de Elemento de Cadena
void eliminarElemento(int pos,char *arreglo,int &medida) {
    for(int i = pos; i < medida;i++) arreglo[i] = arreglo[i+1];
    medida--;
}
// Modulo de Insercion de Elemento en Cadena
void insertarElemento(char valor,int pos,char *arreglo,int &medida) {
    for(int i = medida;i > pos;i--) arreglo[i] = arreglo[i-1];
    arreglo[pos] = valor;
    medida++;
}
// Modulo Insercion Ordenada ASCENDENTEMENTE de Elemento en Cadena {VERSION OPTIMA}
void insertarOrdenado_Asc(char valor,char *arreglo,int &medida){
    int i = 0;
    while(i < medida) {
        if(valor < arreglo[i]) {
            insertarElemento(valor,i,arreglo,medida);
            break;
        }
        i++;
    }
    if(i == medida) {
        arreglo[i] = valor;
        medida++;
    }
}
// Modulo Insercion Ordenada ASCENDENTEMENTE de Elemento en Cadena {VERSION REDUCIDA}
void insertarOrdenado_Asc_V2(char valor,char *arreglo,int &medida){
    for(int i = 0;i <= medida;i++) {
        if(valor < arreglo[i] or i == medida) {
            insertarElemento(valor,i,arreglo,medida);
            break;
        }
    }
}
// Modulo Insercion Ordenada DESCENDENTEMENTE de Elemento en Cadena {VERSION OPTIMA}
void insertarOrdenado_Desc(char valor,char *arreglo,int &medida){
    int i = 0;
    while(i < medida) {
        if(valor > arreglo[i]) {
            insertarElemento(valor,i,arreglo,medida);
            break;
        }
        i++;
    }
    if(i == medida) {
        arreglo[i] = valor;
        medida++;
    }
}
// Modulo Insercion Ordenada DESCENDENTEMENTE de Elemento en Cadena {VERSION REDUCIDA}
void insertarOrdenado_Desc_V2(char valor,char *arreglo,int &medida){
    for(int i = 0;i <= medida;i++) {
        if(valor > arreglo[i] or i == medida) {
            insertarElemento(valor,i,arreglo,medida);
            break;
        }
    }
}
// Modulo de Busqueda SECUENCIAL de Posicion de INICIO de Patrón en Cadena
int obtenerPosision(const char *cadPatron,const char *cadBuff) {
    for(int posBuff = 0,posPatron = 0;cadBuff[posBuff];posBuff++) {
        if(cadBuff[posBuff] == cadPatron[posPatron]) {
            posPatron++;
            if(!cadPatron[posPatron]) return posBuff - posPatron + 1;
        } else if(posPatron > 0) {
            posBuff--;
            posPatron = 0;
        }
    }
    return -1;
}
// Modulo de Busqueda SECUENCIAL de Posicion de INICIO de PROXIMO Patrón en Cadena
int obtenerPosision(const char *cadPatron,const char *cadBuff,int &posBuff) {
    for(int posPatron = 0;cadBuff[posBuff];posBuff++) {
        if(cadBuff[posBuff] == cadPatron[posPatron]) {
            posPatron++;
            if(!cadPatron[posPatron]) {
                posBuff++;
                return posBuff - posPatron + 1;
            }
        } else if(posPatron > 0) {
            posBuff--;
            posPatron = 0;
        }
    }
    return -1;
}
