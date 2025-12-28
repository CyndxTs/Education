/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/cppFiles/class.h to edit this template
 */

/* 
 * File:   Ejemplar.h
 * Author: alulab14
 *
 * Created on 9 de julio de 2024, 08:16
 */

#ifndef EJEMPLAR_H
#define EJEMPLAR_H

#include "Libro.h"


class Ejemplar {
private:
    class Libro *plibro;
    class Ejemplar *sig;
public:
    Ejemplar();
    friend class Pila;
    Ejemplar(const Ejemplar &);
    virtual ~Ejemplar();
    void asignarMemoria(char tipo);
    void leerDatos(ifstream &arch);
    void mostrarDatos(ofstream &arch);
    void operator =(const Ejemplar &);
};

#endif /* EJEMPLAR_H */

