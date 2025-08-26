
/* [/]
 >> Project:    TPX-4
 >> File:       tpx.cpp
 >> Author:     Candy
[/] */

#include <iomanip>
#include <iostream>
#include <fstream>
using namespace std;
#include "tpx.h"

// Modulo de Impresion de Valores de Arreglo [INT]
void imprimirArreglo(int *arreglo,int medida) {
    cout<<"[ ";
    for(int i = 0;i < medida;i++) cout<<"'"<<arreglo[i]<<"' ";
    cout<<"]";
}
// Modulo de Busqueda Secuencial de Posicion de Valor en Arreglo [INT] {1 CRITERIO DE VALIDACION}
int obtenerPosicion(int valor, int *arreglo, int medida) {
    for(int i = 0;i < medida;i++) if(arreglo[i] == valor) return i;
    return -1;
}
// Modulo de Busqueda Secuencial de Posicion de Valor en Arreglo [INT] {2 CRITERIOS DE VALIDACION}
int obtenerPosicion(int val_A,int val_B,int *arr_A,int *arr_B,int medida) {
    for(int i = 0;i < medida;i++) if(arr_A[i] == val_A and arr_B[i] == val_B) return i;
    return -1;
}
// Modulo de Intercambio de Valores en Arreglo [INT] {POR POSICIÓN}
void intercambiarValor(int i,int j,int *arreglo) {
    int auxiliar = arreglo[i];
    arreglo[i] = arreglo[j];
    arreglo[j] = auxiliar;
}
// Modulo de Intercambio de Valores en Arreglo [INT] {POR REFERENCIA}
void intercambiarValor(int &val_i,int &val_j) {
    int auxiliar = val_i;
    val_i = val_j;
    val_j = auxiliar;
}
// Modulo de Ordenamiento ASCENDENTE de Arreglos [INT] {1 CRITERIO DE ORDENAMIENTO}
void ordenar_1C_Asc(int *arreglo,int medida) {
    for(int i = 0;i < medida - 1;i++){
        for(int j = i + 1;j < medida;j++){
            if(arreglo[i] > arreglo[j]){
                intercambiarValor(i,j,arreglo);
            }
        }
    }
}
// Modulo de Ordenamiento DESCENDENTE de Arreglos [INT] {1 CRITERIO DE ORDENAMIENTO}
void ordenar_1C_Desc(int *arreglo,int medida) {
    for(int i = 0;i < medida - 1;i++){
        for(int j = i + 1;j < medida;j++){
            if(arreglo[i] < arreglo[j]){
                intercambiarValor(i,j,arreglo);
            }
        }
    }
}
// Modulo de Ordenamiento de Arreglos [INT] {2 CRITERIOS DE ORDENAMIENTO}
void ordenar_2C(int *arr_1,int *arr_2,int medida) {
    for(int i = 0;i < medida - 1;i++){
        for(int j = i + 1;j < medida;j++){
            if(arr_1[i] < arr_1[j] or
                ((arr_1[i] == arr_1[j]) and (arr_2[i] < arr_2[j]))){
                intercambiarValor(i,j,arr_1);
                intercambiarValor(i,j,arr_2);
            }
        }
    }
}
// Modulo de Ordenamiento de Arreglos [INT] {3 CRITERIOS DE ORDENAMIENTO}
void ordenar_3C(int *arr_1,int *arr_2,int *arr_3,int medida) {
    for(int i = 0;i < medida - 1;i++){
        for(int j = i + 1;j < medida;j++){
            if(arr_1[i] < arr_1[j] or
                ((arr_1[i] == arr_1[j]) and ((arr_2[i] < arr_2[j]) or
                    (arr_2[i] == arr_2[j] and (arr_3[i] < arr_3[j]))))){
                intercambiarValor(i,j,arr_1);
                intercambiarValor(i,j,arr_2);
                intercambiarValor(i,j,arr_3);
            }
        }
    }
}
// Modulo de Busqueda Binaria de Posicion de Valor en Arreglo [INT] ORDENADO ASCENDENTEMENTE
int obtenerPosicion_Bin_Asc(int valor, int *arreglo, int medida) {
    int posIni = 0,posFin = medida - 1,posMid;
    while(1) {
        if(posIni > posFin) return -1;
        posMid = (posIni + posFin)/2;
        if(arreglo[posMid] == valor) return posMid;
        else if(valor > arreglo[posMid]) posIni = posMid + 1;
        else posFin = posMid - 1;
    }
}
// Modulo de Busqueda Binaria de Posicion de Valor en Arreglo [INT] ORDENADO DESCENDENTEMENTE
int obtenerPosicion_Bin_Desc(int valor, int *arreglo, int medida) {
    int posIni = 0,posFin = medida - 1,posMid;
    while(1) {
        if(posIni > posFin) return -1;
        posMid = (posIni + posFin)/2;
        if(arreglo[posMid] == valor) return posMid;
        else if(valor < arreglo[posMid]) posIni = posMid + 1;
        else posFin = posMid - 1;
    }
}
// Modulo de Eliminacion de Elemento de Arreglo [INT]
void eliminarElemento(int pos,int *arreglo,int &medida) {
    for(int i = pos; i < medida;i++) arreglo[i] = arreglo[i+1];
    medida--;
}
// Modulo de Insercion de Elemento en Arreglo [INT]
void insertarElemento(int valor,int pos,int *arreglo,int &medida) {
    for(int i = medida;i > pos;i--) arreglo[i] = arreglo[i-1];
    arreglo[pos] = valor;
    medida++;
}
// Modulo de Insercion ORDENADA ASCENDENTEMENTE de Elemento en Arreglo [INT] {VERSION ÓPTIMA}
void insertarOrdenado_Asc(int valor,int *arreglo,int &medida){
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
// Modulo de Insercion ORDENADA ASCENDENTEMENTE de Elemento en Arreglo [INT] {VERSION REDUCIDA}
void insertarOrdenado_Asc_V2(int valor,int *arreglo,int &medida){
    for(int i = 0;i <= medida;i++) {
        if(valor < arreglo[i] or i == medida) {
            insertarElemento(valor,i,arreglo,medida);
            break;
        }
    }
}
// Modulo de Insercion ORDENADA DESCENDENTEMENTE de Elemento en Arreglo [INT] {VERSION ÓPTIMA}
void insertarOrdenado_Desc(int valor,int *arreglo,int &medida){
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
// Modulo de Insercion ORDENADA DESCENDENTEMENTE de Elemento en Arreglo [INT] {VERSION REDUCIDA}
void insertarOrdenado_Desc_V2(int valor,int *arreglo,int &medida){
    for(int i = 0;i <= medida;i++) {
        if(valor < arreglo[i] or i == medida) {
            insertarElemento(valor,i,arreglo,medida);
            break;
        }
    }
}
