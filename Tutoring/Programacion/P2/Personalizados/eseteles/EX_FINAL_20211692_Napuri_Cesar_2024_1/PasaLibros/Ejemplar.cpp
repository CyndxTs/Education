/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/cppFiles/class.cc to edit this template
 */

/* 
 * File:   Ejemplar.cpp
 * Author: alulab14
 * 
 * Created on 9 de julio de 2024, 08:16
 */

#include "Ejemplar.h"
#include "Novela.h"
#include "Revista.h"

Ejemplar::Ejemplar() {
    plibro = nullptr;
    sig = nullptr;
}

Ejemplar::Ejemplar(const Ejemplar &orig) {
    *this = orig;
}

Ejemplar::~Ejemplar() {
}

void Ejemplar::asignarMemoria(char tipo){
    switch(tipo){
        case 'N':
            plibro = new Novela;
            break;
        case 'R':
            plibro = new Revista;
            break;
    }
}
void Ejemplar::leerDatos(ifstream &arch){
    plibro->leer(arch);
}

void Ejemplar::mostrarDatos(ofstream &arch){
    plibro->mostrar(arch);
}

void Ejemplar::operator =(const Ejemplar &orig){
    plibro = orig.plibro;
    sig = orig.sig;
}