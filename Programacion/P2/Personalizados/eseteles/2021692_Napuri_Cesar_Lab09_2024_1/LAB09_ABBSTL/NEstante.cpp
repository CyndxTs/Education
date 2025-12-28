
/* 
 * File:   NEstante.cpp
 * Author: Cesar Augusto Napuri de la Cruz
 * Codigo: 20211692
 */

#include "NEstante.h"

NEstante::NEstante() {
    izq = nullptr;
    der = nullptr;
}

NEstante::~NEstante() {
}

void NEstante::SetDisponible(double disponible) {
    this->disponible = disponible;
}

double NEstante::GetDisponible() const {
    return disponible;
}

void NEstante::SetCapacidad(double capacidad) {
    this->capacidad = capacidad;
}

double NEstante::GetCapacidad() const {
    return capacidad;
}

void NEstante::SetId(int id) {
    this->id = id;
}

int NEstante::GetId() const {
    return id;
}

void NEstante::cargarLibros(ifstream &archLib){
    char tipo;
    for(int i = 0;i < 10;i++){
        archLib>>tipo;
        if(archLib.eof()) break;
        archLib.get();
        NLibro nLibro;
        nLibro.asignarMemoria(tipo);
        nLibro.cargar(archLib);
        vlibros.push_back(nLibro);
    }
}

void NEstante::actualizarDisponibilidad(){
    for(int i = 0;i < vlibros.size();i++) disponible -= vlibros[i].GetPeso();
}

void NEstante::mostrar(ofstream &archRep){
    archRep<<"Estante:"<<setw(10)<<id<<endl;
    archRep<<"Capacidad:"<<setw(10)<<capacidad<<setw(8)<<' ';
    archRep<<"Disponible:"<<setw(10)<<disponible<<endl;
    imprimirLinea(archRep,DIMLINEA,'-');
    for(int i = 0;i < vlibros.size();i++) vlibros[i].mostar(archRep);
    imprimirLinea(archRep,DIMLINEA,'=');
}
bool NEstante::operator <(class NEstante &nAux){
    return disponible < nAux.GetDisponible();
}

void imprimirLinea(ofstream &archRep,int dim,char simbolo){
    for(int i = 0;i < dim;i++) archRep.put(simbolo);
    archRep<<endl;
}
