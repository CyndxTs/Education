/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/cppFiles/class.cc to edit this template
 */

/* 
 * File:   Procesa.cpp
 * Author: alulab14
 * 
 * Created on 9 de julio de 2024, 08:19
 */

#include "Procesa.h"

Procesa::Procesa() {
}

Procesa::~Procesa() {
}

void Procesa::carga(){
    ifstream arch("Libros5.csv",ios::in);
    if(not arch.is_open()){
        cout<<"ERROR DE APERTURA: Archivo 'Libros5.csv'"<<endl;
        exit(1);
    }
    porigen.cargarDatos(arch);
}
void Procesa::pasa(){
    int n;
    Ejemplar valor,aux;
    while(!porigen.estaVacia()){
        valor = porigen.desapilar();
        n = 0;
        while(!porigen.estaVacia()){
            pdestino.apilar(valor);
            valor = porigen.desapilar();
            n++;
        }
        while(!pdestino.estaVacia() and n > 0){
            aux = pdestino.desapilar();
            porigen.apilar(aux);
            n--;
        }
        pdestino.apilar(valor);
    }
}
void Procesa::muestra(){
    ofstream arch("Reporte.txt",ios::out);
    if(not arch.is_open()){
        cout<<"ERROR DE APERTURA: Archivo 'Reporte.txt'"<<endl;
        exit(1);
    }
    pdestino.mostrarDatos(arch);
}