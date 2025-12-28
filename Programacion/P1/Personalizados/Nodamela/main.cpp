
/* [/]
 >> Project:    Nodamela
 >> File:       main.cpp
 >> Author:     
[/] */

#include <iomanip>
#include <iostream>
#include <fstream>
#include <cstring>
using namespace std;
#include "Lista.h"

/*
 a -> b  == (*a).b
*/
struct Nodo *crearNodo(int numero,struct Nodo *prox) {
    struct Nodo *nodo = new struct Nodo;
    nodo->numero = numero;
    nodo->prox = prox;
};
//
void insertarFinal(struct Lista &lista,int numero) {
    struct Nodo *nAux = lista.inicial,*nAnt = nullptr;
    struct Nodo *newNodo = crearNodo(numero,nullptr);
    if(nAux != nullptr) {
        while(nAux != nullptr) {
            nAnt = nAux;
            nAux = nAux->prox;
        }
        nAnt->prox = newNodo;
    } else lista.inicial = newNodo;
}
//
void insertarInicio(struct Lista &lista,int numero) {
    struct Nodo *newNodo = crearNodo(numero,lista.inicial);
    lista.inicial = newNodo;
}
//
void imprimirLista(struct Lista lista) {
    struct Nodo *nAux = lista.inicial;
    cout<<"[ ";
    while(nAux != nullptr) {
        cout<<"'"<<nAux->numero<<"' ";
        nAux = nAux->prox;
    }
    cout<<"]"<<endl;
}

int main(int argc, char** argv) {

    struct Lista lista;
    
    for(int i = 0;i < 77;i++) insertarInicio(lista,i+1);
    imprimirLista(lista);
    
    return 0;
}
