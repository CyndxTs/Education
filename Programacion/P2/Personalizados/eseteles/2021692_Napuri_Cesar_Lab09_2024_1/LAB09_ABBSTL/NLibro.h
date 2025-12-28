
/* 
 * File:   NLibro.h
 * Author: Cesar Augusto Napuri de la Cruz
 * Codigo: 20211692
 */

#ifndef NLIBRO_H
#define NLIBRO_H
#include "Libro.h"

class NLibro {
private:
    class Libro *plibro;
public:
    NLibro();
    virtual ~NLibro();
    void asignarMemoria(char);
    double GetPeso();
    void cargar(ifstream &);
    void mostar(ofstream &);
};

#endif /* NLIBRO_H */
