
/* [/]
 >> Project:    Priorizando
 >> File:       ColaPrioritaria.h
 >> Author:     Candy
[/] */

#ifndef COLAPRIORITARIA_H
#define COLAPRIORITARIA_H
#define MAXPRIORIDADES 8
#include "Nodo.h"

struct ColaPrioritaria {
    struct Nodo *inicial;
    struct Nodo *colas[MAXPRIORIDADES];
    int longitud;
};

void construir(struct ColaPrioritaria &);

void imprimir(const struct ColaPrioritaria &);

void insertarPriorizando(struct ColaPrioritaria &,const struct Dato &);

struct Nodo *obtenerNuevoNodo(const struct Dato &);

#endif /* COLAPRIORITARIA_H */
