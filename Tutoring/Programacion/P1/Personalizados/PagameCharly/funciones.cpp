/* [/]
 >> Project:    TPX-2
 >> File:       tpx.cpp
 >> Author:     Candy
[/] */

#include <iomanip>
#include <iostream>
#include <fstream>
using namespace std;
#include "funciones.h"
#define XD -1
#define MAXTEXTO 80

void cargarDatos_Repartos(const char *nombArchRepartos,int *arrCodigoPlatos, int *arrNumPlatosVendidos,int &numPlatos) {
    ifstream archRepartos;
    abrirArchivo_IFS(archRepartos,nombArchRepartos);
    int dato,codigoPlato,cantVendida,posPlato;
    while (1) {
        archRepartos>>dato;
        if (archRepartos.eof()) break;
        archRepartos>>dato;
        while (1) {
            if (archRepartos.get() == '\n') break;
            archRepartos>>cantVendida>>codigoPlato;
            posPlato = obtenerPosicion(codigoPlato,arrCodigoPlatos,numPlatos);
            if (posPlato == XD) {
                insertarOrdenado(codigoPlato,cantVendida,arrCodigoPlatos,arrNumPlatosVendidos,numPlatos);
            } else {
                arrNumPlatosVendidos[posPlato]+= cantVendida;
            }
        }
    }
}

void cargarDatos_Plato(const char *nombArchPlato, int *arrCodigoPlatos,int *arrNumPlatosVendidos,double *arrPrecio, double *arrIngresoBruto,
                       double *arrDescuento, bool *arrTieneDescuento, double *arrTotal, int numPlatos) {
    ifstream archPlatos;
    abrirArchivo_IFS(archPlatos,nombArchPlato);
    bool tieneDescuento;
    int codigo,posPlato;
    double precio,descuento;
    char descarte[MAXTEXTO]{};
    while (1) {
        archPlatos>>codigo;
        if (archPlatos.eof()) break;
        posPlato = obtenerPosicion(codigo,arrCodigoPlatos,numPlatos);
        if (posPlato != XD) {
            archPlatos>>descarte>>precio>>descarte;
            if (archPlatos.get() != '\n') {
                archPlatos>>descuento;
                tieneDescuento = true;
                archPlatos.get();
            } else {
                tieneDescuento = false;
                descuento = 0.0;
            }
            arrPrecio[posPlato] = precio;
            arrIngresoBruto[posPlato] = precio * arrNumPlatosVendidos[posPlato];
            arrTieneDescuento[posPlato] = tieneDescuento;
            arrDescuento[posPlato] = (1-descuento/100)*arrNumPlatosVendidos[posPlato];
            arrTotal[posPlato] = arrIngresoBruto[posPlato] - arrDescuento[posPlato];
        } else while (archPlatos.get() != '\n');
    }
}

void emitirPruebaDeCarga(const char *nombArchRep, int *arrCodigoPlatos,int *arrNumPlatosVendidos,double *arrPrecio, double *arrIngresoBruto,
                       double *arrDescuento, bool *arrTieneDescuento, double *arrTotal, int numPlatos) {
    ofstream archRep;
    abrirArchivo_OFS(archRep,nombArchRep);
    archRep<<fixed<<setprecision(2);
    for (int i = 0; i < numPlatos; i++) {
        archRep<<arrCodigoPlatos[i];
        archRep<<setw(20)<<arrNumPlatosVendidos[i];
        archRep<<setw(20)<<arrPrecio[i];
        archRep<<setw(20)<<arrIngresoBruto[i];
        if (arrTieneDescuento[i]) {
            archRep<<" > SI TIENE DESCUENTO < ";
            archRep<<setw(10)<<arrDescuento[i];
            archRep<<setw(10)<<arrTotal[i]<<endl;
        } else {
            archRep<<" > NO TIENE DESCUENTO < ";
            archRep<<setw(20)<<arrTotal[i]<<endl;
        }

    }
}

