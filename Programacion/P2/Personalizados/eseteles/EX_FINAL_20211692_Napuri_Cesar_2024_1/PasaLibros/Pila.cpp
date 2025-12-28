/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/cppFiles/class.cc to edit this template
 */

/* 
 * File:   Pila.cpp
 * Author: alulab14
 * 
 * Created on 9 de julio de 2024, 08:17
 */

#include "Pila.h"

Pila::Pila() {
    nelementos = 0;
    pcima = nullptr;
}

Pila::~Pila() {
}
//
void Pila::cargarDatos(ifstream &arch){
    char tipo;
    Ejemplar ejemAux;
    while(1){
        arch>>tipo;
        if(arch.eof()) break;
        arch.get();
        ejemAux.asignarMemoria(tipo);
        ejemAux.leerDatos(arch);
        apilar(ejemAux);
    }
}
//
void Pila::apilar(Ejemplar &ejemAux){
    Ejemplar *nNuevo = new Ejemplar;
    *nNuevo = ejemAux;
    nNuevo->sig = pcima;
    pcima = nNuevo;
    nelementos++;
}

Ejemplar Pila::desapilar(){
    Ejemplar *nEliminar = pcima,nAux;
    nAux = *nEliminar;
    pcima = pcima->sig;
    nelementos--;
    return nAux;
}
//
void Pila::mostrarDatos(ofstream &arch){
    arch<<fixed<<setprecision(2);
    arch<<"Reporte de Ejemplares"<<endl;
    for(int i = 0;i< 60;i++) arch.put('=');
    arch<<endl;
    while(!estaVacia()){
        desapilar().mostrarDatos(arch);
    }
}

bool Pila::estaVacia(){
    return pcima == nullptr;
}