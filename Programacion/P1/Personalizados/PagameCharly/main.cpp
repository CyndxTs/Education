#include <iomanip>
#include <iostream>
#include <fstream>
using namespace std;
#include "funciones.h"
#define MAXPLATOS 200

int main() {

    bool arrTieneDescuento[MAXPLATOS];
    int arrCodigoPlatos[MAXPLATOS],arrNumPlatosVendidos[MAXPLATOS],numPlatos = 0;
    double arrPrecio[MAXPLATOS],arrIngresoBruto[MAXPLATOS],arrDescuento[MAXPLATOS],arrTotal[MAXPLATOS];

    cargarDatos_Repartos("ArchivosDeDatos/RepartosARealizar.txt",arrCodigoPlatos,arrNumPlatosVendidos,numPlatos);
    cargarDatos_Plato("ArchivosDeDatos/PlatosOfrecidos.txt",arrCodigoPlatos,arrNumPlatosVendidos,arrPrecio,arrIngresoBruto,
                           arrDescuento, arrTieneDescuento,arrTotal,numPlatos);
    emitirPruebaDeCarga("ArchivosDeReporte/ReporteDePruebaDeCargaDeDatos.txt",arrCodigoPlatos,arrNumPlatosVendidos,arrPrecio,arrIngresoBruto,
                           arrDescuento, arrTieneDescuento,arrTotal,numPlatos);
    emitirReporte("ArchivosDeReporte/ReporteDePagoPorAlumno.txt","ArchivosDeDatos/PlatosOfrecidos.txt",arrCodigoPlatos,arrNumPlatosVendidos,arrPrecio,arrIngresoBruto,
                           arrDescuento, arrTieneDescuento,arrTotal,numPlatos);
}