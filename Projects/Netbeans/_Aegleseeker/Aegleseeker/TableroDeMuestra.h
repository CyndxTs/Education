
/* [/]
 >> Project:    Aegleseeker
 >> File:       TableroDeMuestra.h
 >> Author:     CyndxTs o.0?!
[/] */

#ifndef TABLERODEMUESTRA_H
#define TABLERODEMUESTRA_H
#include "Tablero.h"

class TableroDeMuestra : public Tablero {
private:
    int numMarcadores;
public:
    TableroDeMuestra();
    virtual ~TableroDeMuestra();
    void setNumMarcadores(int);
    int getNumMarcadores();
    void inicializar(int,int,int);
    void mostrar();
    void mostrar(int,int);
    
};

#endif /* TABLERODEMUESTRA_H */
