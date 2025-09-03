
/*/ 
 * Projecto:  NoSeJugarSudoku
 * Nombre del Archivo:  sudoku.cpp
 * Autor: Cyndx SUDO APT MODE
/*/

#include <iostream>
#include <iomanip>
#include <fstream>
using namespace std;
#include "sudoku.h"

// MODULO DE LECTURA DE TABLERO INICIAL DESDE ARCHIVO
void obtenerTablero(ifstream &archDatos,int tablero[dimension][dimension]){
    // Declaracion de Variables
    char simbolo;
    // Primera Iterativa del Proceso de Lectura
    for(int y=0;y<dimension;y++){
        simbolo = archDatos.get();
        // Validacion de Proximo Caracter
        if(simbolo != '-'){
            archDatos.unget();
            // Segunda Iterativa del Proceso de Lectura
            for(int x=0;x<dimension;x++){
                archDatos.get();
                simbolo = archDatos.get();
                // Validacion de Proximo Caracter
                if(simbolo != '|'){
                    archDatos.unget();
                    archDatos>>tablero[y][x];
                }else x--;
            }
            archDatos.get();
        }else{
            while(archDatos.get() != '\n'); // Descarte de Linea
            y--;
        }
    }
}
// MODULO DE RESOLUCION DE TABLERO
void resolverTablero(int tablero[dimension][dimension],int valAsignado,
                int posColumna,int posFila,int &cantSoluciones){
    // Validacion de Caso Base
    if(posFila == dimension and posColumna == dimension){
        cantSoluciones++;
        imprimirLinea(false,centradoTitulo,'-');
        cout.fill('0');
        cout<<" Solucion #"<<setw(2)<<cantSoluciones<<' ';
        cout.fill(' ');
        imprimirLinea(true,centradoTitulo,'-');
        mostrarTableroSudoku(tablero);
        imprimirLinea(true,medLinea,'*');
        return;
    }
    // Validacion de Sobrepasode Limite de Soluciones
    if(cantSoluciones != maxSoluciones){
        // Validacion de Numero Preasignado
        if(tablero[posFila][posColumna] == 0){
            // Iterativa de Validacion y Asignacion de Valor en Posicion
            for(valAsignado;valAsignado<10;valAsignado++){
                // Validacion de Posicion de Valor
                if(validarPosicionNumero(tablero,valAsignado,posFila,
                    posColumna)){
                    tablero[posFila][posColumna] = valAsignado;
                    // Validacion de Siguiente Caso
                    if(posColumna == dimension-1)
                        resolverTablero(tablero,1,0,posFila+1,cantSoluciones);
                    else 
                        resolverTablero(tablero,1,posColumna+1,posFila,
                                    cantSoluciones);
                    // Desasignacion por Caso de BackTracking
                    tablero[posFila][posColumna] = 0; /* No afecta a los numeros preasignados. */
                }
            }
        } else if (posColumna < dimension-1) // Validacion de Proxima de Columna [Despues de Numero Preasignado]
            resolverTablero(tablero,1,posColumna+1,posFila,cantSoluciones);
        else if (posFila < dimension-1) // Validacion Proxima de Fila [Despues de Numero Preasignado]
            resolverTablero(tablero,1,0,posFila+1,cantSoluciones);
        else resolverTablero(tablero,1,dimension,dimension,cantSoluciones); // Caso Base
    }
    return; // Retorno por Fallo de Solucion
}
// MODULO DE VALIDACION DE POSICION DE NUMERO EN TABLERO
bool validarPosicionNumero(int tablero[dimension][dimension],int valorAsignado,
                    int posFila,int posColumna){
    // Declaracion de Variables Auxiliares por Validacion de Posicion
    int posRecuadroX = (posColumna<3)?0:(posColumna<6)?1:2;
    int posRecuadroY = (posFila<3)?0:(posFila<6)?1:2;
    // Iterativa de Validacion de Numero en Fila (se validan colummnas)
    for(int x = 0; x < dimension ;x++)
        if(tablero[posFila][x] == valorAsignado) return false;
    // Iterativa de Validacion de Numero en Fila (se validan filas)
    for(int y = 0; y < dimension ;y++)
        if(tablero[y][posColumna] == valorAsignado) return false;
    // Iterativas de Validacion de Numero en Recuadre
    for(int y=3*posRecuadroY,i=0;i<3;y++,i++){
        for(int x=3*posRecuadroX,j=0;j<3;x++,j++){
            if(tablero[y][x] == valorAsignado) return false;
        }
    }
    // Retorno de Posicion Valida
    return true;
}
// MODULO AUXILIAR DE APERTURA DE ARCHIVOS IFSTREAM
ifstream abrirArchivo_IFS(const char *nombArch){
    ifstream archIFS(nombArch,ios::in);
    if(not archIFS.is_open()){
        cout<<"ERROR DE APERUTRA: Archivo "<<nombArch<<endl;
        exit(1);
    }
    return archIFS;
}
// MODULO AUXILIAR DE IMPRESION DE DATOS EN LINEA
void imprimirLinea(bool enter,int medida,char simbolo){
    for(int i=0;i<medida;i++) cout.put(simbolo);
    if(enter) cout<<endl;
}
// MODULO AUXILIAR DE IMPRESION DE TABLERO SUDOKU [FORMATO: PREDETERMINADO]
void mostrarTableroSudoku(int tablero[dimension][dimension]){
    for(int y=0,filasImp = 1,columnasImp;y<dimension;y++,filasImp++){
        imprimirLinea(false,centradoTablero,' ');
        if(filasImp % 4 != 0 or filasImp == 0){
            columnasImp = 1;
            for(int x=0;x<dimension;x++,columnasImp++){
                if(columnasImp % 4 != 0 or columnasImp == 0){
                    cout<<setw(2)<<tablero[y][x];
                }else{
                    cout<<" |";
                    x--;
                }
            }
        }else{
            imprimirLinea(false,medTablero,'-');
            y--;
        }
        cout<<endl;
    }
}
