
/* [/]
 >> Project:    Aegleseeker
 >> File:       TableroDeMuestra.cpp
 >> Author:     CyndxTs o.0?!
[/] */

#include "TableroDeMuestra.h"

//
TableroDeMuestra::TableroDeMuestra() {
    numMarcadores = 0;
}
//
TableroDeMuestra::~TableroDeMuestra() {
    
}
//
void TableroDeMuestra::setNumMarcadores(int numMarcadores) {
    this->numMarcadores = numMarcadores;
}
//
int TableroDeMuestra::getNumMarcadores() {
    return numMarcadores;
}
//
void TableroDeMuestra::inicializar(int dX,int dY,int marcadoresDisponibles){
    setNumMarcadores(marcadoresDisponibles);
    generar(dX,dY);
    for(int y = 0;y < getDimY();y++){
        for(int x = 0;x < getDimX();x++){
            actualizarCasilla(x,y,'?');
        }
    }
}
//
void TableroDeMuestra::mostrar(){
    cout<<" | Marcadores restantes: "<<setw(2)<<numMarcadores<<endl<<endl;
    Tablero::mostrar();
}
//
void TableroDeMuestra::mostrar(int cX,int cY){
    cout<<" | Marcadores restantes: "<<setw(2)<<numMarcadores<<endl<<endl;
    Tablero::mostrar(cX,cY);
}
