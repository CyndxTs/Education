
/* [/]
 >> Project:    HyperPedidos
 >> File:       main.cpp
 >> Author:     Candy
[/] */

#include <iomanip>
#include <iostream>
#include <fstream>
using namespace std;
#include "solution.h"
#define MAXCLI 64

// Modulo Principal
int main(int argc, char** argv) {
    // Apertura de Archivos
    ifstream archPed;
    ofstream archRep;
    abrirArchivo_IFS(archPed,"Pedidos.txt");
    abrirArchivo_OFS(archRep,"Reporte.txt");
    // Declaracion de Variables
    int arrCliID[MAXCLI]{},arrCliCantProd[MAXCLI]{},numCli = 0;
    // TAREA 1 | Carga de Registros de Cliente
    cargarClientes(archPed,arrCliID,numCli);
    // TAREA 2 | Procesamiento && Impresion de Reporte
    procesarReporte(archRep,archPed,arrCliID,arrCliCantProd,numCli);
    
    return 0;
}
