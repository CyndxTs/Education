
/* [/]
 >> Project:    Priorizando
 >> File:       Nodo.h
 >> Author:     Candy
[/] */

#ifndef NODO_H
#define NODO_H
#include "Dato.h"

struct Nodo {
    struct Dato dato;
    struct Nodo *prox;
};

#endif /* NODO_H */
