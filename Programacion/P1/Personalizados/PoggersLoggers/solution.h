
/* [/]
 >> Project:    PoggersLoggers
 >> File:       solution.h
 >> Author:     Candy
[/] */

#ifndef FUNCIONES_H
#define FUNCIONES_H
#include "resources.h"

void cargarUsuarios(ifstream &,int *,char *,int *,int &);

void insertarOrdenado_Usuario(int,char,int,int *,char *,int *,int &);

void cargarLogs(ifstream &,int *,int *,int *,int &);

void cargarValoraciones(ifstream &,int *,int *,int *,int *,int *,int,int *,
                        int *,int *,int *,int *,int &);

int obtenerPosicion_UltimoLog(int,int,int,int *,int *,int *,int);

void insertarElemento_Log(int,int,int,int,int,int,int *,int *,int *,int *,int *,
                          int &);

void eliminarUsuariosSinValoraciones(int *,char *,int *,int *,int *,int *,int &,
                                     int *,int *,int *,int *,int *,int &);

void eliminarElemento_Usuario(int,int *,char *,int *,int *,int *,int *,int &);

void eliminarElemento_Log(int,int *,int *,int *,int *,int *,int &);

void ordenarArreglos_Usuarios(int *,char *,int *,int *,int *,int *,int);

void emitirReporteUsuarios(ofstream &,ifstream &,int *,char *,int *,int *,int *,
                           int *,int);

void imprimirEncabezado_RepUsuarios(ofstream &,char,int);

void imprimirNickname(ofstream &,ifstream &,int);

void ordenarArreglos_Logs(int *,int *,int *,int *,int *,int);

void emitirReporte_Logs(ofstream &,int *,int *,int *,int *,int *,int);

void imprimirEncabezado_RepLogs(ofstream &,char,int);

#endif /* FUNCIONES_H */
