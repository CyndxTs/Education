
/* [/]
 >> Project:    Priorizando
 >> File:       ColaPrioritaria.cpp
 >> Author:     Candy
[/] */

#include <iomanip>
#include <iostream>
#include <fstream>
using namespace std;
#include "ColaPrioritaria.h"

//
void construir(struct ColaPrioritaria &colaPrioritaria) {
    colaPrioritaria.inicial = nullptr;
    for(int i = 0;i < MAXPRIORIDADES;i++) colaPrioritaria.colas[i] = nullptr;
    colaPrioritaria.longitud = 0;
}
//
void imprimir(const struct ColaPrioritaria &colaPrioritaria) {
    //
    struct Nodo *nAux = colaPrioritaria.inicial;
    //
    if(nAux != nullptr) {
        while(nAux != nullptr) {
            cout<<" ["<<nAux->dato.prioridad<<"] "<<nAux->dato.nombre<<endl;
            nAux = nAux->prox;
        }
    } else cout<<"La colaPrioritaria esta vacia.."<<endl;
}
//
void insertarPriorizando(struct ColaPrioritaria &colaPrioritaria,const struct Dato &dato) {
    //
    struct Nodo *nAux = colaPrioritaria.inicial, *nNuevo = obtenerNuevoNodo(dato);
    int posPrioridad = dato.prioridad - 1,posPrioridad_Anterior = -1;
    //
    if(nAux != nullptr) {                                                       //
        if(colaPrioritaria.colas[posPrioridad] != nullptr) {                    //
            nNuevo->prox = colaPrioritaria.colas[posPrioridad]->prox;
            colaPrioritaria.colas[posPrioridad]->prox = nNuevo;
        } else {
            for(int i = 0;i < posPrioridad;i++) {                               //
                if(colaPrioritaria.colas[i] != nullptr) {                       //
                    posPrioridad_Anterior = i;
                }
            }
            if(posPrioridad_Anterior >= 0) {                                    //
                nNuevo->prox = colaPrioritaria.colas[posPrioridad_Anterior]->prox;
                colaPrioritaria.colas[posPrioridad_Anterior]->prox = nNuevo;
            } else {
                nNuevo->prox = colaPrioritaria.inicial;
                colaPrioritaria.inicial = nNuevo;
            }
        }
    } else colaPrioritaria.inicial = nNuevo;
    colaPrioritaria.colas[posPrioridad] = nNuevo;
    colaPrioritaria.longitud++;
}
//
struct Nodo *obtenerNuevoNodo(const struct Dato &dato) {
    struct Nodo *nNuevo = new Nodo;
    nNuevo->dato = dato;
    nNuevo->prox = nullptr;
    return nNuevo;
}
