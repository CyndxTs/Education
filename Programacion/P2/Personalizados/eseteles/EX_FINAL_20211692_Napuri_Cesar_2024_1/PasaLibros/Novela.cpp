/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/cppFiles/class.cc to edit this template
 */

/* 
 * File:   Novela.cpp
 * Author: alulab14
 * 
 * Created on 9 de julio de 2024, 08:10
 */

#include "Novela.h"

Novela::Novela() {
    autor = nullptr;
    editorial = nullptr;
}

Novela::~Novela() {
    if(editorial) delete editorial;
    if(autor) delete autor;
}

void Novela::SetEditorial(const char *edit) {
    if(editorial) delete editorial;
    editorial = new char[strlen(edit) + 1];
    strcpy(editorial,edit);
}

void Novela::GetEditorial(char *edit) const {
    if(!editorial) edit[0] = 0;
    else strcpy(edit,editorial);
}

void Novela::SetAutor(const char *aut) {
    if(autor) delete autor;
    autor = new char[strlen(aut) + 1];
    strcpy(autor,aut);
}

void Novela::GetAutor(char *aut) const {
    if(!autor) aut[0] = 0;
    else strcpy(aut,autor);
}

void Novela::leer(ifstream &arch){
    Libro::leer(arch);
    char cadAux[MAXBUFF]{};
    arch.getline(cadAux,MAXBUFF,',');
    SetAutor(cadAux);
    arch.getline(cadAux,MAXBUFF,'\n');
    SetEditorial(cadAux);
}

void Novela::mostrar(ofstream &arch){
    Libro::mostrar(arch);
    arch<<"Autor: "<<autor<<endl;
    arch<<"Editorial: "<<editorial<<endl;
    arch<<endl;
}
