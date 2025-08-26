
/*/ 
 * Projecto:  NoSeJugarSudoku
 * Nombre del Archivo:  sudoku.h
 * Autor: Cyndx SUDO APT MODE
/*/

#ifndef SUDOKU_H
#define SUDOKU_H

// Declaracion de Constantes
const int medLinea = 60;
const int dimension = 9;
const int medTablero = 23;
const int maxSoluciones = 10;
const int centradoTablero = (medLinea-medTablero)/2;
const int centradoTitulo = (medLinea-14)/2;


// Declaracion de Funciones
void obtenerTablero(ifstream &,int [dimension][dimension]);

void resolverTablero(int [dimension][dimension],int,int,int,int &);

bool validarPosicionNumero(int [dimension][dimension],int,int,int);

ifstream abrirArchivo_IFS(const char *);

void imprimirLinea(bool,int,char);

void mostrarTableroSudoku(int [dimension][dimension]);


#endif /* SUDOKU_H */
