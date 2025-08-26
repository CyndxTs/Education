
/* [/]
 >> Project:    PuntosMeseros
 >> File:       main.cpp
 >> Author:     Candy
[/] */

#include <iomanip>
#include <iostream>
#include <fstream>
#include <cstring>
using namespace std;
#include "funciones.h"

int main(int argc, char** argv) {
    struct Nodo *listaDeCuentas = nullptr;
    
    cargarDatos_Cuentas("CuentasBancarias.csv",listaDeCuentas);
    cargarDatos_Movimientos("MovimientosDeCuentas.csv",listaDeCuentas);
    emitirReporte("ReporteDeSaldosDeLasCuentas.txt",listaDeCuentas);
    eliminarCuentasDeSaldosPositivos(listaDeCuentas);
    emitirReporte("ReporteDeCuentasQueSeCierran.txt",listaDeCuentas);
    
    return 0;
}
