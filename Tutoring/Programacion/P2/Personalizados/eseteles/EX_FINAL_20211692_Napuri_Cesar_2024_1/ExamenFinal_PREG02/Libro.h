
/* 
 * File:   Libro.h
 * Author: alulab14
 *
 * Created on 9 de julio de 2024, 09:35
 */

#ifndef LIBRO_H
#define LIBRO_H
#include <iostream>
#include <iomanip>
#include <fstream>
#include <string>
using namespace std;
#define MAXBUFF 100

class Libro {
private:
    string codigo;
    string titulo;
    string autor;
    string genero;
    string tema;
public:
    Libro();
    Libro(const Libro& orig);
    virtual ~Libro();
    void SetTema(string tema);
    string GetTema() const;
    void SetGenero(string genero);
    string GetGenero() const;
    void SetAutor(string autor);
    string GetAutor() const;
    void SetTitulo(string titulo);
    string GetTitulo() const;
    void SetCodigo(string codigo);
    string GetCodigo() const;
    bool leer(ifstream &);
    bool esRecomendado(const Libro &orig);
};

#endif /* LIBRO_H */

