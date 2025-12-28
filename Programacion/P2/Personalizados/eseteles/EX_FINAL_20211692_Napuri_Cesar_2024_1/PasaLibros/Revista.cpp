
/* 
 * File:   Revista.cpp
 * Author: alulab14
 * 
 * Created on 9 de julio de 2024, 08:10
 */

#include "Revista.h"

Revista::Revista() {
}

Revista::~Revista() {
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

void Revista::leer(ifstream &arch){
    Libro::leer(arch);
    arch>>ISSN;
    arch.get();
    arch>>anho;
    arch.get();
    arch>>numero>>ws;
}

void Revista::mostrar(ofstream &arch){
    Libro::mostrar(arch);
    arch<<"ISSN: "<<ISSN<<setw(12)<<"Año:"<<anho<<endl<<endl;
}