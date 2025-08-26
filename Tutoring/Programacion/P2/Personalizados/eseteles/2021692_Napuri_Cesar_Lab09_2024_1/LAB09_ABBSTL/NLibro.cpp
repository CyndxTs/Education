
/* 
 * File:   NLibro.cpp
 * Author: Cesar Augusto Napuri de la Cruz
 * Codigo: 20211692
 */

#include "NLibro.h"
#include "Enciclopedia.h"
#include "Novela.h"
#include "Revista.h"

NLibro::NLibro() {
    plibro = nullptr;
}

NLibro::~NLibro() {
}

void NLibro::asignarMemoria(char tipo){
    switch(tipo){
        case 'E':
            plibro = new class Enciclopedia;
            break;
        case 'N':
            plibro = new class Novela;
            break;
        case 'R':
            plibro = new class Revista;
            break;
    }
}

double NLibro::GetPeso(){
    return plibro->GetPeso();
}

void NLibro::cargar(ifstream &arch){
    plibro->cargar(arch);
}

void NLibro::mostar(ofstream &arch){
    plibro->mostrar(arch);
    arch<<endl;
}