
/* [/]
 >> Project:    TPX-9
 >> File:       tpx.cpp
 >> Author:     Candy
[/] */

#include <iomanip>
#include <iostream>
#include <fstream>
#include <cstring>
using namespace std;
#include "tpx.h"

//
struct NodoSimple *obtenerNodo(struct Dato dato, struct NodoSimple *prox) {
    struct NodoSimple *ptNodo = new struct NodoSimple;
    ptNodo->dato = dato;
    ptNodo->prox = prox;
    return ptNodo;
}
//
struct NodoDoble *obtenerNodo(struct Dato dato, struct NodoDoble *prev, struct NodoDoble *prox) {
    struct NodoDoble *ptNodo = new struct NodoDoble;
    ptNodo->dato = dato;
    ptNodo->prev = prev;
    ptNodo->prox = prox;
    return ptNodo;
}
//
void construir(struct LSE &lista) {
    lista.longitud = 0;
    lista.inicial = nullptr;
}
//
void construir(struct LC &lista) {
    lista.longitud = 0;
    lista.inicial = nullptr;
}
//
void construir(struct LDE &lista) {
    lista.longitud = 0;
    lista.inicial = nullptr;
}
//
void construir(struct LDEC &lista) {
    lista.longitud = 0;
    lista.inicial = nullptr;
}
//
void construir(struct Pila &pila) {
    pila.medida = 0;
    pila.cima = nullptr;
}
//
void construir(struct Cola &cola) {
    cola.medida = 0;
    cola.cabeza = nullptr;
    cola.cola = nullptr;
}
