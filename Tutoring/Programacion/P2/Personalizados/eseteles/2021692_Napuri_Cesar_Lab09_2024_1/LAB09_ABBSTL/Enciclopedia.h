
/* 
 * File:   Enciclopedia.h
 * Author: Cesar Augusto Napuri de la Cruz
 * Codigo: 20211692
 */

#ifndef ENCICLOPEDIA_H
#define ENCICLOPEDIA_H
#include "Libro.h"

class Enciclopedia : public Libro{
private:
    int sku;
    int anho;
public:
    Enciclopedia();
    void SetAnho(int);
    int GetAnho() const;
    void SetSku(int);
    int GetSku() const;
    void cargar(ifstream &);
    void mostrar(ofstream &);
};

#endif /* ENCICLOPEDIA_H */
