
/* [/]
 >> Project:    HyperPedidos
 >> File:       solution.cpp
 >> Author:     Candy
[/] */

#include <iomanip>
#include <iostream>
#include <fstream>
using namespace std;
#include "solution.h"
#define XD -1
#define MEDLINEA 55
#define MAXTEXTO 32

                      /*  - / > [ - TAREA 1 - ] < / -  */

// Modulo de Carga de Registros de Cliente
void cargarClientes(ifstream &archPed,int *arrCliID,int &numCli) {
    // Declaracion de Variables
    int cliID,posCli;
    // Carga de Datos de Cliente
    while(1) {
        archPed>>cliID;
        if(archPed.eof()) break;
        posCli = obtenerPosicion_Bin_Asc(cliID,arrCliID,numCli);
        if(posCli == XD) insertarOrdenado_Asc_V2(cliID,arrCliID,numCli);        
        while(archPed.get() != '\n');
    }
}

                      /*  - / > [ - TAREA 2 - ] < / -  */

// Modulo de Procesamiento && Emision de Reporte
void procesarReporte(ofstream &archRep,ifstream &archPed,int *arrCliID,int *arrCliCantProd,int numCli) {
    // Declaracion de Variables
    int cliID_Eval;
    // Procesamiento de Reporte con Registros Ordenados
    imprimirEncabezado(archRep,'A');
    for(int i = 0,cliID;i < numCli;i++) {
        // Asignacion de Cliente de Bloque
        cliID = arrCliID[i];
        // Reinicio de Lectura de Archivo
        archPed.clear();
        archPed.seekg(0,ios::beg);
        // Lectura && Impresion de Bloque de Cliente
        while(1) {
            archPed>>cliID_Eval;
            if(archPed.eof()) break;
            if(cliID_Eval == cliID) {
                procesarDatosDeCliente(archRep,archPed,cliID,arrCliCantProd[i] == 0);
                procesarPedidoDeCliente(archRep,archPed,arrCliCantProd[i]);
            } else while(archPed.get() != '\n');
        }
        imprimirLinea(archRep,'*',MEDLINEA,true);
        archRep<<right<<setw(34)<<"NUM. PRODUCTOS COMPRADOS: ";
        archRep<<setw(4)<<arrCliCantProd[i]<<endl;
    }
    imprimirLinea(archRep,'=',MEDLINEA,true);
}
// Modulo Impresion de Encabezados de Reporte
void imprimirEncabezado(ofstream &archRep, char tipo) {
    switch(tipo) {
        case 'A':
            archRep<<setw((MEDLINEA+26)/2)<<"RESUMEN DE ULTIMOS PEDIDOS"<<endl;
            break;
        case 'B':
            archRep<<setw(22)<<"PRODUCTO"<<setw(14)<<"CANTIDAD"<<endl;
            break;
    }
}
// Modulo de Procesamiento de Datos Geenerales de Cliente
void procesarDatosDeCliente(ofstream &archRep,ifstream &archPed,int cliID,bool imprimir) {
    // Declaracion de Variables
    char nombres[MAXTEXTO],apellidos[MAXTEXTO],nombreCompleto[MAXTEXTO];
    // Lectura de Datos
    archPed>>nombres>>apellidos;
    // Validacion de Procesamiento && Impresion
    if(imprimir) {
        procesarNombreCompletoDeCliente(nombres,apellidos,nombreCompleto);
        imprimirLinea(archRep,'=',MEDLINEA,true);
        archRep<<setw(9)<<"DNI: "<<cliID;
        archRep<<setw(12)<<"NOMBRE: "<<nombreCompleto<<endl;
    }
}
// Modulo de Procesamiento de Nombre Completo de Cliente
void procesarNombreCompletoDeCliente(char *nombres,char *apellidos,char *nombreCompleto) {
    int posEspacio = obtenerPosisionDePalabraEnCadena("____",nombres);
    copiar(&nombres[posEspacio+5],".");
    copiar(&nombres[posEspacio+1],&nombres[posEspacio+4]);
    nombres[posEspacio] = ' ';
    posEspacio = obtenerPosisionDePalabraEnCadena("//",apellidos);
    copiar(&apellidos[posEspacio+3],"., ");
    copiar(&apellidos[posEspacio+1],&apellidos[posEspacio+2]);
    apellidos[posEspacio] = ' ';
    nombreCompleto[0] = 0;
    concatenar_V2(nombreCompleto,apellidos);
    concatenar_V2(nombreCompleto,nombres);
    normalizar(nombreCompleto);
}
// Modulo de Procesamiento de Pedido de Cliente
void procesarPedidoDeCliente(ofstream &archRep,ifstream &archPed,int &cliCantProd) {
    // Declaracion de Variables
    int fecha,cantidad,posProd = 0;
    char prodID[7];
    // Lectura de Fecha && Impresion de Bloque de Fecha
    fecha = leerValorTemporal(archPed,'F');
    imprimirLinea(archRep,'-',MEDLINEA,true);
    archRep<<setw(11)<<"FECHA: ";
    imprimirValorTemporal(archRep,fecha,'F');
    archRep<<endl;
    imprimirEncabezado(archRep,'B');
    while(1) {
        if(archPed.get() == '\n') break;
        posProd++;
        archPed>>cantidad>>prodID;
        archRep<<setw(8)<<" "<<setfill('0')<<setw(2)<<posProd<<setfill(' ');
        archRep<<")"<<setw(10)<<prodID<<setw(12)<<cantidad<<endl;
        cliCantProd += cantidad;
    }
}
