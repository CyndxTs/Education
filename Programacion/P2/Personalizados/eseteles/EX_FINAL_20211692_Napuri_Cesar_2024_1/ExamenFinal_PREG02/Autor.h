/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/cppFiles/class.h to edit this template
 */

/* 
 * File:   Autor.h
 * Author: alulab14
 *
 * Created on 9 de julio de 2024, 09:37
 */

#ifndef AUTORES_H
#define AUTORES_H
#include "Libro.h"
#include <list>
#include <vector>
#include <iterator>
using namespace std;

class Autor {
private:
    string codigo;
    string nombre;
    list<Libro> libros;
public:
    Autor();
    Autor(const Autor& orig);
    virtual ~Autor();
    void SetNombre(string nombre);
    string GetNombre() const;
    void SetCodigo(string codigo);
    string GetCodigo() const;
    bool leer(ifstream &);
    void cargarLibros(vector<Libro> libros);
};

#endif /* AUTORES_H */

