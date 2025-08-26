/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/cppFiles/class.h to edit this template
 */

/* 
 * File:   Novela.h
 * Author: alulab14
 *
 * Created on 9 de julio de 2024, 08:10
 */

#ifndef NOVELA_H
#define NOVELA_H

#include "Libro.h"


class Novela : public Libro {
private:
    char *autor;
    char *editorial;
public:
    Novela();
    virtual ~Novela();
    void SetEditorial(const char* );
    void GetEditorial(char *) const;
    void SetAutor(const char *);
    void GetAutor(char *) const;
    void leer(ifstream &);
    void mostrar(ofstream &);
};

#endif /* NOVELA_H */

