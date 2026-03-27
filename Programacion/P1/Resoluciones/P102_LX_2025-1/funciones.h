
/* [/]
 >> Project:    PuntosMeseros
 >> File:       funciones.h
 >> Author:     
[/] */

#ifndef FUNCIONES_H
#define FUNCIONES_H
#include "stNodo.h"

void cargarDatos_Cuentas(const char *,struct Nodo *&);

void cargarDatos_Movimientos(const char *,struct Nodo *);

void actualizarCuenta(struct Nodo *,int,int,double);

void emitirReporte(const char *,struct Nodo *);

void eliminarCuentasDeSaldosPositivos(struct Nodo *&listaDeCuentas);

void imprimirEncabezado(ofstream &);

struct Nodo *crearNodo(struct Cuenta,struct Nodo *);

void insertarOrdenado(struct Nodo *&,struct Cuenta);

void abrirArchivo_IFS(ifstream &,const char *);

void abrirArchivo_OFS(ofstream &,const char *);

void imprimirLinea(ofstream &,char,int,bool);

int leerValorTemporal(ifstream &,char);

int compactarValorTemporal(int,int,int,char);

void imprimirValorTemporal(ofstream &,int,char);

void descompactarValorTemporal(int,int &,int &,int &,char);

char *obtenerDinamicoExacto(const char *);

char *leerCadenaExacta(ifstream &,char);

int obtenerPosisionDePalabraEnCadena(const char *,const char *);

#endif /* FUNCIONES_H */
