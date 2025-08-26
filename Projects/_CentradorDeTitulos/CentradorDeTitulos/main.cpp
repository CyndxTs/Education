
/*/
 * Projecto:  CentradorDeTitulos
 * Nombre del Archivo:  main.cpp
 * Autor: CyndxTs o.0?!
/*/

#include <iostream>
#include <iomanip>
#include <fstream>
#include <cstring>
using namespace std;

// Modulo Principal
int main(int argc, char** argv) {
    // Declaracion de Variables && Apertura de Archivos
    int medLinea = 80; char letra,palabra[medLinea]{};
    ifstream archEntrada("titulos.txt",ios::in);
    ofstream archSalida("centrados.txt",ios::out);
    // Iterativa del Proceso de Lectura & Impresion de Titulos [Centrados]
    for(int i = 0;1;i++){
        letra = archEntrada.get();
        cout<<setw(3)<<i<<" - '"<<letra<<"'"<<endl;
        if(archEntrada.eof()) break;                                            // Validacion de Fin de Archivo
        // Validacion de Inicio de Impresion
        if(letra == '\n'){
            palabra[i] = 0;
            cout<<palabra<<endl;
            archSalida<<endl<<setw((medLinea+strlen(palabra))/2)<<palabra<<endl;
            i = -1;
            archEntrada>>ws;
        } else palabra[i] = letra;
    }
    
    return 0;
}
