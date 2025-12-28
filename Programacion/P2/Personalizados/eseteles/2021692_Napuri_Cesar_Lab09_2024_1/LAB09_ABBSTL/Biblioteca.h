
/* 
 * File:   Biblioteca.h
 * Author: Cesar Augusto Napuri de la Cruz
 * Codigo: 20211692
 */

#ifndef BIBLIOTECA_H
#define BIBLIOTECA_H
#include "Arbol.h"

class Biblioteca {
private:
    class Arbol AEstante;
public:
    Biblioteca();
    virtual ~Biblioteca();
    void carga();
    void muestra();
    void prueba(double);
};

#endif /* BIBLIOTECA_H */

