
/* [/]
 >> Project:    Aegleseeker
 >> File:       TableroDeValores.h
 >> Author:     CyndxTs o.0?!
[/] */

#ifndef TABLERODEVALORES_H
#define TABLERODEVALORES_H
#include "Tablero.h"

class TableroDeValores : public Tablero {
private:
    int chance;
    int numMinas;
public:
    TableroDeValores();
    virtual ~TableroDeValores();
    void setChance(int);
    int getChance() const;
    void setNumMinas(int);
    int getNumMinas();
    int obtenerNumMinas();
    void inicializar(int,int,int);
};

#endif /* TABLERODEVALORES_H */
