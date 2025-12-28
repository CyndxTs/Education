
/* 
 * File:   Enciclopedia.cpp
 * Author: Cesar Augusto Napuri de la Cruz
 * Codigo: 20211692
 */

#include "Enciclopedia.h"

Enciclopedia::Enciclopedia() {
}

void Enciclopedia::SetAnho(int anho) {
    this->anho = anho;
}

int Enciclopedia::GetAnho() const {
    return anho;
}

void Enciclopedia::SetSku(int sku) {
    this->sku = sku;
}

int Enciclopedia::GetSku() const {
    return sku;
}

void Enciclopedia::cargar(ifstream &arch){
    Libro::cargar(arch);
    arch>>sku;
    arch.get();
    arch>>anho;
}

void Enciclopedia::mostrar(ofstream &arch){
    Libro::mostrar(arch);
    arch<<"SKU: "<<setw(10)<<sku<<setw(8)<<' ';
    arch<<"Anio: "<<setw(10)<<anho<<endl;
}