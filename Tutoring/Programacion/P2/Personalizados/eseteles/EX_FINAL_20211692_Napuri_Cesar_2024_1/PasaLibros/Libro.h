
/* 
 * File:   Libro.h
 * Author: Cesar Augusto Napuri de la Cruz
 * Codigo: 20211692
 */

#ifndef LIBRO_H
#define LIBRO_H
#include <iostream>
#include <iomanip>
#include <fstream>
#include <cstring>
using namespace std;
#define MAXBUFF 100

class Libro {
private:
    char *nombre;
    int paginas;
    double peso;
public:
    Libro();
    virtual ~Libro();
    void SetPeso(double);
    double GetPeso() const;
    void SetPaginas(int);
    int GetPaginas() const;
    void SetNombre(const char *);
    void GetNombre(char *) const;
    virtual void leer(ifstream &);
    virtual void mostrar(ofstream &);
};

#endif /* LIBRO_H */

