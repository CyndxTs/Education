/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/cppFiles/class.cc to edit this template
 */

/* 
 * File:   Autor.cpp
 * Author: alulab14
 * 
 * Created on 9 de julio de 2024, 09:37
 */

#include "Autor.h"

Autor::Autor() {
}

Autor::Autor(const Autor& orig) {
}

Autor::~Autor() {
}

void Autor::SetNombre(string nombre) {
    this->nombre = nombre;
}

string Autor::GetNombre() const {
    return nombre;
}

void Autor::SetCodigo(string codigo) {
    this->codigo = codigo;
}

string Autor::GetCodigo() const {
    return codigo;
}

bool Autor::leer(ifstream &arch){
    char cadAux[MAXBUFF]{};
    arch.getline(cadAux,MAXBUFF,',');
    if(arch.eof()) return false;
    codigo = cadAux;
    arch.getline(cadAux,MAXBUFF,'\n');
    nombre = cadAux;
    return true;
}

void Autor::cargarLibros(vector<Libro> bibLibs){
    auto i = libros.begin();
    for(Libro libAux : bibLibs){
        if(libAux.GetAutor().compare(codigo) == 0){
            libros.insert(i,libAux);
            i++;
        }
    }
}
