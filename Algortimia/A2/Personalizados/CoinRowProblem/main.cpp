
/*/ 
 * Projecto:  CoinRowProblem
 * Nombre del Archivo:  main.cpp
 * Autor:
/*/

#include <iostream>
#include <iomanip>
#include <fstream>
using namespace std;
#include "funciones.h"

// Modulo Principal
int main(int argc, char** argv) {
    // Declaracion de Variables
    const int medida = 6;
    int monedas[medida] = {5,1,2,10,6,2};
    // Muestra de Mejor Seleccion por Proceso Dinamico
    mostrarMejorSeleccion(monedas,medida);

    return 0;
}
