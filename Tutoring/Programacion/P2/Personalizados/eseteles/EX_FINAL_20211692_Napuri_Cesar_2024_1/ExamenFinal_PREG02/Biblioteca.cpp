/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/cppFiles/class.cc to edit this template
 */

/* 
 * File:   Biblioteca.cpp
 * Author: alulab14
 * 
 * Created on 9 de julio de 2024, 09:41
 */

#include <vector>

#include "Biblioteca.h"

Biblioteca::Biblioteca() {
}

Biblioteca::~Biblioteca() {
}

void Biblioteca::cargar_libros(const char *nombArch){
    ifstream arch(nombArch,ios::in);
    if(not arch.is_open()){
        cout<<"ERROR DE APERTURA: Archivo "<<nombArch<<endl;
        exit(1);
    }
    while(1){
        Libro libAux;
        if(!libAux.leer(arch)) break;
        libros.push_back(libAux);
    }
}
void Biblioteca::cargar_autores(const char *nombArch){
    ifstream arch(nombArch,ios::in);
    if(not arch.is_open()){
        cout<<"ERROR DE APERTURA: Archivo "<<nombArch<<endl;
        exit(1);
    }
    while(1){
        Autor autAux;
        if(!autAux.leer(arch)) break;
        autAux.cargarLibros(libros);
        autores[autAux.GetCodigo()] = autAux;
    }
}
void Biblioteca::cargar_clientes(const char *nombArch){
    ifstream arch(nombArch,ios::in);
    if(not arch.is_open()){
        cout<<"ERROR DE APERTURA: Archivo "<<nombArch<<endl;
        exit(1);
    }
    while(1){
        Cliente cliAux;
        if(!cliAux.leer(arch,libros)) break;
        cliAux.cargarRecomendaciones(libros);
        clientes.push_back(cliAux);
    }
}
void Biblioteca::mostrar_recomendaciones(const char *nombArch){
    ofstream arch(nombArch,ios::out);
    if(not arch.is_open()){
        cout<<"ERROR DE APERTURA: Archivo "<<nombArch<<endl;
        exit(1);
    }
    arch<<"REPORTE DE RECOMENDACIONES"<<endl;
    for(int i = 0;i< 60;i++) arch.put('=');
    arch<<endl;
    for(Cliente cliAux : clientes) cliAux.imprimir(arch);
    for(int i = 0;i< 60;i++) arch.put('=');
}
