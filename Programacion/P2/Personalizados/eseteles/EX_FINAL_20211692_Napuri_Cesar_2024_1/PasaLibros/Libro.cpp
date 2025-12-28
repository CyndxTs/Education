
/* 
 * File:   Libro.cpp
 * Author: Cesar Augusto Napuri de la Cruz
 * Codigo: 20211692
 */

#include <valarray>

#include "Libro.h"

Libro::Libro() {
    nombre = nullptr;
}

Libro::~Libro() {
    if(nombre) delete nombre;
}

void Libro::SetPeso(double peso) {
    this->peso = peso;
}

double Libro::GetPeso() const {
    return peso;
}

void Libro::SetPaginas(int paginas) {
    this->paginas = paginas;
}

int Libro::GetPaginas() const {
    return paginas;
}

void Libro::SetNombre(const char *nomb) {
    if(nombre) delete nombre;
    nombre = new char[strlen(nomb) + 1];
    strcpy(nombre,nomb);
}

void Libro::GetNombre(char *nomb) const {
    if(!nombre) nomb[0] = 0;
    else strcpy(nomb,nombre);
}

void Libro::leer(ifstream &arch){
    char cadAux[MAXBUFF]{};
    arch.getline(cadAux,MAXBUFF,',');
    SetNombre(cadAux);
    arch>>paginas;
    arch.get();
    arch>>peso;
    arch.get();
}

void Libro::mostrar(ofstream &arch){
    arch<<"Titulo: "<<nombre<<endl;
    arch<<"Peso: "<<peso<<endl;
}