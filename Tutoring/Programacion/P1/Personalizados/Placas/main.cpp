
/* [/]
 >> Project:    Placas
 >> File:       main.cpp
 >> Author:     Candy
[/] */

#include <iomanip>
#include <iostream>
#include <fstream>
#include <cstring>
using namespace std;
#include "funciones.h"
#define MAXEMPRESAS 100
#define MAXINFRACCIONES 200

int main(int argc, char** argv) {

    struct Empresa arrEmpresa[MAXEMPRESAS];
    struct Infraccion arrInfraccion[MAXINFRACCIONES];
    int cantEmpresas = 0;
    int cantInfracciones = 0;
    
    cargarDatos_Empresa("EmpresasRegistradas.csv",arrEmpresa,cantEmpresas);
    cargarDatos_PlacasRegistradas("PlacasRegistradas.txt",arrEmpresa,cantEmpresas);
    cargarDatos_Infraccion("TablaDeInfracciones.csv",arrInfraccion,cantInfracciones);
    
    cargarDatos_InfraccionesDeEmpresa("InfraccionesCometidas.csv",arrEmpresa,arrInfraccion,cantEmpresas,cantInfracciones);
    emitirReporte("ReporteDeInfraccionesPorEmpresa.txt",arrEmpresa,cantEmpresas);
    return 0;
}
