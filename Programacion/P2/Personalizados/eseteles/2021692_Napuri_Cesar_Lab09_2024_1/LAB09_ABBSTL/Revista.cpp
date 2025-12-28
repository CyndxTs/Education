
/* 
 * File:   Revista.cpp
 * Author: Cesar Augusto Napuri de la Cruz
 * Codigo: 20211692
 */

#include "Revista.h"

Revista::Revista() {
}

void Revista::SetNumero(int numero) {
    this->numero = numero;
}

int Revista::GetNumero() const {
    return numero;
}

void Revista::SetAnho(int anho) {
    this->anho = anho;
}

int Revista::GetAnho() const {
    return anho;
}

void Revista::SetISSN(int ISSN) {
    this->ISSN = ISSN;
}

int Revista::GetISSN() const {
    return ISSN;
}

void Revista::cargar(ifstream &arch){
    Libro::cargar(arch);
    arch>>ISSN;
    arch.get();
    arch>>anho;
    arch.get();
    arch>>numero;
}

void Revista::mostrar(ofstream &arch){
    Libro::mostrar(arch);
    arch<<"ISSn: "<<setw(10)<<ISSN<<setw(8)<<' ';
    arch<<"Anio: "<<setw(10)<<anho<<endl;
}