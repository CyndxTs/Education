
/* [/]
 >> Project:    Aegleseeker
 >> File:       Tablero.h
 >> Author:     CyndxTs o.0?!
[/] */

#ifndef TABLERO_H
#define TABLERO_H
#include "Constantes.h"

class Tablero {
private:
    int dimX;
    int dimY;
    char **mapa;
public:
    Tablero();
    virtual ~Tablero();
    void setDimY(int);
    int getDimY() const;
    void setDimX(int);
    int getDimX() const;
    virtual void setNumMinas(int);
    virtual int getNumMinas();
    virtual void setNumMarcadores(int);
    virtual int getNumMarcadores();
    void generar(int,int);
    bool validarPosicionEnDominio(int,int);
    char obtenerCasilla(int,int);
    void actualizarCasilla(int,int,char);
    virtual void inicializar(int,int,int) = 0;
    virtual void mostrar();
    virtual void mostrar(int,int);
};

#endif /* TABLERO_H */
