
/*/ 
 * Projecto:  CoinRowProblem
 * Nombre del Archivo:  funciones.cpp
 * Autor:
/*/

#include <iostream>
#include <iomanip>
#include <fstream>
#include <cstring>
using namespace std;
#include "funciones.h"

// Modulo de Seleccion Dinamica de Monedas
void mostrarMejorSeleccion(int *monedas,const int medida){
    // Declaracion Variables
    int arrSol[medida + 1]{};
    // Incializacion de Elementos
    arrSol[1] = monedas[0];
    // Iterativa del Proceso de Seleccion Dinamica
    for(int i = 2;i <= medida;i++)
        arrSol[i] = obtenerMaximo(monedas[i-1] + arrSol[i-2],arrSol[i-1]);
    // Muestra de Resultados
    mostrarResultados(arrSol,monedas,medida);
}
// Modulo de Muestra de Resultados en Consola
void mostrarResultados(int *arrSol,int *monedas,const int medida){
    cout<<setw((80+16)/2)<<"COIN ROW PROBLEM"<<endl;
    imprimirLinea(80,'-');
    // Muestra de Arreglo de Monedas
    cout<<"Del arreglo: ";
    mostrarArreglo(monedas,medida);
    // Muestra de Arreglo Solucion
    cout<<"Se obtiene el arreglo solucion: ";
    mostrarArreglo(&arrSol[1],medida);
    // Muestra de Componentes de Solucion
    cout<<"Que muestra que la eleccion de los elementos NO ADYACENTES: [";
    // Iterativa del Proceso de Muestra de Componentes de Solucion
    for(int i = medida,sol;i > 0;){
        if(arrSol[i] > arrSol[i-1]){
            sol = arrSol[i] - monedas[i-1];
            cout<<" '"<<monedas[i-1]<<"' ";
            for(int j = 0;j < i;j++) if(arrSol[j] == sol) i = j;
        } else i--;
    }
    // Muestra de Solucion
    cout<<']'<<endl<<"Da lugar a la mejor suma de dinero: '"<<arrSol[medida];
    cout<<"'"<<endl;
    imprimirLinea(80,'=');
}
// Modulo Auxiliar de Muestra de Arreglo en Consola
void mostrarArreglo(int *arr,const int medida){
    cout<<'[';
    for(int i = 0;i < medida;i++) cout<<" '"<<arr[i]<<"' ";
    cout<<']'<<endl;
}
// Modulo Auxiliar de Impresion de Simbolos en Linea
void imprimirLinea(int medida,char simbolo){
    for(int i = 0;i < medida;i++) cout.put(simbolo);
    cout<<endl;
}
// Modulo Auxiliar de Retorno de Maximo Entero
int obtenerMaximo(int a,int b){
    return ((a > b)?a:b);
}
