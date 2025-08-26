/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/cppFiles/class.h to edit this template
 */

/* 
 * File:   Revista.h
 * Author: alulab14
 *
 * Created on 9 de julio de 2024, 08:10
 */

#ifndef REVISTA_H
#define REVISTA_H

#include "Libro.h"


class Revista : public Libro {
private:
    int ISSN;
    int anho;
    int numero;
public:
    Revista();
    virtual ~Revista();
    void SetNumero(int numero);
    int GetNumero() const;
    void SetAnho(int anho);
    int GetAnho() const;
    void SetISSN(int ISSN);
    int GetISSN() const;
    void leer(ifstream &);
    void mostrar(ofstream &);
};

#endif /* REVISTA_H */

