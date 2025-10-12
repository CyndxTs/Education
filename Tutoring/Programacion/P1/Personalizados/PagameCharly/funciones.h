//
// Created by cesar on 5/10/2025.
//

#ifndef PAGAMECHARLY_FUNCIONES_H
#define PAGAMECHARLY_FUNCIONES_H

void cargarDatos_Repartos(const char *nombArchRepartos,int *arrCodigoPlatos, int *arrNumPlatosVendidos,int &numPlatos);
void cargarDatos_Plato(const char *nombArchPlato, int *arrCodigoPlatos,int *arrNumPlatosVendidos,double *arrPrecio, double *arrIngresoBruto,
                       double *arrDescuento, bool *arrTieneDescuento, double *arrTotal, int numPlatos);
void emitirPruebaDeCarga(const char *nombArchRep, int *arrCodigoPlatos,int *arrNumPlatosVendidos,double *arrPrecio, double *arrIngresoBruto,
                       double *arrDescuento, bool *arrTieneDescuento, double *arrTotal, int numPlatos);
void emitirReporte(const char *nombArchRep,const char *nombArchPlatos, int *arrCodigoPlatos,int *arrNumPlatosVendidos,double *arrPrecio, double *arrIngresoBruto,
                       double *arrDescuento, bool *arrTieneDescuento, double *arrTotal, int numPlatos);
void procesarNombreDePlato(ofstream &archRep,ifstream &archPlatos,int codigoPlato,bool tieneDescuento);
void abrirArchivo_IFS(ifstream &arch, const char *nombArch);
void abrirArchivo_OFS(ofstream &arch, const char *nombArch);
void imprimirLinea(ofstream &archSalida, char simbolo, int medida);
int obtenerPosicion(int valor,int *arreglo,int medida);
void insertarOrdenado(int codigoPlato,int cantVendida,int *arrCodigoPlatos,int *arrNumPlatosVendidos,int &numPlatos);
void insertarElemento(int valor,int pos,int *arreglo,int &medida,bool actualizarMedida);
void modificarCadena(char *nombPlato,char *nombCategoria,bool tieneDescuento);
void pasarMayuscula(char &caracter);
void pasarMinuscula(char &caracter);
void concatenar(char *cad1,const char *cad2);
int longitud(const char *cadena);
void copiar(char *cadDest,const char *cadOrig);

#endif //PAGAMECHARLY_FUNCIONES_H