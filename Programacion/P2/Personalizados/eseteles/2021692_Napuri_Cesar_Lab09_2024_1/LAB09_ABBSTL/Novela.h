
/* 
 * File:   Novela.h
 * Author: Cesar Augusto Napuri de la Cruz
 * Codigo: 20211692
 */

#ifndef NOVELA_H
#define NOVELA_H
#include "Libro.h"

class Novela : public Libro{
private:
    char *autor;
public:
    Novela();
    virtual ~Novela();
    void SetAutor(const char *);
    void GetAutor(char *) const;
    void cargar(ifstream &);
    void mostrar(ofstream &);
};

#endif /* NOVELA_H */

