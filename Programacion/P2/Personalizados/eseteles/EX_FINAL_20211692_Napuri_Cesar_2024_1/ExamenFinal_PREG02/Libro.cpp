/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/cppFiles/class.cc to edit this template
 */

/* 
 * File:   Libro.cpp
 * Author: alulab14
 * 
 * Created on 9 de julio de 2024, 09:35
 */

#include "Libro.h"

Libro::Libro() {
}

Libro::Libro(const Libro& orig) {
}

Libro::~Libro() {
    
}

void Libro::SetTema(string tema) {
    this->tema = tema;
}

string Libro::GetTema() const {
    return tema;
}

void Libro::SetGenero(string genero) {
    this->genero = genero;
}

string Libro::GetGenero() const {
    return genero;
}

void Libro::SetAutor(string autor) {
    this->autor = autor;
}

string Libro::GetAutor() const {
    return autor;
}

void Libro::SetTitulo(string titulo) {
    this->titulo = titulo;
}

string Libro::GetTitulo() const {
    return titulo;
}

void Libro::SetCodigo(string codigo) {
    this->codigo = codigo;
}

string Libro::GetCodigo() const {
    return codigo;
}

bool Libro::leer(ifstream &arch){
    char cadAux[MAXBUFF]{};
    arch.getline(cadAux,MAXBUFF,',');
    if(arch.eof()) return false;
    codigo = cadAux;
    arch.getline(cadAux,MAXBUFF,',');
    titulo = cadAux;
    arch.getline(cadAux,MAXBUFF,',');
    autor = cadAux;
    arch.getline(cadAux,MAXBUFF,',');
    genero = cadAux;
    arch.getline(cadAux,MAXBUFF,'\n');
    tema = cadAux;
    return true;
}
bool Libro::esRecomendado(const Libro &orig){
    return ((codigo.compare(orig.codigo) != 0) and
             ((autor.compare(orig.autor) == 0) or
             (genero.compare(orig.genero) == 0) or
             (tema.compare(orig.tema) == 0)));
}


