
/* 
 * File:   NEstante.h
 * Author: Cesar Augusto Napuri de la Cruz
 * Codigo: 20211692
 */

#ifndef NESTANTE_H
#define NESTANTE_H
#include "NEstante.h"
#include "NLibro.h"
#include <vector>
#include <algorithm>
#include "Arbol.h"

class NEstante {
private:
    int id;
    double capacidad;
    double disponible;
    vector<class NLibro>vlibros;
    class NEstante *izq;
    class NEstante *der;
public:
    NEstante();
    friend class Arbol;
    virtual ~NEstante();
    void SetDisponible(double);
    double GetDisponible() const;
    void SetCapacidad(double);
    double GetCapacidad() const;
    void SetId(int);
    int GetId() const;
    void cargarLibros(ifstream &);
    void actualizarDisponibilidad();
    void mostrar(ofstream &);
    bool operator <(class NEstante &);
};

void imprimirLinea(ofstream &,int,char);

#endif /* NESTANTE_H */

