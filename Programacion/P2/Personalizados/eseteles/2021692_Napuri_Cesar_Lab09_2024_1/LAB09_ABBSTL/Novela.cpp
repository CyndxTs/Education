
/* 
 * File:   Novela.cpp
 * Author: Cesar Augusto Napuri de la Cruz
 * Codigo: 20211692
 */

#include "Novela.h"

Novela::Novela() {
    autor = nullptr;
}

Novela::~Novela() {
    if(autor) delete autor;
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

void Novela::cargar(ifstream &arch){
    char cadAux[MAXBUFF];
    Libro::cargar(arch);
    arch.getline(cadAux,MAXBUFF,'\n');
    SetAutor(cadAux);
}

void Novela::mostrar(ofstream &arch){
    Libro::mostrar(arch);
    arch<<"Autor: "<<autor<<endl;
}