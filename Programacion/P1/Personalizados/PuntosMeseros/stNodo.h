
/* [/]
 >> Project:    PuntosMeseros
 >> File:       Nodo.h
 >> Author:     
[/] */

#ifndef NODO_H
#define NODO_H
#include "stCuenta.h"

struct Nodo {
    struct Cuenta cuenta;
    struct Nodo *siguiente;
};


#endif /* NODO_H */
