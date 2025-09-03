
/* [/]
 >> Project:    Aegleseeker
 >> File:       Tablero.cpp
 >> Author:     CyndxTs o.0?!
[/] */

#include "Tablero.h"

//
Tablero::Tablero() {
    dimX = 4;
    dimY = 4;
    mapa = nullptr;
}
//
Tablero::~Tablero() {
    if(mapa) delete mapa;
}
//
void Tablero::setDimY(int dimY) {
    this->dimY = dimY;
}
//
int Tablero::getDimY() const {
    return dimY;
}
//
void Tablero::setDimX(int dimX) {
    this->dimX = dimX;
}
//
int Tablero::getDimX() const {
    return dimX;
}
//
void Tablero::setNumMinas(int){
    cout<<"Proceso indefinido para esta subClase."<<endl;
}
//
int Tablero::getNumMinas(){
    return -1;
}
//
void Tablero::setNumMarcadores(int){
    cout<<"Proceso indefinido para esta subClase."<<endl;
}
//
int Tablero::getNumMarcadores(){
    return -1;
}
//
void Tablero::generar(int dX,int dY){
    setDimX(dX);
    setDimY(dY);
    mapa = new char *[dimY]{};
    for(int y = 0;y < dimY;y++) mapa[y] = new char[dimX]{};
}
//
bool Tablero::validarPosicionEnDominio(int cX,int cY){
    if(cX >= dimX or cX < 0) return false;
    if(cY >= dimY or cY < 0) return false;
    return true;
}
//
char Tablero::obtenerCasilla(int cX,int cY){
    return mapa[cY][cX];
}
//
void Tablero::actualizarCasilla(int cX,int cY,char valor){
    mapa[cY][cX] = valor;
}
//
void Tablero::mostrar(){
    cout<<setw((dimLinea - 4*dimX + 3)/2)<<'|';
    for(int k = 0;k < dimX;k++) cout<<setw(3)<<k<<'|';
    cout<<endl;
    for(int i = 0;i < dimY;i++){
        cout<<setw((dimLinea - 4*dimX + 1)/2)<<i;
        for(int j = 0;j < dimX;j++) cout<<"| "<<((mapa[i][j] == '0')? ' ' : mapa[i][j])<<' ';
        cout<<'|'<<endl;
    }
}
//
void Tablero::mostrar(int sX,int sY){
    cout<<setw((dimLinea - 4*dimX + 3)/2)<<'|';
    for(int k = 0;k < dimX;k++) cout<<setw(3)<<k<<'|';
    cout<<endl;
    for(int i = 0;i < dimY;i++){
        cout<<setw((dimLinea - 4*dimX + 1)/2)<<i;
        for(int j = 0;j < dimX;j++){
            cout<<'|';
            if(i == sY and j == sX) cout<<'['<<((mapa[i][j] == '0')? ' ' : mapa[i][j])<<']';
            else cout<<' '<<((mapa[i][j] == '0')? ' ' : mapa[i][j])<<' ';
        }
        cout<<'|'<<endl;
    }
}
