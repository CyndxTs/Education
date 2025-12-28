/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/cppFiles/class.h to edit this template
 */

/* 
 * File:   Procesa.h
 * Author: alulab14
 *
 * Created on 9 de julio de 2024, 08:19
 */

#ifndef PROCESA_H
#define PROCESA_H
#include "Pila.h"

class Procesa {
private:
    class Pila porigen;
    class Pila pdestino;
public:
    Procesa();
    virtual ~Procesa();
    void carga();
    void pasa();
    void muestra();
};

#endif /* PROCESA_H */

