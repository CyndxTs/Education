
/* [/]
 >> Project:    TPX-1
 >> File:       tpx.cpp
 >> Author:     Candy
[/] */

#include <iomanip>
#include <iostream>
using namespace std;
#include "tpx.h"

// Modulo de Traspaso de Letra a Minuscula
void pasarMayuscula(char &caracter) {
    if(caracter <= 'a' or caracter >= 'z') return;
    caracter -= 'a' - 'A';
}
// Modulo de Traspaso de Letra a Minuscula
void pasarMinuscula(char &caracter) {
    if(caracter <= 'A' or caracter >= 'Z') return;
    caracter += 'a' - 'A';
}
