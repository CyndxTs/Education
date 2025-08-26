
/* 
 * File:   Revista.h
 * Author: Cesar Augusto Napuri de la Cruz
 * Codigo: 20211692
 */

#ifndef REVISTA_H
#define REVISTA_H
#include "Libro.h"

class Revista : public Libro{
private:
    int ISSN;
    int anho;
    int numero;
public:
    Revista();
    void SetNumero(int);
    int GetNumero() const;
    void SetAnho(int);
    int GetAnho() const;
    void SetISSN(int);
    int GetISSN() const;
    void cargar(ifstream &);
    void mostrar(ofstream &);
};

#endif /* REVISTA_H */

