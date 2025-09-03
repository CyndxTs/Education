
/* [/]
 >> Project:    Aegleseeker
 >> File:       Partida.h
 >> Author:     CyndxTs o.0?!
[/] */

#ifndef PARTIDA_H
#define PARTIDA_H
#include "TableroDeValores.h"
#include "TableroDeMuestra.h"

class Partida {
private:
    int numJugadas;
    bool enJuego;
    char dificultad;
    class Tablero *mapa_valores;
    class Tablero *mapa_muestra;
public:
    Partida();
    virtual ~Partida();
    int obtenerChancePorDificultad(char);
    int obtenerDimensionPorDificultad(char);
    void iniciarNuevaPartida();
    void inicializarElementos();
    void mostrar();
    void mostrar(int,int);
    bool realizarJugada();
    bool minarCasilla(int,int);
    bool marcarCasilla(int,int);
    bool desmarcarCasilla(int,int);
    void revelarArea(int,int);
    bool validarRendicion();
    bool validarVictoria();
    void imprimirLinea(int,char);
    void imprimirTitulos(char);
};

#endif /* PARTIDA_H */
