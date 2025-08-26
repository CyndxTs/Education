/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/cppFiles/class.h to edit this template
 */

/* 
 * File:   Biblioteca.h
 * Author: alulab14
 *
 * Created on 9 de julio de 2024, 09:41
 */

#ifndef BIBLIOTECA_H
#define BIBLIOTECA_H
#include "Autor.h"
#include "Cliente.h"

class Biblioteca {
private:
    map<string,Autor> autores;
    vector<Libro>libros;
    vector<Cliente>clientes;
public:
    Biblioteca();
    Biblioteca(const Biblioteca& orig);
    virtual ~Biblioteca();
    void cargar_libros(const char *);
    void cargar_autores(const char *);
    void cargar_clientes(const char *);
    void mostrar_recomendaciones(const char *);
};

#endif /* BIBLIOTECA_H */

