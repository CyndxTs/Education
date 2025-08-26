
/* 
 * File:   Arbol.h
 * Author: Cesar Augusto Napuri de la Cruz
 * Codigo: 20211692
 */

#ifndef ARBOL_H
#define ARBOL_H
#include "NEstante.h"

class Arbol {
private:
    class NEstante *raiz;
    void mostrarEnOrdenRecursivo(ofstream &,class NEstante *);
    void insertarRecursivo(class NEstante *,class NEstante *&);
    void buscarValidadoRecursivo(ofstream &,double,class NEstante *,bool &);
public:
    Arbol();
    virtual ~Arbol();
    void cargarDatos(ifstream &,ifstream &);
    void mostrarDatos(ofstream &);
    void evaluarPeso(double);
};

#endif /* ARBOL_H */

