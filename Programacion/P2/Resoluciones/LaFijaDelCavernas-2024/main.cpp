
/*/ 
 * Projecto:  LaFijaDelCavernas
 * Nombre del Archivo:  main.cpp
 * Autor: El Cavernas
/*/

#include <iostream>
#include <iomanip>
#include <fstream>
using namespace std;
#include "cavernas.h"

//
int main(int argc, char** argv) {
    //
    void *libros,*clientes,*reserva;
    //
    cargarLibros(libros);
    //
    cargarClientes(clientes);
    //
    cargarReservasPorPedidos(reserva,libros,clientes);
    //
    emitirReporte(reserva);
    
    return 0;
}
