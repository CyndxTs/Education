/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/cppFiles/class.h to edit this template
 */

/* 
 * File:   Cliente.h
 * Author: alulab14
 *
 * Created on 9 de julio de 2024, 09:39
 */

#ifndef CLIENTES_H
#define CLIENTES_H
#include "Libro.h"
#include <map>
#include <iterator>
#include <vector>
using namespace std;
#define LIMVENTAS 5

class Cliente {
private:
    string codigo;
    string nombre;
    vector<Libro> libros_comprados;
    int cantidad_libros_vendidos;
    map<Libro,vector<Libro>> recomendaciones;
public:
    Cliente();
    Cliente(const Cliente& orig);
    virtual ~Cliente();
    void SetCantidad_libros_vendidos(int cantidad_libros_vendidos);
    int GetCantidad_libros_vendidos() const;
    void SetNombre(string nombre);
    string GetNombre() const;
    void SetCodigo(string codigo);
    string GetCodigo() const;
    bool leer(ifstream &arch,vector<Libro> bibLibs);
    void asignarLibro(const char *cadAux,vector<Libro> bibLibs);
    void cargarRecomendaciones(vector<Libro> bibLibs);
    void imprimir(ofstream &arch);
};

#endif /* CLIENTES_H */

