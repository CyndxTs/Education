/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/cppFiles/class.cc to edit this template
 */

/* 
 * File:   Cliente.cpp
 * Author: alulab14
 * 
 * Created on 9 de julio de 2024, 09:39
 */

#include "Cliente.h"

Cliente::Cliente() {
    cantidad_libros_vendidos = 0;
}

Cliente::Cliente(const Cliente& orig) {
}

Cliente::~Cliente() {
}

void Cliente::SetCantidad_libros_vendidos(int cantidad_libros_vendidos) {
    this->cantidad_libros_vendidos = cantidad_libros_vendidos;
}

int Cliente::GetCantidad_libros_vendidos() const {
    return cantidad_libros_vendidos;
}

void Cliente::SetNombre(string nombre) {
    this->nombre = nombre;
}

string Cliente::GetNombre() const {
    return nombre;
}

void Cliente::SetCodigo(string codigo) {
    this->codigo = codigo;
}

string Cliente::GetCodigo() const {
    return codigo;
}

bool Cliente::leer(ifstream &arch,vector<Libro> bibLibs){
    char cadAux[MAXBUFF]{};
    arch.getline(cadAux,MAXBUFF,',');
    if(arch.eof()) return false;
    codigo = cadAux;
    arch.getline(cadAux,MAXBUFF,',');
    nombre = cadAux;
    for(int i = 0;i < LIMVENTAS - 1;i++){
        arch.getline(cadAux,MAXBUFF,',');
        if(cadAux[0] != 0) asignarLibro(cadAux,bibLibs);
    }
    arch.getline(cadAux,MAXBUFF,'\n');
    if(cadAux[0] != 0) asignarLibro(cadAux,bibLibs);
    return true;
}

void Cliente::asignarLibro(const char *cadAux,vector<Libro> bibLibs){
    string strAux = cadAux;
    for(Libro libAux : bibLibs){
        if(libAux.GetCodigo().compare(strAux) == 0){
            libros_comprados.push_back(libAux);
            cantidad_libros_vendidos++;
        }
    }
}

void Cliente::cargarRecomendaciones(vector<Libro> bibLibs){
    for(Libro cliLib : libros_comprados){
        vector<Libro> recAux;
        for(Libro libAux : bibLibs){
            if(libAux.esRecomendado(cliLib)) recAux.push_back(libAux);
        }
        recomendaciones[cliLib] = recAux;
    }
}

void Cliente::imprimir(ofstream &arch){
    string strAux;
    int i = 0;
    arch<<"Codigo de Cliente: "<<codigo<<setw(12)<<"Nombre: "<<nombre<<endl;
    for(Libro libComprado : libros_comprados){
        strAux = libComprado.GetTitulo();
        arch<<"Libro Comprado "<<i+1<<") "<<strAux<<endl;
        arch<<"Libros Recomendados:";
        map<Libro,vector<Libro>>::iterator it = recomendaciones.find(libComprado); // con "auto" tmpc me funciona :/
        arch<<endl;
        for(int j = 0;j < cantidad_libros_vendidos;j++){
            strAux = it->second[j].GetTitulo();
            arch<<setw(20)<<' '<<strAux<<endl;
        }
        i++;
    }
}
