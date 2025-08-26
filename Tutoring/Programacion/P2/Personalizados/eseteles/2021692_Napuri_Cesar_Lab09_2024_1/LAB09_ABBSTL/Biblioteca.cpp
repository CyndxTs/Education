
/* 
 * File:   Biblioteca.cpp
 * Author: Cesar Augusto Napuri de la Cruz
 * Codigo: 20211692
 */

#include "Biblioteca.h"

Biblioteca::Biblioteca() {
}

Biblioteca::~Biblioteca() {
}

void Biblioteca::carga(){
    ifstream archEst("Estantes3.csv",ios::in);
    if(not archEst.is_open()){
        cout<<"ERROR DE APERTURA: Archivo 'Estantes3.csv'"<<endl;
        exit(1);
    }
    ifstream archLib("Libros31.csv",ios::in);
    if(not archEst.is_open()){
        cout<<"ERROR DE APERTURA: Archivo 'Libros31.csv'"<<endl;
        exit(1);
    }
    AEstante.cargarDatos(archEst,archLib);
}

void Biblioteca::muestra(){
    ofstream archRep("Reporte.txt",ios::out);
    if(not archRep.is_open()){
        cout<<"ERROR DE APERTURA: Archivo 'Reporte.txt'"<<endl;
        exit(1);
    }
    archRep<<fixed<<setprecision(2);
    AEstante.mostrarDatos(archRep);
}

void Biblioteca::prueba(double pesoEval){
    AEstante.evaluarPeso(pesoEval);
}
