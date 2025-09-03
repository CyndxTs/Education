
/*/ 
 * Projecto:  NoSeJugarSudoku
 * Nombre del Archivo:  main.cpp
 * Autor: Cyndx SUDO APT MODE
/*/

#include <iostream>
#include <iomanip>
#include <fstream>
using namespace std;
#include "sudoku.h"

// MODULO PRINCIPAL
int main(int argc, char** argv) {
    // Declaracion de Variables
    int tablero[dimension][dimension],cantSoluciones = 0;
    ifstream archDatos = abrirArchivo_IFS("Tablero Sudoku.txt");
    // Lectura de Tablero Base
    obtenerTablero(archDatos,tablero);
    // Impresion de Version Inicial de Tablero
    cout<<"Se tiene el tablero SUDOKU:"<<endl<<endl;
    mostrarTableroSudoku(tablero);
    cout<<endl;
    imprimirLinea(true,medLinea,'-');
    cout<<"Resolviendo con una maxima generacion de "<<maxSoluciones;
    cout<<" soluciones.."<<endl;
    imprimirLinea(true,medLinea,'-');
    imprimirLinea(true,medLinea,'*');
    // Busqueda e Impresion de Soluciones de Tablero
    resolverTablero(tablero,1,0,0,cantSoluciones);
    // Impresion Segun Cantidad de Soluciones
    if(cantSoluciones == maxSoluciones) {
          cout<<"Se llego al limite de soluciones habilitadas para ";
          cout<<"el programa. ['"<<maxSoluciones<<"' soluciones]"<<endl;
          cout<<"Puedes incrementar este limite revisando el archivo sudoku.h";
          cout<<endl;
    }else if(cantSoluciones == 1){
        cout<<"Se hallo solo "<<cantSoluciones<<" solucion para ";
        cout<<"la resolucion de este caso."<<endl;
    }else if(cantSoluciones > 1){
        cout<<"Se hallaron solo "<<cantSoluciones<<" soluciones para ";
        cout<<"la resolucion de este caso."<<endl;
    }else cout<<"No se hallaron soluciones para este sudoku."<<endl;
      
    return 0;
}
