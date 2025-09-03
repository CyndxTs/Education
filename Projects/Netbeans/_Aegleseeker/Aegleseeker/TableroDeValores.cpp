
/* [/]
 >> Project:    Aegleseeker
 >> File:       TableroDeValores.cpp
 >> Author:     CyndxTs o.0?!
[/] */

#include "TableroDeValores.h"

//
TableroDeValores::TableroDeValores() {
    numMinas = 0;
}
//
TableroDeValores::~TableroDeValores() {
    
}
//
void TableroDeValores::setChance(int chance) {
    this->chance = chance;
}
//
int TableroDeValores::getChance() const {
    return chance;
}
//
void TableroDeValores::setNumMinas(int numMinas) {
    this->numMinas = numMinas;
}
//
int TableroDeValores::getNumMinas() {
    return numMinas;
}
//
void TableroDeValores::inicializar(int dX,int dY,int chanceDeAparicion){
    setChance(chanceDeAparicion);
    generar(dX,dY);
    for(int y = 0;y < getDimY();y++){
        for(int x = 0;x < getDimX();x++){
            actualizarCasilla(x,y,((1 + rand() % 100 <= chance)?'*':'0'));
        }
    }
    for(int y = 0;y < getDimY();y++){
        for(int x = 0;x < getDimX();x++){
            if(obtenerCasilla(x,y) == '*'){
                numMinas++;
                for(int k = 0,nY,nX;k < 8;k++){
                    nX = x + movsAdy[k][0];
                    nY = y + movsAdy[k][1];
                    if(validarPosicionEnDominio(nX,nY) and
                       obtenerCasilla(nX,nY) != '*'){
                        actualizarCasilla(nX,nY,obtenerCasilla(nX,nY)+1);
                    }
                }
            }
        }
    }
}
