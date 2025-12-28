
/* [/]
 >> Project:    Aegleseeker
 >> File:       Constantes.h
 >> Author:     CyndxTs o.0?!
[/] */

#ifndef BUSCAMINAS_H
#define BUSCAMINAS_H
#include <iostream>
#include <iomanip>
#include <fstream>
using namespace std;

const int dimLinea = 80;

                        // {mX,mY}
const int movsAdy[8][2] = {{ 1, 0},     //  Derecha
                           { 0, 1},     //  Abajo
                           {-1, 0},     //  Izquierda
                           { 0,-1},     //  Arriba
                           { 1,-1},     //  Diagonal [Derecha & Arriba]
                           { 1, 1},     //  Diagonal [Derecha & Abajo]
                           {-1, 1},     //  Diagonal [Izquierda & Abajo]
                           {-1,-1}};    //  Diagonal [Izquierda & Arriba]

const char frases_derrota[7][80] = {"Oops! Pisaste una mina y la pantalla te exploto en la cara.",
                                    "Tus calculos fallaron, y la mina no perdono..",
                                    "Boom! Tu aventura minera termino con un gran estallido.",
                                    "La mina dijo 'hola'.. y luego 'adios' a tu partida.",
                                    "Tu intuicion jugo al escondite.. y la mina gano..",
                                    "Pusiste el pie donde no debias y.. Boom!",
                                    "La mina te dijo 'sorpresa!' y exploto de la emocion.",
                                   };

const char frases_victoria[7][80] = {"Eres el rey del campo minado! Todas las minas se posan ante tus pies!",
                                     "Felicitaciones, ¡dejaste a todas las minas con las ganas de explotar!",
                                     "Victoria explosiva! Pero esta vez las minas se quedaron en silencio.",
                                     "Ganaste! Todas las minas se rinden ante tu poder.",
                                     "Tus clics han desactivado todas las minas con precision quirurgica!",
                                     "Lo hiciste! Cada mina fue neutralizada como un profesional.",
                                     "Lo lograste! El campo minado esta tan despejado como tu mente brillante."
                                    };

#endif /* BUSCAMINAS_H */
