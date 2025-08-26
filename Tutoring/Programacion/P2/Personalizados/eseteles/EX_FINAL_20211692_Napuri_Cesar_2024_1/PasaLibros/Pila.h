/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/cppFiles/class.h to edit this template
 */

/* 
 * File:   Pila.h
 * Author: alulab14
 *
 * Created on 9 de julio de 2024, 08:17
 */

#ifndef PILA_H
#define PILA_H
#include "Ejemplar.h"

class Pila {
private:
    int nelementos;
    class Ejemplar *pcima;
public:
    Pila();
    virtual ~Pila();
    void cargarDatos(ifstream &);
    void apilar(Ejemplar &ejemAux);
    Ejemplar desapilar();
    void mostrarDatos(ofstream &);
    bool estaVacia();
};

#endif /* PILA_H */