void emitirReporte(const char *nombArchRep,const char *nombArchPlatos, int *arrCodigoPlatos,int *arrNumPlatosVendidos,double *arrPrecio, double *arrIngresoBruto,
                       double *arrDescuento, bool *arrTieneDescuento, double *arrTotal, int numPlatos) {
    ofstream archRep;
    abrirArchivo_OFS(archRep,nombArchRep);
    ifstream archPlatos;
    abrirArchivo_IFS(archPlatos,nombArchPlatos);
    archRep<<fixed<<setprecision(2);
    for (int i = 0; i < numPlatos; i++) {
        archRep<<setw(4)<<' '<<setfill('0')<<setw(3)<<i+1<<setfill(' ')<<')';
        archRep<<setw(12)<<arrCodigoPlatos[i]<<setw(8)<<" ";
        procesarNombreDePlato(archRep,archPlatos,arrCodigoPlatos[i],arrTieneDescuento[i]);
        archRep<<setw(20)<<arrNumPlatosVendidos[i];
        archRep<<setw(20)<<arrPrecio[i];
        archRep<<setw(20)<<arrIngresoBruto[i];
        if (arrTieneDescuento[i]) {
            archRep<<setw(10)<<arrDescuento[i];
            archRep<<setw(10)<<arrTotal[i]<<endl;
        } else {
            archRep<<setw(7)<<'-';
            archRep<<setw(13)<<arrTotal[i]<<endl;
        }

    }
}
void procesarNombreDePlato(ofstream &archRep,ifstream &archPlatos,int codigoPlato,bool tieneDescuento) {
    archPlatos.clear();
    archPlatos.seekg(0,ios::beg);
    int codigo_Eval;
    char nombPlato[MAXTEXTO],nombCategoria[MAXTEXTO];
    double descarte;
    while (1) {
        archPlatos>>codigo_Eval;
        if (archPlatos.eof()) break;
        if (codigo_Eval == codigoPlato) {
            archPlatos>>nombPlato>>descarte>>nombCategoria;
            modificarCadena(nombPlato,nombCategoria,tieneDescuento);
            archRep<<left<<setw(MAXTEXTO)<<nombPlato<<right;
        } else while (archPlatos.get() != '\n');
    }
}
// Modulo de Apertura de Archivos IFSTREAM
void abrirArchivo_IFS(ifstream &arch, const char *nombArch) {
    arch.open(nombArch,ios::in);
    if(arch.is_open()) return;
    cout<<"ERROR DE APERTURA: Archivo '"<<nombArch<<"'."<<endl;
    exit(1);
}
// Modulo de Apertura de Archivos OFSTREAM
void abrirArchivo_OFS(ofstream &arch, const char *nombArch) {
    arch.open(nombArch,ios::out);
    if(arch.is_open()) return;
    cout<<"ERROR DE APERTURA: Archivo '"<<nombArch<<"'."<<endl;
    exit(1);
}
// Modulo de Impresion de Simbolos en Linea
void imprimirLinea(ofstream &archSalida, char simbolo, int medida) {
    for(int i = 0;i < medida;i++) archSalida.put(simbolo);
    archSalida<<endl;
}
//
int obtenerPosicion(int valor,int *arreglo,int medida) {
    for (int i = 0; i < medida; i++) if (valor == arreglo[i]) return i;
    return XD;
}
// Modulo Insercion Ordenada DESCENDENTEMENTE de Elemento en Cadena {VERSION OPTIMA}
void insertarOrdenado(int codigoPlato,int cantVendida,int *arrCodigoPlatos,int *arrNumPlatosVendidos,int &numPlatos){
    int i = 0;
    while(i < numPlatos) {
        if(codigoPlato > arrCodigoPlatos[i]) {
            insertarElemento(codigoPlato,i,arrCodigoPlatos,numPlatos,false);
            insertarElemento(cantVendida,i,arrNumPlatosVendidos,numPlatos,true);
            break;
        }
        i++;
    }
    if(i == numPlatos) {
        arrCodigoPlatos[i] = codigoPlato;
        arrNumPlatosVendidos[i] = cantVendida;
        numPlatos++;
    }
}
// Modulo de Insercion de Elemento en Cadena
void insertarElemento(int valor,int pos,int *arreglo,int &medida,bool actualizarMedida) {
    for(int i = medida;i > pos;i--) arreglo[i] = arreglo[i-1];
    arreglo[pos] = valor;
    if (actualizarMedida) medida++;
}
//
void modificarCadena(char *nombPlato,char *nombCategoria,bool tieneDescuento) {
    for (int i = 0;nombPlato[i];i++) if (nombPlato[i] == '_') nombPlato[i] = ' ';
    for (int i = 0;nombPlato[i];i++) pasarMinuscula(nombPlato[i]);
    pasarMayuscula(nombPlato[0]);
    if (tieneDescuento) {
        concatenar(nombPlato,"-PROMOCIONADO");
    }
    concatenar(nombPlato,"-");
    concatenar(nombPlato,nombCategoria);
}

void pasarMayuscula(char &caracter) {
    if(caracter <= 'a' or caracter >= 'z') return;
    caracter -= 'a' - 'A';
}
// Modulo de Traspaso de Letra a Minuscula
void pasarMinuscula(char &caracter) {
    if(caracter <= 'A' or caracter >= 'Z') return;
    caracter += 'a' - 'A';
}
// Modulo de Concatenacion de Cadenas [cad1 + cad2 -> cad1cad2] {VERSION OPTIMA}
void concatenar(char *cad1,const char *cad2) {
    int i = longitud(cad1);
    copiar(&cad1[i],cad2);
}
// Modulo de Cálculo de Longitud de Cadena
int longitud(const char *cadena) {
    int i = 0;
    while(cadena[i]) i++;
    return i;
}
// Modulo de Copia de Cadena Origen a Cadena Destino
void copiar(char *cadDest,const char *cadOrig) {
    int i = 0;
    while(cadOrig[i]) {
        cadDest[i] = cadOrig[i];
        i++;
    }
    cadDest[i] = 0;
}

