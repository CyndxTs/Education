
/* 
 * File:   main.cpp
 * Author: Cesar Augusto Napuri de la Cruz
 * Codigo: 20211692
 */

#include <iostream>
#include <iomanip>
#include <fstream>
#include <cstring>
using namespace std;
#include "Biblioteca.h"
/*
 * 
 */
int main(int argc, char** argv) {
    
    Biblioteca biblioteca;
    
    biblioteca.cargar_libros("Libros.csv");
    biblioteca.cargar_autores("Autores.csv");
    biblioteca.cargar_clientes("Clientes.csv");
    biblioteca.mostrar_recomendaciones("Reporte.txt");
    // El error por algunn motivo reside en la insercion de map, se quita y se deberia solucionar el resto del proyecto
    return 0;
}

